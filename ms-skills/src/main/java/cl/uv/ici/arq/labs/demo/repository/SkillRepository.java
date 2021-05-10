package cl.uv.ici.arq.labs.demo.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.uv.ici.arq.labs.demo.entities.SkillEntity;


@Repository("skillRepository")
public interface SkillRepository extends JpaRepository<SkillEntity, UUID> {

	@Query( value ="SELECT * FROM SKILL WHERE ID IN ( :ids )" , nativeQuery = true)
	List<SkillEntity> findByInventoryIds(List<UUID> ids);
	
	
}