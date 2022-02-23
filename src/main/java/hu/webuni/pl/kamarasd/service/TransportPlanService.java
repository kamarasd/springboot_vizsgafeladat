package hu.webuni.pl.kamarasd.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

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
		
		TransportPlan tPlan = transportPlanRepository.getById(transportPlanId);
		Milestone milestone = milestoneRepository.getById(delay.getMilestoneId());
		
		if(tPlan == null) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		
		if(milestone == null) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		
		List<Section> section = sectionService.getMilestoneWithId(transportPlanId, milestone.getId());
		
		if(section.get(0) == null) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}
		
		if(section.get(0).getFromMilestone().equals(milestone)) {
			Milestone toMilestone = section.get(0).getToMilestone();
			toMilestone.setPlannedTime(toMilestone.getPlannedTime().plusMinutes(delay.getDelay()));
			section.get(0).setToMilestone(toMilestone);
			
			milestoneRepository.save(toMilestone);
			
		} else if(section.get(0).getToMilestone().equals(milestone)) {
			
			List<Section> plannedSection = tPlan.getSection();
			if(plannedSection.size() > section.get(0).getNumber()) {
				
			}
			
		}
		
		if(delay.getDelay() <= config.getDecrase().getThirtyMin()) {
			tPlan.setIncome(this.changeIncome(tPlan.getIncome(), config.getDecrase().getFivep()));	
			
		} else if(delay.getDelay() <= config.getDecrase().getSixtyMin()) {
			tPlan.setIncome(this.changeIncome(tPlan.getIncome(), config.getDecrase().getTenp()));
			
		} else {
			tPlan.setIncome(this.changeIncome(tPlan.getIncome(), config.getDecrase().getFifteenp()));
		} 
		
		return tPlan;
	}
	
	public int changeIncome(int originalIncome, int changePercent) {
		return (int) originalIncome - (originalIncome * (changePercent / 100));
	}
	
	@Transactional
	public TransportPlan addTransportPlan(TransportPlan transportPlan) {
		return transportPlanRepository.save(transportPlan);
	}

}
