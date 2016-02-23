import org.junit.*;
import static org.junit.Assert.*;

public class EmailTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Email.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfObjectsHaveSameName() {
    Email firstEmail = new Email("mmrosani@syr.edu",1);
    Email secondEmail = new Email("mmrosani@syr.edu",1);
    assertTrue(firstEmail.equals(secondEmail));
  }

  @Test
  public void save_savesEmailIntoDatabase() {
    Email newEmail = new Email("mmrosani@syr.edu",1);
    newEmail.save();
    assertTrue(Email.all().get(0).equals(newEmail));
  }

  @Test
  public void find_findEmailInDatabase_true() {
    Email myEmail = new Email("mmrosani@syr.edu",1);
    myEmail.save();
    Email savedEmail = Email.find(myEmail.getId());
    assertTrue(myEmail.equals(savedEmail));
  }
}
