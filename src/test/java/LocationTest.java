// import org.junit.*;
// import static org.junit.Assert.*;
// import org.sql2o.*;
// import java.lang.*;
//
// public class LocationTest {
//   @Rule
//   public DatabaseRule database = new DatabaseRule();
//
//   @Test
//   public void Location_ConstructorInstantiatesCorretly_true() {
//     Location newLocation = new Location("Creek");
//
//     assertTrue(newLocation instanceof Location);
//   }
//
//   @Test
//   public void Save_saveReturnsIDCorrectly_true() {
//     Location newLocation = new Location("Creek");
//     newLocation.save();
//     assertTrue(newLocation.getId() > 0);
//   }
//
//   @Test
//   public void getAllLocations_returnsAllLocationsCorrectly_true() {
//     Location newLocation = new Location("Creek");
//     newLocation.save();
//     assertTrue(Location.getAllLocations().size() > 0);
//   }
//
//   @Test
//   public void findById_FindsCorrectLocation_true() {
//     Location newLocation = new Location("Creek");
//     newLocation.save();
//     assertTrue(Location.findById(newLocation.getId()).equals(newLocation));
//   }
//
//   @Test
//   public void getLocationId_FindsCorrectLocationId_true() {
//     Animal newAnimal = new Animal("Deer, I give up");
//     newAnimal.save();
//     Location newLocation = new Location("Creek");
//     newLocation.save();
//     Animal.addAnimalSighted(newAnimal.getId(),newLocation.getId(),2);
//     int testLocationId = Location.getLocationId(newAnimal.getSightingIds().get(0));
//     Location testLocation = Location.findById(testLocationId);
//     assertTrue(newLocation.equals(testLocation));
//   }
//
//
//
// }
