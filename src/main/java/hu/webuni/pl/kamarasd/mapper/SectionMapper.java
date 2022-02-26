package hu.webuni.pl.kamarasd.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import hu.webuni.pl.kamarasd.dto.SectionDto;
import hu.webuni.pl.kamarasd.model.Section;

@Mapper(componentModel = "spring")
public interface SectionMapper {

	List<SectionDto> dtoToSection(List<Section> section);
	
	SectionDto sectionToDto(Section section);
	
	@InheritInverseConfiguration
	Section dtoToSection(SectionDto sectionDto);
}
