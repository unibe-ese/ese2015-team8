package ch.unibe.ese.controller.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.unibe.ese.model.Subject;
import ch.unibe.ese.model.University;
import ch.unibe.ese.model.dao.NotificationDao;
import ch.unibe.ese.model.dao.SubjectDao;
import ch.unibe.ese.model.dao.TimeframeDao;
import ch.unibe.ese.model.dao.UniversityDao;

@Service("DataService")
public class DataServiceImplementation implements DataService {

	@Autowired UniversityDao universityDao;
	@Autowired SubjectDao subjectDao;
	@Autowired NotificationDao notificationDao;
	@Autowired TimeframeDao timeframeDao;

	public List<University> getAllUniversities() {
		List<University> allUniversities = new LinkedList<University>();
		Iterable<University> universities = universityDao.findAll();

		for (University university : universities) {
			allUniversities.add(university);
		}

		return allUniversities;
	}

	public List<Subject> getAllSubjects() {
		List<Subject> allSubjects = new LinkedList<Subject>();
		Iterable<Subject> subjects = subjectDao.findAll();

		for (Subject subject : subjects) {
			allSubjects.add(subject);
		}

		return allSubjects;
	}

	/*
	 * University of Basel (UNIBAS)
	 * University of Bern (UNIBE)
	 * University of Fribourg (UNIFR)
	 * University of Geneva (UNIGE)
	 * Ecole Polytechnique Fédérale de Lausanne (EPFL)
	 * University of Lausanne (UNIL)
	 * University of Lucerne
	 * University of Neuchâtel (UniNE)
	 * Università della Svizzera italiana (USI)
	 * University of St.Gallen (HSG)
	 * ETH Zurich (ETHZ)
	 * University of Zurich (UZH)
	 */
	public void initializeUniversities() {
		String[] universities = {"UNIBE","ETHZ","EPFL","UNIBAS","UNIFR","UNIGE","Universität Luzern","UniNE","USI","HSG","UZH","EPFL"};
		for(String university : universities){
			University temp = new University();
			temp.setName(university);
			universityDao.save(temp);
		}
	}

	public void initializeSubjects() {
		String[] subjects = {"Computer Science","Mathematics","Physics","Linguistic","History","Philosophy","Biology","Chemistry","Economics","Law"};
		for(String subject : subjects){
			Subject temp = new Subject();
			temp.setLevel("Bachelor");
			temp.setName(subject);
			subjectDao.save(temp);
		}
	}

	public boolean subjectsAreEmpty() {
		if(subjectDao.findAll().iterator().hasNext()){return false;}
		return true;
	}
	
	public boolean universitiesAreEmpty() {
		if(universityDao.findAll().iterator().hasNext()){return false;}
		return true;
	}

	/**
	 * choices to either choose gender or let it open
	 * 
	 * @return list with these three choices
	 */
	public List<String> getAllGender() {
		List<String> allGender = new LinkedList<String>();
		allGender.add("doesn't matter");
		allGender.add("male");
		allGender.add("female");

		return allGender;
	}
}
