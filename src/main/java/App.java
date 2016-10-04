import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      model.put("animals", Animal.getAllAnimals());
      // model.put("Ranger",Ranger.class);
      // model.put("Location",Location.class);
      model.put("endangeredAnimals", EndangeredAnimal.getAllEndangeredAnimals());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/animal/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("animal",Animal.findById(Integer.parseInt(request.params(":id"))));
      model.put("animals", Animal.getAllAnimals());
      model.put("template", "templates/animal.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/animal/:id/update", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String rangerName = request.queryParams("ranger");
      String locationName = request.queryParams("location");
      int speciesId = Integer.parseInt(request.params(":id"));
      String speciesName = request.queryParams("new-species-input");
      try{
        if(locationName.equals("") || rangerName.equals("")) {
          throw new UnsupportedOperationException("Empty Text Field");
        }
      } catch (java.lang.UnsupportedOperationException e) {
        String errorMessage = "All Required Fields must be entered to proceed";
        model.put("errorMessage", errorMessage);
        model.put("animals",Animal.getAllAnimals());
      }
      Animal.updateSighting(speciesId, rangerName, locationName);
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    get("/sightings/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/new-sighting.vtl");
      model.put("animals",Animal.getAllAnimals());
      // model.put("rangers", Ranger.getAllRangers());
      // model.put("locations",Location.getAllLocations());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/sightings/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      // int rangerId = 0;
      String rangerName = request.queryParams("ranger");
      // if (request.queryParams("ranger").equals("new-ranger") && !rangerName.equals("")) {
      //   Ranger newRanger = new Ranger(rangerName);
      //   newRanger.save();
      //   rangerId = newRanger.getId();
      // } else if (request.queryParams("ranger").equals("new-ranger") && rangerName.equals("")){
      //   // Get Ranger Name Flag
      // } else {
      //   rangerId = Integer.parseInt(request.queryParams("ranger"));
      // }
      //
      // int locationId = 0;
      String locationName = request.queryParams("location");
      // if (request.queryParams("location").equals("new-location") && !locationName.equals("")) {
      //   Location newLocation = new Location(locationName);
      //   newLocation.save();
      //   locationId = newLocation.getId();
      // } else if (request.queryParams("location").equals("new-location") && !locationName.equals("")) {
      //   // Get Location Name Flag
      // } else {
      //   locationId = Integer.parseInt(request.queryParams("location"));
      // }
      int speciesId = 0;
      String speciesName = request.queryParams("new-species-input");
      try{
        if(locationName.equals("") || rangerName.equals("")) {
          throw new UnsupportedOperationException("Empty Text Field");
        }
        if(request.queryParams("endangered").equals("Yes") && (request.queryParams("health").equals("") || request.queryParams("age").equals(""))) {
          throw new UnsupportedOperationException("Empty Text Field");
        }
        if(request.queryParams("species").equals("new-species") && request.queryParams("new-species-input").equals("")) {
          throw new UnsupportedOperationException("Empty Text Field");
        }
        if (request.queryParams("species").equals("new-species")) {
          Animal newAnimal = new Animal(speciesName);
          newAnimal.save();
          speciesId = newAnimal.getId();
        } else if (request.queryParams("endangered").equals("Yes")) {
          String health = request.queryParams("health");
          String age = request.queryParams("age");
          EndangeredAnimal newAnimal = new EndangeredAnimal(request.queryParams("species"),health,age);
          newAnimal.save();
          speciesId = newAnimal.getId();
        } else {
          Animal newAnimal = new Animal(request.queryParams("species"));
          newAnimal.save();
          speciesId = newAnimal.getId();
        }
      } catch (java.lang.UnsupportedOperationException e) {
        String errorMessage = "All Required Fields must be entered to proceed";
        model.put("errorMessage", errorMessage);
        model.put("animals",Animal.getAllAnimals());
      }
      Animal.addAnimalSighted(speciesId, locationName, rangerName);
      model.put("template","templates/new-sighting.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // get("/deleteRangers", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   Animal.deleteAll();
    //   response.redirect("/");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    // get("/deleteLocations", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   Ranger.deleteAll();
    //   response.redirect("/");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());

    get("/deleteAnimals", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Animal.deleteAll();
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


  }
}
