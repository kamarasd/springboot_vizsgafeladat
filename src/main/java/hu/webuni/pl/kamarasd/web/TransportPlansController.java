package hu.webuni.pl.kamarasd.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import  hu.webuni.pl.kamarasd.dto.TransportPlanDto;
import hu.webuni.pl.kamarasd.mapper.TransportPlanMapper;
import hu.webuni.pl.kamarasd.repository.TransportPlanRepository;
import hu.webuni.pl.kamarasd.service.TransportPlanService;

@RestController
@RequestMapping("/api/transportPlan")
public class TransportPlansController {
	
	@Autowired
	TransportPlanRepository transportPlanRepository;
	
	@Autowired
	TransportPlanMapper transportPlanMapper;
	
	@Autowired
	TransportPlanService transportPlanService;
	
	@PostMapping
	public TransportPlanDto addNewPlan(@RequestBody @Valid TransportPlanDto transportPlanDto) {
		return transportPlanMapper.transportPlanToDto(transportPlanService.addTransportPlan(transportPlanMapper.dtoToTransportPlan(transportPlanDto)));
	}

	@PostMapping("/{id}/delay")
	public void setDelay(@PathVariable long id, @RequestBody TransportPlanDto transportPlanDto) {
		
	}
}
