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
import ch.unibe.ese.controller.service.LectureService;
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

import java.util.HashSet;
import java.util.Set;

import static org.mockito.AdditionalAnswers.*;

// 100% Coverage
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/test.xml"})
public class LectureServiceTest {
	
	@Autowired private LectureService lectureService;
	@Autowired private StudentDao studentDao;
	@Autowired private LectureDao lectureDao;
	@Autowired private UniversityDao universityDao;
	@Autowired private SubjectDao subjectDao;


	
	private LectureForm lectureForm;
	private Subject sampleSubject, sampleSubject2;
	private University sampleUniversity, sampleUniversity2; 

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
		
		
		sampleSubject2 = new Subject();
		sampleSubject2.setName("sampleSubject2");
		sampleSubject2.setId((long) 2);
		
		sampleUniversity2 = new University();
		sampleUniversity2.setName("sampleUniversity2");
		sampleUniversity2.setId((long) 2);
		
		when(subjectDao.findOne(sampleSubject2.getId())).thenReturn(sampleSubject2);
		when(universityDao.findOne(sampleUniversity2.getId())).thenReturn(sampleUniversity2);
		

		loggedInTutor = new Student();
		loggedInTutor.setIsTutor(true);

		Set<Lecture> lectures = new HashSet<Lecture>();
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

		

		Lecture	addedLecture = lectureService.saveFrom(lectureForm, loggedInTutor);
	

		assertEquals("Introduction to Testing", addedLecture.getName());
		assertTrue(loggedInTutor.getLectures().contains(addedLecture));
		assertEquals(loggedInTutor, addedLecture.getTutor());
		assertEquals(sampleUniversity, addedLecture.getUniversity());

	}
	
	@Test
	public void editLecture() throws InvalidGradeException{
		
		
		//first we create the lecture we'll edit
		Lecture toBeEdited = new Lecture();
		toBeEdited.setId(-2L);
		toBeEdited.setName("Intro to Editing");
		toBeEdited.setGrade(6.0);
		toBeEdited.setSubject(sampleSubject);
		toBeEdited.setTutor(loggedInTutor);
		
		when(lectureDao.findOne(-2L)).thenReturn(toBeEdited);
		
		
		//now we change all the values of that lecture
		LectureForm editForm = new LectureForm();
		editForm.setGrade(5.5);
		editForm.setName("Editing 301");
		editForm.setSubject(sampleSubject2.getId());
		editForm.setUniversity(sampleUniversity2.getId());		
		
		lectureService.editFrom(editForm, toBeEdited.getId());
		
		//check if it has only the new values:
		assertEquals("Editing 301", toBeEdited.getName());
		assertEquals(sampleSubject2, toBeEdited.getSubject());
		assertEquals(sampleUniversity2, toBeEdited.getUniversity());
		assertEquals(5.5, toBeEdited.getGrade(), 0.01);
		
		
	}
	
	@Test
	public void removeLecture(){
		
		Lecture toBeRemoved = new Lecture();
		toBeRemoved.setId(-2L);
		toBeRemoved.setTutor(loggedInTutor);
		toBeRemoved.setName("Soon to be deleted");
		toBeRemoved = lectureDao.save(toBeRemoved);

		lectureService.remove(toBeRemoved);
		assertTrue(toBeRemoved.getTutor()==null);

		
		
		
	}
	

}