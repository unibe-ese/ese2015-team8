package ch.unibe.ese.controller.tests;

import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import ch.unibe.ese.model.Lecture;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.Subject;
import ch.unibe.ese.model.University;
import ch.unibe.ese.model.dao.StudentDao;
import ch.unibe.ese.model.dao.SubjectDao;
import ch.unibe.ese.model.dao.UniversityDao;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/config/springMVC.xml",
									"file:src/main/webapp/WEB-INF/config/springData.xml",
									"file:src/main/webapp/WEB-INF/config/springSecurity.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class LectureControllerTest {

	@Autowired private WebApplicationContext wac;
	@Autowired private FilterChainProxy springSecurityFilterChain;
	
	@Autowired StudentDao studentDao;
	@Autowired UniversityDao universityDao;
	@Autowired SubjectDao subjectDao;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).apply(springSecurity(springSecurityFilterChain))
				.build();
	}

	@Test
	public void testRequestMappingForTutor() throws Exception {

		ModelAndView mav = mockMvc.perform(get("/addLecture").with(user("eseTutor").roles("TUTOR"))).andReturn()
				.getModelAndView();

		assertViewName(mav, "addLecture");
	}

	@Test
	public void testRequestMappingForStudent() throws Exception {

		mockMvc.perform(get("/addLecture").with(user("eseStudent").roles("STUDENT")))
				.andExpect(forwardedUrl("/accessDenied"));

	}
	
	
	@Test
	public void invalidNameLectureForm() throws Exception {
	
		mockMvc.perform(post("/addedLecture")
						.param("name", "")).andExpect(status().isOk()).
											andExpect(model().attributeHasFieldErrors("lectureForm", "name"));
	}
	
	@Test
	public void addingALecture() throws Exception {
		
		Student tutor = new Student();
		tutor.setUsername("tutorForTest");
		tutor.setId((long) -1);
		Set<Lecture> lectures = new HashSet<Lecture>();
		tutor.setLectures(lectures);
		tutor = studentDao.save(tutor);
		
		
		University sampleUni = new University();
		sampleUni.setName("testUni");
		sampleUni.setId((long) -1);
		sampleUni = universityDao.save(sampleUni);

		Subject sampleSub = new Subject();
		sampleSub.setName("testSubject");
		sampleSub.setId((long) -1);
		sampleSub = subjectDao.save(sampleSub);
		
		mockMvc.perform(post("/addedLecture").with(user("tutorForTest"))
						.param("name", "Introduction to Testing")
						.param("university", "-1")
						.param("subject", "-1")
						.param("grade", "5.5"));
		
		//assert that lecture was added to our testTutor
		
		assertEquals(1, tutor.getLectures().size());
		
	}
	
	
	
	
}