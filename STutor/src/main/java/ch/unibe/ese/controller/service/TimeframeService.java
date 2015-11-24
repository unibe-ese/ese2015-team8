package ch.unibe.ese.controller.service;

import ch.unibe.ese.controller.pojos.TimeframeForm;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.Timeframe;

/**
 * 
 * @author ESE Team 8
 * @version 1.0
 * @since 4.11.2015
 */
public interface TimeframeService {
	public Student saveFrom(TimeframeForm timelapsForm, Student loggedInTutor);
	
	public Timeframe editFrom(TimeframeForm timeframeForm, long timeframeId);
	
	public Timeframe findTimeframeById(long timeframeId);

	public void remove(Timeframe timeframe, Student tutor);
	
}
