package hu.webuni.pl.kamarasd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.pl.kamarasd.model.TransportPlan;

public interface TransportPlanRepository extends JpaRepository<TransportPlan, Long> {
	
	@Query("SELECT t FROM TransportPlan t LEFT JOIN FETCH t.section WHERE t.id = :transportPlanId")
	TransportPlan findTransportById(long transportPlanId);

}
