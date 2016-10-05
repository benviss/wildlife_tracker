
import java.util.Date;
import java.util.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;


public class Animal {

  public int id;
  public String species;


  public Animal(String _species) {
    this.species = _species;
  }

  public int getId() {
    return this.id;
  }

  public String getSpecies() {
    return this.species;
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery("INSERT INTO animals (species) VALUES (:species)", true)
      .addParameter("species", this.species)
      .executeUpdate()
      .getKey();
    }
  }

  public static List<Animal> all() {
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery("SELECT species, id FROM animals WHERE endangered IS NULL;")
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

  @Override
  public boolean equals(Object testObj) {
    if(!(testObj instanceof Animal)) {
      return false;
    } else {
      Animal animal = (Animal) testObj;
      return this.id == animal.getId() && this.species.equals(animal.getSpecies());
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
    }
  }



  public List<Integer> getSightingIds() {
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery("SELECT id FROM sightings WHERE animal_id=:animal_id")
      .addParameter("animal_id", this.id)
      .executeAndFetch(Integer.class);
    }
  }
}
