package hu.webuni.pl.kamarasd.dto;

import java.util.List;

public class SectionDto {
	
	public long id;
	
	public MilestoneDto fromMilestone;
	
	public MilestoneDto toMilestone;
	
	public Integer number;
	
	public TransportPlanDto transportPlan;

	public SectionDto(long id, MilestoneDto fromMilestone, MilestoneDto toMilestone, Integer number,
			TransportPlanDto transportPlan) {
		this.id = id;
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

	public MilestoneDto getFromMilestone() {
		return fromMilestone;
	}

	public void setFromMilestone(MilestoneDto fromMilestone) {
		this.fromMilestone = fromMilestone;
	}

	public MilestoneDto getToMilestone() {
		return toMilestone;
	}

	public void setToMilestone(MilestoneDto toMilestone) {
		this.toMilestone = toMilestone;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public TransportPlanDto getTransportPlan() {
		return transportPlan;
	}

	public void setTransportPlan(TransportPlanDto transportPlan) {
		this.transportPlan = transportPlan;
	}

	
}
