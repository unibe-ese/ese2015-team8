package ch.unibe.ese.controller.service.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.unibe.ese.controller.service.NotificationService;
import ch.unibe.ese.model.Notification;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.dao.NotificationDao;
import ch.unibe.ese.model.dao.StudentDao;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

//71% coverage, as simple Dao requests have not been tested (unnecessary here)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/test.xml"})
public class NotificationServiceTest {

	
	private Student receiver;
	private Student sender;
	private Notification testNotification;
	
	@Autowired private StudentDao studentDao;
	@Autowired private NotificationDao notificationDao;
	@Autowired private NotificationService notificationService;

	
	
	@Before
	public void setUp() throws Exception {
		
		Set<Notification> emptySet = new HashSet<Notification>();
		
		receiver = new Student();
		receiver.setId(1L);
		receiver.setNotifications(emptySet);
		when(studentDao.findOne(1L)).thenReturn(receiver);		
		
		
		
		sender = new Student();
		sender.setId(2L);
		sender.setNotifications(emptySet);
		when(studentDao.findOne(2L)).thenReturn(sender);
		
		when(studentDao.save(any(Student.class))).then(returnsFirstArg());

		
		testNotification = new Notification();
		testNotification.setId(3L);
		testNotification.setFromStudentId(sender.getId());
		testNotification.setToStudentId(receiver.getId());
		testNotification.setStatus("new");
	}

	@Test
	public void getNotificationToReceiver() {
		
		notificationService.saveNotificationToStudent(testNotification);
		
		assertTrue(receiver.getNotifications().contains(testNotification));
		
	}
	
	@Test
	public void numberofUnreadNotification(){
		
		//add this unread notification to the receiver
		receiver.addNotification(testNotification);		
		
		
		long unreadNotification = 0L;
		
		for (Notification notification : receiver.getNotifications()) {
			if(notification.getStatus().contains("new")){
				unreadNotification++;
			}
		}
		
		
		when(notificationDao.countByToStudentIdAndStatus(receiver.getId(), "new")).thenReturn(unreadNotification);

		long unreadNotifications = notificationService.numberOfUnreadNotifications(receiver);
		
		
		
		
		//so now he has one unread notification:
		assertEquals(1L, unreadNotifications);
	}
	
	@Test
	public void removeNotification(){
		
		//let's remove our notification, which means that the corresponding student will not have it anymore
		
		notificationService.remove(testNotification, receiver);
		
		assertTrue(receiver.getNotifications().isEmpty());
		
	}
	

}
