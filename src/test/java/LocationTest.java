import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.lang.*;

public class LocationTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Location_ConstructorInstantiatesCorretly_true() {
    Location newLocation = new Location("Creek");

    assertTrue(newLocation instanceof Location);
  }

  @Test
  public void Save_saveReturnsIDCorrectly_true() {
    Location newLocation = new Location("Creek");
    newLocation.save();
    assertTrue(newLocation.getId() > 0);
  }

  @Test
  public void getAllLocations_returnsAllLocationsCorrectly_true() {
    Location newLocation = new Location("Creek");
    newLocation.save();
    assertTrue(Location.getAllLocations().size() > 0);
  }

  @Test
  public void findById_FindsCorrectLocation_true() {
    Location newLocation = new Location("Creek");
    newLocation.save();
    assertTrue(Location.findById(newLocation.getId()).equals(newLocation));
  }




}
