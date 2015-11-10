package ch.unibe.ese.controller.service.tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.unibe.ese.controller.pojos.TimelapsForm;
import ch.unibe.ese.controller.service.TimelapsService;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.Timelaps;
import ch.unibe.ese.model.dao.StudentDao;

// 100 % Coverage
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/test.xml"})
public class TimelapsServiceTest {
	
	@Autowired private TimelapsService timelapsService;
	
	@Autowired private StudentDao studentDao;
	
	private Timelaps timelaps = new Timelaps();
	private TimelapsForm timelapsForm;
	private Student tutor;
	
	
	private int testDay = 1 , testFromTime = 12 , testToTime = 14;
	
	@Before
	public void setUpForm() {
		timelapsForm = new TimelapsForm();
		timelapsForm.setDay(testDay);
		timelapsForm.setFromTime(testFromTime);
		timelapsForm.setToTime(testToTime);
		
		when(studentDao.save(any(Student.class))).then(returnsFirstArg());
	}
	
	@Before
	public void initializeStudentAndSetTimlapses() {
		tutor = new Student();
		tutor.setIsTutor(true);
		tutor.setTimelapses(new HashSet<Timelaps>());
	}
	
	@Test
	public void getCommentFromForm() {
		
		tutor = timelapsService.saveFrom(timelapsForm, tutor);
		
		assertEquals(testDay,timelaps.getDay(tutor.getTimelapses().iterator().next().getFromTime()));
		assertEquals(testFromTime,timelaps.getHour(tutor.getTimelapses().iterator().next().getFromTime()));
		assertEquals(testToTime,timelaps.getHour(tutor.getTimelapses().iterator().next().getToTime()));
	}
	
	
	// to put in a TimelapsTest later
	@Test
	public void getDayTest(){
		assertEquals(4,timelaps.getDay(413));
	}
	
	@Test
	public void getHourTest(){
		assertEquals(13,timelaps.getHour(413));
	}
}
