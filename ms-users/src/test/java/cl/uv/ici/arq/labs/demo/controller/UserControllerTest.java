package cl.uv.ici.arq.labs.demo.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cl.uv.ici.arq.labs.demo.dtos.UserDTO;
import cl.uv.ici.arq.labs.demo.service.UserService;


@SpringBootTest(classes = UserControllerTest.class)
public class UserControllerTest {

	@InjectMocks
    private UserController userController;
	
	@Mock
    private UserService userService;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testFindBylastName() {
    	
    	String lastName = "Saez Del Canto";   	
    	
    	UserDTO user= new UserDTO();
    	user.setFirstName("Carlos Felipe");
    	user.setLastName(lastName);
    	List<UserDTO> userList = new ArrayList<UserDTO>();
    	userList.add(user);
    	ResponseEntity<List<UserDTO>> expected = new ResponseEntity<List<UserDTO>>(userList, HttpStatus.OK);  
    	
    	when(userService.findBylastName(lastName)).thenReturn(userList);    	
    	ResponseEntity<?> actual = userController.findBylastName(lastName);
    	
    	Assertions.assertEquals(expected, actual);
    }
    
    @Test
    public void testfindBylastNameNoContent(){
    	
    	String lastName = "Saez Del Canto";   	
    	List<UserDTO> userList = new ArrayList<UserDTO>();
           	
    	ResponseEntity<List<UserDTO>> expected = new ResponseEntity<List<UserDTO>>(userList, HttpStatus.NO_CONTENT);    	
    	when(userService.findBylastName(lastName)).thenReturn(userList);    	
    	ResponseEntity<?> actual = userController.findBylastName(lastName);
    	
    	Assertions.assertEquals(expected, actual);
    }
    
}
