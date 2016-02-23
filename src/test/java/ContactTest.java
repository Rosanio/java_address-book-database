import org.junit.*;
import static org.junit.Assert.*;

import java.util.*;

public class ContactTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Contact.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfObjectsHaveSameName() {
    Contact firstContact = new Contact("Matt");
    Contact secondContact = new Contact("Matt");
    assertTrue(firstContact.equals(secondContact));
  }

  @Test
  public void save_savesContactIntoDatabase() {
    Contact newContact = new Contact("Matt");
    newContact.save();
    assertTrue(Contact.all().get(0).equals(newContact));
  }

  @Test
  public void find_findContactInDatabase_true() {
    Contact myContact = new Contact("Matt");
    myContact.save();
    Contact savedContact = Contact.find(myContact.getId());
    assertTrue(myContact.equals(savedContact));
  }

  @Test
  public void getPhones_retrievesAllPhoneNumbersFromDatabase_phonesList() {
    Contact myContact = new Contact("Matt");
    myContact.save();
    Phone firstNumber = new Phone("555-555-5555", myContact.getId());
    firstNumber.save();
    Phone secondNumber = new Phone("555-555-5556", myContact.getId());
    secondNumber.save();
    Phone[] numbers = new Phone[] {firstNumber, secondNumber};
    assertTrue(myContact.getPhones().containsAll(Arrays.asList(numbers)));
  }

  @Test
  public void getEmails_retrievesAllEmailsFromDatabase_emailsList() {
    Contact myContact = new Contact("Matt");
    myContact.save();
    Email firstEmail = new Email("mmrosani@syr.edu", myContact.getId());
    firstEmail.save();
    Email secondEmail = new Email("mattro2705@gmail.com", myContact.getId());
    secondEmail.save();
    Email[] emails = new Email[] {firstEmail, secondEmail};
    assertTrue(myContact.getEmails().containsAll(Arrays.asList(emails)));
  }

  @Test
  public void getAddresses_retrievesAllAddressesFromDatabase_addressesList() {
    Contact myContact = new Contact("Matt");
    myContact.save();
    Address firstAddress = new Address("555-555-5555", myContact.getId());
    firstAddress.save();
    Address secondAddress = new Address("555-555-5556", myContact.getId());
    secondAddress.save();
    Address[] addresses = new Address[] {firstAddress, secondAddress};
    assertTrue(myContact.getAddresses().containsAll(Arrays.asList(addresses)));
  }

}
