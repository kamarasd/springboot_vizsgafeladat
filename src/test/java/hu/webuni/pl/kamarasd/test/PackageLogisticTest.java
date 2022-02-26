package hu.webuni.pl.kamarasd.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import hu.webuni.pl.kamarasd.dto.AddressDto;
import hu.webuni.pl.kamarasd.dto.DelayDto;
import hu.webuni.pl.kamarasd.dto.LoginDto;
import hu.webuni.pl.kamarasd.dto.SectionDto;
import hu.webuni.pl.kamarasd.dto.TransportPlanDto;
import hu.webuni.pl.kamarasd.mapper.TransportPlanMapper;
import hu.webuni.pl.kamarasd.model.Address;
import hu.webuni.pl.kamarasd.model.Milestone;
import hu.webuni.pl.kamarasd.model.Section;
import hu.webuni.pl.kamarasd.model.TransportPlan;
import hu.webuni.pl.kamarasd.repository.AddressRepository;
import hu.webuni.pl.kamarasd.repository.MilestoneRepository;
import hu.webuni.pl.kamarasd.repository.SectionRepository;
import hu.webuni.pl.kamarasd.repository.TransportPlanRepository;
import hu.webuni.pl.kamarasd.service.InitApp;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PackageLogisticTest {

	private static final String TPLAN_URI = "/api/transportPlan/";
	private static final String LOGIN_URI = "/api/login";
	private static final String ADDRESS_URI = "/api/addresses";
	
	@Autowired
	InitApp initApp;
	
	@Autowired
	TransportPlanRepository transportPlanRepository;
	
	@Autowired
	MilestoneRepository milestoneRepository;
	
	@Autowired
	SectionRepository sectionRepository;
	
	@Autowired
	AddressRepository addressRepository; 
	
	@Autowired
	WebTestClient webTestClient;
	
	@Autowired
	TransportPlanMapper transportPlanMapper;
	
	@BeforeEach
	public void clearDb() {
		sectionRepository.deleteAll();
		milestoneRepository.deleteAll();
		addressRepository.deleteAll();
		transportPlanRepository.deleteAll();
	}
	
	@Test
	public void testDelayChange() {
		TransportPlan tPlan1 = createTplan(1L, 15000, null);
		TransportPlan tPlan2 = createTplan(2L, 30000, null);
		
		Address addr1 = createAddress("HU", "Budapest", "Gadagréti út", "1112", "54", 47.476103, 18.987353);
		Address addr2 = createAddress("HU", "Adony", "Kossuth Lajos utca", "2457", "2", 47.121161, 18.863167);
		Address addr3 = createAddress("HU", "Hatvan", "Csaba utca", "3000", "13", 47.666122, 19.680265);
		
		Milestone m1 = createMilestone(1L, addr1, "2022-02-25T08:00:00");
		Milestone m2 = createMilestone(2L, addr2, "2022-02-25T13:00:00");
		Milestone m3 = createMilestone(3L, addr3, "2022-02-26T12:00:00");
		
		Section s1 = createSection(1L, m1, m2, 1, tPlan1);
		
		LoginDto login = new LoginDto("user2", "pass2");
		DelayDto delayDto = new DelayDto(m1.getId(), 15);
		
		String token = getToken(login);
		
		TransportPlanDto delayedTplan = addDelayToTransportPlan(tPlan1.getId(), delayDto, token);
		
		assertThat(delayedTplan.getIncome())
			.isNotEqualTo(tPlan1.getIncome());
		assertThat(delayedTplan.getSection().get(0).getToMilestone().getPlannedTime())
			.isNotEqualTo(m1.getPlannedTime());	
		assertThat(delayedTplan.getSection().get(0).getToMilestone().getPlannedTime())
			.isEqualTo(LocalDateTime.parse("2022-02-25T13:15:00"));
		assertThat(delayedTplan.getSection().get(0).getFromMilestone().getPlannedTime())
			.isNotEqualTo(m2.getPlannedTime());
		assertThat(delayedTplan.getSection().get(0).getFromMilestone().getPlannedTime())
			.isEqualTo(LocalDateTime.parse("2022-02-25T08:15:00"));
	}
	
	@Test
	public void testDelayChangeOnSecondPlan() {
		TransportPlan tPlan1 = createTplan(1L, 15000, null);
		TransportPlan tPlan2 = createTplan(2L, 30000, null);
		
		Address addr1 = createAddress("HU", "Budapest", "Gadagréti út", "1112", "54", 47.476103, 18.987353);
		Address addr2 = createAddress("HU", "Adony", "Kossuth Lajos utca", "2457", "2", 47.121161, 18.863167);
		Address addr3 = createAddress("HU", "Hatvan", "Csaba utca", "3000", "13", 47.666122, 19.680265);
		Address addr4 = createAddress("HU", "Gödöllő", "Cserkész köz", "2100", "1", 47.600267, 19.351794);
		
		Milestone m1 = createMilestone(1L, addr1, "2022-02-25T08:00:00");
		Milestone m2 = createMilestone(2L, addr2, "2022-02-25T13:00:00");
		Milestone m3 = createMilestone(3L, addr3, "2022-02-26T12:00:00");
		Milestone m4 = createMilestone(4L, addr4, "2022-02-26T15:00:00");
		
		Section s1 = createSection(1L, m1, m2, 1, tPlan1);
		Section s2 = createSection(2L, m3, m4, 2, tPlan1);
		
		LoginDto login = new LoginDto("user2", "pass2");
		DelayDto delayDto = new DelayDto(m2.getId(), 30);
		
		String token = getToken(login);
		
		TransportPlanDto delayedTplan = addDelayToTransportPlan(tPlan1.getId(), delayDto, token);
		
		assertThat(delayedTplan.getIncome())
			.isNotEqualTo(tPlan1.getIncome());
		assertThat(delayedTplan.getSection().get(1).getFromMilestone().getPlannedTime())
			.isNotEqualTo(m3.getPlannedTime());
		assertThat(delayedTplan.getSection().get(1).getFromMilestone().getPlannedTime())
			.isEqualTo(LocalDateTime.parse("2022-02-26T12:30"));
		assertThat(delayedTplan.getSection().get(1).getToMilestone().getPlannedTime())
			.isEqualTo(m4.getPlannedTime());
		assertThat(delayedTplan.getSection().get(0).getToMilestone().getPlannedTime())
			.isEqualTo(m2.getPlannedTime());
	}
	
	@Test
	public void testDelayChangeOnSecondPlanLastMilestone() {
		TransportPlan tPlan1 = createTplan(1L, 15000, null);
		TransportPlan tPlan2 = createTplan(2L, 30000, null);
		
		Address addr1 = createAddress("HU", "Budapest", "Gadagréti út", "1112", "54", 47.476103, 18.987353);
		Address addr2 = createAddress("HU", "Adony", "Kossuth Lajos utca", "2457", "2", 47.121161, 18.863167);
		Address addr3 = createAddress("HU", "Hatvan", "Csaba utca", "3000", "13", 47.666122, 19.680265);
		Address addr4 = createAddress("HU", "Gödöllő", "Cserkész köz", "2100", "1", 47.600267, 19.351794);
		
		Milestone m1 = createMilestone(1L, addr1, "2022-02-25T08:00:00");
		Milestone m2 = createMilestone(2L, addr2, "2022-02-25T13:00:00");
		Milestone m3 = createMilestone(3L, addr3, "2022-02-26T12:00:00");
		Milestone m4 = createMilestone(4L, addr4, "2022-02-26T15:00:00");
		
		Section s1 = createSection(1L, m1, m2, 1, tPlan2);
		Section s2 = createSection(2L, m3, m4, 2, tPlan2);
		
		LoginDto login = new LoginDto("user2", "pass2");
		DelayDto delayDto = new DelayDto(m4.getId(), 20);
		
		String token = getToken(login);
		
		TransportPlanDto delayedTplan = addDelayToTransportPlan(tPlan2.getId(), delayDto, token);
		
		assertThat(delayedTplan.getIncome())
			.isNotEqualTo(tPlan1.getIncome());
		assertThat(delayedTplan.getSection().get(0).getFromMilestone().getPlannedTime())
			.isEqualTo(m1.getPlannedTime());
		assertThat(delayedTplan.getSection().get(0).getToMilestone().getPlannedTime())
			.isEqualTo(m2.getPlannedTime());
		assertThat(delayedTplan.getSection().get(1).getFromMilestone().getPlannedTime())
			.isEqualTo(m3.getPlannedTime());
		assertThat(delayedTplan.getSection().get(1).getToMilestone().getPlannedTime())
			.isNotEqualTo(m4.getPlannedTime());
		assertThat(delayedTplan.getSection().get(1).getToMilestone().getPlannedTime())
			.isEqualTo(LocalDateTime.parse("2022-02-26T15:20:00"));
	}
	
	@Test
	public void testBadLogin() {
		LoginDto login = new LoginDto("user4", "pass4");
		
		webTestClient
				.post()
				.uri(LOGIN_URI)
				.bodyValue(login)
				.exchange()
				.expectStatus()
				.isForbidden()
				.expectBody(String.class)
				.returnResult()
				.getResponseBody();
	}
	
	@Test
	public void testForbiddenUsrAddressModification() {
		LoginDto login = new LoginDto("user2", "pass2");
		String token = getToken(login);
		
		webTestClient
				.get()
				.uri(ADDRESS_URI)
				.headers(headers -> headers.setBearerAuth(token))
				.exchange()
				.expectStatus()
				.isForbidden()
				.expectBody(AddressDto.class)
				.returnResult()
				.getResponseBody();
	}
	
	@Test
	public void testForbiddenUsrTransportModification() {
		LoginDto login = new LoginDto("user1", "pass1");
		String token = getToken(login);
		
		webTestClient
				.post()
				.uri(TPLAN_URI+"/1/delay")
				.headers(headers -> headers.setBearerAuth(token))
				.exchange()
				.expectStatus()
				.isForbidden()
				.expectBody(TransportPlanDto.class)
				.returnResult()
				.getResponseBody();
	}
	
	public String getToken(LoginDto loginDto) {
		
		return webTestClient
			.post()
			.uri(LOGIN_URI)
			.bodyValue(loginDto)
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody(String.class)
			.returnResult()
			.getResponseBody();
	}
	
	public TransportPlanDto addDelayToTransportPlan(Long planId, DelayDto delayDto, String token) {

		return webTestClient
			.post()
			.uri(TPLAN_URI + planId + "/delay")
			.bodyValue(delayDto)
			.headers(headers -> headers.setBearerAuth(token))
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody(TransportPlanDto.class)
			.returnResult()
			.getResponseBody();
		
	}
	
	@Transactional
	public TransportPlan createTplan(Long id, int income, Section section) {
		return transportPlanRepository.save(new TransportPlan(id, income, null));
	}
	
	@Transactional
	public Address createAddress(String iso, String city, String street, String postal, String houseno, double gpslat, double gpslong) {
		return addressRepository.save(new Address(iso, city, street, postal, houseno, gpslat, gpslong));
	}
	
	@Transactional
	public Milestone createMilestone(Long id, Address address, String date) {
		return milestoneRepository.save(new Milestone(id, address, LocalDateTime.parse(date)));
	}
	
	@Transactional
	public Section createSection(Long id, Milestone m1, Milestone m2, int planNum, TransportPlan tPlan) {
		return sectionRepository.save(new Section(id, m1, m2, planNum, tPlan));
	}
	


}
