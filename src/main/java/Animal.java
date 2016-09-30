
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;


public class Animal {

  private int id;
  private String species;

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

  public static List<Animal> getAllAnimals() {
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery("SELECT * FROM animals;")
      .executeAndFetch(Animal.class);
    }
  }

  public static Animal findById(int _id) {
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery("SELECT * FROM animals where id=:id")
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


}
