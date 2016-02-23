import java.util.*;
import org.sql2o.*;

public class Email {
  private String email;
  private int id;
  private int contact_id;

  public Email(String email, int contact_id) {
    this.email = email;
    this.contact_id = contact_id;
  }

  public String getEmail() {
    return email;
  }

  public int getId() {
    return id;
  }

  public int getContact_id() {
    return contact_id;
  }

  public static List<Email> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM emails";

      return con.createQuery(sql).executeAndFetch(Email.class);
    }
  }

  @Override
  public boolean equals(Object otherEmail) {
    if(!(otherEmail instanceof Email)) {
      return false;
    } else {
      Email newEmail = (Email) otherEmail;
      return newEmail.getEmail().equals(email);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO emails (email, contact_id) VALUES (:email, :contact_id)";
      this.id = (int) con.createQuery(sql, true).addParameter("email", email).addParameter("contact_id", contact_id).executeUpdate().getKey();
    }
  }

  public static Email find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM emails WHERE id = :id";
      return con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Email.class);
    }
  }

  public void update(String email) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE emails SET email = :email WHERE id = :id";
      con.createQuery(sql).addParameter("email", email).addParameter("id", id).executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM emails WHERE id = :id";
      con.createQuery(sql).addParameter("id", id).executeUpdate();
    }
  }
}
