package cl.uv.ici.arq.labs.demo.dtos;

import java.io.Serializable;

import lombok.Data;

@Data
public class SkillDTO implements Serializable {

	private String skillId;
	private String skillDescription;

}
