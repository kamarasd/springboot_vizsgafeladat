package hu.webuni.pl.kamarasd.model;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Milestone {

	@Id
	@GeneratedValue
	public long id;
	
	//public Address address;
	
	public LocalTime plannedTime;
	
	public Milestone() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

//	public Address getAddress() {
//		return address;
//	}
//
//	public void setAddress(Address address) {
//		this.address = address;
//	}

	public LocalTime getPlannedTime() {
		return plannedTime;
	}

	public void setPlannedTime(LocalTime plannedTime) {
		this.plannedTime = plannedTime;
	}
	
	
}
