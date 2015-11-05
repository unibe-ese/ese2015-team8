package ch.unibe.ese.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * A Notification is an Object attached to a {@link Student}. They can be easily created through the NotificationFactory.
 * <p>It contains:
 * <ul>
 * <li>id (Long), unique and auto generated: it defines the Notification.</li>
 * <li>titel (String), is the title of the Notification.</li>
 * <li>status (String), status of the Notification: New (new), Read (   ), Accepted (_/), Declined (x) .</li>
 * <li>message (String), message shown by the notification.</li>
 * <li>date (Timestamp), time when notification was sent.</li>
 * <li>fromStudentId (Long){@link Student}, helps to backtrack to the {@link Student} who has sent this notification.</li>
 * <li>toStudentId (Long){@link Student}, helps to backtrack to the {@link Student} who should receive this notification.</li>
 * </ul>
 * @author Christian Zürcher
 * @version 1.0
 * @since 28.10.2015
 * @see ch.unibe.ese.model.factory.NotificationFactory
 * @see ch.unibe.ese.controller.NotificationController
 * @see ch.unibe.ese.model.dao.NotificationDao
 */

@Entity
public class Notification {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String titel;
	private String status;
	private String message;
	private Timestamp date;
	private Long fromStudentId;
	private Long toStudentId;
	
	public Long getFromStudentId() {
		return fromStudentId;
	}
	public void setFromStudentId(Long fromStudentId) {
		this.fromStudentId = fromStudentId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public Long getToStudentId() {
		return toStudentId;
	}
	public void setToStudentId(Long toStudentId) {
		this.toStudentId = toStudentId;
	}
}
