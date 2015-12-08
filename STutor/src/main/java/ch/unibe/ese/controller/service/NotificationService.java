package ch.unibe.ese.controller.service;

import java.util.List;

import ch.unibe.ese.model.Notification;
import ch.unibe.ese.model.Student;

/**
 * Handles procession of notifications. A notification belongs to a specific student. It can be 
 * found by the id, be removed etc. Self-explaining methods.
 */
public interface NotificationService {
	
	public Student saveNotificationToStudent(Notification notificiation);

	public List<Notification> getNotificationsByStudentId(Long id);

	public Notification findById(long id);

	public Student remove(Notification chosenNotification, Student loggedInTutor);
	
	public Notification modifie(Notification notification);
	
	public Long numberOfUnreadNotifications(Student loggedInTutor);
}
