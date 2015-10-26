package ch.unibe.ese.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import ch.unibe.ese.controller.exceptions.InvalidUserException;
import ch.unibe.ese.controller.pojos.AddLectureForm;
import ch.unibe.ese.controller.pojos.SignupForm;
import ch.unibe.ese.controller.pojos.TutorSignupForm;
import ch.unibe.ese.model.Lecture;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.dao.LectureDao;
import ch.unibe.ese.model.dao.StudentDao;


@Service
public class SampleServiceStudent implements SampleService {

    @Autowired    	StudentDao userDao;
    @Autowired		LectureDao lectureDao;
    
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
    public AddLectureForm saveTutorLecture(AddLectureForm lectureForm, Student loggedInTutor) throws InvalidUserException{
		
    	long lectureId = lectureForm.getLecture();
    	
    	Lecture chosenLecture = lectureDao.findOne(lectureId);
    	System.err.println("Been here, the chosen lecture is " + chosenLecture.toString());
    	loggedInTutor.addLecture(chosenLecture);
    	chosenLecture.addTutor(loggedInTutor);
    	lectureDao.save(chosenLecture);
    	userDao.save(loggedInTutor);
    	

    	return lectureForm;
    	
    }
    
}
