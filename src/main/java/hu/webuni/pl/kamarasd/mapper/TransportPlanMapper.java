package hu.webuni.pl.kamarasd.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.webuni.pl.kamarasd.dto.SectionDto;
import hu.webuni.pl.kamarasd.dto.TransportPlanDto;
import hu.webuni.pl.kamarasd.model.Section;
import hu.webuni.pl.kamarasd.model.TransportPlan;

@Mapper(componentModel = "spring")
public interface TransportPlanMapper {

	TransportPlanDto transportPlanToDto(TransportPlan transportPlan);
	
	TransportPlan dtoToTransportPlan(TransportPlanDto transportPlanDto);
	
	@Mapping(target = "transportPlan", ignore = true)
	SectionDto dtoToSection(Section section); 
	
	TransportPlanDto modelToDtoWithoutSections(TransportPlan transportPlan);
	
	List<SectionDto> sectionToDto(List<Section> section);
}
