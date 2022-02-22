package hu.webuni.pl.kamarasd.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import hu.webuni.pl.kamarasd.dto.TransportPlanDto;
import hu.webuni.pl.kamarasd.model.TransportPlan;

@Mapper(componentModel = "spring")
public interface TransportPlanMapper {

	List<TransportPlanDto> trasportPlanToDtos(List<TransportPlan> transportPlan);
	
	TransportPlanDto transportPlanToDto(TransportPlan transportPlan);
	
	@InheritInverseConfiguration
	TransportPlan dtoToTransportPlan(TransportPlanDto transportPlanDto);
}
