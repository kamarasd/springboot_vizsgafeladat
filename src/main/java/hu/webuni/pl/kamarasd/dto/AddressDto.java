package hu.webuni.pl.kamarasd.dto;

public class AddressDto {
	
	public Long id;
	
	public String country;
	public String city;
	public String street;
	public String postal;
	public String houseno;
	public float gpslong;
	public float gpslat;
	
	public AddressDto(Long id, String country, String city, String street, String postal, String houseno, float gpslong,
			float gpslat) {
		super();
		this.id = id;
		this.country = country;
		this.city = city;
		this.street = street;
		this.postal = postal;
		this.houseno = houseno;
		this.gpslong = gpslong;
		this.gpslat = gpslat;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getPostal() {
		return postal;
	}
	
	public void setPostal(String postal) {
		this.postal = postal;
	}
	
	public String getHouseno() {
		return houseno;
	}
	
	public void setHouseno(String houseno) {
		this.houseno = houseno;
	}
	
	public float getGpslong() {
		return gpslong;
	}
	
	public void setGpslong(float gpslong) {
		this.gpslong = gpslong;
	}
	
	public float getGpslat() {
		return gpslat;
	}
	
	public void setGpslat(float gpslat) {
		this.gpslat = gpslat;
	}
	
	
	

}