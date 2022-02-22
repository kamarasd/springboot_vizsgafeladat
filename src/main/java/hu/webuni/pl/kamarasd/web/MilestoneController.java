package hu.webuni.pl.kamarasd.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.pl.kamarasd.dto.MilestoneDto;
import hu.webuni.pl.kamarasd.mapper.MilestoneMapper;
import hu.webuni.pl.kamarasd.model.Milestone;
import hu.webuni.pl.kamarasd.repository.MilestoneRepository;

@RestController
@RequestMapping("/api/milestone")
public class MilestoneController {
	
	@Autowired
	MilestoneMapper milestoneMapper;
	
	@Autowired
	MilestoneRepository milestoneRepository;

	@PostMapping
	public MilestoneDto addNewMilestone(@RequestBody MilestoneDto milestoneDto) {
		Milestone milestone = milestoneRepository.save(milestoneMapper.dtoToMilestone(milestoneDto));
		return milestoneMapper.milestoneToDto(milestone);
	}
	
	@GetMapping
	public List<MilestoneDto> getMilestones() {
		List<MilestoneDto> milestone = milestoneMapper.milestoneToDtos(milestoneRepository.findAll());
		return milestone;
	}
}
