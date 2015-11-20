package ch.unibe.ese.controller.tests;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

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

import ch.unibe.ese.model.Lecture;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.Timelaps;
import ch.unibe.ese.model.dao.StudentDao;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/config/springMVC.xml",
		"file:src/main/webapp/WEB-INF/config/springData.xml",
		"file:src/main/webapp/WEB-INF/config/springSecurity.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ProfileControllerTest {

	@Autowired
	private WebApplicationContext wac;
	@Autowired
	private FilterChainProxy springSecurityFilterChain;
	@Autowired
	private StudentDao studentDao;

	private Student student, tutor;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		
		student = initStudent();
		tutor = initTutor();

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).apply(springSecurity(springSecurityFilterChain))
				.build();
	}

	@Test
	public void testProfileForStudent() throws Exception {
		

		mockMvc.perform(get("/profile?userId="+student.getId()))
							.andExpect(model().attribute("student", this.student))
							//the information (gives lectures, available during which time) shouldn't be visible in  a student's profile
							.andExpect(model().attributeDoesNotExist("lectures"))
							.andExpect(model().attributeDoesNotExist("timelapses"));
	
	}
	
	@Test
	public void testProfileForTutor() throws Exception {
		
	

		mockMvc.perform(get("/profile?userId="+tutor.getId()))
							.andExpect(model().attribute("student", this.tutor))
							.andExpect(model().attributeExists("lectures"))
							.andExpect(model().attributeExists("timelapses"));
	
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

	private Student initTutor(){
		tutor = new Student();
		tutor.setFirstName("first");
		tutor.setLastName("last");
		tutor.setUsername("tutorForTest");
		tutor.setPassword("1234");
		tutor.setEmail("st@test.com");
		
		
		Set<Lecture> lectures = new HashSet<Lecture>();
		tutor.setLectures(lectures);
		
		Set<Timelaps> timelapses = new HashSet<Timelaps>();
		tutor.setTimelapses(timelapses);
		
		tutor.setIsTutor(true);

		tutor.setId((long) -100);

		tutor = studentDao.save(this.tutor);
		
		return tutor;
	}
	
}