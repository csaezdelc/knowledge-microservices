package cl.uv.ici.arq.labs.demo.service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cl.uv.ici.arq.labs.demo.dtos.KnowledgeRequestDTO;
import cl.uv.ici.arq.labs.demo.dtos.UserDTO;

@FeignClient(name = "userClient", url = "${rest.endpoints.user.url}")
public interface UserClient {

	@GetMapping("/users")
	public ResponseEntity<List<UserDTO>> findAll();
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<UserDTO> findById(@PathVariable String userId);
	
	@PutMapping("/users/knowledge")
	public ResponseEntity<KnowledgeRequestDTO> saveKnowledge(@RequestBody KnowledgeRequestDTO request);

	@GetMapping("/users/knowledge")
	public ResponseEntity<List<KnowledgeRequestDTO>> getKnowledge();	
	
	@GetMapping("/users/{userId}/knowledge")
	public ResponseEntity<KnowledgeRequestDTO> getKnowledgeByUser(@PathVariable String userId);
}
