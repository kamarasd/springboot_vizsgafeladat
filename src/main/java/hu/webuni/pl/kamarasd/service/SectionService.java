package hu.webuni.pl.kamarasd.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.pl.kamarasd.model.Section;
import hu.webuni.pl.kamarasd.repository.SectionRepository;

@Service
public class SectionService {
	
	@Autowired
	SectionRepository sectionRepository;

	@Transactional
	public List<Section> getMilestoneWithId(long transportPlanId, long id) {
		return sectionRepository.getMilestoneWithId(transportPlanId, id);
	}

	public List<Section> findAllSections() {
		return sectionRepository.findAll();
	}
	
	@Transactional
	public Section saveSection(Section saveSection) {
		return sectionRepository.save(saveSection);
	} 
	
	public Optional<Section> getById(long id) {
		return sectionRepository.findById(id);
	}
}
