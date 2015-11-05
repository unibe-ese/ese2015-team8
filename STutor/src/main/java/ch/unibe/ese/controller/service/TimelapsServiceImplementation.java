package ch.unibe.ese.controller.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.unibe.ese.controller.pojos.TimelapsForm;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.Timelaps;
import ch.unibe.ese.model.dao.StudentDao;

/**
 * 
 * @author Christian Zürcher
 * @version 1.0
 * @since 4.11.2015
 */
@Service
public class TimelapsServiceImplementation implements TimelapsService {
	
	@Autowired StudentDao studentDao;
	
	private long dayToMs = 86400000, hourToMs = 3600000;

	private Timestamp getFromTime(TimelapsForm timelapsForm) {
		Timestamp timestamp = new Timestamp(timelapsForm.getDay()*dayToMs+timelapsForm.getFromTime()*hourToMs);
		return timestamp;
	}

	private Timestamp getToTime(TimelapsForm timelapsForm) {
		Timestamp timestamp = new Timestamp(timelapsForm.getDay()*dayToMs+timelapsForm.getToTime()*hourToMs);
		return timestamp;
	}

	@Override
	public TimelapsForm saveFrom(TimelapsForm timelapsForm, Student loggedInTutor) {
		Timelaps timelaps = new Timelaps();
		timelaps.setFromTime(getFromTime(timelapsForm));
		timelaps.setToTime(getToTime(timelapsForm));
		
		loggedInTutor.addTimelaps(timelaps);
		loggedInTutor = studentDao.save(loggedInTutor);
		
		return timelapsForm;
	}
}
