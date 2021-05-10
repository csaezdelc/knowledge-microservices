package cl.uv.ici.arq.labs.demo.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.uv.ici.arq.labs.demo.entities.UserEntity;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    @Query(value = "SELECT * FROM USUARIO", nativeQuery = true)
    public List<UserEntity> getAll();
    
    @Query(value = "SELECT * FROM USUARIO WHERE APELLIDOS LIKE CONCAT('%',:lastName,'%')", nativeQuery = true)
    public List<UserEntity> findByLastName(String lastName);
}