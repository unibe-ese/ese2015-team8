package ch.unibe.ese.controller.tests;

import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.HashSet;
import java.util.Set;

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
import ch.unibe.ese.model.Comment;



@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/config/springMVC.xml", 
								"file:src/main/webapp/WEB-INF/config/springData.xml",
								"file:src/main/webapp/WEB-INF/config/springSecurity.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class RatingControllerTest {
	
	@Autowired private WebApplicationContext wac;
	@Autowired private FilterChainProxy springSecurityFilterChain;
	@Autowired StudentDao studentDao;
	
	private Student student, tutor;
	private MockMvc mockMvc;

	@Before
	public void setup(){
		
		student = new Student();
		student.setFirstName("first");
		student.setLastName("last");
		student.setUsername("studentForTest");
		student.setPassword("1234");
		student.setEmail("st@test.com");
		student.setIsTutor(false);

		student.setId((long) -1);

		student = studentDao.save(this.student);
		
		tutor = new Student();
		tutor.setFirstName("firstTutor");
		tutor.setLastName("lastTutor");
		tutor.setUsername("tutorForTest");
		tutor.setPassword("1234");
		tutor.setEmail("tut@test.com");
		tutor.setIsTutor(true);
		Set<Comment> emptycommentsset = new HashSet<Comment>();
		tutor.setComments(emptycommentsset);

		tutor.setId((long) -2);

		tutor = studentDao.save(this.tutor);
		
		
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).apply(springSecurity(springSecurityFilterChain)).build();
	}

	
	@Test
	public void testRequestMapping() throws Exception{
		
		ModelAndView mav = mockMvc.perform(get("/rateTutor?tutorId="+tutor.getId()).with(user("studentForTest"))).andReturn().getModelAndView();
						
		assertViewName(mav, "rateTutor");
	}
	
	@Test
	public void saveRating() throws Exception{
		
		
		mockMvc.perform(get("/rateTutor?tutorId="+tutor.getId()).with(user("studentForTest"))).andReturn().getModelAndView();
		
		mockMvc.perform(post("/saveTutorRating").with(user("studentForTest"))
																.param("rating", "10")
																.param("comment", "fantastic imaginary tutor"));
		
		
		//he received a 10 from the user and a positive comment. Did it save that for the tutor?:
		assertEquals(10, tutor.getComments().iterator().next().getRating());
		assertEquals("fantastic imaginary tutor", tutor.getComments().iterator().next().getComment());
											
		
	

	}
	
	

}