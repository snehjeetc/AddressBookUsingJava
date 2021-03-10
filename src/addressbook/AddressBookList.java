package addressbook;
import addressbook.AddressBook;
import java.util.HashMap;
import java.util.Map;

import scannerwrapper.ScannerWrapped;

public class AddressBookList {
	private Map<String, AddressBook> addressBooks;
	
	public AddressBookList() {
		addressBooks = new HashMap<>();
	}
	
	public void mainMenu() {
		System.out.println("Welcome to Address Book Program");
		char ch = 'Y';
		while( ch == 'Y') {
			printMenu();
			int option = ScannerWrapped.sc.nextInt();
			ScannerWrapped.sc.nextLine();
			if(option <= 0 || option > 6) {
				System.out.println("Wrong input");
				System.out.println("Do you want to try again?(y/n)" );
				ch = ScannerWrapped.sc.nextLine().toUpperCase().charAt(0);
				continue;
			}
			performOperations(option);
			System.out.println("Continue? (Y/N)");
			ch = ScannerWrapped.sc.nextLine().toUpperCase().charAt(0);
		}
		
		
	}
	
	private void printMenu() {
		System.out.println("-------------------------------------");
		System.out.println("Choose from the following option: ");
		System.out.println("1.) Create an address book.");
		System.out.println("2.) Search an address book.");
		System.out.println("3.) Modify an address book.");
		System.out.println("4.) Delete an address book.");
		System.out.println("5.) Print a book");
		System.out.println("6.) Find total number of address books.");
		System.out.println("-------------------------------------");
	}
	
	private void performOperations(int option) {
		System.out.println("Enter the name of the book: ");
		String name = ScannerWrapped.sc.nextLine();
		AddressBook book;
		switch(option) {
		case 1:
			if(addressBooks.containsKey(name) == true) {
				System.out.println(name + " Already exists!");
				System.out.println("Try to modify the existing book/use a new name");
				System.out.println("Taking you back to main menu!");
				return;
			}
			book = new AddressBook(name);
			addressBooks.put(name, book);
			performBookOperations(book);
			break;
		case 2:
			book = addressBooks.get(name);
			if(book == null) {
				System.out.println(name + " not found!");
				return;
			}
			performBookOperations(book);
			break;
		case 3: 
			book = addressBooks.get(name);
			if(book == null) {
				System.out.println(name + " not found!");
				return;
			}
			System.out.println("-------------------------------------");
			System.out.println("Modify: ");
			System.out.println("1.) Book Name \t 2.) Modify a contact");
			System.out.println("-------------------------------------");
			int choice = ScannerWrapped.sc.nextInt();
			ScannerWrapped.sc.nextLine();
			if(choice != 1 && choice != 2) {
				System.out.println("OOPs! Incorrect input");
				System.out.println("Taking you back to main menu.");
				return;
			}
			if(choice == 1) {
				System.out.println("Enter new book name: ");
				String bookName = ScannerWrapped.sc.nextLine();			
				if(addressBooks.containsKey(name) || bookName.equals(book.getName())) {
					System.out.println(bookName + "Already exists!");
					System.out.println("Try deleting or find another name.");
					System.out.println("Taking you back to main menu.");
					return;
				}
				book.setName(bookName);
				addressBooks.remove(name);
				addressBooks.put(bookName, book);
				return;
			}
			else {
				performBookOperations(book);
			}
			return;
		case 4:
			book = addressBooks.get(name);
			if(book == null) {
				System.out.println(name + " not found!");
				return;
			}
			addressBooks.remove(name);
			return;
		case 5:
			book = addressBooks.get(name);
			if(book == null) {
				System.out.println(name + " not found!");
				return;
			}
			System.out.println("-------------------------------------");
			System.out.println("Show sorted order: ");
			System.out.println("1.) Name");
			System.out.println("2.) City");
			System.out.println("3.) State");
			System.out.println("4.) Zip");
			System.out.println("-------------------------------------");
			int sortOrder = ScannerWrapped.sc.nextInt();
			ScannerWrapped.sc.nextLine();
			switch(sortOrder) {
			case 1: book.printBook();
					return;
			case 2:	book.viewSortedOrder(AddressBook.SortType.SORT_CITY);
					return;
			case 3:	book.viewSortedOrder(AddressBook.SortType.SORT_STATE);
					return;
			case 4: book.viewSortedOrder(AddressBook.SortType.SORT_ZIP);
					return;
			default:
					System.out.println("Invalid input!");
					System.out.println("Taking you back to main menu");
					return;
			}
		case 6:
			System.out.println("Total number of address books"
					+ " in the system: " + size());
			return;
		}
	}
	
