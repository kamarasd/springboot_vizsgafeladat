package hu.webuni.pl.kamarasd.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.pl.kamarasd.dto.SectionDto;
import hu.webuni.pl.kamarasd.mapper.SectionMapper;
import hu.webuni.pl.kamarasd.model.Section;
import hu.webuni.pl.kamarasd.repository.SectionRepository;

@RestController
@RequestMapping("/api/section")
public class SectionController {
	
	@Autowired
	SectionMapper sectionMapper;
	
	@Autowired
	SectionRepository sectionRepository;

	@PostMapping
	public SectionDto addNewSection(@RequestBody SectionDto sectionDto) {
		return sectionMapper.sectionToDto(sectionRepository.save(sectionMapper.dtoToSection(sectionDto)));
	}
	
	@GetMapping
	public List<SectionDto> getSections() {
		return sectionMapper.sectionToDtos(sectionRepository.findAll());
	}
}

