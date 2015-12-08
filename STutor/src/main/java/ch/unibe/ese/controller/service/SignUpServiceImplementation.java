package ch.unibe.ese.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import ch.unibe.ese.controller.exceptions.InvalidUserException;
import ch.unibe.ese.controller.pojos.SignupForm;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.dao.StudentDao;

/**
 * 
 * Handles the processing of the @see SignupForm, so saving the inputs into the form for registering into the database
 * 
 * @author ESE Team 8
 * @version 1.0
 * @since 21.10.2015
 */
@Service
public class SignUpServiceImplementation implements SignUpService {

    @Autowired StudentDao userDao;
    
    @Transactional
    public Student saveStudentFrom(SignupForm signupForm) throws InvalidUserException{

        String firstName = signupForm.getFirstName();

        if(StringUtils.isEmpty(firstName)) {
            throw new InvalidUserException("Empty name not valid");   // throw exception
        }
        
        Student student = new Student();
        
        student.setFirstName(signupForm.getFirstName());
        student.setLastName(signupForm.getLastName());
        Student temp = userDao.findByUsername(signupForm.getUsername());
        if(temp==null){
            student.setUsername(signupForm.getUsername());
        }
        else{
        	throw new InvalidUserException("Username already taken");
        }
        Student temp2 = userDao.findByEmail(signupForm.getEmail());
        if(temp2==null){
            student.setEmail(signupForm.getEmail());        }
        else{
        	throw new InvalidUserException("Email already taken");
        }
        student.setEmail(signupForm.getEmail());
        student.setPassword(signupForm.getPassword());
        student.setIsTutor(signupForm.getIsTutor());

        student = userDao.save(student);   // save object to DB
        
        signupForm.setId(student.getId());

        return student;
    }
    
    @Transactional
    public Student modifieUserFrom(SignupForm signupForm, Student actualStudent) throws InvalidUserException{
            	
        actualStudent.setFirstName(signupForm.getFirstName());
        actualStudent.setLastName(signupForm.getLastName());
        actualStudent.setUsername(signupForm.getUsername());
        actualStudent.setEmail(signupForm.getEmail());
        actualStudent.setPassword(signupForm.getPassword());
        actualStudent.setIsTutor(signupForm.getIsTutor());
        actualStudent.setGender(signupForm.getGender());
        
        actualStudent = userDao.save(actualStudent);   // save object to DB

        return actualStudent;
    }
}