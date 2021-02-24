package addressbook;
import detailsofperson.*;


public class AddressBookMain {

	public static void main(String[] args) {
		System.out.println("Welcome to Address Book Program");
		AddressBook myAddressBook = new AddressBook();
		fillBook(myAddressBook);
	}

	public static void fillBook(AddressBook book) {
		int NUMBER_OF_CONTACTS = 3;
		String[][] names = new String[][] {
			{"Abc", "Def"}, 
			{"Ghi", "Jkl"}, 
			{"Mno", "Pqr"}
			};
		
		String[] emails = new String[] {"csdfg.com", "ohiog.com", "wetfi.com"};
		long[] phoneNums = new long[] {9999999999L, 9999999876L, 9999999997L};
		String[][] addresses = new String[][] {
				{"mgRoad", "Delhi", "Delhi", "India"},
				{"skyroad", "Pune", "Maharashtra", "India"},
				{"qprcity", "Jaipur", "Rajasthan", "India"}
				};
		
		int[] buildingNumbers = new int[] {12, 13, 14};
		int[] zipNumbers = new int[] {120051, 100000, 100003};
		
		for(int i=0; i<NUMBER_OF_CONTACTS; i++) {
			Address a = new Address(buildingNumbers[i], addresses[i], zipNumbers[i]);
			Person p = new Person(names[i], phoneNums[i], emails[i], a);
			book.addContact(p);
		}
	}
	
	
}
