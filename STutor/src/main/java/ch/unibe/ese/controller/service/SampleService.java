package ch.unibe.ese.controller.service;

import ch.unibe.ese.controller.exceptions.*;
import ch.unibe.ese.controller.pojos.*;

public interface SampleService {

    public SignupForm saveStudentFrom(SignupForm signupForm) throws InvalidUserException;
    public SignupForm saveTutorFrom(SignupForm signupForm, TutorSignupForm tutorSignupForm) throws InvalidUserException;
    public CommentForm saveFrom(CommentForm commentForm) throws InvalidDataException;
    public LectureForm saveFrom(LectureForm lectureForm) throws InvalidDataException;
    public NotificationForm saveFrom(NotificationForm notificationForm) throws InvalidDataException;
    public TimelapsForm saveFrom(TimelapsForm timelapsForm) throws InvalidDataException;
}
