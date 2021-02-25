package addressbook;
import detailsofperson.Address;
import detailsofperson.Person;
import java.util.*;

public class AddressBook {
	Scanner sc = new Scanner(System.in);
	private List<Person> contactList;
	AddressBook(){
		contactList = new ArrayList<Person>();
	}
	public void fillBook() {
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
			this.contactList.add(p);
		}
	}
	public void addContact(Person p) {
		contactList.add(p);
	}
	
	public void modify(String[] name ) {
		int atIndex = search(name);
		if(atIndex == -1) {
			System.out.println("Contact not found!");
			return; 
		}
		contactList.get(atIndex).modify();
	}
	public void remove(String[] name) {
		int atIndex = search(name);
		if(atIndex == -1) {
			System.out.println("Contact not found!");
			return;
		}
		contactList.remove(atIndex);
		System.out.println("Contact removed successfully!");
	}
	private int search(String[] name) {
		
		for(int i=0; i<contactList.size(); i++) {
			if(contactList.get(i).checkName(name)) {
				
				System.out.println("Is it the contact?");
				System.out.println(contactList.get(i));
				System.out.println("y/n?");
				
				char ch = sc.nextLine().charAt(0);
				System.out.println(ch);
				if(ch == 'y' || ch == 'Y') {
					return i;
				}
			}
		}
	
		return -1;
	}
	private int search(long phoneNumber) {
		for(int i=0; i<contactList.size(); i++) {
			if(contactList.get(i).getPhoneNumber() == phoneNumber) {
					return i;
			}
		}
		return -1;
	}
	public void printBook() {
		for(int i=0; i<contactList.size(); i++) {
			System.out.println(contactList.get(i));
			System.out.println();
		}
	}
	public void print(String[] name) {
		int atIndex = search(name);
		if(atIndex == -1) {
			System.out.println("Contact not found!");
			return;
		}
		System.out.println(contactList.get(atIndex));
	}
	public void print(long phoneNumber) {
		int atIndex = search(phoneNumber);
		if(atIndex == -1) {
			System.out.println("Contact not found");
			return;
		}
		System.out.println(contactList.get(atIndex));
	}
	public void printAtIndex(int i) {
		if(i >= contactList.size() || i<0)
			return;
		System.out.println(contactList.get(i));	
	}
}
