package ch.unibe.ese.controller.service;

import java.util.List;

import ch.unibe.ese.model.Lecture;

public interface LectureSearchService {

	public List<Lecture> getAllLectures();
	
	public Lecture findById(long id);
	
	public List<Lecture> findLecturesByName(String searchTerm);
	
	public List<Lecture> findByNameAndGradeGreaterThan(String lectureName, double grade, String sortBy);

	public List<Lecture> findByNameAndUniversityAndGradeGreaterThan(String lectureName, long universityId, double grade, String sortBy);

	public List<Lecture> findByNameAndSubjectAndGradeGreaterThan(String lectureName, long subjectId, double grade, String sortBy);

	public List<Lecture> findByNameAndUniversityAndSubjectAndGradeGreaterThan(String lectureName,long universityId, long subjectId, double grade, String sortBy);

}
