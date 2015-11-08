package ch.unibe.ese.controller.service.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;

import ch.unibe.ese.controller.exceptions.InvalidUserException;
import ch.unibe.ese.controller.pojos.OptionForm;
import ch.unibe.ese.controller.pojos.SignupForm;
import ch.unibe.ese.controller.service.OptionService;
import ch.unibe.ese.controller.service.SignUpService;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.dao.StudentDao;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.mockito.Mockito.*;
import static org.mockito.AdditionalAnswers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/test.xml"})
public class OptionServiceTest {
	
	@Autowired private OptionService optionService;
	@Autowired private StudentDao studentDao;
	private OptionForm optionForm;
	private Student student;
	
	//student already has to exist to change his settings in the form
	@Before
	public void setUpStudent(){
	
		student = new Student();
		student.setFirstName("Max");
		student.setLastName("Muster");
		student.setUsername("user1");
		student.setPassword("abc");
		student.setEmail("email@email.com");
		
		when(studentDao.save(any(Student.class))).then(returnsFirstArg());
	}
	
	
	@Test
	public void changeName(){
		OptionForm optionForm = new OptionForm();
		optionForm.setFirstName("John");
		optionForm.setLastName("Doe");
		
		
		student = optionService.saveStudentFrom(student, optionForm, false);
		
		assertNotEquals("Max", student.getFirstName());
		assertEquals("John", student.getFirstName());
		
		assertNotEquals("Muster", student.getLastName());
		assertEquals("Doe", student.getLastName());
	}
	
}
	