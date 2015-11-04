package ch.unibe.ese.admin;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ch.unibe.ese.model.Subject;
import ch.unibe.ese.model.University;
import ch.unibe.ese.model.dao.SubjectDao;
import ch.unibe.ese.model.dao.UniversityDao;

public class AdminData {
	
	@Autowired UniversityDao universityDao;
	@Autowired SubjectDao subjectDao;
	
	private static final double CONTACT_PRICE = 5.0;
	
	private static final String[] temp = {"Master","Visa"};
	private static final List<String> CREDIT_CARDS = Arrays.asList(temp);

	public static double getContactprice() {
		return CONTACT_PRICE;
	}
	
	public static List<String> getCreditCards() {
		return CREDIT_CARDS;
	}
	
	public void initializeUniversities(){
		Subject temp = new Subject();
		temp.setLevel("Bachelor");
		temp.setName("Informatics");
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
	
	public void initializesSubjects(){
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
}
