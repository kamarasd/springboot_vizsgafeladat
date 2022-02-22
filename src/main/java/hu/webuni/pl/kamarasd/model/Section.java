package hu.webuni.pl.kamarasd.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Section {
	
	@Id
	@GeneratedValue
	public long id;
	
	@ManyToOne
	public Milestone fromMilestone;
	
	@ManyToOne
	public Milestone toMilestone;
	
	public Integer number;
	
	@ManyToOne
	public TransportPlan transportPlan;
	
	public Section() {
		
	}

	public Section(Milestone fromMilestone, Milestone toMilestone, Integer number, TransportPlan transportPlan) {
		this.fromMilestone = fromMilestone;
		this.toMilestone = toMilestone;
		this.number = number;
		this.transportPlan = transportPlan;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Milestone getFromMilestone() {
		return fromMilestone;
	}

	public void setFromMilestone(Milestone fromMilestone) {
		this.fromMilestone = fromMilestone;
	}

	public Milestone getToMilestone() {
		return toMilestone;
	}

	public void setToMilestone(Milestone toMilestone) {
		this.toMilestone = toMilestone;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public TransportPlan getTransportPlan() {
		return transportPlan;
	}

	public void setTransportPlan(TransportPlan transportPlan) {
		this.transportPlan = transportPlan;
	}

	
	
	
}
