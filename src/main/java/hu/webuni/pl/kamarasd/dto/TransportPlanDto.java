package hu.webuni.pl.kamarasd.dto;

import java.util.List;

public class TransportPlanDto {

	public Long id;
	
	public Integer income;
	
	public List<SectionDto> Section;

	public TransportPlanDto(Long id, Integer income, List<SectionDto> section) {
		this.id = id;
		this.income = income;
		Section = section;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getIncome() {
		return income;
	}

	public void setIncome(Integer income) {
		this.income = income;
	}

	public List<SectionDto> getSection() {
		return Section;
	}

	public void setSection(List<SectionDto> section) {
		Section = section;
	}
	
	
	
	
	
	
}
