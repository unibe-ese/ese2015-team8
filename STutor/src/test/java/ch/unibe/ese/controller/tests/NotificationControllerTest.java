package ch.unibe.ese.controller.tests;

import static org.springframework.test.web.ModelAndViewAssert.assertViewName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;



import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
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

import ch.unibe.ese.controller.pojos.OptionForm;
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
public class NotificationControllerTest {
	
	@Autowired private WebApplicationContext wac;
	@Autowired private FilterChainProxy springSecurityFilterChain;
	@Autowired StudentDao studentDao;
	
	private Student student, tutor, tutor2;
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
		tutor.setIsTutor(false);

		tutor.setId((long) -2);

		tutor = studentDao.save(this.tutor);
		
		
		tutor2 = new Student();
		tutor2.setId((long) -10);
		tutor2 = studentDao.save(this.tutor2);
		
		
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).apply(springSecurity(springSecurityFilterChain)).build();
	}

	
	@Test
	public void testRequestMapping() throws Exception{
		
		ModelAndView mav = mockMvc.perform(get("/notifications?userId="+tutor.getId()).
											with(user("tutorForTest"))).andReturn().getModelAndView();
						
		assertViewName(mav, "notifications");
		
		//trying to access notifications of a different tutor
		ModelAndView mavDenied = mockMvc.perform(get("/notifications?userId="+tutor2.getId()).
				with(user(tutor.getUsername()))).andReturn().getModelAndView();

		assertViewName(mavDenied, "accessDenied");
	}
	

	
	
	

}