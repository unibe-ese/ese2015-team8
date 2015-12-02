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

	@Autowired
	UniversityDao universityDao;
	@Autowired
	SubjectDao subjectDao;
	@Autowired
	NotificationDao notificationDao;
	@Autowired
	TimeframeDao timeframeDao;


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
		University temp = new University();
		temp.setName("UNIBE");
		universityDao.save(temp);
		temp = new University();
		temp.setName("ETHZ");
		universityDao.save(temp);
		temp = new University();
		temp.setName("EPFL");
		universityDao.save(temp);
		temp.setName("UNIBAS");
		universityDao.save(temp);
		temp = new University();
		temp.setName("UNIFR");
		universityDao.save(temp);
		temp = new University();
		temp.setName("UNIGE");
		universityDao.save(temp);
		temp = new University();
		temp.setName("UNIL");
		universityDao.save(temp);
		temp = new University();
		temp.setName("Universität Luzern");
		universityDao.save(temp);
		temp = new University();
		temp.setName("UniNE");
		universityDao.save(temp);
		temp = new University();
		temp.setName("USI");
		universityDao.save(temp);
		temp = new University();
		temp.setName("HSG");
		universityDao.save(temp);
		temp = new University();
		temp.setName("UZH");
		universityDao.save(temp);
		temp = new University();

	}

	public void initializeSubjects() {

		Subject temp = new Subject();
		temp.setLevel("Bachelor");
		temp.setName("Computer Science");
		subjectDao.save(temp);
		temp = new Subject();
		temp.setLevel("Bachelor");
		temp.setName("Mathematics");
		subjectDao.save(temp);
		temp = new Subject();
		temp.setLevel("Bachelor");
		temp.setName("Physics");
		subjectDao.save(temp);
		temp = new Subject();
		temp.setLevel("Bachelor");
		temp.setName("Linguistic");
		subjectDao.save(temp);
		temp = new Subject();
		temp.setLevel("Bachelor");
		temp.setName("History");
		subjectDao.save(temp);
		temp = new Subject();
		temp.setLevel("Bachelor");
		temp.setName("Philosophy");
		subjectDao.save(temp);
		temp = new Subject();
		temp.setLevel("Bachelor");
		temp.setName("Biology");
		subjectDao.save(temp);
		temp = new Subject();
		temp.setLevel("Bachelor");
		temp.setName("Chemistry");
		subjectDao.save(temp);
		temp = new Subject();
		temp.setLevel("Bachelor");
		temp.setName("Economics");
		subjectDao.save(temp);
		temp = new Subject();
		temp.setLevel("Bachelor");
		temp.setName("Law");
		subjectDao.save(temp);
	}

	public boolean subjectsAreEmpty() {
		if(subjectDao.findOne((long) 1) == null){return true;}
		
		return false;
	}
	
	public boolean universitiesAreEmpty() {
		if(universityDao.findOne((long) 1) == null){return true;}
		
		return false;
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
