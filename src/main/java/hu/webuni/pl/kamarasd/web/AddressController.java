package hu.webuni.pl.kamarasd.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.pl.kamarasd.dto.AddressDto;
import hu.webuni.pl.kamarasd.mapper.AddressMapper;
import hu.webuni.pl.kamarasd.model.Address;
import hu.webuni.pl.kamarasd.model.AddressSearch;
import hu.webuni.pl.kamarasd.service.AddressService;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
	
	@Autowired
	AddressMapper addressMapper;
	
	@Autowired
	AddressService addressService;
	
	@GetMapping
	public List<AddressDto> getAll() {
		return addressMapper.addressesToDtos(addressService.getAllAddresses());
	}
	
	@PostMapping
	public ResponseEntity<AddressDto> saveAddress(@RequestBody AddressDto addressDto) {
		if(addressDto == null || addressDto.getId() != null) {
			return ResponseEntity.badRequest().build();
		}
		addressDto.setCountry(addressDto.getCountry().toUpperCase());
		Address address = addressService.saveAddress(addressMapper.dtoToAddress(addressDto));
		return ResponseEntity.ok(addressMapper.addressToDto(address));
	}
	
	@GetMapping("/{id}")
	public AddressDto getAddressById(@PathVariable Long id) {
		Address address = addressService.getAddressById(id).orElseThrow(() -> new  ResponseStatusException(HttpStatus.NOT_FOUND));
		return addressMapper.addressToDto(address);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<AddressDto> deleteAddressById(@PathVariable Long id) {
		addressService.deleteAddressById(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AddressDto> updateAddressById(@PathVariable Long id, @RequestBody AddressDto addressDto) {
		Long dtoId = addressDto.getId();
		if(addressService.getAddressById(id) == null) {
			return ResponseEntity.notFound().build();
		}  
		
		if( (dtoId != null && id != dtoId) || addressDto == null ) {
			return ResponseEntity.badRequest().build();
		} else {
			addressDto.setCountry(addressDto.getCountry().toUpperCase());
			Address address = addressService.saveAddress(addressMapper.dtoToAddress(addressDto));
			return ResponseEntity.ok(addressMapper.addressToDto(address));
		}
		
	}
	
	@GetMapping("/search")
	public List<AddressDto> findAddressByExample(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "0") int size, @RequestParam(defaultValue = "id,asc") String sort,  @RequestBody AddressSearch addressSearch) {
		String[] sortArray;
		sortArray = StringUtils.split(sort, ",");
		Sort sorting;
		
		if(size == 0) {
			size = addressService.countAddresses();
		}
		
		if((sortArray[1].equals("desc") || sortArray[1].contentEquals("desc")) && !sortArray[1].isEmpty()) {
			sorting = Sort.by(sortArray[0]).descending();
		} else {
			sorting = Sort.by(sortArray[0]).ascending();
		}
						
		Pageable pageProperties = PageRequest.of(page, size, sorting);
		
		Page<Address> addressPage = addressService.findAddressByExample(addressSearch, pageProperties);

		List<Address> addresses = addressPage.getContent();
		return addressMapper.addressesToDtos(addresses);
	}
	
	
	

}
