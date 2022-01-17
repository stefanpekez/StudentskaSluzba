package model;

public class Address {
	private String street;
	private String number;
	private String city;
	private String country;
	
	public Address(String street, String number, String city, String country) {
		super();
		this.street = street;
		this.number = number;
		this.city = city;
		this.country = country;
	}
	
	public Address() {
		
	}
	
	public Address(String unparsed) {
		String[] split = unparsed.split(",");
		
		if(split.length == 4) {
			this.street = split[0];
			
			try {
				this.number = split[1].replaceAll("\\W", "");
			} catch (Exception e) {
				// TODO: handle exception
				this.number = "";
				
			}
			
			this.city = split[2];
			this.country = split[3];
		} else {
			this.street = unparsed;
			this.number = "";
			this.city = "";
			this.country = "";
			System.out.println("Address coulnt be split");
		}
			
	}
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		if(number == "" && city == "" && country == "") {
			return street;
		} else {
			return String.format("%s, %s, %s, %s", street, number, city, country);
		}
	}
	
	
}
