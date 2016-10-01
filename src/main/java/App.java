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
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/sightings/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/new-sighting.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/sightings/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String ranger = request.queryParams("ranger");
      String rangerName = request.queryParams("new-ranger-input");
      if (ranger.equals("new-ranger") && !rangerName.equals("")) {
        Ranger newRanger = new Ranger(rangerName);
        System.out.println("woo");
        newRanger.save();
      }

      String species = request.queryParams("species");
      String speciesName = request.queryParams("new-species-input");
      if (species.equals("new-species") && !speciesName.equals("")) {
        Animal newAnimal = new Animal(speciesName);
        System.out.println("woo");
        newAnimal.save();
      }

      String location = request.queryParams("location");
      String locationName = request.queryParams("new-location-input");
      if (location.equals("new-location") && !locationName.equals("")) {
        Location newLocation = new Location(locationName);
        System.out.println("woo");
        newLocation.save();
      }

      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());



  }
}
