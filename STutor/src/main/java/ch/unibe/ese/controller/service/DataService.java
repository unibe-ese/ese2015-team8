package ch.unibe.ese.controller.service;


import java.util.List;
import ch.unibe.ese.model.Subject;
import ch.unibe.ese.model.University;

public interface DataService {
		
	public List<University> getAllUniversities();
	
	public List<Subject> getAllSubjects();

	public void initializeUniversities();
	
	public void initializeSubjects();
	
	boolean subjectsAreEmpty();
	
	boolean universitiesAreEmpty();

	List<String> getAllGender();
	
}
