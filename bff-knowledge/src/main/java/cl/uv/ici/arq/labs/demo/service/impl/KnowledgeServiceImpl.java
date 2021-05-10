package cl.uv.ici.arq.labs.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.uv.ici.arq.labs.demo.dtos.KnowledgeRequestDTO;
import cl.uv.ici.arq.labs.demo.dtos.KnowledgeResponseDTO;
import cl.uv.ici.arq.labs.demo.dtos.SkillDTO;
import cl.uv.ici.arq.labs.demo.dtos.UserDTO;
import cl.uv.ici.arq.labs.demo.service.KnowledgeService;
import cl.uv.ici.arq.labs.demo.service.client.SkillClient;
import cl.uv.ici.arq.labs.demo.service.client.UserClient;

@Service("knowledgeService")
public class KnowledgeServiceImpl implements KnowledgeService {

	@Autowired
	private UserClient user;

	@Autowired
	private SkillClient skill;

	@Override
	public KnowledgeRequestDTO updateUserSkills(KnowledgeRequestDTO request) {
		return user.saveKnowledge(request).getBody();
	}

	@Override
	public List<KnowledgeResponseDTO> getKnlowledge() {

		List<KnowledgeResponseDTO> response = new ArrayList<KnowledgeResponseDTO>();
		List<KnowledgeRequestDTO> listUsers = user.getKnowledge().getBody();

		UserDTO userDTO = new UserDTO();
		List<SkillDTO> listSkills = new ArrayList<SkillDTO>();

		for (KnowledgeRequestDTO knowledgeRequestDTO : listUsers) {

			userDTO = user.findById(knowledgeRequestDTO.getUserId()).getBody();
			listSkills = new ArrayList<SkillDTO>();

			for (String skillId : knowledgeRequestDTO.getSkillList()) {
				listSkills.add(skill.getSkillById(skillId).getBody());
			}

			response.add(new KnowledgeResponseDTO(userDTO, listSkills));
		}

		return response;

	}

	@Override
	public KnowledgeResponseDTO getUserSkills(String userId) {

		UserDTO userDTO = user.findById(userId).getBody();
		List<SkillDTO> listSkills = new ArrayList<SkillDTO>();
		List<String> skills = user.getKnowledgeByUser(userId).getBody().getSkillList();

		for (String skillId : skills) {
			listSkills.add(skill.getSkillById(skillId).getBody());
		}

		return new KnowledgeResponseDTO(userDTO, listSkills);
	}


}
