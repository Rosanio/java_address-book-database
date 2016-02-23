import java.util.*;
import org.sql2o.*;

public class Contact {
  private String name;
  private int id;

  public Contact(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public static List<Contact> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM contacts";
      return con.createQuery(sql).executeAndFetch(Contact.class);
    }
  }

  @Override
  public boolean equals(Object otherContact) {
    if(!(otherContact instanceof Contact)) {
      return false;
    } else {
      Contact newContact = (Contact) otherContact;
      return newContact.getName().equals(name);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO contacts (name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true).addParameter("name", name).executeUpdate().getKey();
    }
  }

  public static Contact find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM contacts WHERE id = :id";
      return con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Contact.class);
    }
  }

  public void update(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE contacts SET name = :name WHERE id = :id";
      con.createQuery(sql).addParameter("name", name).addParameter("id", id).executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM contacts WHERE id = :id";
      con.createQuery(sql).addParameter("id", id).executeUpdate();
    }
  }

  public List<Phone> getPhones() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM phones WHERE contact_id = :id";
      return con.createQuery(sql).addParameter("id", id).executeAndFetch(Phone.class);
    }
  }

  public List<Email> getEmails() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM emails WHERE contact_id = :id";
      return con.createQuery(sql).addParameter("id", id).executeAndFetch(Email.class);
    }
  }

  public List<Address> getAddresses() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM addresses WHERE contact_id = :id";
      return con.createQuery(sql).addParameter("id", id).executeAndFetch(Address.class);
    }
  }

  public List<Relationship> getRelationships() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM contacts_relationships WHERE contact_id = :id";
      return con.createQuery(sql).addParameter("id", id).executeAndFetch(Relationship.class);
    }
  }
}
