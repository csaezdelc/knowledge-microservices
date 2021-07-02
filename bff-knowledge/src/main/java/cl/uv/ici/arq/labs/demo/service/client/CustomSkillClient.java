package cl.uv.ici.arq.labs.demo.service.client;

import java.net.URI;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cl.uv.ici.arq.labs.demo.dtos.KnowledgeRequestDTO;
import cl.uv.ici.arq.labs.demo.dtos.SkillDTO;
import feign.Headers;
import feign.RequestLine;

@FeignClient(name = "customSkillClient")
public interface CustomSkillClient {

	//@GetMapping("/skills/{skillId}")
	@RequestLine("GET")
	@Headers("Content-Type: application/json")
	public ResponseEntity<SkillDTO> getSkillById(URI baseUri);
	
	//@PutMapping("/users/knowledge")
	@RequestLine("PUT")
	@Headers("Content-Type: application/json")
	public ResponseEntity<KnowledgeRequestDTO> saveKnowledge(URI baseUri,@RequestBody KnowledgeRequestDTO request);
}
