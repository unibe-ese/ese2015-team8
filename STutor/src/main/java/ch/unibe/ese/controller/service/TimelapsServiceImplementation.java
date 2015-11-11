package ch.unibe.ese.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.unibe.ese.controller.pojos.TimelapsForm;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.Timelaps;
import ch.unibe.ese.model.dao.StudentDao;

/**
 * 
 * @author ESE Team 8
 * @version 1.0
 * @since 4.11.2015
 */
@Service
public class TimelapsServiceImplementation implements TimelapsService {
	
	@Autowired StudentDao studentDao;

	private int getFromTime(TimelapsForm timelapsForm) {
		return timelapsForm.getDay()*100+timelapsForm.getFromTime();
	}

	private int getToTime(TimelapsForm timelapsForm) {
		return timelapsForm.getDay()*100+timelapsForm.getToTime();
	}

	@Override
	public Student saveFrom(TimelapsForm timelapsForm, Student loggedInTutor) {
		Timelaps timelaps = new Timelaps();
		timelaps.setFromTime(getFromTime(timelapsForm));
		timelaps.setToTime(getToTime(timelapsForm));
		
		loggedInTutor.addTimelaps(timelaps);
		loggedInTutor = studentDao.save(loggedInTutor);
		
		return loggedInTutor;
	}
}
