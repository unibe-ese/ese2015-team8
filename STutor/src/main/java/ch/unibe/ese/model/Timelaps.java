package ch.unibe.ese.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import ch.unibe.ese.admin.AdminData;

/**
 * @author Christian Zürcher
 * @version 1.0
 * @since 4.11.2015
 */

@Entity
public class Timelaps {
	
	@Id
    @GeneratedValue
	private Long id;
	
	private Timestamp fromTime;
	private Timestamp toTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Timestamp getFromTime() {
		return fromTime;
	}
	public void setFromTime(Timestamp fromTime) {
		this.fromTime = fromTime;
	}
	public Timestamp getToTime() {
		return toTime;
	}
	public void setToTime(Timestamp toTime) {
		this.toTime = toTime;
	}
	
	@SuppressWarnings("deprecation")
	public String toString(){
		return AdminData.getDays().get(fromTime.getDay())+" from "+fromTime.getHours()+"h to "+toTime.getHours()+"h";
	}
}
