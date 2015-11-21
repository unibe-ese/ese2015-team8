package ch.unibe.ese.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import ch.unibe.ese.admin.AdminData;

/**
 * A Timeframe is an Object attached to a {@link Student} (isTutor=true).
 * <p>The Timeframe is defined by the time between fromTime and toTime. Here only the day and the hour of the Timeframe matter. For the day, each day corresponds to a day in the week (1=Monday,...).
 * <p>It contains:
 * <ul>
 * <li>id (Long), unique and auto generated: it defines the Timeframe.</li>
 * <li>fromTime (int*), start of the timeframe.</li>
 * <li>toTime (int*), end of the timeframe.</li>
 * </ul>
 * @author ESE Team 8
 * @version 1.0
 * @since 4.11.2015
 * @see ch.unibe.ese.controller.TimeframeController
 * @see ch.unibe.ese.controller.service.TimeframeServiceImplementation
 * @see ch.unibe.ese.controller.pojos.TimeframeForm
 * @see ch.unibe.ese.model.dao.TimeframeDao
 */

@Entity
public class Timeframe {
	
	@Id
    @GeneratedValue
	private Long id;
	
	private int day;
	private int fromTime;
	private int toTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getDay(){
		return this.day;
	}
	
	public void setDay(int day){
		this.day = day;
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
	
	public String toString(){
		
		return AdminData.getDays().get(getDay()-1)+" from "+(fromTime)+"h to "+(toTime)+"h";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + day;
		result = prime * result + fromTime;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + toTime;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Timeframe other = (Timeframe) obj;
		if (day != other.day)
			return false;
		if (fromTime != other.fromTime)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (toTime != other.toTime)
			return false;
		return true;
	}
	
	
	
}
