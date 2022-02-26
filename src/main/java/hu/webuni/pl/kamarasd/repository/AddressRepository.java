package hu.webuni.pl.kamarasd.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.pl.kamarasd.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>, JpaSpecificationExecutor<Address> {

	@Query("SELECT a FROM Address a WHERE id = :id")
	Optional<Address> getAddressesById(Long id);

}
