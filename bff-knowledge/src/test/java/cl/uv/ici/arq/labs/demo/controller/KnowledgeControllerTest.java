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

import cl.uv.ici.arq.labs.demo.dtos.KnowledgeRequestDTO;
import cl.uv.ici.arq.labs.demo.dtos.KnowledgeResponseDTO;
import cl.uv.ici.arq.labs.demo.dtos.SkillDTO;
import cl.uv.ici.arq.labs.demo.dtos.UserDTO;
import cl.uv.ici.arq.labs.demo.service.KnowledgeService;


@SpringBootTest(classes = KnowledgeControllerTest.class)
public class KnowledgeControllerTest {

	@InjectMocks
    private KnowledgeController knowledgeController;
	
	@Mock
    private KnowledgeService knowledgeService;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void saveTest() {    	

    	KnowledgeRequestDTO request = new KnowledgeRequestDTO();
		List<String> list = new ArrayList<String>();
		list.add("8106684c-8652-428e-a80b-f2b8b1476379");
		list.add("b323974e-b413-4776-bcba-52bd3957482c");
		request.setUserId("16266e63-2978-4a6e-8ff6-451b20fb1a35");
		request.setSkillList(list);		
    	    	
    	ResponseEntity<KnowledgeRequestDTO> expected = new ResponseEntity<KnowledgeRequestDTO>(request, HttpStatus.CREATED);  
    	
    	when(knowledgeService.updateUserSkills(request)).thenReturn(request);    	
    	ResponseEntity<?> actual = knowledgeController.save(request);
    	
    	Assertions.assertEquals(expected, actual);
    }
    
    @Test
    public void getbyUserIdTest() {
    	
    	String userId="16266e63-2978-4a6e-8ff6-451b20fb1a35";
    	UserDTO user = new UserDTO();
		user.setUserId(userId);
		user.setFirstName("Carlos");
		user.setLastName("Saez");

		List<SkillDTO> skills = new ArrayList<SkillDTO>();
		SkillDTO java = new SkillDTO();
		java.setSkillId("8106684c-8652-428e-a80b-f2b8b1476379");
		java.setSkillDescription("JAVA");
		SkillDTO aws = new SkillDTO();
		aws.setSkillId("b323974e-b413-4776-bcba-52bd3957482c");
		aws.setSkillDescription("AWS");
		skills.add(java);
		skills.add(aws);
		
		KnowledgeResponseDTO response = new KnowledgeResponseDTO(user,skills);
		
		ResponseEntity<KnowledgeResponseDTO> expected = new ResponseEntity<KnowledgeResponseDTO>(response, HttpStatus.OK); 
    	

    	when(knowledgeService.getUserSkills(userId)).thenReturn(response);    	
    	ResponseEntity<?> actual = knowledgeController.getbyUserId(userId);
    	
    	Assertions.assertEquals(expected, actual);
    	
    }
    
    
    @Test
    public void getAllTest() {
    	
    	
    	UserDTO user = new UserDTO();
		user.setUserId("16266e63-2978-4a6e-8ff6-451b20fb1a35");
		user.setFirstName("Carlos");
		user.setLastName("Saez");

		List<SkillDTO> skills = new ArrayList<SkillDTO>();
		SkillDTO java = new SkillDTO();
		java.setSkillId("8106684c-8652-428e-a80b-f2b8b1476379");
		java.setSkillDescription("JAVA");
		SkillDTO aws = new SkillDTO();
		aws.setSkillId("b323974e-b413-4776-bcba-52bd3957482c");
		aws.setSkillDescription("AWS");
		skills.add(java);
		skills.add(aws);
		
		List<KnowledgeResponseDTO> response = new ArrayList<KnowledgeResponseDTO>();
		response.add(new KnowledgeResponseDTO(user,skills));
		
		ResponseEntity<List<KnowledgeResponseDTO>> expected = new ResponseEntity<List<KnowledgeResponseDTO>>(response, HttpStatus.OK); 
    	

    	when(knowledgeService.getKnlowledge()).thenReturn(response);    	
    	ResponseEntity<?> actual = knowledgeController.getAll();
    	
    	Assertions.assertEquals(expected, actual);
    	
    }
    
}
