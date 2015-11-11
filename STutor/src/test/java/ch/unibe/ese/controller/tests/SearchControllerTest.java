package ch.unibe.ese.controller.tests;

import static org.springframework.test.web.ModelAndViewAssert.assertViewName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.LinkedList;
import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;

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

import ch.unibe.ese.model.Lecture;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.dao.LectureDao;
import ch.unibe.ese.model.dao.StudentDao;

import static org.hamcrest.Matchers.*;




@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/config/springMVC.xml", 
								"file:src/main/webapp/WEB-INF/config/springData.xml",
								"file:src/main/webapp/WEB-INF/config/springSecurity.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class SearchControllerTest {
	
	@Autowired private WebApplicationContext wac;
	@Autowired private FilterChainProxy springSecurityFilterChain;
	@Autowired private StudentDao studentDao;
	@Autowired private LectureDao lectureDao;
	
	private MockMvc mockMvc;

	@Before
	public void setup(){
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).apply(springSecurity(springSecurityFilterChain)).build();
	}

	
	@Test
	public void testRequestMapping() throws Exception{
		ModelAndView mav = mockMvc.perform(get("/basicSearch?q=")).andReturn().getModelAndView();					
		assertViewName(mav, "searchResult");
	}
	
	@Test
	public void testEmptyReults() throws Exception{
		mockMvc.perform(get("/basicSearch?q="))
											.andExpect(model().attributeExists("lectures"))
											.andExpect(model().attribute("lectures", hasSize(0)))
											.andExpect(model().attributeExists("tutors"))
											.andExpect(model().attribute("tutors", hasSize(0)));
		
	}
	
	//test a valid search that returns the created lecture and tutor
	@Test
	public void testGettingReults() throws Exception{
		
		Student tutor = initializeTutor("tutorForTest");
		
		Lecture lecture = initizalieLecture("Introduction to Testing", tutor);
		
	
		//search results are in a list, so let's put our tutor in one for later comparison
		List<Student> ourTutors = new LinkedList<Student>();
		ourTutors.add(tutor);
		
		mockMvc.perform(get("/basicSearch?q="+lecture.getName()))
											.andExpect(model().attributeExists("lectures"))
											.andExpect(model().attribute("lectures", hasSize(1)))
											.andExpect(model().attribute("tutors", hasSize(1)))
											
											//make sure that it is indeed our tutor that has been found
											
											.andExpect(model().attribute("tutors", ourTutors));
		
	}
	
	


	private Student initializeTutor(String username){
		Student tutor = new Student();
		tutor.setUsername(username);
		tutor.setId((long) -1);
		tutor = studentDao.save(tutor);
		
		return tutor;
	}
	
	private Lecture initizalieLecture(String name, Student tutor) {
		Lecture lecture = new Lecture();
		lecture.setName(name);
		lecture.setId((long) -1);
		lecture.setTutor(tutor);

		lecture = lectureDao.save(lecture);
		return lecture;
	}

	
	
	

}