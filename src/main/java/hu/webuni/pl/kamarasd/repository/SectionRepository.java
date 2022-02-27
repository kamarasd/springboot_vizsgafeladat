package hu.webuni.pl.kamarasd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.pl.kamarasd.model.Section;

public interface SectionRepository extends JpaRepository<Section, Long>, JpaSpecificationExecutor<Section>{

	@Query("SELECT s FROM Section s "
			+ "LEFT JOIN FETCH s.transportPlan "
			+ "LEFT JOIN FETCH s.fromMilestone "
			+ "LEFT JOIN FETCH s.toMilestone "
			+ "WHERE (s.toMilestone.id = :mId OR s.fromMilestone.id = :mId) "
			+ "AND s.transportPlan.id = :tPId ORDER BY s.number ASC")
	List<Section> getMilestoneWithId(long tPId, long mId);

}
