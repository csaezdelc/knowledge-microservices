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
@Table(name = "KNOWLEDGE")
public class KnowledgeEntity {

	public KnowledgeEntity(String skill, String user) {
		this.skillId = skill;
		this.userId = user;
	}

	@Id
	@Column(name = "ID", nullable = false, columnDefinition = "BINARY(16)")
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private UUID relationId;

	@Column(name = "SKILL_ID")
	private String skillId;

	@Column(name = "USER_ID")
	private String userId;

}
