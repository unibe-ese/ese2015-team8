package ch.unibe.ese.model.factory;

import java.sql.Timestamp;
import java.util.Date;

import ch.unibe.ese.model.Notification;

public class NotificationFactory {
	
	public static Notification getContactNotification(String senderEMail, String lecture, Long receverId){
		Notification temp = new Notification();
		temp.setTitel("Contact Request");
		temp.setStatus("new");
		temp.setMessage("A Student wants to contact you for the "+lecture+" lecture. Do you accept this?");
		temp.setDate(new Timestamp(new Date().getTime()));
		temp.setFromEMail(senderEMail);
		temp.setToStudentId(receverId);
		
		return temp;
	}
}