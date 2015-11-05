package ch.unibe.ese.controller.service;

import ch.unibe.ese.controller.exceptions.*;
import ch.unibe.ese.controller.pojos.*;
import ch.unibe.ese.model.Student;

/**
 * 
 * @author Christian Zürcher
 * @version 1.0
 * @since 21.10.2015
 */
public interface SampleService {

    public SignupForm saveStudentFrom(SignupForm signupForm) throws InvalidUserException;
    public SignupForm saveTutorFrom(SignupForm signupForm) throws InvalidUserException;
    public LectureForm saveFrom(LectureForm lectureForm, Student loggedInTutor) throws InvalidDataException;
    public SignupForm modifieUserFrom(SignupForm signupForm, Student acctualStudent) throws InvalidUserException;

}
