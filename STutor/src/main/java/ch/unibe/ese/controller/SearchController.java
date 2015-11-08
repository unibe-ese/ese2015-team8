package ch.unibe.ese.controller;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.unibe.ese.controller.exceptions.InvalidUserException;
import ch.unibe.ese.controller.pojos.RefinedSearchForm;
import ch.unibe.ese.controller.pojos.SearchForm;
import ch.unibe.ese.model.Lecture;
import ch.unibe.ese.model.Notification;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.Subject;
import ch.unibe.ese.model.University;
import ch.unibe.ese.model.dao.LectureDao;
import ch.unibe.ese.model.dao.NotificationDao;
import ch.unibe.ese.model.dao.StudentDao;
import ch.unibe.ese.model.dao.SubjectDao;
import ch.unibe.ese.model.dao.UniversityDao;

/**
 * This is for the basic search. Before a user gets to the refined search,
 * he has a simple search field on every page where he can enter the subject.
 * When entered, he gets redirected to the refined search page with already the 
 * basic parameters set.
 * @author Till Schnabel, Christian Zürcher
 * @version 1.0
 * @since 28.10.2015
 */
@Controller
public class SearchController {
	
	@Autowired
	StudentDao studentDao;
	
	@Autowired
	LectureDao lectureDao;
	
	@Autowired
	UniversityDao universityDao;
	
	@Autowired
	SubjectDao subjectDao;
	
	@Autowired
	NotificationDao notificationDao;
	
	private Student tempTutor;
	private String tempLectureName;
	
	/**
	 * This method reads all Lectures from the database, saves them 
	 * in a List and returns that List. 
	 * @return List with all Lecures included
	 */
	@ModelAttribute("lectures")
	public List<Lecture> allLectures(){
		List<Lecture> allLectures = new LinkedList<Lecture>();
		Iterable<Lecture> lectures = lectureDao.findAll();
		
		for (Lecture lecture : lectures) {
			allLectures.add(lecture);
		}
		
		return allLectures;
	}
	
	/**
	 * This method reads all Universities from the database, saves them 
	 * in a List and returns that List. 
	 * @return List with all Universities included
	 */
	@ModelAttribute("universities")
	public List<University> allUniversities(){
		List<University> allUniversities = new LinkedList<University>();
		Iterable<University> universities = universityDao.findAll();
		
		for (University university : universities) {
			allUniversities.add(university);
		}
		
		return allUniversities;
	}
	
	/**
	 * This method reads all Subjects from the database, saves them 
	 * in a List and returns that List. 
	 * @return List with all Subjects included
	 */
	@ModelAttribute("subjects")
	public List<Subject> allSubjects(){
		List<Subject> allSubjects = new LinkedList<Subject>();
		Iterable<Subject> subjects = subjectDao.findAll();
		
		for (Subject subject : subjects) {
			allSubjects.add(subject);
		}
		
		return allSubjects;
	}	
	
	/**
	 * choices to either choose gender or let it open
	 * @return list with these three choices
	 */
	@ModelAttribute("gender")
	public List<String> allGender(){
		List<String> allGender = new LinkedList<String>();
		allGender.add("doesn't matter");
		allGender.add("male");
		allGender.add("female");
	
		return allGender;
	}	
	
    @RequestMapping(value="/searchInitialisation", method = RequestMethod.GET)
	public ModelAndView search() {
    	ModelAndView model = new ModelAndView("search");
		model.addObject("searchForm", new SearchForm());
		return model;
	}
    
    @RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView create(@Valid SearchForm searchForm, BindingResult result, RedirectAttributes redirectAttributes) {
		ModelAndView model;
		if (!result.hasErrors()) {
			try {
				Iterable<Lecture> lecturesTemp = lectureDao.findByNameAndUniversity_idAndSubject_id(searchForm.getName(), searchForm.getUniversity(), searchForm.getSubject());
				List<Student> tutors = new LinkedList<Student>();
				List<Lecture> lectures = new LinkedList<Lecture>();
				for(Lecture temp : lecturesTemp){
					tutors.add(studentDao.findOne(temp.getTutor().getId()));
					lectures.add(temp);
				}
				model = new ModelAndView("searchResult");
				model.addObject("tutors", tutors);
				model.addObject("lectures",lectures);
				
				RefinedSearchForm refSearchForm = new RefinedSearchForm();
				refSearchForm.setName(searchForm.getName());
				refSearchForm.setSubject(searchForm.getSubject());
				refSearchForm.setUniversity(searchForm.getUniversity());
				model.addObject("refinedSearchForm", refSearchForm);
				
				tempLectureName = searchForm.getName();
											
				return model;

			} catch (InvalidUserException e) {
				model = new ModelAndView("searchInitialisation");
				model.addObject("page_error", e.getMessage());

			}
		} else {
			model = new ModelAndView("searchInitialisation");
		}

		return model;
	}
    
    @RequestMapping(value = "/basicSearch", method = RequestMethod.GET)
    public ModelAndView basicSearch(@RequestParam("q") String term) {
    	ModelAndView model;
    	Iterable<Lecture> lecturesTemp = lectureDao.findByName(term);
		List<Student> tutors = new LinkedList<Student>();
		List<Lecture> lectures = new LinkedList<Lecture>();
		for(Lecture temp : lecturesTemp){
			tutors.add(studentDao.findOne(temp.getTutor().getId()));
			lectures.add(temp);
		}
		model = new ModelAndView("searchResult");
		model.addObject("tutors", tutors);
		model.addObject("lectures",lectures);
		
		RefinedSearchForm refSearchForm = new RefinedSearchForm();
		refSearchForm.setName(term);
		model.addObject("refinedSearchForm", refSearchForm);
		
		return model;
    }
    
    @RequestMapping(value = "/hiddenProfile", method = RequestMethod.GET)
    public ModelAndView profile(@RequestParam("userId") long id) {
    	ModelAndView model;
        try {
        	model = new ModelAndView("hiddenProfile");
        	model.addObject("lectures", studentDao.findOne(id).getLectures().toArray());
        	model.addObject("timelapses", studentDao.findOne(id).getTimelapses().toArray());
        	tempTutor = studentDao.findOne(id);
        	model.addObject("student",tempTutor);
        	

        } catch (InvalidUserException e) {
        	model = new ModelAndView("index");
        	model.addObject("page_error", e.getMessage());
        } 	
    	return model;
    }
    
    @RequestMapping(value = "/contact", method = RequestMethod.GET)
	 public ModelAndView contact(Principal principal) {
    	Notification notification = ch.unibe.ese.model.factory.NotificationFactory.getContactNotification(studentDao.findByUsername(principal.getName()).getId(),tempLectureName, tempTutor.getId());
    	tempTutor.addNotification(notification);
    	notification = notificationDao.save(notification);
    	tempTutor = studentDao.save(tempTutor);
    	ModelAndView model = new ModelAndView("/show");
    	model.addObject("text","Notification was sent!");
		return model;
	 }
}