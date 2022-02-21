package hu.webuni.pl.kamarasd.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TransportPlan {
	
	@Id
	@GeneratedValue
	public long id;
	
	public Integer income;
	
	@OneToMany(mappedBy = "transportPlan")
	public List<Section> section;
	
	public TransportPlan() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getIncome() {
		return income;
	}

	public void setIncome(Integer income) {
		this.income = income;
	}

	public List<Section> getSection() {
		return section;
	}

	public void setSection(List<Section> section) {
		this.section = section;
	}

	
	
}
