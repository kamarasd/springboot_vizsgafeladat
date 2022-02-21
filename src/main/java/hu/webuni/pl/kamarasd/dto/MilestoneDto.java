package hu.webuni.pl.kamarasd.dto;

import java.time.LocalTime;

public class MilestoneDto {
	
	public Long id;
	public AddressDto address;
	public LocalTime plannedTime;

	public MilestoneDto(Long id, AddressDto address, LocalTime plannedTime) {
		this.id = id;
		this.address = address;
		this.plannedTime = plannedTime;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public AddressDto getAddress() {
		return address;
	}
	
	public void setAddress(AddressDto address) {
		this.address = address;
	}
	
	public LocalTime getPlannedTime() {
		return plannedTime;
	}
	
	public void setPlannedTime(LocalTime plannedTime) {
		this.plannedTime = plannedTime;
	}
	
	

}
