package ch.unibe.ese.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.unibe.ese.controller.exceptions.InvalidGradeException;
import ch.unibe.ese.controller.pojos.LectureForm;
import ch.unibe.ese.model.Lecture;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.dao.LectureDao;
import ch.unibe.ese.model.dao.StudentDao;
import ch.unibe.ese.model.dao.SubjectDao;
import ch.unibe.ese.model.dao.UniversityDao;

/**
 * 
 * @author ESE Team 8
 * @version 1.0
 * 
 * This implementation of the addLecture service handles the processing of the @see LectureForm
 *
 */

@Service
public class AddLectureServiceImplementation implements AddLectureService {

    @Autowired		StudentDao userDao;
    @Autowired		LectureDao lectureDao;
    @Autowired		SubjectDao subjectDao;
    @Autowired		UniversityDao universityDao;
    


    /**
     * Saves the lecture from the form into the dao. 
     */
    @Transactional
    public Lecture saveFrom(LectureForm lectureForm, Student loggedInTutor) throws InvalidGradeException{
		
    	Lecture addedLecture = new Lecture();
    	addedLecture.setName(lectureForm.getName());
    	addedLecture.setSubject(subjectDao.findOne(lectureForm.getSubject()));
    	addedLecture.setUniversity(universityDao.findOne(lectureForm.getUniversity()));
    	addedLecture.setGrade(lectureForm.getGrade());
    	
    	
    	loggedInTutor.addLecture(addedLecture);
    	addedLecture.setTutor(loggedInTutor);
    	userDao.save(loggedInTutor);
    	

    	return addedLecture;
    }
    
   



}