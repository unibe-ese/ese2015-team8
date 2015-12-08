package ch.unibe.ese.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.unibe.ese.controller.pojos.TimeframeForm;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.Timeframe;
import ch.unibe.ese.model.dao.StudentDao;
import ch.unibe.ese.model.dao.TimeframeDao;

/**
 * 
 * @author ESE Team 8
 * @version 1.0
 * @since 4.11.2015
 */
@Service
public class TimeframeServiceImplementation implements TimeframeService {
	
	@Autowired StudentDao studentDao;
	@Autowired TimeframeDao timeframeDao;

	@Transactional
	public Student saveFrom(TimeframeForm timeframeForm, Student loggedInTutor) {
		Timeframe timeframe = new Timeframe();
		timeframe.setDay(timeframeForm.getDay());
		timeframe.setFromTime(timeframeForm.getFromTime());
		timeframe.setToTime(timeframeForm.getToTime());
		
		timeframe = timeframeDao.save(timeframe);
		
		loggedInTutor.addTimeframe(timeframe);
		loggedInTutor = studentDao.save(loggedInTutor);
				
		return loggedInTutor;
	}
	
	@Transactional
	public Timeframe editFrom(TimeframeForm timeframeForm, long timeframeId) {

		Timeframe timeframe = timeframeDao.findOne(timeframeId);

		timeframe.setDay(timeframeForm.getDay());
		timeframe.setFromTime(timeframeForm.getFromTime());
		timeframe.setToTime(timeframeForm.getToTime());	
		
		timeframe = timeframeDao.save(timeframe);

		return timeframe;
	}
	
	@Override
	public Timeframe findTimeframeById(long timeframeId) {
		
		return timeframeDao.findOne(timeframeId);
	}

	@Transactional
	public void remove(Timeframe timeframe, Student tutor) {
		
		tutor.getTimeframes().remove(timeframe);
		tutor = studentDao.save(tutor);
		timeframeDao.delete(timeframe);
	}

	@Transactional
	public TimeframeForm getTimeframeFormFrom(Timeframe chosenTimeframe) {
		TimeframeForm editForm = new TimeframeForm();
		editForm.setDay(chosenTimeframe.getDay());
		editForm.setFromTime(chosenTimeframe.getFromTime());
		editForm.setToTime(chosenTimeframe.getToTime());
		editForm.setId(chosenTimeframe.getId());
		return editForm;
	}
}
