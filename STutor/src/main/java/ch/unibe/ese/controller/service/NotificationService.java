package ch.unibe.ese.controller.service;

import java.util.List;

import ch.unibe.ese.model.Notification;
import ch.unibe.ese.model.Student;

public interface NotificationService {
	
	public Notification saveNotification(Notification notificiation);

	public List<Notification> getNotificationsByStudentId(Long id);

	public Notification findById(long id);

	public Student remove(Notification chosenNotification, Student loggedInTutor);
}
