
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;


public class Animal {

private int id;
private String species;

public Animal(String _species) {
  this.species = _species;
}

public int getId() {
  return this.id;
}
public void save() {
  try(Connection con = DB.sql2o.open()) {
    this.id = (int) con.createQuery("INSERT INTO animals (species) VALUES (:species)", true)
    .addParameter("species", this.species)
    .executeUpdate()
    .getKey();
  }
}



}
