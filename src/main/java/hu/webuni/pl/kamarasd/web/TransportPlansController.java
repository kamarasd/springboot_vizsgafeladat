package hu.webuni.pl.kamarasd.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import  hu.webuni.pl.kamarasd.dto.TransportPlanDto;

@RestController
@RequestMapping("/api/transportPlans")
public class TransportPlansController {

	@PostMapping("/{id}/delay")
	public void setDelay(@PathVariable long id, @RequestBody TransportPlanDto transportPlanDto) {
		
	}
}
