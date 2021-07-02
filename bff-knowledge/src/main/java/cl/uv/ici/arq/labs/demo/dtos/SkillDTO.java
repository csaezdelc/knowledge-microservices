package cl.uv.ici.arq.labs.demo.dtos;

import java.io.Serializable;

import lombok.Data;

@Data
public class SkillDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String skillId;
	private String skillDescription;
	
}
