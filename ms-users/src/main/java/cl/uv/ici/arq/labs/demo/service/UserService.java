package cl.uv.ici.arq.labs.demo.service;

import java.util.List;

import cl.uv.ici.arq.labs.demo.dtos.KnowledgeRequestDTO;
import cl.uv.ici.arq.labs.demo.dtos.UserDTO;

public interface UserService {

	public UserDTO createUser(UserDTO user);
	public boolean removeUser(String idUser);
	public List<UserDTO> getUsers();
	public UserDTO updateUser(UserDTO user);
	public UserDTO getUser(String userId);
	public List<UserDTO> findBylastName(String lastName);
	
	public KnowledgeRequestDTO updateUserSkills(KnowledgeRequestDTO request);
	public List<KnowledgeRequestDTO> getKnlowledge();
	public KnowledgeRequestDTO getKnowledgeByUser(String userId);
	
	

}
