package ch.unibe.ese.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.unibe.ese.controller.exceptions.InvalidUserException;
import ch.unibe.ese.controller.pojos.OptionForm;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.dao.StudentDao;

/**
 * 
 * @author Christian Zürcher
 * @version 1.0
 * @since 4.11.2015
 */
@Service
public class OptionServiceImplementation implements OptionService {
	
	@Autowired
	StudentDao studentDao;

	@Transactional
	public OptionForm getFrom(Student student)  throws InvalidUserException{
		OptionForm optionForm = new OptionForm();
		optionForm.setEmail(student.getEmail());
		optionForm.setUsername(student.getUsername());
		optionForm.setFirstName(student.getFirstName());
		optionForm.setLastName(student.getLastName());
		optionForm.setIsTutor(student.getIsTutor());
		if(student.getIsTutor())
			optionForm.setGender(student.getGender());
		return optionForm;
	}
	
	@Transactional
	public OptionForm getFrom(Long studentId) {
		return getFrom(studentDao.findOne(studentId));
	}
	
	@Transactional
	public OptionForm getFrom(String username) {
		return getFrom(studentDao.findByUsername(username));
	}
	
	@Transactional
    public Student saveStudentFrom(Student student, OptionForm optionForm, boolean hasChangedPassword) throws InvalidUserException{
        
        student.setFirstName(optionForm.getFirstName());
        student.setLastName(optionForm.getLastName());
        student.setUsername(optionForm.getUsername());
        student.setEmail(optionForm.getEmail());
        student.setIsTutor(optionForm.getIsTutor());
        
        if(optionForm.getIsTutor())
			student.setGender(optionForm.getGender());
        
        if(hasChangedPassword)
        	student.setPassword(optionForm.getPassword());
        
        student = studentDao.save(student);   // save object to DB

        return student;
    }

}
