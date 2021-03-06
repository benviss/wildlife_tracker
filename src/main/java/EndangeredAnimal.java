
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;


public class EndangeredAnimal extends Animal{

  private String health;
  private String age;
  private static final boolean endangered = true;

  public EndangeredAnimal(String _species, String _health, String _age) {
    super(_species);
    this.health = _health;
    this.age = _age;
  }

  public String getHealth() {
    return this.health;
  }

  public String getAge() {
    return this.age;
  }

  public boolean getEndangered() {
    return endangered;
  }
  @Override
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery("INSERT INTO animals (species, health, age, endangered) VALUES (:species, :health, :age, :endangered)", true)
      .addParameter("species", this.species)
      .addParameter("health", this.health)
      .addParameter("age", this.age)
      .addParameter("endangered", this.endangered)
      .executeUpdate()
      .getKey();
    }
  }

  public static List<EndangeredAnimal> allEndangered() {
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery("SELECT * FROM animals WHERE endangered=:boolean;")
      .throwOnMappingFailure(false)
      .addParameter("boolean", true)
      .executeAndFetch(EndangeredAnimal.class);
    }
  }

  public static EndangeredAnimal findById(int _id) {
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery("SELECT * FROM animals where id=:id")
      .throwOnMappingFailure(false)
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

  public static List<EndangeredAnimal> getEndangeredAnimalsFromAnimalIds(List<Integer> _animalIdList) {
    List<EndangeredAnimal> animals = new ArrayList<>();
    try(Connection con = DB.sql2o.open()) {
      for (int animalId : _animalIdList) {
        EndangeredAnimal animal = con.createQuery("SELECT * FROM animals WHERE id=:id AND endangered=:endangered")
        .throwOnMappingFailure(false)
        .addParameter("id", animalId)
        .addParameter("endangered", true)
        .executeAndFetchFirst(EndangeredAnimal.class);

        animals.add(animal);
      }
    }
    return animals;
  }



}
