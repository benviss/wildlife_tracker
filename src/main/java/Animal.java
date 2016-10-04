
import java.util.Date;
import java.util.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;


public class Animal {

  public int id;
  public String species;
  public boolean endangered = false;

  public Animal(String _species) {
    this.species = _species;
  }

  public int getId() {
    return this.id;
  }

  public String getSpecies() {
    return this.species;
  }

  public boolean getEndangeredBoolean() {
    return this.endangered;
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery("INSERT INTO animals (species, endangered, seen) VALUES (:species, :endangered, now())", true)
      .addParameter("species", this.species)
      .addParameter("endangered", this.endangered)
      .executeUpdate()
      .getKey();
    }
  }

  public static List<Animal> getAllAnimals() {
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery("SELECT species, id FROM animals;")
      .throwOnMappingFailure(false)
      .executeAndFetch(Animal.class);
    }
  }

  public static Animal findById(int _id) {
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery("SELECT * FROM animals where id=:id")
      .throwOnMappingFailure(false)
      .addParameter("id", _id)
      .executeAndFetchFirst(Animal.class);
    }
  }

  public static boolean checkAnimalEndagered(int _id) {
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery("SELECT endangered FROM animals where id=:id")
      .addParameter("id", _id)
      .executeAndFetchFirst(Boolean.class);
    }
  }

  @Override
  public boolean equals(Object testObj) {
    if(!(testObj instanceof Animal)) {
      return false;
    } else {
      Animal animal = (Animal) testObj;
      return this.id == animal.getId() && this.species.equals(animal.getSpecies());
    }
  }

  public static void addAnimalSighted(int _animal_id, String _location, String _ranger) {
    try(Connection con = DB.sql2o.open()) {
      con.createQuery("INSERT INTO sightings (animal_id, location, ranger) VALUES (:animal_id, :location, :ranger)")
      .addParameter("animal_id", _animal_id)
      .addParameter("location", _location)
      .addParameter("ranger", _ranger)
      .executeUpdate();
    }
  }

  public static List<Animal> getAnimalByEndangeredBoolean(boolean _endangeredBool) {
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery("SELECT * FROM animals WHERE endangered=:endangered")
      .addParameter("endangered",_endangeredBool)
      .executeAndFetch(Animal.class);
    }
  }

  public static List<Integer> getAnimalSightingIds() {
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery("SELECT animal_id FROM sightings;")
      .executeAndFetch(Integer.class);
    }
  }

  public static List<Animal> getAnimalsFromAnimalIds(List<Integer> _animalIdList) {
    List<Animal> animals = new ArrayList<>();
    try(Connection con = DB.sql2o.open()) {
      for (int animalId : _animalIdList) {
        Animal animal = con.createQuery("SELECT * FROM animals WHERE id=:id AND endangered=:endangered")
        .throwOnMappingFailure(false)
        .addParameter("id", animalId)
        .addParameter("endangered", false)
        .executeAndFetchFirst(Animal.class);

        animals.add(animal);
      }
    }
    return animals;
  }

  public static void deleteAll() {
    try(Connection con = DB.sql2o.open()) {
      con.createQuery("DELETE FROM animals *")
      .executeUpdate();

      con.createQuery("DELETE FROM sightings *")
      .executeUpdate();
    }
  }



  public List<Integer> getSightingIds() {
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery("SELECT id FROM sightings WHERE animal_id=:animal_id")
      .addParameter("animal_id", this.id)
      .executeAndFetch(Integer.class);
    }
  }

  public String getLocation() {
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery("SELECT location FROM sightings WHERE animal_id=:animal_id")
      .addParameter("animal_id", this.id)
      .executeAndFetchFirst(String.class);
    }
  }

  public String getRanger() {
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery("SELECT ranger FROM sightings WHERE animal_id=:animal_id")
      .addParameter("animal_id", this.id)
      .executeAndFetchFirst(String.class);
    }
  }

  public Timestamp getTime() {
    try(Connection con = DB.sql2o.open()) {
      Timestamp timestamp = con.createQuery("SELECT seen FROM animals where id=:id")
      .addParameter("id",this.id)
      .executeAndFetchFirst(Timestamp.class);

      return timestamp;
    }
  }

  public static void updateSighting(int animal_id, String newRangerName, String newLocationDescription) {
    try(Connection con = DB.sql2o.open()) {
      con.createQuery("UPDATE sightings SET (ranger, location) = (:rangerName, :location) WHERE animal_id=:id")
      .addParameter("rangerName", newRangerName)
      .addParameter("location", newLocationDescription)
      .addParameter("id", animal_id)
      .executeUpdate();
    }
  }


}
