package ch.unibe.ese.controller.service;


import java.util.List;
import ch.unibe.ese.model.Subject;
import ch.unibe.ese.model.University;

/**
 * @author Till
 * This Service provides some necessary data. If the data is not present in the db 
 * (e.g. no universities) it initializes some data (standard Suisse universities set).
 */
public interface DataService {
		
	public List<University> getAllUniversities();
	
	public List<Subject> getAllSubjects();

	public void initializeUniversities();
	
	public void initializeSubjects();
	
	boolean subjectsAreEmpty();
	
	boolean universitiesAreEmpty();

	List<String> getAllGender();
	
}
