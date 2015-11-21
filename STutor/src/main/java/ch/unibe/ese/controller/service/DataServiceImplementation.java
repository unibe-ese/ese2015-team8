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
	}

	public boolean subjectsAreEmpty() {

		if(subjectDao.findOne((long) 1) == null){return true;}
		
		return false;
	}
	
	public boolean universitiesAreEmpty() {

		if(universityDao.findOne((long) 1) == null){return true;}
		
		return false;
	}

}
