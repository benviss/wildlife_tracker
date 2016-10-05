import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.lang.*;

public class SightingTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Sighting_InstantiatesCorrectly_true() {
    Sighting newSighting = new Sighting("Creek","Rick", 1, "Endangered");
    assertTrue(newSighting instanceof Sighting);
  }

  @Test
  public void getEndangered_ReturnsBoolean_true() {
    Sighting newSighting = new Sighting("Creek","Rick", 1, "Endangered");
    newSighting.save();
    assertTrue(newSighting.getEndangered().equals("Endangered"));
  }

  @Test
  public void updateSightings_updateSightingCorrectly_true() {
    Sighting newSighting = new Sighting("Creek","Rick", 1, "Endangered");
    newSighting.save();

    newSighting.setRanger("ralph");
    newSighting.update();
    assertTrue(Sighting.findById(newSighting.getId()).getRanger().equals("ralph"));
  }

  @Test
  public void Save_saveReturnsIDCorrectly_true() {
    Sighting newSighting = new Sighting("Creek","Rick", 1, "Endangered");
    newSighting.save();
    assertTrue(newSighting.getId() > 0);
  }

  @Test
  public void getAllSightings_returnsAllSightingsCorrectly_true() {
    Sighting newSighting = new Sighting("Creek","Rick", 1, "Endangered");
    newSighting.save();
    assertTrue(Sighting.all().size() > 0);
  }

  @Test
  public void findById_FindsCorrectSighting_true() {
    Sighting newSighting = new Sighting("Creek","Rick", 1, "Endangered");
    newSighting.save();
    assertTrue(Sighting.findById(newSighting.getId()).equals(newSighting));
  }

  @Test
  public void Delete_Correctlydeletessingle_true() {
    Sighting newSighting = new Sighting("Creek","Rick", 1, "Endangered");
    newSighting.save();
    Sighting newSighting2 = new Sighting("Creek","Rick", 1, "Endangered");
    newSighting2.save();
    int test = Sighting.all().size();
    newSighting.delete();
    int test2 = Sighting.all().size();
    assertTrue(test > test2);
  }


}
