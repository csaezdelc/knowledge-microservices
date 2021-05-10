package cl.uv.ici.arq.labs.demo.entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "SKILL")
public class SkillEntity {

    public SkillEntity(String uuidString,String description) {		
		this.skillDescription=description;
		this.skillId=UUID.fromString(uuidString);
	}
    
    public SkillEntity() {		
		
	}

	

	@Id
    @Column(name = "ID", nullable = false,columnDefinition = "BINARY(16)")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID  skillId;	

    @Column(name = "SKILL", nullable = false)
    private String skillDescription;



}
