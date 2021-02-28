package addressbook;
import scannerwrapper.ScannerWrapped;

public class AddressBookMain {
	public static void main(String[] args) {
		AddressBookList mybooklist = new AddressBookList();
		mybooklist.mainMenu();
		ScannerWrapped.close();
	}
	
}
