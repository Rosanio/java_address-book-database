import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/address_book_test", null, null);
   }

  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteContacts = "DELETE FROM contacts *;";
      String deletePhones = "DELETE FROM phones *;";
      String deleteEmails = "DELETE FROM emails *;";
      String deleteAddresses = "DELETE FROM addresses *;";
      String deleteRelationships = "DELETE FROM relationships *";
      String deleteContactsRelationships = "DELETE FROM contacts_relationships *";
      con.createQuery(deleteContacts).executeUpdate();
      con.createQuery(deletePhones).executeUpdate();
      con.createQuery(deleteEmails).executeUpdate();
      con.createQuery(deleteAddresses).executeUpdate();
      con.createQuery(deleteRelationships).executeUpdate();
      con.createQuery(deleteContactsRelationships).executeUpdate();
    }
  }
}
