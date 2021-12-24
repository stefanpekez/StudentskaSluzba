package model;

public class Address {
	private String street;
	private int number;
	private String city;
	private String country;
	
	public Address(String street, int number, String city, String country) {
		super();
		this.street = street;
		this.number = number;
		this.city = city;
		this.country = country;
	}
	
	public Address(String unparsed) {
		String[] split = unparsed.split(",");
		
		if(split.length == 4) {
			this.street = split[0];
			
			try {
				this.number = Integer.parseInt(split[1].replaceAll("\\W", ""));
			} catch (Exception e) {
				// TODO: handle exception
				this.number = -1;
			}
			
			this.city = split[2];
			this.country = split[3];
		} else {
			this.street = unparsed;
			this.number = 0;
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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
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
		if(number == 0 && city == "" && country == "") {
			return street;
		} else {
			return String.format("%s, %d, %s, %s", street, number, city, country);
		}
	}
	
	
}
