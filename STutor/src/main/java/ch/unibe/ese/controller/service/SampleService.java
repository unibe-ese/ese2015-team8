package ch.unibe.ese.controller.service;

import ch.unibe.ese.controller.exceptions.InvalidUserException;
import ch.unibe.ese.controller.pojos.SignupForm;
import ch.unibe.ese.controller.pojos.TutorSignupForm;

public interface SampleService {

    public SignupForm saveStudentFrom(SignupForm signupForm) throws InvalidUserException;
    public SignupForm saveTutorFrom(SignupForm signupForm, TutorSignupForm tutorSignupForm) throws InvalidUserException;
}
