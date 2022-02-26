package hu.webuni.pl.kamarasd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Address {
	
	@Id
	@GeneratedValue
	public Long id;
	
	@NotBlank(message = "Country need to be fill!")
	public String country;
	
	@NotBlank(message = "City need to be fill!")
	public String city;
	
	@NotBlank(message = "Street need to be fill!")
	public String street;
	
	@NotBlank(message = "Postal need to be fill!")
	public String postal;
	
	@NotBlank(message = "Houseno need to be fill!")
	public String houseno;
	public double gpslong;
	public double gpslat;
	
	public Address() {
		
	}
	
	public Address(String country, String city, String street, String postal, String houseno, double gpslong,
			double gpslat) {
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
	
	public double getGpslong() {
		return gpslong;
	}
	
	public void setGpslong(double gpslong) {
		this.gpslong = gpslong;
	}
	
	public double getGpslat() {
		return gpslat;
	}
	
	public void setGpslat(double gpslat) {
		this.gpslat = gpslat;
	}
	
	

}
