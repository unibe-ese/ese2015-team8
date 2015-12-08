package ch.unibe.ese.controller.tests;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.sql.Timestamp;
import java.time.Instant;
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
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;


import ch.unibe.ese.controller.service.NotificationService;
import ch.unibe.ese.controller.service.StudentSearchService;
import ch.unibe.ese.model.Notification;
import ch.unibe.ese.model.Student;


@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/config/springMVC.xml",
		"file:src/main/webapp/WEB-INF/config/springData.xml",
		"file:src/main/webapp/WEB-INF/config/springSecurity.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class NotificationControllerTest {

	@Autowired
	private WebApplicationContext wac;
	@Autowired
	private FilterChainProxy springSecurityFilterChain;
	@Autowired
	StudentSearchService studentSearchService;
	@Autowired
	NotificationService notificationService;

	private Student student, tutor, tutor2;
	private Notification notification;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).apply(springSecurity(springSecurityFilterChain))
				.build();

		student = initStudent();
		tutor = initTutor();		
		tutor2 = initTutor2();
		notification = initNotification();

	
	}

	
	@Test
	public void testRequestMapping() throws Exception {
		ModelAndView mav = mockMvc
				.perform(get("/notifications?userId=" + tutor2.getId()).with(user(tutor2.getUsername()).roles("TUTOR"))).andReturn()
				.getModelAndView();

		assertViewName(mav, "notifications");
	}
	
	@Test
	public void testDeniedAccess() throws Exception {
		
		//let's try to access tutor2's notifications as the user tutor
		ModelAndView mav = mockMvc
				.perform(get("/notifications?userId=" + tutor2.getId()).with(user(tutor.getUsername()).roles("TUTOR"))).andReturn()
				.getModelAndView();

		assertViewName(mav, "accessDenied");
	}
	
	@Test
	public void readNotification() throws Exception {
		
		//empty set for the tutor, where a notification will be added to
		Set<Notification> notifications = new HashSet<Notification>();
		
		tutor.setNotifications(notifications);
		tutor.addNotification(notification);

		//if the tutor opens our created notification he should be able to read exactly that notification:
		mockMvc.perform(get("/readNotification?notificationId=" + notification.getId()).with(user("tutorForTest")))
														.andExpect(model().attribute("notification", notification));
		
				

	}

	private Student initStudent() {
		student = new Student();
		student.setFirstName("first");
		student.setLastName("last");
		student.setUsername("studentForTest");
		student.setPassword("1234");
		student.setEmail("st@test.com");
		student.setIsTutor(false);

		student.setId((long) -1);

		student =  studentSearchService.saveStudentIntoDB(this.student);

		return student;
	}

	private Student initTutor() {
		tutor = new Student();
		tutor.setFirstName("firstTutor");
		tutor.setLastName("lastTutor");
		tutor.setUsername("tutorForTest");
		tutor.setPassword("1234");
		tutor.setEmail("tut@test.com");
		tutor.setIsTutor(true);

		tutor.setId((long) 1);

		tutor = studentSearchService.saveStudentIntoDB(this.tutor);
		return tutor;
	}
	
	private Student initTutor2() {
		tutor2 = new Student();
		tutor2.setFirstName("secondTutor");
		tutor2.setLastName("secondLastTutor");
		tutor2.setUsername("tutor2ForTest");
		tutor2.setPassword("1234");
		tutor2.setEmail("tut2@test2.com");
		tutor2.setIsTutor(true);

		tutor2.setId((long) 2);

		tutor2 = studentSearchService.saveStudentIntoDB(this.tutor2);
		return tutor2;
	}
	
	
	
	private Notification initNotification(){
		
		notification = new Notification();
		notification.setDate(Timestamp.from(Instant.now()));
		notification.setFromStudentId((long) -1);
		notification.setMessage("Testing notification");
		notification.setTitel("Notification!");
		notification.setId((long) 1);
		notification = notificationService.modifie(notification);
		
		
		return notification;
		
	}

}