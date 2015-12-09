package ch.unibe.ese.controller.service.tests;

import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.unibe.ese.controller.service.DataService;
import ch.unibe.ese.model.Subject;
import ch.unibe.ese.model.University;
import ch.unibe.ese.model.dao.SubjectDao;
import ch.unibe.ese.model.dao.UniversityDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/test.xml"})
public class DataServiceTest {
	@Autowired DataService dataService;
	@Autowired UniversityDao universityDao;
	@Autowired SubjectDao subjectDao;
	
	int numberOfSavedUniversities;
	int numberOfSavedSubjects;
	
	@Before
	public void mockDaos(){
		LinkedList<University> emptyUniversities = new LinkedList<University>();
		LinkedList<Subject> emptySubjects = new LinkedList<Subject>();
		when(universityDao.save(any(University.class))).then(incrementNumberOfUniversities())
													   .then(incrementNumberOfUniversities())
													   .then(incrementNumberOfUniversities())
													   .then(incrementNumberOfUniversities())
													   .then(incrementNumberOfUniversities())
													   .then(incrementNumberOfUniversities())
													   .then(incrementNumberOfUniversities())
													   .then(incrementNumberOfUniversities())
													   .then(incrementNumberOfUniversities())
													   .then(incrementNumberOfUniversities())
													   .then(incrementNumberOfUniversities())
													   .then(incrementNumberOfUniversities());
		when(subjectDao.save(any(Subject.class))).then(incrementNumberOfSubjects())
												 .then(incrementNumberOfSubjects())
												 .then(incrementNumberOfSubjects())
												 .then(incrementNumberOfSubjects())
												 .then(incrementNumberOfSubjects())
												 .then(incrementNumberOfSubjects())
												 .then(incrementNumberOfSubjects())
												 .then(incrementNumberOfSubjects())
												 .then(incrementNumberOfSubjects())
												 .then(incrementNumberOfSubjects());
		when(universityDao.findAll()).thenReturn(emptyUniversities);
		when(subjectDao.findAll()).thenReturn(emptySubjects);
	}
	
	private Answer<Object> incrementNumberOfUniversities(){
		this.numberOfSavedUniversities++;
		return returnsFirstArg();
	}
	
	private Answer<Object> incrementNumberOfSubjects(){
		this.numberOfSavedSubjects++;
		return returnsFirstArg();
	}
	
	@Test
	public void getAllGenders(){
		List<String> allGenders = dataService.getAllGender();
		assertTrue(allGenders.contains("doesn't matter"));
		assertTrue(allGenders.contains("male"));
		assertTrue(allGenders.contains("female"));
	}
	
	@Test
	public void testForEmtyness(){
		assertTrue(dataService.universitiesAreEmpty());
		assertTrue(dataService.subjectsAreEmpty());
	}
	
	@Test
	public void initializeUniversities(){
		dataService.initializeUniversities();
		dataService.initializeSubjects();
		assertEquals(10,numberOfSavedSubjects);
		assertEquals(12, numberOfSavedUniversities);
	}

}
