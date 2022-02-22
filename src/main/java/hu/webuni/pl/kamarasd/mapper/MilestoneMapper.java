package hu.webuni.pl.kamarasd.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import hu.webuni.pl.kamarasd.dto.MilestoneDto;
import hu.webuni.pl.kamarasd.model.Milestone;

@Mapper(componentModel = "spring")
public interface MilestoneMapper {
	List<MilestoneDto> milestoneToDtos(List<Milestone> milestone);
	
	MilestoneDto milestoneToDto(Milestone milestone); 
	
	@InheritInverseConfiguration
	Milestone dtoToMilestone(MilestoneDto milestoneDto);
}
