package ch.unibe.ese.controller.service;

import java.util.List;

import ch.unibe.ese.model.Lecture;

public interface LectureSearchService {

	public List<Lecture> getAllLectures();
	
	public Lecture findById(long id);
	
	public Iterable<Lecture> findLecturesByName(String searchTerm);
	
	public Iterable<Lecture> findByNameAndGradeGreaterThan(String lectureName, double grade);

	public Iterable<Lecture> findByNameAndUniversityAndGradeGreaterThan(String lectureName, long universityId, double grade);

	public Iterable<Lecture> findByNameAndSubjectAndGradeGreaterThan(String lectureName, long subjectId, double grade);

	public Iterable<Lecture> findByNameAndUniversityAndSubjectAndGradeGreaterThan(String lectureName,
			long universityId, long subjectId, double grade);

}
