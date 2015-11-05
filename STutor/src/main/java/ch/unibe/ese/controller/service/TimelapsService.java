package ch.unibe.ese.controller.service;

import ch.unibe.ese.controller.pojos.TimelapsForm;
import ch.unibe.ese.model.Student;

/**
 * 
 * @author Christian Zürcher
 * @version 1.0
 * @since 4.11.2015
 */
public interface TimelapsService {
	public TimelapsForm saveFrom(TimelapsForm timelapsForm, Student loggedInTutor);
}
