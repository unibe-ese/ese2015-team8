package ch.unibe.ese.controller.service.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import ch.unibe.ese.controller.exceptions.InvalidDataException;
import ch.unibe.ese.controller.exceptions.InvalidGradeException;
import ch.unibe.ese.controller.pojos.LectureForm;
import ch.unibe.ese.controller.service.AddLectureService;
import ch.unibe.ese.model.Lecture;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.Subject;
import ch.unibe.ese.model.University;
import ch.unibe.ese.model.dao.LectureDao;
import ch.unibe.ese.model.dao.StudentDao;
import ch.unibe.ese.model.dao.SubjectDao;
import ch.unibe.ese.model.dao.UniversityDao;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.AdditionalAnswers.*;

// 100% Coverage
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/test.xml"})
public class AddLectureServiceTest {
	
	@Autowired private AddLectureService addLectureService;
	@Autowired private StudentDao studentDao;
	@Autowired private LectureDao lectureDao;
	@Autowired private UniversityDao universityDao;
	@Autowired private SubjectDao subjectDao;


	
	private LectureForm lectureForm;
	private Subject sampleSubject;
	private University sampleUniversity;

	private Student loggedInTutor;

	@Before
	public void setUpData() {

		when(lectureDao.save(any(Lecture.class))).then(returnsFirstArg());
		when(studentDao.save(any(Student.class))).then(returnsFirstArg());

		sampleSubject = new Subject();
		sampleSubject.setName("sampleSubject");
		sampleSubject.setId((long) 1);

		sampleUniversity = new University();
		sampleUniversity.setName("sampleUniversity");
		sampleUniversity.setId((long) 1);

		loggedInTutor = new Student();
		loggedInTutor.setIsTutor(true);

		List<Lecture> lectures = new LinkedList<Lecture>();
		loggedInTutor.setLectures(lectures);
	}

	@Before
	public void fillOutValidForm() {

		lectureForm = new LectureForm();
		lectureForm.setName("Introduction to Testing");

		lectureForm.setSubject((long) 1);
		lectureForm.setUniversity((long) 1);
		lectureForm.setGrade(5.5);

	}

	@Test
	public void addingValidLecture() throws InvalidDataException, InvalidGradeException {

		when(universityDao.findOne((long) 1)).thenReturn(sampleUniversity);
		when(subjectDao.findOne((long) 1)).thenReturn(sampleSubject);

		

		Lecture	addedLecture = addLectureService.saveFrom(lectureForm, loggedInTutor);
	

		assertEquals("Introduction to Testing", addedLecture.getName());
		assertEquals(loggedInTutor.getLectures().get(0), addedLecture);
		assertEquals(loggedInTutor, addedLecture.getTutor());
		assertEquals(sampleUniversity, addedLecture.getUniversity());

	}
	

}