package cl.uv.ici.arq.labs.demo.service;

import java.util.List;

import cl.uv.ici.arq.labs.demo.dtos.SkillDTO;

public interface SkillService {

	public SkillDTO createSkill(SkillDTO skill);
	public boolean removeSkill(String skillId);
	public List<SkillDTO> getSkills();
	public List<SkillDTO> getSkills(List<String> skills);
	public SkillDTO getSkillById(String skillId);

}
