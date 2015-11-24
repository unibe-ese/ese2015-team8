package ch.unibe.ese.controller.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.unibe.ese.model.Notification;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.dao.NotificationDao;
import ch.unibe.ese.model.dao.StudentDao;

@Service("NotificationService")
public class NotificationServiceImplementation implements NotificationService{

	@Autowired NotificationDao notificationDao;
	@Autowired StudentDao studentDao;
	
	@Transactional
	public Notification saveNotification(Notification notification) {
		notification = notificationDao.save(notification);
		return notification;
	}

	public List<Notification> getNotificationsByStudentId(Long id) {
		
		return notificationDao.getByToStudentId(id);
	}

	
	public Notification findById(long id) {
		return notificationDao.findOne(id);
	}

	@Transactional
	public void remove(Notification chosenNotification, Student loggedInTutor) {
		LinkedList<Notification> before = new LinkedList<Notification>(loggedInTutor.getNotifications());
		loggedInTutor.getNotifications().remove(chosenNotification);
		loggedInTutor = studentDao.save(loggedInTutor);
		notificationDao.delete(chosenNotification);
		LinkedList<Notification> after = new LinkedList<Notification>(loggedInTutor.getNotifications());
		before = before;
	}
}
