package addressbook;
import detailsofperson.Address;
import detailsofperson.Person;
import java.util.*;
import scannerwrapper.ScannerWrapped;

public class AddressBook {
	private String name;
	private Map<String, Person> contactTable_Name_to_Person;
	AddressBook(String name){
		this.name = name;
		contactTable_Name_to_Person = new HashMap<>();
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
		String fullName = name[0] + " " + name[1];
		if(contactTable_Name_to_Person.containsKey(fullName)) {
			System.out.println("Name already exists");
			System.out.println("Try modifying or remove contact");
			return null;
		}
		System.out.println("Enter the phone number: ");
		long phoneNum = ScannerWrapped.sc.nextLong();
		ScannerWrapped.sc.nextLine();
		while(this.search(phoneNum) != null) {
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
		contactTable_Name_to_Person.put(p.getName(), p);
	}
	
	public void modify(String[] name) {
		Person p = search(name);
		if(p == null) {
			System.out.println("Contact doesn't exist");
			return;
		}
		p.modify();
	}
	
	public void modify(long phoneNumber) {
		Person p = search(phoneNumber);
		if(p == null) {
			System.out.println("Contact doesn't exist");
			return;
		}
		p.modify();
	}
	
	public void remove(String[] name) {
		Person p = search(name);
		if(p == null) {
			System.out.println("Contact not found!");
			return;
		}
		contactTable_Name_to_Person.remove(p.getName());
		System.out.println("Contact removed successfully!");
	}
	
	public void remove(long phoneNumber) {
		Person p = search(phoneNumber);
		if(p == null) {
			System.out.println("Contact doesn't exist");
			return;
		}
		contactTable_Name_to_Person.remove(p.getName());
	}
	
	private Person search(String[] name) {
		String fullName = name[0] + " " + name[1];
		if(contactTable_Name_to_Person.containsKey(fullName)) {
			return contactTable_Name_to_Person.get(fullName);
		}
		return null;
	}
	
	private Person search(long phoneNumber) {
		Iterator<Map.Entry<String, Person>> itr = 
				contactTable_Name_to_Person.entrySet().iterator();
		while(itr.hasNext()) {
			Map.Entry<String, Person> entry = itr.next();
			if(entry.getValue().getPhoneNumber() == phoneNumber)
				return entry.getValue();
		}
		return null;
	}
	
	public void printBook() {
		int serial = 1;
		for(Map.Entry<String, Person> entry : 
			contactTable_Name_to_Person.entrySet()) {
			System.out.println(serial++ + ".)" + entry.getValue());
		}
	}
	
	public void print(String[] name) {
		Person p = search(name);
		if(p == null) {
			System.out.println("Contact doesn't exist");
			return;
		}
		System.out.println(p);
	}
	
	public void print(long phoneNumber) {
		Person p = search(phoneNumber);
		if(p == null) {
			System.out.println("Contact doesn't exist");
			return;
		}
		System.out.println(p);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
