package hu.webuni.pl.kamarasd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.pl.kamarasd.dto.SectionDto;
import hu.webuni.pl.kamarasd.mapper.SectionMapper;
import hu.webuni.pl.kamarasd.service.SectionService;

@RestController
@RequestMapping("/api/section")
public class SectionController {
	
	@Autowired
	SectionMapper sectionMapper;
	
	@Autowired
	SectionService sectionService;

	@PostMapping
	public SectionDto addNewSection(@RequestBody SectionDto sectionDto) {
		return sectionMapper.sectionToDto(sectionService.saveSection(sectionMapper.dtoToSection(sectionDto)));
	}
}

