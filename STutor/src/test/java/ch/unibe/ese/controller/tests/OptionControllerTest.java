package ch.unibe.ese.controller.tests;

import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.dao.StudentDao;



@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/config/springMVC.xml", 
								"file:src/main/webapp/WEB-INF/config/springData.xml",
								"file:src/main/webapp/WEB-INF/config/springSecurity.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class OptionControllerTest {
	
	@Autowired private WebApplicationContext wac;
	@Autowired private FilterChainProxy springSecurityFilterChain;
	@Autowired StudentDao studentDao;
	
	private Student student;
	private MockMvc mockMvc;

	@Before
	public void setup(){
		
		student = initStudent();
		
		
		
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).apply(springSecurity(springSecurityFilterChain)).build();
	}

	
	@Test
	public void testRequestMapping() throws Exception{
		


		ModelAndView mav = mockMvc.perform(get("/options").with(user("studentForTest"))).andReturn().getModelAndView();
						
		assertViewName(mav, "options");
	}
	
	@Test
	public void testValidForm() throws Exception{

		mockMvc.perform(get("/options").with(user("studentForTest")))
										.andExpect(model().attributeHasNoErrors("optionForm"));
						
	}
	
	@Test
	public void testSavingChanges() throws Exception{
		//let's change the email of our student:

		String differentEmail = "totallydifferent@email.com";
		mockMvc.perform(post("/optionsSaved").with(user("studentForTest"))
										.param("firstName", student.getFirstName())
										.param("lastName", student.getFirstName())
										.param("username", student.getUsername())
										.param("password", "")
										.param("email", differentEmail)
										.param("isTutor", "false"));
		
		assertEquals(differentEmail, student.getEmail());
						
	}
	
	
	
	
	private Student initStudent(){
		student = new Student();
		student.setFirstName("first");
		student.setLastName("last");
		student.setUsername("studentForTest");
		student.setPassword("1234");
		student.setEmail("st@test.com");
		student.setIsTutor(false);

		student.setId((long) -1);

		student = studentDao.save(this.student);
		
		return student;
		
		
	}
	
	

}