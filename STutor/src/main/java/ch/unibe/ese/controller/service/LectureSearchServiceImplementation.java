package ch.unibe.ese.controller.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import ch.unibe.ese.controller.pojos.RefinedSearchForm;
import ch.unibe.ese.model.Lecture;
import ch.unibe.ese.model.dao.LectureDao;

@Service("LectureSearchService")
public class LectureSearchServiceImplementation implements LectureSearchService {
	@Autowired
	LectureDao lectureDao;

	private Sort sort;

	public List<Lecture> getAllLectures() {
		List<Lecture> allLectures = new LinkedList<Lecture>();
		Iterable<Lecture> lectures = lectureDao.findAll();

		for (Lecture lecture : lectures) {
			allLectures.add(lecture);
		}

		return allLectures;
	}

	public List<Lecture> findLecturesByName(String searchTerm) {

		return lectureDao.findByName(searchTerm);
	}

	public List<Lecture> findByNameAndGradeGreaterThan(String lectureName, double grade, String sortBy) {

		if (sortBy.contentEquals("tutor.wage")) {
			sort = new Sort(Direction.ASC, sortBy);
		} else {
			sort = new Sort(Direction.DESC, sortBy);
		}

		List<Lecture> searchResult = lectureDao.findByNameAndGradeGreaterThan(lectureName, (grade - 0.01), sort);

		return searchResult;
	}

	public List<Lecture> findByNameAndUniversityAndGradeGreaterThan(String lectureName, long universityId, double grade,
			String sortBy) {

		if (sortBy.contentEquals("tutor.wage")) {
			sort = new Sort(Direction.ASC, sortBy);
		} else {
			sort = new Sort(Direction.DESC, sortBy);
		}

		List<Lecture> searchResult = lectureDao.findByNameAndUniversity_idAndGradeGreaterThan(lectureName, universityId,
				(grade - 0.01), sort);

		return searchResult;
	}

	public List<Lecture> findByNameAndSubjectAndGradeGreaterThan(String lectureName, long subjectId, double grade,
			String sortBy) {

		if (sortBy.contentEquals("tutor.wage")) {
			sort = new Sort(Direction.ASC, sortBy);
		} else {
			sort = new Sort(Direction.DESC, sortBy);
		}

		List<Lecture> searchResult = lectureDao.findByNameAndSubject_idAndGradeGreaterThan(lectureName, subjectId,
				(grade - 0.01), sort);
		return searchResult;
	}

	public List<Lecture> findByNameAndUniversityAndSubjectAndGradeGreaterThan(String lectureName, long universityId,
			long subjectId, double grade, String sortBy) {

		if (sortBy.contentEquals("tutor.wage")) {
			sort = new Sort(Direction.ASC, sortBy);
		} else {
			sort = new Sort(Direction.DESC, sortBy);
		}

		List<Lecture> searchResult = lectureDao.findByNameAndUniversity_idAndSubject_idAndGradeGreaterThan(lectureName,
				universityId, subjectId, (grade - 0.01), sort);
		return searchResult;
	}

	public Lecture findById(long id) {
		Lecture lecture = lectureDao.findOne(id);
		return lecture;
	}
	
	// ids with -1 as value represent nonexistent values, so it's
	// like "doesn't matter" which subject/uni etc.
	public Iterable<Lecture> getCorrectTempLecture(RefinedSearchForm refSearchForm, String sortBy, String lectureName,
			Double minGrade) {
		Iterable<Lecture> lecturesTemp;
		if ((refSearchForm.getSubject() == -1) && (refSearchForm.getUniversity() == -1)) {
			lecturesTemp = findByNameAndGradeGreaterThan(lectureName,
					minGrade, sortBy);
		}
		else if ((refSearchForm.getSubject() == -1) && (refSearchForm.getUniversity() != -1)) {
			lecturesTemp = findByNameAndUniversityAndGradeGreaterThan(lectureName,
					refSearchForm.getUniversity(), minGrade, sortBy);
		}
		else if ((refSearchForm.getSubject() != -1) && (refSearchForm.getUniversity() == -1)) {
			lecturesTemp = findByNameAndSubjectAndGradeGreaterThan(lectureName,
					refSearchForm.getSubject(), minGrade, sortBy);

		}
		// subject and university given:
		else {
			lecturesTemp = findByNameAndUniversityAndSubjectAndGradeGreaterThan(
					lectureName, refSearchForm.getUniversity(), refSearchForm.getSubject(),
					minGrade, sortBy);
		}
		return lecturesTemp;
	}

	public RefinedSearchForm getNewRefinedSearchForm(RefinedSearchForm refSearchForm) {
		RefinedSearchForm refSearchFormNew = new RefinedSearchForm();

		refSearchFormNew.setName(refSearchForm.getName());
		refSearchFormNew.setSubject(refSearchForm.getSubject());
		refSearchFormNew.setUniversity(refSearchForm.getUniversity());
		refSearchFormNew.setGender(refSearchForm.getGender());
		refSearchFormNew.setMinGrade(refSearchForm.getMinGrade());
		refSearchFormNew.setSortBy(refSearchForm.getSortBy());
		return refSearchFormNew;
	}

}
