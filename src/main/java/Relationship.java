import java.util.*;
import org.sql2o.*;

public class Relationship {
  private String relationship;
  private int id;
  private int contact_id;

  public Relationship(String relationship, int contact_id) {
    this.relationship = relationship;
    this.contact_id = contact_id;
  }

  public String getRelation() {
    return relationship;
  }

  public int getId() {
    return id;
  }

  public int getContact_id() {
    return contact_id;
  }

  public static List<Relationship> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM relationships";

      return con.createQuery(sql).executeAndFetch(Relationship.class);
    }
  }

  @Override
  public boolean equals(Object otherRelationship) {
    if(!(otherRelationship instanceof Relationship)) {
      return false;
    } else {
      Relationship newRelationship = (Relationship) otherRelationship;
      return newRelationship.getRelation().equals(relationship);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO relationships (relationship, contact_id) VALUES (:relationship, :contact_id)";
      this.id = (int) con.createQuery(sql, true).addParameter("relationship", relationship).addParameter("contact_id", contact_id).executeUpdate().getKey();
    }
  }

  public static Relationship find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM relationships WHERE id = :id";
      return con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Relationship.class);
    }
  }

  public void update(String relationship) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE relationships SET relationship = :relationship WHERE id = :id";
      con.createQuery(sql).addParameter("relationship", relationship).addParameter("id", id).executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM relationships WHERE id = :id";
      con.createQuery(sql).addParameter("id", id).executeUpdate();
    }
  }

  public List<Contact> getContacts() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM contacts WHERE relationship_id = :id";
      return con.createQuery(sql).addParameter("id", id).executeAndFetch(Contact.class);
    }
  }
}
