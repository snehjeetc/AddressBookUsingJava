package addressbook;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import detailsofperson.Person;
import scannerwrapper.ScannerWrapped;

public class AddressBookUtility {
	private static String DIR = "/home/snehjeetc12/git/AddressBookUsingJava/AddressBookUsingJava/AddressBooks/";
	
	enum IOService{ FILE_IO; }
	
	public static AddressBook loadFile(String bookName, IOService ioService) throws IOException {
		if(ioService.equals(IOService.FILE_IO)) {
			Path targetFile = Paths.get(DIR+bookName+".txt");
			if(!Files.exists(targetFile)) 
				throw new IOException("File doesn't exists");
			List<Person> list = Files.lines(targetFile)
					 .map(line -> line.trim())
					 .map(line -> AddressBook.extractPerson(line, ","))
					 .collect(Collectors.toList());
			AddressBook book = new AddressBook(bookName);
			list.forEach(e -> book.addContact(e));
			return book;
		}
		return null;
	}
	
	public static void showFiles() {
		File f = new File(DIR);
		String[] files = f.list();
		for(String file : files)
			System.out.println(file);
	}
	
	public static void rename(String newbookName, String oldBookName, IOService ioService) 
			throws IOException {
		if(ioService.equals(IOService.FILE_IO)) {
			File oldFile = new File(DIR+oldBookName+".txt");
			if(oldFile.exists()) {
				File newFilename = new File(DIR+newbookName+".txt");
				if(newFilename.exists()) {
					throw new IOException("File name already exists");
				}
				oldFile.renameTo(newFilename);
				return;
			}
		}
	}
	
	public static void saveChanges(AddressBook book, IOService ioService) 
			throws IOException {
		if(ioService.equals(IOService.FILE_IO)) {
			File f = new File(DIR+book.getName()+".txt");
			if(f.exists()) {
				System.out.println(book.getName() + "Already exists!");
				System.out.println("Want to overwrite?(y/n)");
				char ch = ScannerWrapped.sc.nextLine().toUpperCase().charAt(0);
				if(ch != 'Y') {
					System.out.println("Changes couldn't be saved!");
					return;
				}
				deleteFile(book.getName(), IOService.FILE_IO);
			}
			Path targetFile = Paths.get(DIR+book.getName()+".txt");
			Path donePath = Files.createFile(targetFile);
			AddressBook.writeBook(book, donePath, AddressBook.IOService.FILE_IO);
			System.out.println("Done writing");
		}
	}
	
	public static void deleteFile(String bookName, IOService ioService) 
			throws IOException {
		if(ioService.equals(IOService.FILE_IO)) {
			File f = new File(DIR+bookName+".txt");
			if(f.delete())
				return;
			else
				throw new IOException("Failed to delete the file");
		}
	}

	public static long countFiles() {
		return new File(DIR).list().length;
	}
}
