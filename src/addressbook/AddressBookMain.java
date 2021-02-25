package addressbook;
import detailsofperson.*;


public class AddressBookMain {

	public static void main(String[] args) {
		System.out.println("Welcome to Address Book Program");
		AddressBook myAddressBook = new AddressBook();
		myAddressBook.fillBook();
		myAddressBook.printAtIndex(0);
	}
	
}
