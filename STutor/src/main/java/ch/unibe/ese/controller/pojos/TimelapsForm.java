package ch.unibe.ese.controller.pojos;

import java.sql.Timestamp;

public class TimelapsForm {

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
}
