package ch.unibe.ese.model.factory;

import java.sql.Timestamp;
import java.util.Date;

import ch.unibe.ese.model.Notification;
import ch.unibe.ese.model.Student;

/**
 * Factory for handling four different kinds of Notifications:
 * Contact Request, Request Declined, Request Accepted, Student Info
 * @author ESE Team 8
 * @version 1.0
 * @since 28.10.2015
 */
public class NotificationFactory {
	
	/**
	 * When a Student wants to contact a Tutor, he has to send a request.
	 * The Tutor then gets this "Contact Request" Notification, which asks him
	 * for acceptation and also adds a time stamp
	 * @param senderId
	 * @param lecture
	 * @param receverId
	 * @return the Notification
	 */
	public static Notification getContactNotification(Long senderId, String lecture, Long receverId){
		Notification temp = new Notification();
		temp.setTitel("Contact Request");
		temp.setStatus("new");
		temp.setMessage("A Student wants to contact you. Do you accept this?");
		temp.setDate(new Timestamp(new Date().getTime()));
		temp.setFromStudentId(senderId);
		temp.setToStudentId(receverId);
		
		return temp;
	}
	
	/**
	 * When the Tutor gets the Notification "Contact Request" he can choose to decline,
	 * which the Student gets notified of. He gets a "Request Declined" Notification with
	 * a time stamp.
	 * @param tutor
	 * @param receverId
	 * @return the Notification
	 */
	public static Notification getDeclineNotification(Student tutor, Long receverId){
		Notification temp = new Notification();
		temp.setTitel("Request Declined");
		temp.setStatus("new");
		temp.setMessage("The Tutor "+tutor.getUsername()+" declined your request.");
		temp.setDate(new Timestamp(new Date().getTime()));
		temp.setFromStudentId(tutor.getId());
		temp.setToStudentId(receverId);
		
		return temp;
	}
	
	/**
	 * When the Tutor gets the Notification "Contact Request" he can choose to accept,
	 * which the Student gets notified of. He gets a "Request Accepted" Notification with
	 * a time stamp.
	 * @param tutor
	 * @param receverId
	 * @return the Notification
	 */
	public static Notification getAcceptNotification(Student tutor, Long receverId){
		Notification temp = new Notification();
		temp.setTitel("Request Accepted");
		temp.setStatus("new");
		temp.setMessage("The Tutor "+tutor.getUsername()+" accepted your request.");
		temp.setDate(new Timestamp(new Date().getTime()));
		temp.setFromStudentId(tutor.getId());
		temp.setToStudentId(receverId);
		
		return temp;
	}

	/**
	 * When the Tutor has paid his fee, he gets the Student's contact information.
	 * That's what this "Student Infos" Notification is about. It gives the Tutor the Student's 
	 * email address for further contact and a time stamp.
	 * @param student
	 * @param tutorId
	 * @return the Notification
	 */
	public static Notification getStudentContactDetails(Student student, Long tutorId) {
		Notification temp = new Notification();
		temp.setTitel("Student Infos");
		temp.setStatus("new");
		temp.setMessage("The Students "+student.getUsername()+"'s E-Mail is: " + student.getEmail());
		temp.setDate(new Timestamp(new Date().getTime()));
		temp.setFromStudentId(student.getId());
		temp.setToStudentId(tutorId);
		return temp;
	}
}