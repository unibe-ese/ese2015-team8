package ch.unibe.ese.controller.pojos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Form to set time ranges for tutor. He can set the 
 * day and from when to when.
 * @author ESE Team 8
 * @version 1.0
 * @since 4.11.2015
 */
public class TimelapsForm {

	private Long id;
	
	@Min(0)
	@Max(24)
	private int fromTime;
	
	@Min(0)
	@Max(24)
	private int toTime;
	
	@Min(1)
	@Max(7)
	private int day;
	
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
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
}
