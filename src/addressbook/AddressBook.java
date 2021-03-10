package addressbook;
import detailsofperson.Address;
import detailsofperson.Person;
import java.util.*;
import scannerwrapper.ScannerWrapped;

public class AddressBook {
	private String name;
	enum ListType{
		CITY,
		STATE;
	}
	private Map<String, Person> contactTable_Name_to_Person;
	private ArrayList<Map<String, LinkedList<Person>>> table;
	AddressBook(String name){
		this.name = name;
		contactTable_Name_to_Person = new HashMap<>();
		table = new ArrayList<Map<String, LinkedList<Person>>>(2);
		table.add(new HashMap<>());
		table.add(new HashMap<>());
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
		updateList(p, p.getAddress().getCity(), p.getAddress().getState());
	}
	
	private void updateList(Person p, String city, String state) {
		Map<String, LinkedList<Person>> cityList = table.get(ListType.CITY.ordinal());
		Map<String, LinkedList<Person>> stateList = table.get(ListType.STATE.ordinal());
		String personCity = p.getAddress().getCity();
		String personState = p.getAddress().getState();
		LinkedList<Person> personList;
		
		if(cityList.containsKey(personCity)){
			if(!cityList.get(personCity).contains(p))
				cityList.get(personCity).add(p);
		}
		else {
			personList = new LinkedList<>();
			personList.add(p);
			cityList.put(personCity, personList);
		}
		if(stateList.containsKey(personState)) {
			if(!stateList.get(personState).contains(p))
				stateList.get(personState).add(p);
		}
		else {
			personList = new LinkedList<>();
			personList.add(p);
			stateList.put(personState, personList);
		}
		
		if(!personCity.equals(city)) {
			cityList.get(city).remove(p);
			clearEmptyList(city, "");
		}
		if(!personState.equals(state)) {
			stateList.get(state).remove(p);
			clearEmptyList("", state);
		}
		
	}
	
	private void clearEmptyList(String city, String state) {
		Map<String, LinkedList<Person>> cityList = table.get(ListType.CITY.ordinal());
		Map<String, LinkedList<Person>> stateList = table.get(ListType.STATE.ordinal());
		if(city.length()!=0) {
			if(cityList.get(city).isEmpty())
				cityList.remove(city);
		}
		if(state.length()!=0) {
			if(stateList.get(state).isEmpty())
				stateList.remove(state);
		}
	}
	
	private void modify(Person p) {
		String email = null;
		long phoneNumber = 0;
		String[] name = new String[2];
		name[0] = null;
		name[1] = null;
		System.out.println("Change First Name (y/n)? ");
		char ch = ScannerWrapped.sc.nextLine().charAt(0);
		if(ch == 'Y' || ch == 'y') {
			System.out.println("Enter the first name: ");
			name[0] = ScannerWrapped.sc.nextLine();
		}
		if(name[0] == null)
			name[0] = p.getFirstName();
		
		System.out.println("Change the last Name (y/n)?");
		ch = ScannerWrapped.sc.nextLine().charAt(0);
		if(ch == 'Y' || ch == 'y') {
			System.out.println("Enter the last name: ");
			name[1] = ScannerWrapped.sc.nextLine();
		}
		if(name[1] == null)
			name[1] = p.getLastName();
		
		if(!p.getName().equals(name[0] + " " + name[1]) && search(name) != null) {
			System.out.println("Contact name exists!");
			System.out.println("Modification failed. Try again.");
			return;
		}
		
		System.out.println("Change the phone number (y/n)?");
		ch = ScannerWrapped.sc.nextLine().charAt(0);
		if(ch == 'Y' || ch == 'y') {
			System.out.println("Enter the phone number: ");
			phoneNumber = ScannerWrapped.sc.nextLong();
			ScannerWrapped.sc.nextLine();
		}
		if(phoneNumber == 0)
			phoneNumber = p.getPhoneNumber();
		
		if(phoneNumber != p.getPhoneNumber() && search(phoneNumber) != null) {
			System.out.println("Contact number already exists!");
			System.out.println("Modification failed. Try again.");
			return;
		}
		
		System.out.println("Change the email (y/n)?");
		ch = ScannerWrapped.sc.nextLine().charAt(0);
		if(ch == 'Y' || ch == 'y') {
			System.out.println("Enter the email: ");
			email = ScannerWrapped.sc.nextLine();
		}
		System.out.println("Change the address (y/n)?");
		ch = ScannerWrapped.sc.nextLine().charAt(0);
		if(ch == 'Y' || ch == 'y') {
			p.getAddress().modify();
		}
		p.setFirstName(name[0]);
		p.setLastName(name[1]);
		p.setPhoneNumber(phoneNumber);
		p.setEmail(email);
	}
	
	private void remove(Person p) {
		Map<String, LinkedList<Person>> cityList = table.get(ListType.CITY.ordinal());
		Map<String, LinkedList<Person>> stateList = table.get(ListType.STATE.ordinal());
		contactTable_Name_to_Person.remove(p.getName());
		cityList.get(p.getAddress().getCity()).remove(p);
		stateList.get(p.getAddress().getState()).remove(p);
		clearEmptyList(p.getAddress().getCity(), p.getAddress().getCity());
	}
	
	public void modify(String[] name) {
		Person p = search(name);
		if(p == null) {
			System.out.println("Contact doesn't exist");
			return;
		}
		String city = p.getAddress().getCity();
		String state = p.getAddress().getState();
		modify(p);
		updateList(p, city, state);
	}
	
	public void modify(long phoneNumber) {
		Person p = search(phoneNumber);
		if(p == null) {
			System.out.println("Contact doesn't exist");
			return;
		}
		String city = p.getAddress().getCity();
		String state = p.getAddress().getState();
		modify(p);
		updateList(p, city, state);
	}
	
	public void remove(String[] name) {
		Person p = search(name);
		if(p == null) {
			System.out.println("Contact not found!");
			return;
		}
		remove(p);
		System.out.println("Contact removed successfully!");
	}
	
	public void remove(long phoneNumber) {
		Person p = search(phoneNumber);
		if(p == null) {
			System.out.println("Contact doesn't exist");
			return;
		}
		remove(p);
		System.out.println("Contact removed successfully!");
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
	
	public void viewCityOrState(String addressVar, ListType listType ) {
		Map<String, LinkedList<Person>> list = table.get(listType.ordinal());
		if(!list.containsKey(addressVar)) { 
			System.out.println(addressVar + " not found in database!");
			return;
		}	
		
		list.entrySet().stream()
					   .filter(e -> addressVar.equals(e.getKey()))
					   .flatMap(e -> e.getValue().stream())
					   .forEach(System.out::println);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public int size() {
		return contactTable_Name_to_Person.size();
	}
	
	public long countCityOrState(String addressVar, ListType listType) {
		if(!table.get(listType.ordinal()).containsKey(addressVar))
			return -1;
		
		return table.get(listType.ordinal()).entrySet()
									 .stream()
									 .filter(e -> addressVar.equals(e.getKey()))
									 .flatMap(e -> e.getValue().stream())
									 .count();
	}
}
