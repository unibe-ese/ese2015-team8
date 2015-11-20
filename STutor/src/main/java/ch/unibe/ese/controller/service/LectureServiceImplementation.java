package ch.unibe.ese.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.unibe.ese.controller.pojos.LectureForm;
import ch.unibe.ese.model.Lecture;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.dao.LectureDao;
import ch.unibe.ese.model.dao.StudentDao;
import ch.unibe.ese.model.dao.SubjectDao;
import ch.unibe.ese.model.dao.UniversityDao;

@Service("LectureService")
public class LectureServiceImplementation implements LectureService {

	@Autowired
	StudentDao userDao;
	@Autowired
	LectureDao lectureDao;
	@Autowired
	SubjectDao subjectDao;
	@Autowired
	StudentDao studentDao;
	@Autowired
	UniversityDao universityDao;

	/**
	 * Saves the lecture from the form into the dao.
	 */
	@Transactional
	public Lecture saveFrom(LectureForm lectureForm, Student loggedInTutor) {

		Lecture addedLecture = new Lecture();
		addedLecture.setName(lectureForm.getName());
		addedLecture.setSubject(subjectDao.findOne(lectureForm.getSubject()));
		addedLecture.setUniversity(universityDao.findOne(lectureForm.getUniversity()));
		addedLecture.setGrade(lectureForm.getGrade());

		loggedInTutor.addLecture(addedLecture);
		addedLecture.setTutor(loggedInTutor);
		userDao.save(loggedInTutor);
		lectureDao.save(addedLecture);

		return addedLecture;
	}


	@Transactional
	public Lecture editFrom(LectureForm lectureForm, long lectureId) {

		Lecture editedLecture = lectureDao.findOne(lectureId);

		editedLecture.setName(lectureForm.getName());
		editedLecture.setSubject(subjectDao.findOne(lectureForm.getSubject()));
		editedLecture.setUniversity(universityDao.findOne(lectureForm.getUniversity()));
		editedLecture.setGrade(lectureForm.getGrade());
		editedLecture = lectureDao.save(editedLecture);

		return editedLecture;
	}

	@Transactional
	public Lecture remove(Lecture lecture) {
		

		lecture.setTutor(null);
		lectureDao.delete(lecture);
		
		return lecture;
	}

}
