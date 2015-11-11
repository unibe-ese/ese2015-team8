package ch.unibe.ese.controller.tests;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;
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

import ch.unibe.ese.model.Notification;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.dao.NotificationDao;
import ch.unibe.ese.model.dao.StudentDao;

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
	StudentDao studentDao;
	@Autowired
	NotificationDao notificationDao;

	private Student student, tutor, tutor2;
	private Notification notification;
	private MockMvc mockMvc;

	@Before
	public void setup() {

		student = initStudent();
		tutor = initTutor();

		tutor2 = new Student();
		tutor2.setId((long) -10);
		tutor2 = studentDao.save(this.tutor2);

		notification = initNotification();

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).apply(springSecurity(springSecurityFilterChain))
				.build();
	}

	@Test
	public void testRequestMapping() throws Exception {

		ModelAndView mav = mockMvc.perform(get("/notifications?userId=" + tutor.getId()).with(user("tutorForTest")))
				.andReturn().getModelAndView();

		assertViewName(mav, "notifications");

		// trying to access notifications of a different tutor shouldn't be
		// possible
		ModelAndView mavDenied = mockMvc
				.perform(get("/notifications?userId=" + tutor2.getId()).with(user(tutor.getUsername()))).andReturn()
				.getModelAndView();

		assertViewName(mavDenied, "accessDenied");
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

		student = studentDao.save(this.student);

		return student;
	}

	private Student initTutor() {
		tutor = new Student();
		tutor.setFirstName("firstTutor");
		tutor.setLastName("lastTutor");
		tutor.setUsername("tutorForTest");
		tutor.setPassword("1234");
		tutor.setEmail("tut@test.com");
		tutor.setIsTutor(false);

		tutor.setId((long) -2);

		tutor = studentDao.save(this.tutor);
		return tutor;
	}
	
	
	private Notification initNotification(){
		
		notification = new Notification();
		notification.setDate(Timestamp.from(Instant.now()));
		notification.setFromStudentId((long) -1);
		notification.setMessage("Testing notification");
		notification.setTitel("Notification!");
		notification.setId((long) -1);
		notification = notificationDao.save(notification);
		
		
		return notification;
		
	}

}