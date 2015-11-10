package ch.unibe.ese.controller.service.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.unibe.ese.controller.pojos.OptionForm;
import ch.unibe.ese.controller.service.OptionService;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.dao.StudentDao;

// Coverage 39.1% (Line 25-45, 57, 60 not Tested)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/test.xml"})
public class OptionServiceTest {
	
	@Autowired private OptionService optionService;
	@Autowired private StudentDao studentDao;

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
	