package ch.unibe.ese.model.factory;

import java.sql.Timestamp;
import java.util.Date;

import ch.unibe.ese.model.Notification;
import ch.unibe.ese.model.Student;

public class NotificationFactory {
	
	public static Notification getContactNotification(Long senderId, String lecture, Long receverId){
		Notification temp = new Notification();
		temp.setTitel("Contact Request");
		temp.setStatus("new");
		temp.setMessage("A Student wants to contact you for the "+lecture+" lecture. Do you accept this?");
		temp.setDate(new Timestamp(new Date().getTime()));
		temp.setFromStudentId(senderId);
		temp.setToStudentId(receverId);
		
		return temp;
	}
	
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