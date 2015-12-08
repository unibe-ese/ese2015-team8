package ch.unibe.ese.controller.tests;

import static org.springframework.test.web.ModelAndViewAssert.assertViewName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

import ch.unibe.ese.controller.exceptions.InvalidGradeException;
import ch.unibe.ese.controller.pojos.RefinedSearchForm;
import ch.unibe.ese.model.Lecture;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.Subject;
import ch.unibe.ese.model.University;
import ch.unibe.ese.model.dao.LectureDao;
import ch.unibe.ese.model.dao.StudentDao;
import ch.unibe.ese.model.dao.SubjectDao;
import ch.unibe.ese.model.dao.UniversityDao;

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
	@Autowired private UniversityDao universityDao;
	@Autowired private SubjectDao subjectDao;
	
	private MockMvc mockMvc;
	

	private Student tutor;
	private Lecture lecture;
	private Subject sampleSub;
	private University sampleUni, sampleUni2;

	private String lectureName, subjId, uniId, uni2Id;

	@Before
	public void setup() throws InvalidGradeException{
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).apply(springSecurity(springSecurityFilterChain)).build();
	
		sampleSub = initSubject();
		sampleUni = initUniversity("Testing University", (long) -1);
		sampleUni2 = initUniversity("University of Testing", (long) -2);

		
		tutor = initTutor("tutorForTest");
		lecture = initLecture("Introduction to Testing", tutor) ;
		
		lectureName = lecture.getName();
		subjId = Long.toString(sampleSub.getId());
		uniId = Long.toString(sampleUni.getId());
		uni2Id = Long.toString(sampleUni2.getId());
				
	
	
	
	
	}

	
	@Test
	public void testRequestMapping() throws Exception{
		ModelAndView mav = mockMvc.perform(get("/basicSearch?q=")).andReturn().getModelAndView();					
		assertViewName(mav, "searchResult");
	}
	
	@Test
	public void testMappingRequestWithFilters() throws Exception{
		ModelAndView mav = mockMvc.perform(post("/searchWFilters")
				.param("name", "lecture")
				.param("university", "-1")
				.param("subject", "-1")
				.param("gender", "male")
				.param("minGrade", "0")
				.param("sortBy", "name")
				).andReturn().getModelAndView();
		
		assertViewName(mav, "searchResult");
	}
	
	
	
	@Test
	public void testEmptyResults() throws Exception{
		mockMvc.perform(get("/basicSearch?q="))
											.andExpect(model().attributeExists("lectures"))
											.andExpect(model().attribute("lectures", hasSize(0)))
											.andExpect(model().attributeExists("tutors"))
											.andExpect(model().attribute("tutors", hasSize(0)));
		
	}
	
	//test a valid search that returns the created lecture and tutor
	@Test
	public void testGettingReults() throws Exception{
				
		mockMvc.perform(get("/basicSearch?q="+lecture.getName()))
											.andExpect(model().attributeExists("lectures"))
											.andExpect(model().attribute("lectures", hasSize(1)))
											.andExpect(model().attribute("tutors", hasSize(1)));
		
	}
	
	
	@Test
	public void testEmptySearchResultWFilters() throws Exception {
		
		RefinedSearchForm searchForm = new RefinedSearchForm();
		
		String lectureName = "nonexistentLecture";
		String subjId = Long.toString(sampleSub.getId());
		String uniId = Long.toString(sampleUni.getId());
		searchForm.setUniversity(sampleUni.getId());
		

		//no one teaches that lecture, so there won't be any results
		
		mockMvc.perform(post("/searchWFilters")	.param("name", lectureName)
												.param("gender", "male")
												.param("subject", subjId)
												.param("university",uniId)
												.param("minGrade", "0")
												.param("sortBy", "name"))
		
												.andExpect(model().attribute("lectures", hasSize(0)));
							
	
	}
	
	
	
	@Test
	public void testValidSearchResultWFilters() throws Exception {
		
		
		mockMvc.perform(post("/searchWFilters")	.param("name", lectureName)
												.param("gender", "male")
												.param("subject", subjId)
												.param("university",uniId)
												.param("minGrade", "0")
												.param("sortBy", "name"))
		//now that our tutor teaches that lecture from that uni & subject, we'll see that one result
												.andExpect(model().attribute("lectures", hasSize(1)));		
	}
	
	@Test
	public void testChangingGrade() throws Exception {
		
		//by increasing our minGrade to 5, our tutor is not wanted anymore and disappears from the results:
		
		mockMvc.perform(post("/searchWFilters")	.param("name", lectureName)
												.param("gender", "male")
												.param("subject", subjId)
												.param("university",uniId)
												.param("minGrade", "5.5")
												.param("sortBy", "name"))

												.andExpect(model().attribute("lectures", hasSize(0)));
		
	}
	
	@Test
	public void testChangingUni() throws Exception {
	
		//by changing our university, he'll disappear from the results as well:
		
		mockMvc.perform(post("/searchWFilters")	.param("name", lectureName)
												.param("gender", "male")
												.param("subject", subjId)
												//a different University:
												.param("university",uni2Id)
												.param("sortBy", "name")
												.param("minGrade", "0"))

												.andExpect(model().attribute("lectures", hasSize(0)));							
	
	}
	
		
	
	private Student initTutor(String username){
		Student tutor = new Student();
		tutor.setIsTutor(true);
		tutor.setGender("male");
		tutor.setUsername(username);
		tutor.setId((long) -1);
		tutor = studentDao.save(tutor);
		
		return tutor;
	}
	
	private Lecture initLecture(String name, Student tutor) throws InvalidGradeException {
		Lecture lecture = new Lecture();
		lecture.setName(name);
		lecture.setId((long) -1);
		lecture.setTutor(tutor);
		lecture.setSubject(sampleSub);
		lecture.setUniversity(sampleUni);
		lecture.setGrade(5.0);

		lecture = lectureDao.save(lecture);
		return lecture;
	}

	
	
	private Subject initSubject() {
		Subject sampleSub = new Subject();
		sampleSub.setId((long) -1);
		sampleSub.setName("testSubject");
		sampleSub.setLevel("Bachelor");
		sampleSub = subjectDao.save(sampleSub);
		
		return sampleSub;
		
	}
	
	private University initUniversity(String name, long id) {
		University sampleUni = new University();
		sampleUni.setId(id);
		sampleUni.setName(name);
		sampleUni = universityDao.save(sampleUni);
		
		return sampleUni;
		
	}
	
	

}