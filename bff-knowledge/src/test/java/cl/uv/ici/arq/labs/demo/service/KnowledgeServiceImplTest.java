package cl.uv.ici.arq.labs.demo.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import cl.uv.ici.arq.labs.demo.dtos.KnowledgeRequestDTO;
import cl.uv.ici.arq.labs.demo.dtos.KnowledgeResponseDTO;
import cl.uv.ici.arq.labs.demo.dtos.SkillDTO;
import cl.uv.ici.arq.labs.demo.dtos.UserDTO;
import cl.uv.ici.arq.labs.demo.service.client.SkillClient;
import cl.uv.ici.arq.labs.demo.service.client.UserClient;
import cl.uv.ici.arq.labs.demo.service.impl.KnowledgeServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = KnowledgeServiceImplTest.class)
public class KnowledgeServiceImplTest {

	@Mock
	private UserClient userClientMock;

	@Mock
	private SkillClient skillClientMock;

	@InjectMocks
	private KnowledgeServiceImpl knowledgeService;

	@BeforeEach
	void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	void updateUserSkillsTest() {
		
		KnowledgeRequestDTO expected = new KnowledgeRequestDTO();
		List<String> list = new ArrayList<String>();
		list.add("8106684c-8652-428e-a80b-f2b8b1476379");
		list.add("b323974e-b413-4776-bcba-52bd3957482c");
		expected.setUserId("16266e63-2978-4a6e-8ff6-451b20fb1a35");
		expected.setSkillList(list);		
		
		
		when(userClientMock.saveKnowledge(expected)).thenReturn(saveKnowledge());
		
		KnowledgeRequestDTO actual = knowledgeService.updateUserSkills(expected);

		Assertions.assertEquals(expected, actual);

		
	}
	
	
	@Test
	void getKnlowledgeTest() {
		String userId = "16266e63-2978-4a6e-8ff6-451b20fb1a35";

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

		List<KnowledgeResponseDTO> expected = new ArrayList<KnowledgeResponseDTO>();
		expected.add(new KnowledgeResponseDTO(user,skills));
		
		
		
		when(userClientMock.getKnowledge()).thenReturn(getKnowledge());
		when(userClientMock.findById(userId)).thenReturn(getUser());
		when(skillClientMock.getSkillById("8106684c-8652-428e-a80b-f2b8b1476379")).thenReturn(getSkillByIdJAVA());
		when(skillClientMock.getSkillById("b323974e-b413-4776-bcba-52bd3957482c")).thenReturn(getSkillByIdAWS());

		List<KnowledgeResponseDTO> actual = knowledgeService.getKnlowledge();

		Assertions.assertEquals(expected, actual);

		
		
	}
	
	
	@Test
	void getUserSkillsTest() {
		String userId = "16266e63-2978-4a6e-8ff6-451b20fb1a35";

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

		KnowledgeResponseDTO expected = new KnowledgeResponseDTO();
		expected.setUser(user);
		expected.setListSkill(skills);

		when(userClientMock.findById(userId)).thenReturn(getUser());
		when(userClientMock.getKnowledgeByUser(userId)).thenReturn(getKnowledgeByUser());
		when(skillClientMock.getSkillById("8106684c-8652-428e-a80b-f2b8b1476379")).thenReturn(getSkillByIdJAVA());
		when(skillClientMock.getSkillById("b323974e-b413-4776-bcba-52bd3957482c")).thenReturn(getSkillByIdAWS());

		KnowledgeResponseDTO actual = knowledgeService.getUserSkills(userId);

		Assertions.assertEquals(expected, actual);

	}
	
	
	ResponseEntity<KnowledgeRequestDTO> saveKnowledge(){
		KnowledgeRequestDTO response = new KnowledgeRequestDTO();
		List<String> list = new ArrayList<String>();
		list.add("8106684c-8652-428e-a80b-f2b8b1476379");
		list.add("b323974e-b413-4776-bcba-52bd3957482c");
		response.setUserId("16266e63-2978-4a6e-8ff6-451b20fb1a35");
		response.setSkillList(list);		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
		
	}

	
	private ResponseEntity<List<KnowledgeRequestDTO>> getKnowledge(){
		List<KnowledgeRequestDTO> response = new ArrayList<KnowledgeRequestDTO>();
		List<String> list = new ArrayList<String>();
		list.add("8106684c-8652-428e-a80b-f2b8b1476379");
		list.add("b323974e-b413-4776-bcba-52bd3957482c");
		response.add(new KnowledgeRequestDTO("16266e63-2978-4a6e-8ff6-451b20fb1a35",list));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	private ResponseEntity<UserDTO> getUser() {
		UserDTO response = new UserDTO();
		response.setUserId("16266e63-2978-4a6e-8ff6-451b20fb1a35");
		response.setFirstName("Carlos");
		response.setLastName("Saez");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private ResponseEntity<KnowledgeRequestDTO> getKnowledgeByUser() {
		KnowledgeRequestDTO response = new KnowledgeRequestDTO();
		List<String> list = new ArrayList<String>();
		list.add("8106684c-8652-428e-a80b-f2b8b1476379");
		list.add("b323974e-b413-4776-bcba-52bd3957482c");
		response.setUserId("16266e63-2978-4a6e-8ff6-451b20fb1a35");
		response.setSkillList(list);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private ResponseEntity<SkillDTO> getSkillByIdAWS() {
		SkillDTO aws = new SkillDTO();
		aws.setSkillId("b323974e-b413-4776-bcba-52bd3957482c");
		aws.setSkillDescription("AWS");
		return new ResponseEntity<>(aws, HttpStatus.OK);
	}

	private ResponseEntity<SkillDTO> getSkillByIdJAVA() {
		SkillDTO java = new SkillDTO();
		java.setSkillId("8106684c-8652-428e-a80b-f2b8b1476379");
		java.setSkillDescription("JAVA");
		return new ResponseEntity<>(java, HttpStatus.OK);
	}
}
