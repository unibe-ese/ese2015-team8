package ch.unibe.ese.controller.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.unibe.ese.model.Lecture;
import ch.unibe.ese.model.dao.LectureDao;

@Service("LectureSearchService")
public class LectureSearchServiceImplementation implements LectureSearchService {
	@Autowired
	LectureDao lectureDao;

	public List<Lecture> getAllLectures() {
		List<Lecture> allLectures = new LinkedList<Lecture>();
		Iterable<Lecture> lectures = lectureDao.findAll();

		for (Lecture lecture : lectures) {
			allLectures.add(lecture);
		}

		return allLectures;
	}
	
	
	public Iterable<Lecture> findLecturesByName(String searchTerm) {

		return lectureDao.findByName(searchTerm);
	}
	

	public Iterable<Lecture> findByNameAndGradeGreaterThan(String lectureName, double grade) {
		
		Iterable<Lecture> searchResult = lectureDao.findByNameAndGradeGreaterThan(lectureName, grade);
		
		return searchResult;
	}

	public Iterable<Lecture> findByNameAndUniversityAndGradeGreaterThan(String lectureName, long universityId,
			double grade) {
		
		Iterable<Lecture> searchResult = lectureDao.findByNameAndUniversity_idAndGradeGreaterThan(lectureName, universityId, grade);
		
		return searchResult;
	}

	public Iterable<Lecture> findByNameAndSubjectAndGradeGreaterThan(String lectureName, long subjectId, double grade) {
		Iterable<Lecture> searchResult = lectureDao.findByNameAndSubject_idAndGradeGreaterThan(lectureName, subjectId, grade);
		return searchResult;
	}

	public Iterable<Lecture> findByNameAndUniversityAndSubjectAndGradeGreaterThan(String lectureName,
			long universityId, long subjectId, double grade) {
		Iterable<Lecture> searchResult = lectureDao.findByNameAndUniversity_idAndSubject_idAndGradeGreaterThan
													(lectureName, universityId, subjectId, grade);
		return searchResult;
	}


}
