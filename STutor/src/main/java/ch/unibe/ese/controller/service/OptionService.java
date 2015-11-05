package ch.unibe.ese.controller.service;

import ch.unibe.ese.controller.exceptions.InvalidUserException;
import ch.unibe.ese.controller.pojos.OptionForm;
import ch.unibe.ese.model.Student;

/**
 * 
 * @author Christian Zürcher
 * @version 1.0
 * @since 4.11.2015
 */
public interface OptionService {
	public OptionForm getFrom(Student student) throws InvalidUserException;
	public OptionForm getFrom(Long studentId);
	public OptionForm getFrom(String username);
	
	public OptionForm saveTutorFrom(Student student, OptionForm signupForm, boolean hasChangedPassword) throws InvalidUserException;
}
