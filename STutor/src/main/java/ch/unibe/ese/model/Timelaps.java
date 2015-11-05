package ch.unibe.ese.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import ch.unibe.ese.admin.AdminData;

/**
 * A Timelaps is an Object attached to a {@link Student} (isTutor=true).
 * <p>The Timelaps defined the time between fromTime and toTime. here only the day and the hour of the Timestamp matter. For the day, each day corresponds to a day in the week (1=Monday,...).
 * <p>It contains:
 * <ul>
 * <li>id (Long), unique and auto generated: it defines the Timelaps.</li>
 * <li>fromTime (Timestamp), start of the timelaps.</li>
 * <li>toTime (Timestamp), end of the timelaps.</li>
 * </ul>
 * @author Christian Zürcher
 * @version 1.0
 * @since 4.11.2015
 * @see ch.unibe.ese.controller.TimelapsController
 * @see ch.unibe.ese.controller.service.TimelapsServiceImplementation
 * @see ch.unibe.ese.controller.pojos.TimelapsForm
 * @see ch.unibe.ese.model.dao.TimelapsDao
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
