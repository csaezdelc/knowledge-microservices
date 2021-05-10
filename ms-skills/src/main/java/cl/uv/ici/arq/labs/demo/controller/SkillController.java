package cl.uv.ici.arq.labs.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.uv.ici.arq.labs.demo.dtos.SkillDTO;
import cl.uv.ici.arq.labs.demo.service.SkillService;

@RestController
@RequestMapping("/skills")

public class SkillController {

	@Autowired
	SkillService service;
		
	@PostMapping
	public ResponseEntity<SkillDTO> save(@RequestBody @Valid SkillDTO skillDTO) {
		return new ResponseEntity<>(service.createSkill(skillDTO), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{skillId}")
	public ResponseEntity<Boolean> deleteById(@PathVariable String skillId) {
		return new ResponseEntity<>(service.removeSkill(skillId), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<SkillDTO>> findAll() {
		return new ResponseEntity<>(service.getSkills(), HttpStatus.OK);
	}
	
	@GetMapping("/{skillId}")
	public ResponseEntity<SkillDTO> getSkillById(@PathVariable String skillId) {
		return new ResponseEntity<>(service.getSkillById(skillId), HttpStatus.OK);
	}

}
