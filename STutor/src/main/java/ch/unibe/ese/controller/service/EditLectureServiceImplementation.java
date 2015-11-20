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

@Service("EditLectureService")
public class EditLectureServiceImplementation implements EditLectureService{

	@Autowired LectureDao lectureDao;
	@Autowired StudentDao studentDao;
	@Autowired SubjectDao subjectDao;
	@Autowired UniversityDao universityDao;
	
	
	public Lecture getChosenLecture(int index, Student tutor) {
		Lecture chosenLecture = tutor.getLectures().get(index);
		return chosenLecture;
	}
	
	 @Transactional
	   public Lecture editFrom(LectureForm lectureForm, long lectureId) throws InvalidGradeException{
		 
	    	Lecture TBC = lectureDao.findOne(lectureId);
	    			
	    	TBC.setName(lectureForm.getName());
	    	TBC.setSubject(subjectDao.findOne(lectureForm.getSubject()));
	    	TBC.setUniversity(universityDao.findOne(lectureForm.getUniversity()));
	    	TBC.setGrade(lectureForm.getGrade());
	       	TBC = lectureDao.save(TBC);
	    
	    	return TBC;
	    }
	
	
}
