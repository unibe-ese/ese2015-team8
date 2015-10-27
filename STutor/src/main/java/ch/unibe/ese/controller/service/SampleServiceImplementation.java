package ch.unibe.ese.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import ch.unibe.ese.controller.exceptions.InvalidDataException;
import ch.unibe.ese.controller.exceptions.InvalidUserException;
import ch.unibe.ese.controller.pojos.*;
import ch.unibe.ese.model.*;
import ch.unibe.ese.model.dao.*;


@Service
public class SampleServiceImplementation implements SampleService {

    @Autowired		StudentDao userDao;
    @Autowired		CommentDao commentDao;
    @Autowired		LectureDao lectureDao;
    @Autowired		TimelapsDao timelapsDao;
    @Autowired		NotificationDao notificationDao;
    
    @Transactional
    public SignupForm saveStudentFrom(SignupForm signupForm) throws InvalidUserException{

        String firstName = signupForm.getFirstName();

        if(StringUtils.isEmpty(firstName)) {
            throw new InvalidUserException("Empty name not valid");   // throw exception
        }
        
        Student user = new Student();
        
        user.setFirstName(signupForm.getFirstName());
        user.setLastName(signupForm.getLastName());
        user.setUsername(signupForm.getUsername());
        user.setEmail(signupForm.getEmail());
        user.setPassword(signupForm.getPassword());
        user.setIsTutor(signupForm.getIsTutor());

        user = userDao.save(user);   // save object to DB
        
        signupForm.setId(user.getId());

        return signupForm;

    }
    
    @Transactional
    public SignupForm saveTutorFrom(SignupForm signupForm, TutorSignupForm tutorSignupForm) throws InvalidUserException{

        String firstName = signupForm.getFirstName();

        if(StringUtils.isEmpty(firstName)) {
            throw new InvalidUserException("Empty name not valid");   // throw exception
        }
        
        Student user = new Student();
        
        user.setFirstName(signupForm.getFirstName());
        user.setLastName(signupForm.getLastName());
        user.setUsername(signupForm.getUsername());
        user.setEmail(signupForm.getEmail());
        user.setPassword(signupForm.getPassword());
        user.setIsTutor(signupForm.getIsTutor());
        user.setGender(tutorSignupForm.getGender());
        

        user = userDao.save(user);   // save object to DB
        
        signupForm.setId(user.getId());

        return signupForm;
    }
    
    @Transactional
    public CommentForm saveFrom(CommentForm commentForm) throws InvalidDataException{
    	Comment comment = new Comment();
    	
    	comment.setComment(commentForm.getComment());
    	comment.setRating(commentForm.getRating());
    	
    	comment = commentDao.save(comment);
    	
    	commentForm.setId(comment.getId());
    	
    	return commentForm;
    }
    
    @Transactional
    public LectureForm saveFrom(LectureForm lectureForm) throws InvalidDataException{
    	Lecture lecture = new Lecture();
    	
    	lecture.setName(lectureForm.getName());
    	lecture.setSubject(lectureForm.getSubject());
    	lecture.setGrade(lectureForm.getGrade());
    	
    	lecture = lectureDao.save(lecture);
    	
    	lectureForm.setId(lecture.getId());
    	
    	return lectureForm;
    }
    
    @Transactional
    public NotificationForm saveFrom(NotificationForm notificationForm) throws InvalidDataException{
    	Notification notification = new Notification();
    	
    	notification.setTitel(notificationForm.getTitel());
    	notification.setMessage(notificationForm.getMessage());
    	notification.setDate(notificationForm.getDate());
    	
    	notification = notificationDao.save(notification);
    	
    	notificationForm.setId(notification.getId());
    	
    	return notificationForm;
    }
    
    @Transactional
    public TimelapsForm saveFrom(TimelapsForm timelapsForm) throws InvalidDataException{
    	Timelaps timelaps = new Timelaps();
    	
    	timelaps.setFromTime(timelapsForm.getFromTime());
    	timelaps.setToTime(timelapsForm.getToTime());
    	
    	timelaps = timelapsDao.save(timelaps);
    	
    	timelapsForm.setId(timelaps.getId());
    	
    	return timelapsForm;
    }
    
    @Transactional
    public AddLectureForm saveTutorLecture(AddLectureForm lectureForm, Student loggedInTutor) throws InvalidUserException{
		
    	long lectureId = lectureForm.getLecture();
    	
    	Lecture chosenLecture = lectureDao.findOne(lectureId);
    	System.err.println("Been here, the chosen lecture is " + chosenLecture.toString());
    	loggedInTutor.addLecture(chosenLecture);
    	lectureDao.save(chosenLecture);
    	userDao.save(loggedInTutor);
    	

    	return lectureForm;
    }
}