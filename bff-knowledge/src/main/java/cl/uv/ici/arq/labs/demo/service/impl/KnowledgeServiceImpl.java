package cl.uv.ici.arq.labs.demo.service.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import com.google.gson.Gson;

import cl.uv.ici.arq.labs.demo.dtos.KnowledgeRequestDTO;
import cl.uv.ici.arq.labs.demo.dtos.KnowledgeResponseDTO;
import cl.uv.ici.arq.labs.demo.dtos.SkillDTO;
import cl.uv.ici.arq.labs.demo.dtos.UserDTO;
import cl.uv.ici.arq.labs.demo.service.KnowledgeService;
import cl.uv.ici.arq.labs.demo.service.client.CustomSkillClient;
import cl.uv.ici.arq.labs.demo.service.client.SkillClient;
import cl.uv.ici.arq.labs.demo.service.client.UserClient;
import feign.Feign;
import feign.Target;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.extern.slf4j.Slf4j;

@Service("knowledgeService")
@Import(FeignClientsConfiguration.class)
@Slf4j
public class KnowledgeServiceImpl implements KnowledgeService {

	@Autowired
	private UserClient user;

	@Autowired
	private SkillClient skill;
	
	private CustomSkillClient customClient;
	
	

	@Override
	public KnowledgeRequestDTO updateUserSkills(KnowledgeRequestDTO request) {
		
		ResponseEntity<KnowledgeRequestDTO> s = null;
		URI determinedBasePathUri = URI.create("http://localhost:8082/users/knowledge");
		customClient = Feign.builder().encoder(new JacksonEncoder()).decoder(byteArrayResourceDecoder()).target(Target.EmptyTarget.create(CustomSkillClient.class));
		return customClient.saveKnowledge(determinedBasePathUri,request).getBody();
	}

	@Override
	public List<KnowledgeResponseDTO> getKnlowledge() {

		List<KnowledgeResponseDTO> response = new ArrayList<KnowledgeResponseDTO>();
		List<KnowledgeRequestDTO> listUsers = user.getKnowledge().getBody();
		log.info("Response recibido desde /users/knowledge " + new Gson().toJson(listUsers));
		
		
		UserDTO userDTO = new UserDTO();
		List<SkillDTO> listSkills = new ArrayList<SkillDTO>();

		for (KnowledgeRequestDTO knowledgeRequestDTO : listUsers) {

			userDTO = user.findById(knowledgeRequestDTO.getUserId()).getBody();
			log.info("Response recibido desde /users/"+knowledgeRequestDTO.getUserId()+" "+new Gson().toJson(userDTO));
			listSkills = new ArrayList<SkillDTO>();

			for (String skillId : knowledgeRequestDTO.getSkillList()) {

				ResponseEntity<SkillDTO> s = null;
				URI determinedBasePathUri = URI.create("http://localhost:8081/skills/"+skillId);
				
				customClient = Feign.builder().decoder(byteArrayResourceDecoder()).target(Target.EmptyTarget.create(CustomSkillClient.class));
				
				s = customClient.getSkillById(determinedBasePathUri);
				log.info("Response recibido desde /skills/"+skillId+" " +new Gson().toJson( s));
				listSkills.add(s.getBody());
			}

			response.add(new KnowledgeResponseDTO(userDTO, listSkills));
		}

		log.info("Response para Retornar "+new Gson().toJson(response ));
		return response;

	}

	@Override
	public KnowledgeResponseDTO getUserSkills(String userId) {

		KnowledgeResponseDTO response=null;
		
		UserDTO userDTO = user.findById(userId).getBody();
		log.info("Response recibido desde /users/"+userId+" " +new Gson().toJson( userDTO));
		List<SkillDTO> listSkills = new ArrayList<SkillDTO>();
		List<String> skills = user.getKnowledgeByUser(userId).getBody().getSkillList();
		log.info("Response recibido desde /users/"+userId+"/knowledge" +new Gson().toJson( skills));

		for (String skillId : skills) {
			SkillDTO s=skill.getSkillById(skillId).getBody();
			log.info("Response recibido desde /skills/"+skillId+" " +new Gson().toJson( s));
			listSkills.add(s);
		}
		
		response=new KnowledgeResponseDTO(userDTO, listSkills);
		
		log.info("Response para Retornar "+new Gson().toJson(response ));
		return response;
	}
	
	private Decoder byteArrayResourceDecoder() {
        Decoder decoder = (response, type) -> {
            if (type instanceof Class && ByteArrayResource.class.isAssignableFrom((Class) type)) {
                return StreamUtils.copyToByteArray(response.body().asInputStream());
            }
            return new JacksonDecoder().decode(response, type);
        };

        return new ResponseEntityDecoder(decoder);
    }


}
