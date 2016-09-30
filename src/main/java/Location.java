import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;


public class Location {
  private int id;
  private String description;

  public Location(String description) {
    this.description = description;
  }


  public void save() {
    try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery("INSERT INTO locations (description) VALUES (:description)", true)
      .addParameter("description", this.description)
      .executeUpdate()
      .getKey();
    }
  }

  public String getDescription() {
    return this.description;
  }
  public int getId() {
    return this.id;
  }

  public static List<Location> getAllLocations() {
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery("SELECT * FROM locations;")
      .executeAndFetch(Location.class);
    }
  }

  public static Location findById(int _location_id) {
    try(Connection con = DB.sql2o.open()) {
    return con.createQuery("SELECT * FROM locations where id=:id")
      .addParameter("id", _location_id)
      .executeAndFetchFirst(Location.class);
    }
  }

  @Override
  public boolean equals(Object testObj) {
    if(!(testObj instanceof Location)) {
      return false;
    } else {
      Location location = (Location) testObj;
      return this.id == location.getId() && this.description.equals(location.getDescription());
    }
  }
}
