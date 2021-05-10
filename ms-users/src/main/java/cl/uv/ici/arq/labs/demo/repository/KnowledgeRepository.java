package cl.uv.ici.arq.labs.demo.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.uv.ici.arq.labs.demo.entities.KnowledgeEntity;


@Repository("knowledgeRepository")
public interface KnowledgeRepository extends JpaRepository<KnowledgeEntity, UUID> {
	
	 @Query(value = "SELECT DISTINCT(USER_ID) FROM KNOWLEDGE", nativeQuery = true)
	    public List<String> getUsersId();
	 @Query(value = "SELECT DISTINCT(SKILL_ID) FROM KNOWLEDGE WHERE USER_ID = :userId", nativeQuery = true)
	    public List<String> getSkillsByUserId(String userId);
	 


	
	
}