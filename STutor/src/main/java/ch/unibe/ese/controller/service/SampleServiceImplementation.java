package ch.unibe.ese.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import ch.unibe.ese.controller.exceptions.InvalidUserException;
import ch.unibe.ese.controller.pojos.LectureForm;
import ch.unibe.ese.controller.pojos.SignupForm;
import ch.unibe.ese.controller.pojos.TutorSignupForm;
import ch.unibe.ese.model.Lecture;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.dao.LectureDao;
import ch.unibe.ese.model.dao.StudentDao;
import ch.unibe.ese.model.dao.SubjectDao;
import ch.unibe.ese.model.dao.UniversityDao;


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
        Student temp = userDao.findByUsername(signupForm.getUsername());
        if(temp==null){
            user.setUsername(signupForm.getUsername());
        }
        else{
        	throw new InvalidUserException("Username already taken");
        }
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
    	chosenLecture.setGrade(lectureForm.getGrade());
    	
    	
    	loggedInTutor.addLecture(chosenLecture);
    	chosenLecture.setTutor(loggedInTutor);
    	lectureDao.save(chosenLecture);
    	userDao.save(loggedInTutor);
    	

    	return lectureForm;
    }


}