import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.lang.*;

public class RangerTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Ranger_ConstructorInstantiatesCorretly_true() {
    Ranger newRanger = new Ranger("Rick");

    assertTrue(newRanger instanceof Ranger);
  }

  @Test
  public void Save_saveReturnsIDCorrectly_true() {
    Ranger newRanger = new Ranger("Rick");
    newRanger.save();
    assertTrue(newRanger.getId() > 0);
  }

  @Test
  public void getAllRangers_returnsAllRangersCorrectly_true() {
    Ranger newRanger = new Ranger("Rick");
    newRanger.save();
    assertTrue(Ranger.getAllRangers().size() > 0);
  }

  @Test
  public void findById_FindsCorrectRanger_true() {
    Ranger newRanger = new Ranger("Rick");
    newRanger.save();
    assertTrue(Ranger.findById(newRanger.getId()).equals(newRanger));
  }




}
