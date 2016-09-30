
import java.util.Date;
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
      this.id = (int) con.createQuery("INSERT INTO animals (species, endangered) VALUES (:species, :endangered)", true)
      .addParameter("species", this.species)
      .addParameter("endangered", this.endangered)
      .executeUpdate()
      .getKey();
    }
  }

  public static List<Animal> getAllAnimals() {
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery("SELECT * FROM animals;")
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

  public void addAnimalSighted(int _location_id, int _ranger_id) {
    try(Connection con = DB.sql2o.open()) {
      con.createQuery("INSERT INTO animal_sightings (animal_id, location_id, ranger_id) VALUES (:animal_id, :location_id, :ranger_id)")
      .addParameter("animal_id", this.id)
      .addParameter("location_id", _location_id)
      .addParameter("ranger_id", _ranger_id)
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
      return con.createQuery("SELECT animal_id FROM animal_sightings;")
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

}
