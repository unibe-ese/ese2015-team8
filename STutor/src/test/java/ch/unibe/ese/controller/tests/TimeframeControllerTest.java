package ch.unibe.ese.controller.tests;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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

import ch.unibe.ese.controller.service.TimeframeService;
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
public class TimeframeControllerTest {
	@Autowired private WebApplicationContext wac;
	@Autowired private FilterChainProxy springSecurityFilterChain;
	@Autowired TimeframeService timeframeService;
	@Autowired StudentDao studentDao;
	
	private MockMvc mockMvc;
	private Student tutor1;
	
	@Before
	public void setupData(){
		tutor1 = new Student();
		initTutor();
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).apply(springSecurity(springSecurityFilterChain)).build();
	}
	
	private void initTutor(){
		tutor1 = new Student();
		tutor1.setUsername("tutor1");
		tutor1.setIsTutor(true);
		tutor1.setId((long) -1);
		
		tutor1 = studentDao.save(this.tutor1);
	}
	
	@Test
	public void testRequestMappingAddTimeframe() throws Exception{
		ModelAndView mav = mockMvc.perform(get("/addTimeframe").with(user("tutor1"))).andReturn().getModelAndView();				
		assertViewName(mav, "addTimeframe");
	}
}
