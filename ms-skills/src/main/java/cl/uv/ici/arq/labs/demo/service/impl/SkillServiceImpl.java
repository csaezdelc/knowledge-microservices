package cl.uv.ici.arq.labs.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.uv.ici.arq.labs.demo.dtos.SkillDTO;
import cl.uv.ici.arq.labs.demo.entities.SkillEntity;
import cl.uv.ici.arq.labs.demo.mapper.MapperUtils;
import cl.uv.ici.arq.labs.demo.repository.SkillRepository;
import cl.uv.ici.arq.labs.demo.service.SkillService;

@Service("skillService")
public class SkillServiceImpl implements SkillService{

	@Autowired
	SkillRepository skillRepository;
	
	private SkillEntity mapSkillEntity(SkillDTO skillDTO) {
		SkillEntity skill= new SkillEntity();	
		skill.setSkillDescription(skillDTO.getSkillDescription());		
		return skill;
	}
	
	
	
	@Override
	public SkillDTO createSkill(SkillDTO skillDTO) {
		SkillEntity entity = this.mapSkillEntity(skillDTO);
		entity =this.skillRepository.save(entity);
		skillDTO=(SkillDTO) MapperUtils.map(entity, SkillDTO.class);
		return skillDTO;
	}

	@Override
	public boolean removeSkill(String skillId) {
		boolean delete=true;		
		this.skillRepository.deleteById(UUID.fromString(skillId));		
		return delete;
	}

	@Override
	@SuppressWarnings("unchecked")	
	public List<SkillDTO> getSkills() {
		 return (List<SkillDTO>) MapperUtils.mapAsList(skillRepository.findAll(), new TypeToken<List<SkillDTO>>() {}.getType());
	}
	
	
	@Override
	@SuppressWarnings("unchecked")	
	public List<SkillDTO> getSkills(List<String> skills) {
		List<UUID> skillsList= new ArrayList<UUID>();
		for (String s : skills) {
			skillsList.add(UUID.fromString(s));
		}
		 return (List<SkillDTO>) MapperUtils.mapAsList(skillRepository.findByInventoryIds(skillsList), new TypeToken<List<SkillDTO>>() {}.getType());
	}



	@Override
	public SkillDTO getSkillById(String skillId) {
		return (SkillDTO) MapperUtils.map(this.skillRepository.findById(UUID.fromString(skillId)).get(), SkillDTO.class);
	}
}
