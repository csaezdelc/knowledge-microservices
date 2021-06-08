package cl.uv.ici.arq.labs.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.uv.ici.arq.labs.demo.dtos.KnowledgeRequestDTO;
import cl.uv.ici.arq.labs.demo.dtos.UserDTO;
import cl.uv.ici.arq.labs.demo.entities.KnowledgeEntity;
import cl.uv.ici.arq.labs.demo.entities.UserEntity;
import cl.uv.ici.arq.labs.demo.mapper.MapperUtils;
import cl.uv.ici.arq.labs.demo.repository.KnowledgeRepository;
import cl.uv.ici.arq.labs.demo.repository.UserRepository;
import cl.uv.ici.arq.labs.demo.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	private UserEntity mapUserEntity(UserDTO userDTO) {
		UserEntity user = new UserEntity();
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		return user;
	}

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		UserEntity userEntity = this.mapUserEntity(userDTO);
		userEntity = this.userRepository.save(userEntity);
		userDTO = (UserDTO) MapperUtils.map(userEntity, UserDTO.class);
		return userDTO;
	}

	@Override
	public boolean removeUser(String idUser) {
		boolean delete = true;
		this.userRepository.deleteById(UUID.fromString(idUser));
		return delete;
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO) {
		UserEntity userEntity = this.mapUserEntity(userDTO);
		userEntity.setUserId(UUID.fromString(userDTO.getUserId()));
		userEntity = this.userRepository.save(userEntity);
		userDTO = (UserDTO) MapperUtils.map(userEntity, UserDTO.class);
		return userDTO;
	}

	@Override
	public UserDTO getUser(String idUser) {
		return (UserDTO) MapperUtils
				.map(this.userRepository.findById(UUID.fromString(idUser)).get(), UserDTO.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<UserDTO> getUsers() {
		return (List<UserDTO>) MapperUtils.mapAsList(userRepository.findAll(),
				new TypeToken<List<UserDTO>>() {
				}.getType());
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<UserDTO> findBylastName(String lastName) {
		return (List<UserDTO>) MapperUtils.mapAsList(userRepository.findByLastName(lastName),new TypeToken<List<UserDTO>>() {}.getType());
	}

	@Autowired
	private KnowledgeRepository knowledgeRepository;

	
	@SuppressWarnings("unused")
	private List<String> UUIDConvert(List<UUID> input){
		List<String> out = new ArrayList<String>();
		for(UUID item:input) {
			out.add(item.toString());
		}
		return out;
	}
	
	
	
	@Override
	public List<KnowledgeRequestDTO> getKnlowledge() {

		List<KnowledgeRequestDTO> list = new ArrayList<KnowledgeRequestDTO>();

		for (String uuid : (knowledgeRepository.getUsersId())) {
			list.add(new KnowledgeRequestDTO(uuid.toString(),
					(knowledgeRepository.getSkillsByUserId(uuid))));
		}

		return list;

	}

	@Override
	public KnowledgeRequestDTO updateUserSkills(KnowledgeRequestDTO request) {
		for (String id : request.getSkillList()) {
			knowledgeRepository.save(new KnowledgeEntity(id, request.getUserId()));
		}
		return request;
	}

	@Override
	public KnowledgeRequestDTO getKnowledgeByUser(String userId) {
		return new KnowledgeRequestDTO(userId, (knowledgeRepository.getSkillsByUserId(userId)));
	}

}
