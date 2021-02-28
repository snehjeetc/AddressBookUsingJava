package detailsofperson;

import scannerwrapper.ScannerWrapped;

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
	
	public void modify() {
		System.out.println("Change Building number (y/n)? ");
		char ch = ScannerWrapped.sc.nextLine().charAt(0);
		if(ch == 'Y' || ch == 'y') {
			System.out.println("Enter the building number: ");
			this.buildingNumber = ScannerWrapped.sc.nextInt();
			ScannerWrapped.sc.nextLine();
		}
		System.out.println("Change the street name (y/n)?");
		ch = ScannerWrapped.sc.nextLine().charAt(0);
		if(ch == 'Y' || ch == 'y') {
			System.out.println("Enter the street name: ");
			this.street = ScannerWrapped.sc.nextLine();
		}
		System.out.println("Change the city name (y/n)?");
		ch = ScannerWrapped.sc.nextLine().charAt(0);
		if(ch == 'Y' || ch == 'y') {
			System.out.println("Enter the city name: ");
			this.city = ScannerWrapped.sc.nextLine();
		}
		System.out.println("Change the state(y/n)?");
		ch = ScannerWrapped.sc.nextLine().charAt(0);
		if(ch == 'Y' || ch == 'y') {
			System.out.println("Enter the state name: ");
			this.state = ScannerWrapped.sc.nextLine();
		}
		System.out.println("Change the country name (y/n)?");
		ch = ScannerWrapped.sc.nextLine().charAt(0);
		if(ch == 'Y' || ch == 'y') {
			System.out.println("Enter the country name: ");
			this.country = ScannerWrapped.sc.nextLine();
		}
		System.out.println("Change the zip code(y/n)?");
		ch = ScannerWrapped.sc.nextLine().charAt(0);
		if(ch == 'Y' || ch == 'y') {
			System.out.println("Enter the new zip code: ");
			this.zip = ScannerWrapped.sc.nextInt();
			ScannerWrapped.sc.nextLine();
		}
		
	}
	
	//Setters
	//---------------------------------------------------
	public void setStreet(String street) {
		this.street = street;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setZipCode(int zip) {
		this.zip = zip;
	}
	public void setBuildingNumber(int buildingNumber) {
		this.buildingNumber = buildingNumber;
	}
	//---------------------------------------------------
	
	// Getters
	//---------------------------------------------------
	public String getStreet() {
		return street;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getCountry() {
		return country;
	}
	public int getZipCode() {
		return zip;
	}
	public int getBuildingNumber() {
		return buildingNumber;
	}
	//---------------------------------------------------
	
	public String toString() {
		return "Building Number: " + buildingNumber + "\n"
				+ "Street: " + street + "\n"
				+ "City: " + city + "\n"
				+ "State: " + state + "\n"
				+ "Country: " + country;
	}
}
