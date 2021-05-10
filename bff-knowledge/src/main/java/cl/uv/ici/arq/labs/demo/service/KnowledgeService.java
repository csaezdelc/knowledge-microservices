package cl.uv.ici.arq.labs.demo.service;

import java.util.List;

import cl.uv.ici.arq.labs.demo.dtos.KnowledgeRequestDTO;
import cl.uv.ici.arq.labs.demo.dtos.KnowledgeResponseDTO;

public interface KnowledgeService {

	public KnowledgeRequestDTO updateUserSkills(KnowledgeRequestDTO request);

	public List<KnowledgeResponseDTO> getKnlowledge();

	public KnowledgeResponseDTO getUserSkills(String userId);

}
