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
    @Autowired		LectureDao lectureDao;
    @Autowired		SubjectDao subjectDao;
    @Autowired		UniversityDao universityDao;
    
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
    public LectureForm saveFrom(LectureForm lectureForm, Student loggedInTutor) throws InvalidUserException{
		
    	Lecture chosenLecture = new Lecture();
    	chosenLecture.setName(lectureForm.getName());
    	chosenLecture.setSubject(subjectDao.findOne(lectureForm.getSubject()));
    	chosenLecture.setUniversity(universityDao.findOne(lectureForm.getUniversity()));
    	
    	
    	loggedInTutor.addLecture(chosenLecture);
    	chosenLecture.setTutor(loggedInTutor);
    	lectureDao.save(chosenLecture);
    	userDao.save(loggedInTutor);
    	

    	return lectureForm;
    }


}