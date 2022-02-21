package hu.webuni.pl.kamarasd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Section {
	
	@Id
	@GeneratedValue
	public long id;
	
	public String fromMilestone;
	public String toMilestone;
	
	public Integer number;
	
	public Section() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFromMilestone() {
		return fromMilestone;
	}

	public void setFromMilestone(String fromMilestone) {
		this.fromMilestone = fromMilestone;
	}

	public String getToMilestone() {
		return toMilestone;
	}

	public void setToMilestone(String toMilestone) {
		this.toMilestone = toMilestone;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
	
	
}
