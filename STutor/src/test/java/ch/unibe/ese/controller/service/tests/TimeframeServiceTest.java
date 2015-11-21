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

import ch.unibe.ese.controller.pojos.TimeframeForm;
import ch.unibe.ese.controller.service.TimeframeService;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.Timeframe;
import ch.unibe.ese.model.dao.StudentDao;
import ch.unibe.ese.model.dao.TimeframeDao;

// 100 % Coverage
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/test.xml"})
public class TimeframeServiceTest {
	
	@Autowired private TimeframeService timeframeService;
	
	@Autowired private StudentDao studentDao;
	@Autowired private TimeframeDao timeframeDao;
	
	private TimeframeForm timeframeForm;
	private Student tutor;
	
	//Timeframe: On a Monday(1), from 12h to 14h:
	private int testDay = 1, testFromTime = 12 , testToTime = 14;
	
	@Before
	public void setUpData() {
		
		when(timeframeDao.save(any(Timeframe.class))).then(returnsFirstArg());
		when(studentDao.save(any(Student.class))).then(returnsFirstArg());
		
		tutor = new Student();
		tutor.setIsTutor(true);
		tutor.setTimeframes(new HashSet<Timeframe>());
		
		timeframeForm = new TimeframeForm();
		timeframeForm.setDay(testDay);
		timeframeForm.setFromTime(testFromTime);
		timeframeForm.setToTime(testToTime);
		
	}
	
	@Before
	public void fillOutValidForm() {

		timeframeForm = new TimeframeForm();
		timeframeForm.setDay(testDay);
		timeframeForm.setFromTime(testFromTime);
		timeframeForm.setToTime(testToTime);
		timeframeForm.setId(1L);
		

	}
	
	@Test
	public void saveTimeFromForm() {
		
		tutor = timeframeService.saveFrom(timeframeForm, tutor);
		
		assertEquals(testDay, tutor.getTimeframes().iterator().next().getDay());
		
		assertEquals(testFromTime,tutor.getTimeframes().iterator().next().getFromTime());
		
		assertEquals(testToTime,tutor.getTimeframes().iterator().next().getToTime());
	}
	
}
