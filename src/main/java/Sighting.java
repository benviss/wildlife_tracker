import java.util.Date;
import java.util.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Sighting {

  private int id;
  private String location;
  private String ranger;
  private int animal_id;
  private long seen;
  private String endangered;

  public Sighting(String location, String ranger, int animalId, String endangered) {
    this.location = location;
    this.ranger = ranger;
    this.animal_id = animalId;
    this.endangered = endangered;
    seen = new Date().getTime();
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery("INSERT INTO sightings (animal_id, ranger, location, seen, endangered) VALUES (:animal_id, :ranger, :location, :seen, :endangered)", true)
      .addParameter("animal_id", this.animal_id)
      .addParameter("ranger", this.ranger)
      .addParameter("location", this.location)
      .addParameter("seen",this.seen)
      .addParameter("endangered", this.endangered)
      .executeUpdate()
      .getKey();
    }
  }

  public int getAnimalId() {
    return animal_id;
  }
  public int getId() {
    return id;
  }
  public static List<Sighting> all() {
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery("SELECT * FROM sightings")
      .executeAndFetch(Sighting.class);
     }
  }

  public String getEndangered() {
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery("SELECT endangered FROM sightings where id=:id")
      .addParameter("id",this.id)
      .executeAndFetchFirst(String.class);
    }
  }

  public static Sighting findById(int _id) {
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery("SELECT * FROM sightings where id=:id")
      .throwOnMappingFailure(false)
      .addParameter("id", _id)
      .executeAndFetchFirst(Sighting.class);
    }
  }


  public String getLocation() {
    return location;
  }

  public String getRanger() {
    return ranger;
  }
  public Date getSeen() {
    Date date = new Date(seen);
    return date;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public void setRanger(String ranger) {
    this.ranger = ranger;
  }

  public void update() {
    try(Connection con = DB.sql2o.open()) {
      con.createQuery("UPDATE sightings SET (ranger, location) = (:rangerName, :location) WHERE id=:id")
      .addParameter("rangerName", this.ranger)
      .addParameter("location", this.location)
      .addParameter("id", this.id)
      .executeUpdate();
    }
  }

  @Override
  public boolean equals(Object testObj) {
    if(!(testObj instanceof Sighting)) {
      return false;
    } else {
      Sighting sighting = (Sighting) testObj;
      return this.id == sighting.getId() && this.ranger.equals(sighting.getRanger());
    }
  }

  public static void deleteAll() {
    try(Connection con = DB.sql2o.open()) {
      con.createQuery("DELETE FROM sightings *")
      .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      con.createQuery("DELETE FROM sightings WHERE id=:id")
      .addParameter("id",this.id)
      .executeUpdate();
    }
  }

}
