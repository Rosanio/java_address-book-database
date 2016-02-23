import org.junit.*;
import static org.junit.Assert.*;

public class AddressTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Address.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfObjectsHaveSameName() {
    Address firstAddress = new Address("mmrosani@syr.edu",1);
    Address secondAddress = new Address("mmrosani@syr.edu",1);
    assertTrue(firstAddress.equals(secondAddress));
  }

  @Test
  public void save_savesAddressIntoDatabase() {
    Address newAddress = new Address("mmrosani@syr.edu",1);
    newAddress.save();
    assertTrue(Address.all().get(0).equals(newAddress));
  }

  @Test
  public void find_findAddressInDatabase_true() {
    Address myAddress = new Address("mmrosani@syr.edu",1);
    myAddress.save();
    Address savedAddress = Address.find(myAddress.getId());
    assertTrue(myAddress.equals(savedAddress));
  }
}
