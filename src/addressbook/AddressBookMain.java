package addressbook;
import detailsofperson.*;


public class AddressBookMain {
	public static void main(String[] args) {
		System.out.println("Welcome to Address Book Program");
		AddressBook myAddressBook = new AddressBook();
		myAddressBook.fillBook();
		myAddressBook.printAtIndex(0);
		long phoneNumber = 9999999999L;
		String[] name = {"Ghi", "Jkl"};
		String[] name1 = {"Abc", "Def"};
		System.out.println();
		System.out.println("Printing the address book: ");
		myAddressBook.printBook();
		System.out.println("Modifying a contact name by searching it: "
				+ "(name):" + name1[0] + " " + name1[1]);
		myAddressBook.modify(name1);
		System.out.println();
		System.out.println("Printing a phone number by searching in "
				+ "the book: (phoneNumber) " + phoneNumber );
		myAddressBook.print(phoneNumber);
		System.out.println();
		System.out.println("Searching the book for a contact name and "
				+ "printing the details: (name)" + name[0]+ " " + name[1]);
		myAddressBook.print(name);
		System.out.println();
		System.out.println("Removing a contact by searching "
				+ "the name in the book: name" + name[0] + " " + name[1]);
		myAddressBook.remove(name);
		System.out.println();
		System.out.println("Printing the book again: ");
		myAddressBook.printBook();
	}
}
