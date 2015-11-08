package ch.unibe.ese.controller.service.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;

import ch.unibe.ese.controller.exceptions.InvalidUserException;
import ch.unibe.ese.controller.pojos.SignupForm;
import ch.unibe.ese.controller.service.SignUpService;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.dao.StudentDao;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.mockito.Mockito.*;
import static org.mockito.AdditionalAnswers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/test.xml"})
public class SignupServiceTest {
	
	@Autowired private SignUpService signupService;
	@Autowired private StudentDao studentDao;
	private SignupForm signUpForm;
	
	@Before
	public void setupSignup(){
		signUpForm = new SignupForm();
		signUpForm.setFirstName("John");
		signUpForm.setLastName("Doe");
		signUpForm.setUsername("johndoe");
		signUpForm.setEmail("student@unibe.ch");
		signUpForm.setPassword("123");
		
		signUpForm.setIsTutor(true); 
		
		when(studentDao.save(any(Student.class))).then(returnsFirstArg());
	}
	
	@Test
	public void testStudentCredentials(){
		
		Student student = signupService.saveStudentFrom(signUpForm);
		
		assertEquals("John", student.getFirstName());
		assertEquals("Doe", student.getLastName());
		assertEquals("student@unibe.ch", student.getEmail());
		assertEquals("johndoe", student.getUsername());
		
		assertEquals(true, student.getIsTutor());
	}
	
	@Test
	public void testPWEncryption(){
		Student student = signupService.saveStudentFrom(signUpForm);

		assertNotEquals("123", student.getPassword());
		ShaPasswordEncoder pwEncoder = new ShaPasswordEncoder();
		assertEquals
			(pwEncoder.encodePassword("123", student.getUsername()), student.getPassword());
		
	}
	
	
	@Test(expected = InvalidUserException.class)
	public void testEmptyName(){
		SignupForm signUpForm = new SignupForm();
		
		signUpForm.setFirstName("");
		signUpForm.setLastName("Doe");
		signUpForm.setUsername("johndoe");
		
		signupService.saveStudentFrom(signUpForm);
		
	}	

}
