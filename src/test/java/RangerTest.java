// import org.junit.*;
// import static org.junit.Assert.*;
// import org.sql2o.*;
// import java.lang.*;
//
// public class RangerTest {
//   @Rule
//   public DatabaseRule database = new DatabaseRule();
//
//   @Test
//   public void Ranger_ConstructorInstantiatesCorretly_true() {
//     Ranger newRanger = new Ranger("Rick");
//
//     assertTrue(newRanger instanceof Ranger);
//   }
//
//   @Test
//   public void Save_saveReturnsIDCorrectly_true() {
//     Ranger newRanger = new Ranger("Rick");
//     newRanger.save();
//     assertTrue(newRanger.getId() > 0);
//   }
//
//   @Test
//   public void getAllRangers_returnsAllRangersCorrectly_true() {
//     Ranger newRanger = new Ranger("Rick");
//     newRanger.save();
//     assertTrue(Ranger.getAllRangers().size() > 0);
//   }
//
//   @Test
//   public void findById_FindsCorrectRanger_true() {
//     Ranger newRanger = new Ranger("Rick");
//     newRanger.save();
//     assertTrue(Ranger.findById(newRanger.getId()).equals(newRanger));
//   }
//
//   @Test
//   public void getRangerId_FindsCorrectRangerId_true() {
//     Animal newAnimal = new Animal("Deer, I give up");
//     newAnimal.save();
//     Ranger newRanger = new Ranger("Rick");
//     newRanger.save();
//     System.out.println(newRanger.getId());
//     Animal.addAnimalSighted(newAnimal.getId(),1,newRanger.getId());
//     int testRangerId = Ranger.getRangerId(newAnimal.getId());
//     System.out.println(testRangerId);
//     Ranger testRanger = Ranger.findById(testRangerId);
//     assertTrue(newRanger.equals(testRanger));
//   }
//
//
//
//
// }
