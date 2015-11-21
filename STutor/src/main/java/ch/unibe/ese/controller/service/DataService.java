package ch.unibe.ese.controller.service;


import java.util.List;

import ch.unibe.ese.model.Notification;
import ch.unibe.ese.model.Subject;
import ch.unibe.ese.model.Timeframe;
import ch.unibe.ese.model.University;

public interface DataService {
		
	public List<University> getAllUniversities();
	
	public List<Subject> getAllSubjects();

	public void initializeUniversities();
	
	public void initializeSubjects();
	
	public Notification saveNotification(Notification notificiation);
	
	boolean subjectsAreEmpty();
	
	boolean universitiesAreEmpty();


	
}
