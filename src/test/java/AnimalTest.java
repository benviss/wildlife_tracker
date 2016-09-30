import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.lang.*;

public class AnimalTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Animal_InstantiatesCorrectly_true() {
    Animal newAnimal = new Animal("Willie Nelson; a squirrel maybe?");
    assertTrue(newAnimal instanceof Animal);
  }

  @Test
  public void Save_saveReturnsIDCorrectly_true() {
    Animal newAnimal = new Animal("Led Zeppelin; an eagle probably");
    newAnimal.save();
    assertTrue(newAnimal.getId() > 0);
  }

  @Test
  public void getEndageredBoolean_EndangeredShouldInstantiateAsFalse_False() {
    Animal newAnimal = new Animal("Led Zeppelin; an eagle probably");
    newAnimal.save();
    assertFalse(newAnimal.getEndangeredBoolean());
  }

  @Test
  public void getAllAnimals_returnsAllAnimalsCorrectly_true() {
    Animal newAnimal = new Animal("White Stripes; definitely elephants");
    newAnimal.save();
    assertTrue(Animal.getAllAnimals().size() > 0);
  }

  @Test
  public void findById_FindsCorrectAnimal_true() {
    Animal newAnimal = new Animal("Deer, I give up");
    newAnimal.save();
    assertTrue(Animal.findById(newAnimal.getId()).equals(newAnimal));
  }

  @Test
  public void getAnimalIds_ReturnsAllUnEndangeredAnimals_true() {
    Animal newAnimal = new Animal("Deer, I give up");
    newAnimal.save();

    assertTrue(Animal.getAnimalByEndangeredBoolean(false).get(0).equals(newAnimal));
  }

  @Test
  public void addAnimalSighted_SavesAnimalSighting_true() {
    Animal newAnimal = new Animal("Deer, I give up");
    newAnimal.save();
    newAnimal.addAnimalSighted(1,2);
    assertTrue(Animal.getAnimalsFromAnimalIds(Animal.getAnimalSightingIds()).get(0).equals(newAnimal));
  }
}
