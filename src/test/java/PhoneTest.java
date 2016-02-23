import org.junit.*;
import static org.junit.Assert.*;

public class PhoneTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Phone.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfObjectsHaveSameName() {
    Phone firstPhone = new Phone("555-555-5555",1);
    Phone secondPhone = new Phone("555-555-5555",1);
    assertTrue(firstPhone.equals(secondPhone));
  }

  @Test
  public void save_savesPhoneIntoDatabase() {
    Phone newPhone = new Phone("555-555-5555",1);
    newPhone.save();
    assertTrue(Phone.all().get(0).equals(newPhone));
  }

  @Test
  public void find_findPhoneInDatabase_true() {
    Phone myPhone = new Phone("555-555-5555",1);
    myPhone.save();
    Phone savedPhone = Phone.find(myPhone.getId());
    assertTrue(myPhone.equals(savedPhone));
  }
}
