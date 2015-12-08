package ch.unibe.ese.controller.service;

import java.util.List;

import ch.unibe.ese.controller.pojos.RefinedSearchForm;
import ch.unibe.ese.model.Lecture;

/**
 * @author Till
 * Processes the lecture searching. You can get all lectures with an id or you can search for lectures
 * with specific filters on the tutors. The methods are self-explaining.
 */
public interface LectureSearchService {

	public List<Lecture> getAllLectures();
	
	public Lecture findById(long id);
	
	public List<Lecture> findLecturesByName(String searchTerm);
	
	public List<Lecture> findByNameAndGradeGreaterThan(String lectureName, double grade, String sortBy);

	public List<Lecture> findByNameAndUniversityAndGradeGreaterThan(String lectureName, long universityId, double grade, String sortBy);

	public List<Lecture> findByNameAndSubjectAndGradeGreaterThan(String lectureName, long subjectId, double grade, String sortBy);

	public List<Lecture> findByNameAndUniversityAndSubjectAndGradeGreaterThan(String lectureName,long universityId, long subjectId, double grade, String sortBy);
	
	public List<Lecture> getCorrectTempLectures(RefinedSearchForm refSearchForm, String sortBy, String lectureName, Double minGrade, String gender);
	
	public RefinedSearchForm getNewRefinedSearchForm(RefinedSearchForm refSearchForm);

}
