package ch.unibe.ese.controller;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ch.unibe.ese.controller.exceptions.InvalidUserException;
import ch.unibe.ese.controller.pojos.RefinedSearchForm;
import ch.unibe.ese.controller.service.DataService;
import ch.unibe.ese.controller.service.LectureSearchService;
import ch.unibe.ese.controller.service.NotificationService;
import ch.unibe.ese.controller.service.StudentSearchService;
import ch.unibe.ese.model.Lecture;
import ch.unibe.ese.model.Notification;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.Subject;
import ch.unibe.ese.model.University;


/**
 * This is for the basic search. Before a user gets to the refined search,
 * he has a simple search field on every page where he can enter the subject.
 * When entered, he gets redirected to the refined search page with already the 
 * basic parameters set.
 * @author ESE Team 8
 * @version 1.0
 * @since 28.10.2015
 */
@Controller
public class SearchController {
	
	@Autowired
	DataService dataService;
	@Autowired
	NotificationService notificationService;
	
	@Autowired 
	LectureSearchService lectureSearchService;

	
	@Autowired
	StudentSearchService studentSearchService;
	
	private Student tempTutor;
	private String tempLectureName;
	
	/**
	 * This method reads all Lectures from the database, saves them 
	 * in a List and returns that List. 
	 * @return List with all Lecures included
	 */
	@ModelAttribute("lectures")
	public List<Lecture> allLectures(){		
		return lectureSearchService.getAllLectures();
	}
	
	/**
	 * This method reads all Universities from the database, saves them 
	 * in a List and returns that List. 
	 * @return List with all Universities included
	 */
	@ModelAttribute("universities")
	public List<University> allUniversities(){
		return dataService.getAllUniversities();
	}
	
	/**
	 * This method reads all Subjects from the database, saves them 
	 * in a List and returns that List. 
	 * @return List with all Subjects included
	 */
	@ModelAttribute("subjects")
	public List<Subject> allSubjects(){
		return dataService.getAllSubjects();
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
	
    
    @RequestMapping(value = "/basicSearch", method = RequestMethod.GET)
    public ModelAndView basicSearch(@RequestParam("q") String term) {
    	ModelAndView model;
    	Iterable<Lecture> lecturesTemp = lectureSearchService.findLecturesByName(term);
		List<Student> tutors = new LinkedList<Student>();
		List<Lecture> lectures = new LinkedList<Lecture>();
		for(Lecture temp : lecturesTemp){
			tutors.add(studentSearchService.findTutorById(temp.getTutor().getId()));
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
        	model.addObject("lectures", studentSearchService.findTutorById(id).getLectures().toArray());
        	model.addObject("timelapses", studentSearchService.findTutorById(id).getTimeframes().toArray());
        	tempTutor = studentSearchService.findTutorById(id);
        	model.addObject("student",tempTutor);
        	

        } catch (InvalidUserException e) {
        	model = new ModelAndView("index");
        	model.addObject("page_error", e.getMessage());
        } 	
    	return model;
    }
    
    @RequestMapping(value = "/contact", method = RequestMethod.GET)
	 public ModelAndView contact(Principal principal) {
    	try
    	{
    	Notification notification = ch.unibe.ese.model.factory.NotificationFactory.getContactNotification(studentSearchService.getStudentByUsername(principal.getName()).getId(),tempLectureName, tempTutor.getId());
    	tempTutor.addNotification(notification);
    	notification = notificationService.saveNotification(notification);
    	tempTutor = studentSearchService.saveStudentIntoDB(tempTutor);
    	ModelAndView model = new ModelAndView("/show");
    	model.addObject("text","Notification was sent!");
    	return model;
    	}
    	catch(NullPointerException n)
    	{
    		ModelAndView model = new ModelAndView("/login");
    		return model;
    	}		
	 }
}