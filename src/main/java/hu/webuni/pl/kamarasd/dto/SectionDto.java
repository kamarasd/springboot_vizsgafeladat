package hu.webuni.pl.kamarasd.dto;

import java.util.List;

import hu.webuni.pl.kamarasd.model.Milestone;

public class SectionDto {
	
	public long id;
	
	public List<Milestone> fromMilestone;
	
	public List<Milestone> toMilestone;
	
	public Integer number;

	public SectionDto(long id, List<Milestone> fromMilestone, List<Milestone> toMilestone, Integer number) {
		this.id = id;
		this.fromMilestone = fromMilestone;
		this.toMilestone = toMilestone;
		this.number = number;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Milestone> getFromMilestone() {
		return fromMilestone;
	}

	public void setFromMilestone(List<Milestone> fromMilestone) {
		this.fromMilestone = fromMilestone;
	}

	public List<Milestone> getToMilestone() {
		return toMilestone;
	}

	public void setToMilestone(List<Milestone> toMilestone) {
		this.toMilestone = toMilestone;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
	
}
