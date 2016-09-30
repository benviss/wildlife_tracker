
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;


public class EndangeredAnimal extends Animal{

  private String health;
  private int age;

  public EndangeredAnimal(String _species, String _health, int _age) {
    super(_species);
    this.health = _health;
    this.age = _age;
    this.endangered = true;
  }

  public String getHealth() {
    return this.health;
  }

  public int getAge() {
    return this.age;
  }

  @Override
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery("INSERT INTO endangered_animals (species, health, age) VALUES (:species, :health, :age)", true)
      .addParameter("species", this.species)
      .addParameter("health", this.health)
      .addParameter("age", this.age)
      .executeUpdate()
      .getKey();
    }
  }

  public static List<EndangeredAnimal> getAllEndangeredAnimals() {
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery("SELECT * FROM endangered_animals;")
      .executeAndFetch(EndangeredAnimal.class);
    }
  }

  public static EndangeredAnimal findById(int _id) {
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery("SELECT * FROM endangered_animals where id=:id")
      .addParameter("id", _id)
      .executeAndFetchFirst(EndangeredAnimal.class);
    }
  }

  @Override
  public boolean equals(Object testObj) {
    if(!(testObj instanceof EndangeredAnimal)) {
      return false;
    } else {
      EndangeredAnimal animal = (EndangeredAnimal) testObj;
      return this.id == animal.getId() && this.species.equals(animal.getSpecies());
    }
  }



}
