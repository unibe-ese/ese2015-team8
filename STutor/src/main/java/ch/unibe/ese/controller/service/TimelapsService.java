package ch.unibe.ese.controller.service;

import ch.unibe.ese.controller.pojos.TimelapsForm;
import ch.unibe.ese.model.Student;

public interface TimelapsService {
	public TimelapsForm saveFrom(TimelapsForm timelapsForm, Student loggedInTutor);
}
