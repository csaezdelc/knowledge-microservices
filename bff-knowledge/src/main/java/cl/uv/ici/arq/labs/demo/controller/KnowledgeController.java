package cl.uv.ici.arq.labs.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import cl.uv.ici.arq.labs.demo.dtos.KnowledgeRequestDTO;
import cl.uv.ici.arq.labs.demo.dtos.KnowledgeResponseDTO;
import cl.uv.ici.arq.labs.demo.service.KnowledgeService;
import feign.codec.Decoder;
import feign.codec.Encoder;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/knowledge")
@Slf4j
public class KnowledgeController {

	
	@Autowired
	private KnowledgeService service;
	

		
	@PutMapping
	public ResponseEntity<KnowledgeRequestDTO> save(@RequestBody KnowledgeRequestDTO request) {	
		 log.info("Request recibido " + new Gson().toJson(request));
		return new ResponseEntity<>(service.updateUserSkills(request), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<KnowledgeResponseDTO>> getAll() {		
		return new ResponseEntity<>(service.getKnlowledge(), HttpStatus.OK);
	}

	 @GetMapping("/{userId}")
	public ResponseEntity<KnowledgeResponseDTO> getbyUserId(@PathVariable String userId) {		
		return new ResponseEntity<>(service.getUserSkills(userId), HttpStatus.OK);
	}


	
}
