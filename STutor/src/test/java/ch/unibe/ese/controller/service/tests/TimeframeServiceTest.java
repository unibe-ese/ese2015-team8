package ch.unibe.ese.controller.service.tests;

import static org.junit.Assert.*;
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

// 92 % Coverage, as simple Dao search was not tested (unnecessary)
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
	
	
	
	@Test
	public void editTimeFromForm(){
		
		//first we create a time frame with certain values
		
		Timeframe timeframe = new Timeframe();
		timeframe.setDay(1);
		timeframe.setFromTime(10);
		timeframe.setToTime(12);
		timeframe.setId(100L);
		
		when(timeframeDao.findOne(timeframe.getId())).thenReturn(timeframe);
		
		
		// now we'll try to edit those values through a form
		
		TimeframeForm editForm = new TimeframeForm();
		editForm.setDay(2);
		editForm.setFromTime(1);
		editForm.setToTime(5);
		
		timeframeService.editFrom(editForm, timeframe.getId());
		
		//check if new values applied
		
		assertEquals(2, timeframe.getDay());
		assertEquals(1, timeframe.getFromTime());
		assertEquals(5, timeframe.getToTime());
	}
	
	
	@Test
	public void removeTime(){
		Timeframe timeframe = new Timeframe();
		timeframe.setId(100L);
		tutor.addTimeframe(timeframe);
		
		when(timeframeDao.findOne(timeframe.getId())).thenReturn(timeframe);
		
		timeframeService.remove(timeframe, tutor);
		
		assertTrue(tutor.getTimeframes().isEmpty());
		
	}
	
}