	private void printBookOperations() {
		System.out.println("-------------------------------------");
		System.out.println("Choose from the following option: ");
		System.out.println("1.) Add contacts");
		System.out.println("2.) Search and view");
		System.out.println("3.) Modify a contact");
		System.out.println("4.) Delete a contact");
		System.out.println("5.) Go back to the main menu");
		System.out.println("-------------------------------------");
	}
	
	private void performBookOperations(AddressBook book) {
		char ch = 'Y';
		while(ch == 'Y') {
			printBookOperations();
			System.out.println("Enter your choice: ");
			int option = ScannerWrapped.sc.nextInt();
			ScannerWrapped.sc.nextLine();
			if(option <= 0 || option > 5  ) {
				System.out.println("Incorrect input");
				System.out.println("Do you want to try again?(y/n)");
				ch = ScannerWrapped.sc.nextLine().toUpperCase().charAt(0);
				continue;
			}
			if(option == 5)
				return;
			switch(option) {
			case 1:
				book.fillBook();
				return;
			case 2: {
				System.out.println("-------------------------------------");
				System.out.println("Search by: ");
				System.out.println("1.) Name/PhoneNumber \t 2.) City"
									+ "	3.)State");
				System.out.println("-------------------------------------");
				int searchOpt = ScannerWrapped.sc.nextInt();
				ScannerWrapped.sc.nextLine();
				if(searchOpt < 1 || searchOpt > 3) {
					System.out.println("Invalid Input!");
					System.out.println("Taking you back to the main menu");
					return;
				}
				if(searchOpt == 1) {
					int res = searchAndPerform(book, 2);
					if(res == 1)
					System.out.println("Taking you back to main menu.");
					}
				else if(searchOpt == 2) 
					viewCityState(book, AddressBook.ListType.CITY);
				else
					viewCityState(book, AddressBook.ListType.STATE);
				}
				return;
			case 3:{
				int res = searchAndPerform(book, 3);
				if(res == 1)
					System.out.println("Taking you back to main menu.");
				}
				return;
			case 4:{
				int res = searchAndPerform(book, 4);
				if(res == 1)
					System.out.println("Taking you back to main menu.");
				}
			}
		}
	}
	
	private int searchAndPerform(AddressBook book, int perform) {
		System.out.println("-------------------------------------");
		System.out.println("Search by: ");
		System.out.println("1.) Name \t 2.) Phone Number ");
		System.out.println("-------------------------------------");
		int choice = ScannerWrapped.sc.nextInt();
		ScannerWrapped.sc.nextLine();
		if(choice < 1 || choice > 2) {
			System.out.println("Wrong input");
			return 1;
		}
		switch(choice) {
		case 1 : {
			String[] name = new String[2];
			System.out.println("Enter first name: ");
			name[0] = ScannerWrapped.sc.nextLine();
			System.out.println("Enter last name: ");
			name[1] = ScannerWrapped.sc.nextLine();
			searchAndPerform(book, name, perform);
			}
			break;
		case 2: {
			System.out.println("Enter the phone number: ");
			long phoneNumber = ScannerWrapped.sc.nextLong();
			ScannerWrapped.sc.nextLine();
			searchAndPerform(book, phoneNumber, perform);
			}
			break;
		}
		return 0;
	}
	
	private void searchAndPerform(AddressBook book, String[] name, int perform) {
		switch(perform) {
		case 2:
			book.print(name);
			return;
		case 3:
			book.modify(name);
			return;
		case 4:
			book.remove(name);
		}
	}
	
	private void searchAndPerform(AddressBook book, long phoneNumber, int perform) {
		switch(perform) {
		case 2:
			book.print(phoneNumber);
			return;
		case 3:
			book.modify(phoneNumber);
			return;
		case 4:
			book.remove(phoneNumber);
		}
	}
	
	private void viewCityState(AddressBook book, AddressBook.ListType listType) {
		String addressVar;
		if(listType == AddressBook.ListType.CITY) 
			System.out.println("Enter the city name: ");
		else 
			System.out.println("Enter the state name: ");	
		addressVar = ScannerWrapped.sc.nextLine();
		System.out.println("Show the count?(y/n) ");
		char ch = ScannerWrapped.sc.nextLine().toUpperCase().charAt(0);
		if(ch == 'Y') {
			long count = book.countCityOrState(addressVar, listType);
			if(count != -1) {
				System.out.println("Total number of persons in " 
						+ addressVar + ": " + count);
				System.out.println("Continue to display contacts?(Y/n)");
				ch = ScannerWrapped.sc.nextLine().toUpperCase().charAt(0);
				if(ch != 'Y')
					return;
			}
			else {
				System.out.println(addressVar + " not found");
				return;
			}
			book.viewCityOrState(addressVar, listType);
		}
		
	}
	
	public int size() {
		return addressBooks.size();
	}
}
