package cl.uv.ici.arq.labs.demo.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.uv.ici.arq.labs.demo.dtos.SkillDTO;

@FeignClient(name = "skillClient", url = "${rest.endpoints.skills.url}")
public interface SkillClient {

	@GetMapping("/skills/{skillId}")
	public ResponseEntity<SkillDTO> getSkillById(@PathVariable String skillId);
	
	
}
