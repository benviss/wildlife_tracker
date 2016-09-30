import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.lang.*;

public class EndangeredAnimalTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void EndangeredAnimal_InstantiatesCorrectly_true() {
    EndangeredAnimal newEndangeredAnimal = new EndangeredAnimal("Willie Nelson; a squirrel maybe?", "Sick", 99);
    assertTrue(newEndangeredAnimal instanceof EndangeredAnimal);
  }

  @Test
  public void getEndageredBoolean_EndangeredShouldInstantiateAsTrue_True() {
    EndangeredAnimal newEndangeredAnimal = new EndangeredAnimal("Led Zeppelin; an eagle probably", "Sick", 99);
    newEndangeredAnimal.save();
    System.out.println(newEndangeredAnimal.getEndangeredBoolean());
    assertTrue(newEndangeredAnimal.getEndangeredBoolean());
  }

  @Test
  public void Save_saveReturnsIDCorrectly_true() {
    EndangeredAnimal newEndangeredAnimal = new EndangeredAnimal("Led Zeppelin; an eagle probably", "Sick", 99);
    newEndangeredAnimal.save();
    assertTrue(newEndangeredAnimal.getId() > 0);
  }

  @Test
  public void getAllEndangeredAnimals_returnsAllEndangeredAnimalsCorrectly_true() {
    EndangeredAnimal newEndangeredAnimal = new EndangeredAnimal("White Stripes; definitely elephants", "Sick", 99);
    newEndangeredAnimal.save();
    assertTrue(EndangeredAnimal.getAllEndangeredAnimals().size() > 0);
  }

  @Test
  public void findById_FindsCorrectEndangeredAnimal_true() {
    EndangeredAnimal newEndangeredAnimal = new EndangeredAnimal("Deer, I give up", "Sick", 99);
    newEndangeredAnimal.save();
    assertTrue(EndangeredAnimal.findById(newEndangeredAnimal.getId()).equals(newEndangeredAnimal));
  }

  @Test
  public void addAnimalSighted_SavesCorrectInfoAndBooleanReturnsTrue_true() {
    EndangeredAnimal newEndangeredAnimal = new EndangeredAnimal("Deer, I give up", "Sick", 99);
    newEndangeredAnimal.save();
    newEndangeredAnimal.addAnimalSighted(1,2);
    assertTrue(EndangeredAnimal.findById(EndangeredAnimal.getAnimalIds(true).get(0)).equals(newEndangeredAnimal));
  }
}
