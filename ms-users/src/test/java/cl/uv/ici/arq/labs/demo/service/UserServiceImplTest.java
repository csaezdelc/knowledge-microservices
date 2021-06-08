package cl.uv.ici.arq.labs.demo.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.TypeToken;
import org.springframework.boot.test.context.SpringBootTest;

import cl.uv.ici.arq.labs.demo.dtos.UserDTO;
import cl.uv.ici.arq.labs.demo.entities.UserEntity;
import cl.uv.ici.arq.labs.demo.mapper.MapperUtils;
import cl.uv.ici.arq.labs.demo.repository.UserRepository;
import cl.uv.ici.arq.labs.demo.service.impl.UserServiceImpl;

@SpringBootTest(classes = UserServiceImplTest.class)
public class UserServiceImplTest {

	@InjectMocks
	private UserServiceImpl userService;
	
	@Mock
	private UserRepository userRepository;
	
	@BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testFindBylastName(){
		
		String lastName = "Saez Del Canto";   	
    	
    	UserEntity user= new UserEntity();
    	user.setFirstName("Carlos Felipe");
    	user.setLastName(lastName);
    	user.setUserId(UUID.randomUUID());
    	List<UserEntity> userList = new ArrayList<UserEntity>();
    	userList.add(user);
		
    	List<UserDTO> expected =	(List<UserDTO>) MapperUtils.mapAsList(userList,new TypeToken<List<UserDTO>>() {}.getType());
		when(userRepository.findByLastName(lastName)).thenReturn(userList);
		
		List<UserDTO> actual = userService.findBylastName(lastName);
		
		Assertions.assertEquals(expected, actual);
	}
	
	
	@Test
	void getUsersTest() {
		
		UserEntity user1= new UserEntity();
    	user1.setFirstName("Carlos Felipe");
    	user1.setLastName("Saez Del Canto");
    	user1.setUserId(UUID.randomUUID());
    	
    	UserEntity user2= new UserEntity();
    	user2.setFirstName("Santiago");
    	user2.setLastName("Saez Cordova");
    	user2.setUserId(UUID.randomUUID());
    	
    	List<UserEntity> userList = new ArrayList<UserEntity>();
    	userList.add(user1);
    	userList.add(user2);
		
    	List<UserDTO> expected =	(List<UserDTO>) MapperUtils.mapAsList(userList,new TypeToken<List<UserDTO>>() {}.getType());
		when(userRepository.findAll()).thenReturn(userList);
		
		List<UserDTO> actual = userService.getUsers();
		
		Assertions.assertEquals(expected, actual);
	}
	
}
