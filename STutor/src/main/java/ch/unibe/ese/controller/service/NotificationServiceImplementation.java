package ch.unibe.ese.controller.service;

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
		//notification = notificationDao.save(notification);
		Student temp = studentDao.findOne(notification.getToStudentId());
		temp.addNotification(notification);
		studentDao.save(temp);
		return notification;
	}

	public List<Notification> getNotificationsByStudentId(Long id) {
		
		return notificationDao.getByToStudentId(id);
	}

	
	public Notification findById(long id) {
		return notificationDao.findOne(id);
	}

	@Transactional
	public Student remove(Notification chosenNotification, Student loggedInTutor) {
		loggedInTutor.getNotifications().remove(chosenNotification);
		loggedInTutor = studentDao.save(loggedInTutor);
		notificationDao.delete(chosenNotification);
		return loggedInTutor;
	}

	@Transactional
	public Long numberOfUnreadNotifications(Student loggedInTutor) {
		return notificationDao.countByToStudentIdAndStatus(loggedInTutor.getId(), "new");
	}
}