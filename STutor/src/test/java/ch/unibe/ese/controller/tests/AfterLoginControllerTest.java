package ch.unibe.ese.controller.tests;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;
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
import org.springframework.web.servlet.ModelAndView;

import ch.unibe.ese.controller.service.NotificationService;
import ch.unibe.ese.controller.service.StudentSearchService;
import ch.unibe.ese.model.Notification;
import ch.unibe.ese.model.Student;





@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/config/springMVC.xml", 
								"file:src/main/webapp/WEB-INF/config/springData.xml",
								"file:src/main/webapp/WEB-INF/config/springSecurity.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AfterLoginControllerTest {
	
	@Autowired private WebApplicationContext wac;
	@Autowired private FilterChainProxy springSecurityFilterChain;
	@Autowired private NotificationService notificationService;
	@Autowired private StudentSearchService studentSearchService;

	private MockMvc mockMvc;
	private Student loggedInStudent;
	
	
	@Before
	public void setup(){
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).apply(springSecurity(springSecurityFilterChain)).build();
		
		loggedInStudent = new Student();
		loggedInStudent.setUsername("loggedIn");
		loggedInStudent.setId(1L);
		loggedInStudent = studentSearchService.saveStudentIntoDB(loggedInStudent);
		
		
		Set<Notification> notifications = new HashSet<Notification>();
		Notification welcome = new Notification();
		welcome.setStatus("new");
		welcome.setToStudentId(loggedInStudent.getId());
		notifications.add(welcome);
		notificationService.modifie(welcome);
		
		loggedInStudent.setNotifications(notifications);
	
	}

	
	@Test
	public void testRequestMapping() throws Exception{

		ModelAndView mav = mockMvc.perform(get("/afterLogin").with(user("loggedIn").roles("STUDENT"))).andReturn().getModelAndView();
						
		assertViewName(mav, "main");
	}
	
	@Test
	public void testNotificationModelAttribute() throws Exception{

		//we've added an unread welcome notification to the user. It should show him that he has one new:
		
		mockMvc.perform(get("/afterLogin").with(user("loggedIn").roles("STUDENT")))
									.andExpect(model().attribute("notificationNumber", 1L));
						
	
	}

}