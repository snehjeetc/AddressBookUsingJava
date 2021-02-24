package addressbook;
import detailsofperson.Person;
import java.util.*;

public class AddressBook {
	List<Person> contactList;
	AddressBook(){
		contactList = new ArrayList<Person>();
	}
	public void addContact(Person p) {
		contactList.add(p);
	}
	
}
