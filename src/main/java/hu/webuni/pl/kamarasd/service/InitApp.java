package hu.webuni.pl.kamarasd.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import hu.webuni.pl.kamarasd.model.Address;
import hu.webuni.pl.kamarasd.model.DemoUser;
import hu.webuni.pl.kamarasd.model.Milestone;
import hu.webuni.pl.kamarasd.model.Section;
import hu.webuni.pl.kamarasd.model.TransportPlan;
import hu.webuni.pl.kamarasd.repository.AddressRepository;
import hu.webuni.pl.kamarasd.repository.MilestoneRepository;
import hu.webuni.pl.kamarasd.repository.SectionRepository;
import hu.webuni.pl.kamarasd.repository.TransportPlanRepository;

@Service
public class InitApp {
	
	@Autowired
	TransportPlanRepository transportPlanRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	MilestoneRepository milestoneRepository;
	
	@Autowired
	SectionRepository sectionRepository;
	
	public DemoUser createDemoUser(String username) {
		Map<String, DemoUser> demoUser = new HashMap<String, DemoUser>();

		demoUser.put("user1", new DemoUser(1L, "user1", new BCryptPasswordEncoder().encode("pass1"), "AddressManager"));
		demoUser.put("user2", new DemoUser(2L, "user2", new BCryptPasswordEncoder().encode("pass2"), "TransportManager"));
		demoUser.put("admin", new DemoUser(3L, "admin", new BCryptPasswordEncoder().encode("pass3"), "admin"));
		
		return demoUser.get(username) != null ?  demoUser.get(username) : null;
	} 
	
	public void createDemoData() {
		TransportPlan tPlan1 = transportPlanRepository.save(new TransportPlan(1L, 15000, null));
		
		Address addr1 = addressRepository.save(new Address("HU", "Budapest", "Gadagréti út", "1112", "54", 47.476103, 18.987353));
		Address addr2 = addressRepository.save(new Address("HU", "Adony", "Kossuth Lajos utca", "2457", "2", 47.121161, 18.863167));
		Address addr3 = addressRepository.save(new Address("HU", "Hatvan", "Csaba utca", "3000", "13", 47.666122, 19.680265));
		Address addr4 = addressRepository.save(new Address("HU", "Bátonyterenye", "Zöldfa út", "3070", "17", 47.987226, 19.835582));
		
		Milestone m1 = milestoneRepository.save(new Milestone(1L, addr1, LocalDateTime.parse("2022-02-25T08:00:00")));
		Milestone m2 = milestoneRepository.save(new Milestone(2L, addr2, LocalDateTime.parse("2022-02-25T13:00:00")));
		Milestone m3 = milestoneRepository.save(new Milestone(3L, addr3, LocalDateTime.parse("2022-02-26T12:00:00")));
		Milestone m4 = milestoneRepository.save(new Milestone(4L, addr4, LocalDateTime.parse("2022-02-26T15:00:00")));
		
		sectionRepository.save(new Section(1L, m1, m2, 1, tPlan1));
		sectionRepository.save(new Section(2L, m3, m4, 2, tPlan1));
	}
	
}
