package detailsofperson;

public class Person {
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
}
