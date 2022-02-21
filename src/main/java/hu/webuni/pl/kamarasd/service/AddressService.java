package hu.webuni.pl.kamarasd.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import hu.webuni.pl.kamarasd.model.Address;
import hu.webuni.pl.kamarasd.model.AddressSearch;
import hu.webuni.pl.kamarasd.repository.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	AddressRepository addressRepository;

	public List<Address> getAllAddresses() {
		return addressRepository.findAll();
	}

	@Transactional
	public Address saveAddress(Address address) {
		return addressRepository.save(address);
	}

	public Optional<Address> getAddressById(Long id) {
		return addressRepository.findById(id);
	}

	public void deleteAddressById(Long id) {
		addressRepository.deleteById(id);
		
	}

	public Page<Address> findAddressByExample(AddressSearch addressSearch, Pageable pageProperties) {
		Specification<Address> spec = Specification.where(null);
		
		if(StringUtils.hasText(addressSearch.getCity())) {
			spec = spec.and(DinamycAddressSearch.findByCity(addressSearch.getCity()));
		}
		
		if(StringUtils.hasText(addressSearch.getCountry())) {
			spec = spec.and(DinamycAddressSearch.findByCountry(addressSearch.getCountry()));
		}
		
		if(StringUtils.hasText(addressSearch.getPostal())) {
			spec = spec.and(DinamycAddressSearch.findByPostal(addressSearch.getPostal()));
		}
		
		if(StringUtils.hasText(addressSearch.getStreet())) {
			spec = spec.and(DinamycAddressSearch.findByStreet(addressSearch.getStreet()));
		}
		
		Page<Address> addressPages = addressRepository.findAll(spec, pageProperties);
		return addressPages;
		
	}

	public int countAddresses() {
		return (int) addressRepository.count();
	}
}
