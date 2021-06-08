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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import cl.uv.ici.arq.labs.demo.dtos.KnowledgeRequestDTO;
import cl.uv.ici.arq.labs.demo.dtos.UserDTO;
import cl.uv.ici.arq.labs.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

	@Autowired
	UserService service;

	@PostMapping
	public ResponseEntity<UserDTO> save(@RequestBody @Valid UserDTO userDTO) {
		log.info("Request recibido " + new Gson().toJson(userDTO));
		return new ResponseEntity<>(service.createUser(userDTO), HttpStatus.CREATED);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> findById(@PathVariable String userId) {
		return new ResponseEntity<>(service.getUser(userId), HttpStatus.OK);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Boolean> deleteById(@PathVariable String userId) {
		return new ResponseEntity<>(service.removeUser(userId), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		return new ResponseEntity<>(service.getUsers(), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<UserDTO> update(@RequestBody @Valid UserDTO userDTO) {
		log.info("Request recibido " + new Gson().toJson(userDTO));
		return new ResponseEntity<>(service.updateUser(userDTO), HttpStatus.OK);
	}

	@GetMapping("/custom")
	public ResponseEntity<List<UserDTO>> findBylastName(@RequestParam String lastName) {
		List<UserDTO> userList = service.findBylastName(lastName);
		return new ResponseEntity<>(userList, userList.size()>0?HttpStatus.OK:HttpStatus.NO_CONTENT);
	}

	@PutMapping("/knowledge")
	public ResponseEntity<KnowledgeRequestDTO> saveKnowledge(
			@RequestBody KnowledgeRequestDTO request) {
		log.info("Request recibido " + new Gson().toJson(request));
		return new ResponseEntity<>(service.updateUserSkills(request), HttpStatus.CREATED);
	}

	@GetMapping("/knowledge")
	public ResponseEntity<List<KnowledgeRequestDTO>> getKnowledge() {
		return new ResponseEntity<>(service.getKnlowledge(), HttpStatus.OK);
	}

	@GetMapping("/{userId}/knowledge")
	public ResponseEntity<KnowledgeRequestDTO> getKnowledgeByUser(@PathVariable String userId) {
		return new ResponseEntity<>(service.getKnowledgeByUser(userId), HttpStatus.OK);
	}
}
