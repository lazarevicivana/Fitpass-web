package beans;

public class Adress {
	private String street;
	private String number;
	private String city;
	private String postalCode;
	private String longitude;
	private String latitude;

	public Adress(String street, String number, String city, String postalCode, String longitude, String latitude) {
		super();
		this.street = street;
		this.number = number;
		this.city = city;
		this.postalCode = postalCode;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public String getStreet() {
		return street;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setStreet(String streetAndNumber) {
		this.street = streetAndNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}


	@Override
	public String toString() {
		return "Adress [streetAndNumber=" + street + number + ", city=" + city + ", postalCode=" + postalCode + "]";
	}

}
