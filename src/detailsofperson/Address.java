package detailsofperson;

public class Address {
	private int buildingNumber;
	private String street;
	private String city;
	private String state;
	private String country;
	private int zip;
	
	public Address(int buildingNumber, String[] args, int zip){
		this.buildingNumber = buildingNumber;
		this.street = args[0];
		this.city = args[1];
		this.state = args[2];
		this.country = args[3];
		this.zip = zip;
	}
}
