package addressbook;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import detailsofperson.Person;
import scannerwrapper.ScannerWrapped;

public class AddressBookUtility {
	private static String DIR = "/home/snehjeetc12/git/AddressBookUsingJava/AddressBookUsingJava/AddressBooks/";
	
	enum IOService{
		FILE_IO (".txt"),
		CSV_IO (".csv"),
		JSON_IO (".json");
		private String extension;

		IOService(String extension){
			this.extension = extension;
		}
		String getExtension() { return this.extension; }
	}
	
	public static AddressBook loadFile(String bookName, IOService ioService) throws IOException {
		if(!ioService.equals(IOService.FILE_IO) && !ioService.equals(IOService.CSV_IO)
												&& !ioService.equals(IOService.JSON_IO))
			throw new IOException("Wrong IO service type");


		if(ioService.equals(IOService.FILE_IO)) {
			Path targetFile = Paths.get(DIR+bookName+IOService.FILE_IO.extension);
			if(!Files.exists(targetFile))
				throw new IOException("File doesn't exists");
			List<Person> list = Files.lines(targetFile)
					.map(line -> line.trim())
					.map(line -> {
						String[] fields = line.split(",");
						return AddressBook.extractPerson(fields);
					})
					.collect(Collectors.toList());
			AddressBook book = new AddressBook(bookName);
			list.forEach(e -> book.addContact(e));
			return book;
		}
		else if(ioService.equals(IOService.CSV_IO)){
			Reader reader = Files.newBufferedReader(Paths.get(DIR+bookName+IOService.CSV_IO.extension));
			CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();

			//Reading all records at once
			List<String[]> records = csvReader.readAll();
			List<Person> personList = records.stream()
											 .map(fields -> AddressBook.extractPerson(fields))
											 .collect(Collectors.toList());
			AddressBook book = new AddressBook(bookName);
			personList.forEach(person -> book.addContact(person));
			return book;
		}
		else if(ioService.equals(IOService.JSON_IO)){
			BufferedReader bufferedReader = new BufferedReader(
					new FileReader(DIR+bookName+IOService.JSON_IO.extension));
			AddressBook book = new Gson().fromJson(bufferedReader, AddressBook.class);
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
		if(!ioService.equals(IOService.FILE_IO) && !ioService.equals(IOService.CSV_IO)
												&& !ioService.equals(IOService.JSON_IO))
			throw new IOException("Wrong IO service type");

		File oldFile = new File(DIR+oldBookName+ioService.extension);
		if(oldFile.exists()) {
				File newFilename = new File(DIR+newbookName+ioService.extension);
				if(newFilename.exists()) {
				throw new IOException("File name already exists");
				}
				oldFile.renameTo(newFilename);
				return;
			}
		else
			throw new IOException("No such file exists");
	}
	
	public static void saveChanges(AddressBook book, IOService ioService) 
			throws IOException {
		if(!ioService.equals(IOService.FILE_IO) && !ioService.equals(IOService.CSV_IO)
												&& !ioService.equals(IOService.JSON_IO))
			throw new IOException("Wrong IO service type");

		File f = new File(DIR+book.getName()+ioService.extension);
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
		if(ioService.equals(IOService.FILE_IO)) {
			Path targetFile = Paths.get(DIR+book.getName()+IOService.FILE_IO.extension);
			Path donePath = Files.createFile(targetFile);
			AddressBook.writeBook(book, donePath, AddressBook.IOService.FILE_IO);
		}
		else if(ioService.equals(IOService.CSV_IO)){
			try(
					Writer writer = Files.newBufferedWriter(Paths.get(DIR+book.getName()+IOService.CSV_IO.extension));
					){
				AddressBook.writeBook(book, writer, AddressBook.IOService.CSV_IO);
				writer.close();
			}
		}
		else if(ioService.equals(IOService.JSON_IO)){
			FileWriter writer = new FileWriter(DIR+book.getName()+IOService.JSON_IO.extension);
			String json = new Gson().toJson(book);
			writer.write(json);
			writer.close();
		}
		System.out.println("Done writing");
	}
	
	public static void deleteFile(String bookName, IOService ioService) 
			throws IOException {
		if(!ioService.equals(IOService.FILE_IO) && !ioService.equals(IOService.CSV_IO)
												&& !ioService.equals(IOService.JSON_IO))
			throw new IOException("Wrong IO service type");

		File f = new File(DIR+bookName+ioService.extension);
		if(f.delete())
			return;
		else
			throw new IOException("Failed to delete the file");
	}
}
