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
      model.put("Animal",Animal.class);
      model.put("EndangeredAnimal",EndangeredAnimal.class);
      model.put("template", "templates/index.vtl");
      model.put("sightings",Sighting.all());
      model.put("Sighting",Sighting.class);
      // model.put("Ranger",Ranger.class);
      // model.put("Location",Location.class);
      model.put("endangeredAnimals", EndangeredAnimal.allEndangered());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/sighting/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("sighting",Sighting.findById(Integer.parseInt(request.params(":id"))));
      model.put("template", "templates/sighting.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/delete/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Sighting newSighting = Sighting.findById(Integer.parseInt(request.params(":id")));
      newSighting.delete();
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/sighting/:id/update", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String rangerName = request.queryParams("ranger");
      String locationName = request.queryParams("location");
      try{
        if(locationName.equals("") || rangerName.equals("")) {
          throw new UnsupportedOperationException("Empty Text Field");
        }
      } catch (java.lang.UnsupportedOperationException e) {
        String errorMessage = "All Required Fields must be entered to proceed";
        model.put("errorMessage", errorMessage);
        model.put("animals",Animal.all());
      }
      Sighting updateSighting = Sighting.findById(Integer.parseInt(request.params(":id")));
      updateSighting.setRanger(rangerName);
      updateSighting.setLocation(locationName);
      updateSighting.update();
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    get("/sightings/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/new-sighting.vtl");
      model.put("animals",Animal.all());
      // model.put("rangers", Ranger.getAllRangers());
      // model.put("locations",Location.getAllLocations());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/sightings/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String rangerName = request.queryParams("ranger");
      String locationName = request.queryParams("location");
      int speciesId = 0;
      String speciesName = request.queryParams("new-species-input");
      try{
        if(locationName.equals("") || rangerName.equals("")) {
          System.out.println(1);
          throw new UnsupportedOperationException("Empty Text Field");
        }
        if(request.queryParams("endangered").equals("Yes") && (request.queryParams("health").equals("") || request.queryParams("age").equals(""))) {
          System.out.println(3);
          throw new UnsupportedOperationException("Empty Text Field");
        }
        if(request.queryParams("species").equals("new-species") && request.queryParams("new-species-input").equals("")) {
          System.out.println(4);
          throw new UnsupportedOperationException("Empty Text Field");
        } if (request.queryParams("endangered").equals("Yes") && (request.queryParams("species").equals("new-species"))) {
          String health = request.queryParams("health");
          String age = request.queryParams("age");
          EndangeredAnimal newAnimal = new EndangeredAnimal(request.queryParams("new-species-input"),health,age);
          newAnimal.save();
          speciesId = newAnimal.getId();
        } else if (request.queryParams("species").equals("new-species")) {
          Animal newAnimal = new Animal(speciesName);
          newAnimal.save();
          speciesId = newAnimal.getId();
        } else {
          Animal newAnimal = new Animal(request.queryParams("species"));
          newAnimal.save();
          speciesId = newAnimal.getId();
        }
        System.out.println(request.queryParams("endangered").equals("Yes"));
      } catch (java.lang.UnsupportedOperationException e) {
        String errorMessage = "All Required Fields must be entered to proceed";
        model.put("errorMessage", errorMessage);
        model.put("animals",Animal.all());
      }
      if(request.queryParams("endangered").equals("Yes")) {
        Sighting newSighting = new Sighting(locationName, rangerName, speciesId, "Endangered");
        newSighting.save();
      } else {
        Sighting newSighting = new Sighting(locationName, rangerName, speciesId, "Not Endangered");
        newSighting.save();
      }

      model.put("template","templates/new-sighting.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/deleteAnimals", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Animal.deleteAll();
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


  }
}
