import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.lang.*;

public class EndangeredAnimalTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void EndangeredAnimal_InstantiatesCorrectly_true() {
    EndangeredAnimal newEndangeredAnimal = new EndangeredAnimal("Willie Nelson; a squirrel maybe?", "Sick", "Young?");
    assertTrue(newEndangeredAnimal instanceof EndangeredAnimal);
  }

  @Test
  public void getEndagered_EndangeredShouldInstantiateAsTrue_True() {
    EndangeredAnimal newEndangeredAnimal = new EndangeredAnimal("Led Zeppelin; an eagle probably", "Sick", "Young?");
    newEndangeredAnimal.save();
    System.out.println(newEndangeredAnimal.getEndangered());
    assertTrue(newEndangeredAnimal.getEndangered());
  }

  @Test
  public void Save_saveReturnsIDCorrectly_true() {
    EndangeredAnimal newEndangeredAnimal = new EndangeredAnimal("Led Zeppelin; an eagle probably", "Sick", "Young?");
    newEndangeredAnimal.save();
    assertTrue(newEndangeredAnimal.getId() > 0);
  }

  @Test
  public void getAllEndangeredAnimals_returnsAllEndangeredAnimalsCorrectly_true() {
    EndangeredAnimal newEndangeredAnimal = new EndangeredAnimal("White Stripes; definitely elephants", "Sick", "Young?");
    newEndangeredAnimal.save();
    assertTrue(EndangeredAnimal.allEndangered().size() > 0);
  }

  @Test
  public void findById_FindsCorrectEndangeredAnimal_true() {
    EndangeredAnimal newEndangeredAnimal = new EndangeredAnimal("Deer, I give up", "Sick", "Young?");
    newEndangeredAnimal.save();
    assertTrue(EndangeredAnimal.findById(newEndangeredAnimal.getId()).equals(newEndangeredAnimal));
  }

  @Test
  public void findById_FindsCorrectEndangdAnimal_true() {
    EndangeredAnimal newEndangeredAnimal = new EndangeredAnimal("Deer, I give up", "Sick", "Young?");
    newEndangeredAnimal.save();
    assertTrue(EndangeredAnimal.findById(newEndangeredAnimal.getId()).getHealth().equals("Sick"));
  }
}
