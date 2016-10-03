// import java.util.Date;
// import java.util.List;
// import java.util.ArrayList;
// import org.sql2o.*;
//
//
// public class Ranger {
//   private int id;
//   private String name;
//
//   public Ranger(String name) {
//     this.name = name;
//   }
//
//
//   public void save() {
//     try(Connection con = DB.sql2o.open()) {
//       this.id = (int) con.createQuery("INSERT INTO rangers (name) VALUES (:name)", true)
//       .addParameter("name", this.name)
//       .executeUpdate()
//       .getKey();
//     }
//   }
//
//   public String getName() {
//     return this.name;
//   }
//   public int getId() {
//     return this.id;
//   }
//
//   public static List<Ranger> getAllRangers() {
//     try(Connection con = DB.sql2o.open()) {
//       return con.createQuery("SELECT * FROM rangers;")
//       .executeAndFetch(Ranger.class);
//     }
//   }
//
//   public static Ranger findById(int _ranger_id) {
//     try(Connection con = DB.sql2o.open()) {
//     return con.createQuery("SELECT * FROM rangers where id=:id")
//       .addParameter("id", _ranger_id)
//       .executeAndFetchFirst(Ranger.class);
//     }
//   }
//
//   @Override
//   public boolean equals(Object testObj) {
//     if(!(testObj instanceof Ranger)) {
//       return false;
//     } else {
//       Ranger ranger = (Ranger) testObj;
//       return this.id == ranger.getId() && this.name.equals(ranger.getName());
//     }
//   }
//
//   public static void deleteAll() {
//     try(Connection con = DB.sql2o.open()) {
//       con.createQuery("DELETE FROM rangers *")
//       .executeUpdate();
//     }
//   }
//
//   public static Integer getRangerId(int _animalId) {
//     try(Connection con = DB.sql2o.open()) {
//       return con.createQuery("SELECT ranger_id FROM animal_sightings WHERE animal_id=:_animalId")
//       .addParameter("_animalId", _animalId)
//       .executeAndFetchFirst(Integer.class);
//     }
//   }
// }
