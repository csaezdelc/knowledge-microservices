package cl.uv.ici.arq.labs.demo.dtos;

import java.util.List;

import lombok.Data;

@Data
public class KnowledgeRequestDTO {

	public KnowledgeRequestDTO() {

	}

	public KnowledgeRequestDTO(String userId, List<String> skillList) {
		super();
		this.userId = userId;
		this.skillList = skillList;
	}

	private String userId;
	private List<String> skillList;
}
