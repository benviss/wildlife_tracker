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
}
