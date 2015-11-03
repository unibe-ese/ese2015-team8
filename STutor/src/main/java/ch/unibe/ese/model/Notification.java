package ch.unibe.ese.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Christian
 *
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
