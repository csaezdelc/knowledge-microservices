package cl.uv.ici.arq.labs.demo.dtos;

import java.util.List;

import lombok.Data;

@Data
public class KnowledgeResponseDTO {
	

	
	public KnowledgeResponseDTO(UserDTO user, List<SkillDTO> skills) {
		this.user=user;
		this.listSkill=skills;
	}
	
	public KnowledgeResponseDTO() {}
	
	private UserDTO user;
	private List<SkillDTO> listSkill;

}
