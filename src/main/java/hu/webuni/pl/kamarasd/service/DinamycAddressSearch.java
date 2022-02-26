package hu.webuni.pl.kamarasd.service;

import org.springframework.data.jpa.domain.Specification;

import hu.webuni.pl.kamarasd.model.Address;
import hu.webuni.pl.kamarasd.model.Address_;

public class DinamycAddressSearch {

	public static Specification<Address> findByCity(String city) {
		return (root, cq, cb) -> cb.like(cb.lower(root.get(Address_.city)), city.toLowerCase() + "%");
	}

	public static Specification<Address> findByCountry(String country) {
		return (root, cq, cb) -> cb.equal(root.get(Address_.country), country.toUpperCase());
	}

	public static Specification<Address> findByPostal(String postal) {
		return (root, cq, cb) -> cb.equal(root.get(Address_.postal), postal);
	}

	public static Specification<Address> findByStreet(String street) {
		return (root, cq, cb) -> cb.like(cb.lower(root.get(Address_.street)), "%" + street.toLowerCase() + "%");
	}

}
