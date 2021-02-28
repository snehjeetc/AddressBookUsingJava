package addressbook;
import detailsofperson.Address;
import detailsofperson.Person;
import java.util.*;
import scannerwrapper.ScannerWrapped;

public class AddressBook {
	private String name;
	private List<Person> contactList;
	AddressBook(String name){
		this.name = name;
		contactList = new ArrayList<Person>();
	}
	
	public void fillBook() {
		char ch = 'Y';
		while(ch == 'Y') {
			Person p = getPerson();
			if(p != null)
				this.addContact(p);
			System.out.println("Do you want to add another contact?");
			ch = ScannerWrapped.sc.nextLine().toUpperCase().charAt(0);
		}
	}
	
	private Person getPerson() {
		String[] name = new String[2];
		System.out.println("Enter the First Name: ");
		name[0] = ScannerWrapped.sc.nextLine();
		System.out.println("Enter the Last Name: ");
		name[1] = ScannerWrapped.sc.nextLine();
		System.out.println("Enter the phone number: ");
		long phoneNum = ScannerWrapped.sc.nextLong();
		ScannerWrapped.sc.nextLine();
		while(this.search(phoneNum) != -1) {
			System.out.println("Phone number already exists");
			System.out.println("Do you have a different number?(y/n)");
			char ch = ScannerWrapped.sc.nextLine().toUpperCase().charAt(0);
			if(ch == 'Y') {
				phoneNum = ScannerWrapped.sc.nextLong();
				ScannerWrapped.sc.nextLine();
			}
			else {
				System.out.println("Try to modify/remove the existing number");
				return null;
			}
		}
		System.out.println("Enter the email: ");
		String email = ScannerWrapped.sc.nextLine();
		System.out.println("Enter the address:");
		Address add = getAddress();
		Person p = new Person(name, phoneNum, email, add);
		return p;
	}
	
	private Address getAddress() {
		String[] args = new String[4];
		System.out.println("Enter the building number: ");
		int buildingNumber = ScannerWrapped.sc.nextInt();
		ScannerWrapped.sc.nextLine();
		System.out.println("Enter street, city, state, country: ");
		for(int i=0; i<4; i++) 
			args[i] = ScannerWrapped.sc.nextLine();
		System.out.println("Enter the zip code: ");
		int zip = ScannerWrapped.sc.nextInt();
		ScannerWrapped.sc.nextLine();
		Address a = new Address(buildingNumber, args, zip);
		return a;
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
	
	public void modify(long phoneNumber) {
		int atIndex = search(phoneNumber);
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
	
	public void remove(long phoneNumber) {
		int atIndex = search(phoneNumber);
		if(atIndex == -1) {
			System.out.println("Contact not found!");
			return;
		}
		contactList.remove(atIndex);
		System.out.println("Contact removed successfully!");
	}
	
	private int search(String[] name) {
		
		for(int i=0; i<contactList.size(); i++) {
			Person p = contactList.get(i);
			System.out.println(p.getName());
			if(contactList.get(i).checkName(name)) {
				
				System.out.println("Is it the contact?");
				System.out.println(contactList.get(i));
				System.out.println("y/n?");
				
				char ch = ScannerWrapped.sc.nextLine().charAt(0);
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
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
