import java.util.*;
import org.sql2o.*;

public class Address {
  private String address;
  private int id;
  private int contact_id;

  public Address(String address, int contact_id) {
    this.address = address;
    this.contact_id = contact_id;
  }

  public String getAddress() {
    return address;
  }

  public int getId() {
    return id;
  }

  public int getContact_id() {
    return contact_id;
  }

  public static List<Address> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM addresses";

      return con.createQuery(sql).executeAndFetch(Address.class);
    }
  }

  @Override
  public boolean equals(Object otherAddress) {
    if(!(otherAddress instanceof Address)) {
      return false;
    } else {
      Address newAddress = (Address) otherAddress;
      return newAddress.getAddress().equals(address);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO addresses (address, contact_id) VALUES (:address, :contact_id)";
      this.id = (int) con.createQuery(sql, true).addParameter("address", address).addParameter("contact_id", contact_id).executeUpdate().getKey();
    }
  }

  public static Address find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM addresses WHERE id = :id";
      return con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Address.class);
    }
  }

  public void update(String address) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE addresses SET address = :address WHERE id = :id";
      con.createQuery(sql).addParameter("address", address).addParameter("id", id).executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM addresses WHERE id = :id";
      con.createQuery(sql).addParameter("id", id).executeUpdate();
    }
  }
}
