package detailsofperson;
import java.util.Scanner;

public class Person {
	Scanner sc = new Scanner(System.in);
	private String firstName, lastName;
	private long phoneNumber;
	private String email;
	Address add;
	
	public Person(String[] name, long phoneNum, String email, Address add){
		this.firstName = name[0];
		this.lastName = name[1];
		this.phoneNumber = phoneNum;
		this.email = email;
		
		this.add = add;
	}
	public void modify() {
		System.out.println("Change First Name (y/n)? ");
		char ch = sc.nextLine().charAt(0);
		if(ch == 'Y' || ch == 'y') {
			System.out.println("Enter the first name: ");
			this.firstName = sc.nextLine();
		}
		System.out.println("Change the last Name (y/n)?");
		ch = sc.nextLine().charAt(0);
		if(ch == 'Y' || ch == 'y') {
			System.out.println("Enter the last name: ");
			this.lastName = sc.nextLine();
		}
		System.out.println("Change the phone number (y/n)?");
		ch = sc.nextLine().charAt(0);
		if(ch == 'Y' || ch == 'y') {
			System.out.println("Enter the phone number: ");
			this.phoneNumber = sc.nextLong();
			sc.nextLine();
		}
		System.out.println("Change the email (y/n)?");
		ch = sc.nextLine().charAt(0);
		if(ch == 'Y' || ch == 'y') {
			System.out.println("Enter the email: ");
			this.email = sc.nextLine();
		}
		System.out.println("Change the address (y/n)?");
		ch = sc.nextLine().charAt(0);
		if(ch == 'Y' || ch == 'y') {
			add.modify();
		}
	}
	public boolean checkName(String[] name) {
		return name[0] == this.firstName && name[1] == this.lastName;
	}
	
	//Setters
	//---------------------------------------------------
	public void setFristName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setName(String[] name) {
		this.firstName = name[0]; 
		this.lastName = name[1];
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void setAddress(Address add) {
		this.add = add;
	}
	//---------------------------------------------------
	
	
	//Getters
	//---------------------------------------------------
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getName() {
		return firstName + " " + lastName;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public Address getAdress() {
		return add;
	}
	//---------------------------------------------------
	
	public String toString() {
		return "Name: " + firstName + " " + lastName + "\n"
				+ "Phone Number: " + phoneNumber + "\n" 
				+ "Email: " + email + "\n"
				+ "Address: \n" + add.toString();
	}
}
