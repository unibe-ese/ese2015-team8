package ch.unibe.ese.controller.tests;

import static org.springframework.test.web.ModelAndViewAssert.assertViewName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;




import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

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
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/config/springMVC.xml",
									"file:src/main/webapp/WEB-INF/config/springData.xml",
									"file:src/main/webapp/WEB-INF/config/springSecurity.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AddLectureControllerTest {

	@Autowired
	private WebApplicationContext wac;
	@Autowired
	private FilterChainProxy springSecurityFilterChain;
	
	@Autowired StudentDao studentDao;

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
	
	
	
}