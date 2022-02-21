package hu.webuni.pl.kamarasd.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import hu.webuni.pl.kamarasd.dto.AddressDto;
import hu.webuni.pl.kamarasd.model.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
	
	List<AddressDto> addressesToDtos(List<Address> addresses);
	
	AddressDto addressToDto(Address address); 
	
	@InheritInverseConfiguration
	Address dtoToAddress(AddressDto addressDto);

}
