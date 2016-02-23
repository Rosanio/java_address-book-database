import java.util.*;
import org.sql2o.*;

public class Phone {
  private String number;
  private int id;
  private int contact_id;

  public Phone(String number, int contact_id) {
    this.number = number;
    this.contact_id = contact_id;
  }

  public String getNumber() {
    return number;
  }

  public int getId() {
    return id;
  }

  public int getContact_id() {
    return contact_id;
  }

  public static List<Phone> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM phones";

      return con.createQuery(sql).executeAndFetch(Phone.class);
    }
  }

  @Override
  public boolean equals(Object otherPhone) {
    if(!(otherPhone instanceof Phone)) {
      return false;
    } else {
      Phone newPhone = (Phone) otherPhone;
      return newPhone.getNumber().equals(number);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO phones (number, contact_id) VALUES (:number, :contact_id)";
      this.id = (int) con.createQuery(sql, true).addParameter("number", number).addParameter("contact_id", contact_id).executeUpdate().getKey();
    }
  }

  public static Phone find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM phones WHERE id = :id";
      return con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Phone.class);
    }
  }

  public void update(String number) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE phones SET number = :number WHERE id = :id";
      con.createQuery(sql).addParameter("number", number).addParameter("id", id).executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM phones WHERE id = :id";
      con.createQuery(sql).addParameter("id", id).executeUpdate();
    }
  }
}
