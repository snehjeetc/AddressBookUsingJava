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
			if(option <= 0 || option > 5) {
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
			book.printBook();
			return;
		}
	}
	
	private void printBookOperations() {
		System.out.println("-------------------------------------");
		System.out.println("Choose from the following option: ");
		System.out.println("1.) Add contacts");
		System.out.println("2.) Search and print a contact ");
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
				int res = searchAndPerform(book, 2);
				if(res == 1)
					System.out.println("Taking you back to main menu.");
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
		System.out.println("1.) Name \t 2.) Phone Number");
		System.out.println("-------------------------------------");
		int choice = ScannerWrapped.sc.nextInt();
		ScannerWrapped.sc.nextLine();
		if(choice!=1 && choice!=2) {
			System.out.println("Wrong input");
			return 1;
		}
		if(choice == 1) {
			String[] name = new String[2];
			System.out.println("Enter first name: ");
			name[0] = ScannerWrapped.sc.nextLine();
			System.out.println("Enter last name: ");
			name[1] = ScannerWrapped.sc.nextLine();
			searchAndPerform(book, name, perform);
		}
		else {
			System.out.println("Enter the phone number: ");
			long phoneNumber = ScannerWrapped.sc.nextLong();
			ScannerWrapped.sc.nextLine();
			searchAndPerform(book, phoneNumber, perform);
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
	
	public int size() {
		return addressBooks.size();
	}
}
