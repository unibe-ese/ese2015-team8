package ch.unibe.ese.controller.service;

import ch.unibe.ese.controller.exceptions.InvalidUserException;
import ch.unibe.ese.controller.pojos.OptionForm;
import ch.unibe.ese.model.Student;

/**
 * 
 * @author ESE Team 8
 * @version 1.0
 * @since 4.11.2015
 * Processes the editing of the user's profile page.
 */
public interface OptionService {
	
	public OptionForm getFrom(Student student) throws InvalidUserException;
	
	public OptionForm getFrom(Long studentId);
	
	public OptionForm getFrom(String username);
	
	public Student saveStudentFrom(Student student, OptionForm optionForm, boolean hasChangedPassword) throws InvalidUserException;
}
