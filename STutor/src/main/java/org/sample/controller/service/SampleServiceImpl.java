package org.sample.controller.service;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.SignupForm;
import org.sample.model.Student;
import org.sample.model.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service
public class SampleServiceImpl implements SampleService {

    @Autowired    StudentDao userDao;
    
    @Transactional
    public SignupForm saveFrom(SignupForm signupForm) throws InvalidUserException{

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

        user = userDao.save(user);   // save object to DB
        
        signupForm.setId(user.getId());

        return signupForm;

    }
}
