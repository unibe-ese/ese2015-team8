package org.sample.controller.service;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.pojos.TutorSignupForm;

public interface SampleService {

    public SignupForm saveStudentFrom(SignupForm signupForm) throws InvalidUserException;
    public SignupForm saveTutorFrom(SignupForm signupForm, TutorSignupForm tutorSignupForm) throws InvalidUserException;
}
