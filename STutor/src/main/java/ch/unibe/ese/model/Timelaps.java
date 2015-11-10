package ch.unibe.ese.model;

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
 * <li>fromTime (int*), start of the timelaps.</li>
 * <li>toTime (int*), end of the timelaps.</li>
 * </ul>
 * <p>(*) the int Code is structured as follow: day(1-7)*100 + hour(0-24)
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
	
	private int fromTime;
	private int toTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getFromTime() {
		return fromTime;
	}
	public void setFromTime(int fromTime) {
		this.fromTime = fromTime;
	}
	public int getToTime() {
		return toTime;
	}
	public void setToTime(int toTime) {
		this.toTime = toTime;
	}
	
	// Special Methods for the int Code (see javadoc) --------------------------------------------------
	
	public int getDay(int intCode){
		return intCode/100;
	}
	
	public int getHour(int intCode){
		return intCode-(intCode/100)*100;
	}
	
	//--------------------------------------------------------------------------------------------------
	
	public String toString(){
		return AdminData.getDays().get(getDay(fromTime)-1)+" from "+(getHour((fromTime)))+"h to "+getHour(toTime)+"h";
	}
}
