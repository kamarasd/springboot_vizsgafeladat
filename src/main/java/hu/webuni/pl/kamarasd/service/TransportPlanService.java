package hu.webuni.pl.kamarasd.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.pl.kamarasd.config.TransportPlanConfig;
import hu.webuni.pl.kamarasd.dto.DelayDto;
import hu.webuni.pl.kamarasd.model.Milestone;
import hu.webuni.pl.kamarasd.model.Section;
import hu.webuni.pl.kamarasd.model.TransportPlan;
import hu.webuni.pl.kamarasd.repository.MilestoneRepository;
import hu.webuni.pl.kamarasd.repository.TransportPlanRepository;

@Service
public class TransportPlanService {
	
	@Autowired
	TransportPlanRepository transportPlanRepository;
	
	@Autowired
	MilestoneRepository milestoneRepository;
	
	@Autowired
	SectionService sectionService;
	
	@Autowired
	TransportPlanConfig config;
	
	public TransportPlan setDelay(long transportPlanId, DelayDto delay) {
		TransportPlan tPlan = transportPlanRepository.findById(transportPlanId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		Milestone milestone = milestoneRepository.findById(delay.getMilestoneId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		List<Section> section = sectionService.getMilestoneWithId(transportPlanId, milestone.getId());
		
		if(section.size() == 0 || section == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		if(section.get(0).getFromMilestone().getId() == milestone.getId()) {
			Milestone fromMilestone = section.get(0).getFromMilestone();
			fromMilestone.setPlannedTime(fromMilestone.getPlannedTime().plusMinutes(delay.getDelay()));
			Milestone toMilestone = section.get(0).getToMilestone();
			toMilestone.setPlannedTime(toMilestone.getPlannedTime().plusMinutes(delay.getDelay()));
			
			section.get(0).setFromMilestone(fromMilestone);
			section.get(0).setToMilestone(toMilestone);

			milestoneRepository.save(fromMilestone);
			milestoneRepository.save(toMilestone);
			
		} else if(section.get(0).getToMilestone().getId() == milestone.getId()) {
			List<Section> plannedSection = tPlan.getSection();
			
			if(plannedSection.size() > section.get(0).getNumber()) {
				
				Section nextSection = plannedSection.get(section.get(0).getNumber());
				
				if(plannedSection.get(0).getFromMilestone().getId() == milestone.getId() && nextSection != null) {
					Milestone fromMilestone = nextSection.getFromMilestone();
					fromMilestone.setPlannedTime(fromMilestone.getPlannedTime().plusMinutes(delay.getDelay()));

					milestoneRepository.save(fromMilestone);
				} else {
					Milestone fromMilestone = nextSection.getFromMilestone();
					
					fromMilestone.setPlannedTime(fromMilestone.getPlannedTime().plusMinutes(delay.getDelay()));
					milestoneRepository.save(fromMilestone);
				}
			} else {
				Section actualSection = plannedSection.get(section.get(0).getNumber() - 1);
				Milestone toMilestone = actualSection.getToMilestone();
				
				toMilestone.setPlannedTime(toMilestone.getPlannedTime().plusMinutes(delay.getDelay()));
				milestoneRepository.save(toMilestone);
			}
		}
		
		if(delay.getDelay() <= config.getDecrase().getThirtyMin()) {
			tPlan.setIncome(this.changeIncome(tPlan.getIncome(), config.getDecrase().getFivep()));	
			
		} else if(delay.getDelay() <= config.getDecrase().getSixtyMin()) {
			tPlan.setIncome(this.changeIncome(tPlan.getIncome(), config.getDecrase().getTenp()));
			
		} else {
			tPlan.setIncome(this.changeIncome(tPlan.getIncome(), config.getDecrase().getFifteenp()));
		} 
		
		transportPlanRepository.save(tPlan);
		
		return tPlan;
	}
	
	public int changeIncome(int originalIncome, int changePercent) {
		return (int) originalIncome - (originalIncome * changePercent / 100);
	}
	
	@Transactional
	public TransportPlan addTransportPlan(TransportPlan transportPlan) {
		return transportPlanRepository.save(transportPlan);
	}

}
