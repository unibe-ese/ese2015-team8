package ch.unibe.ese.controller;

import java.security.Principal;
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
import org.springframework.web.servlet.view.RedirectView;

import ch.unibe.ese.controller.exceptions.InvalidGradeException;
import ch.unibe.ese.controller.exceptions.InvalidUserException;
import ch.unibe.ese.controller.pojos.LectureForm;
import ch.unibe.ese.controller.service.DataService;
import ch.unibe.ese.controller.service.LectureSearchService;
import ch.unibe.ese.controller.service.LectureService;
import ch.unibe.ese.controller.service.StudentSearchService;
import ch.unibe.ese.model.Lecture;
import ch.unibe.ese.model.Subject;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.University;

/**
 * 
 * 
 * This controller handles the mapping to the relevant pages to add a lecture
 * and the act of adding a lecture as a tutor through a form, @see lectureForm.
 * 
 * @author ESE Team 8
 * @version 1.0
 * @since 28.10.2015

 */
@Controller
public class LectureController {


	@Autowired
	LectureService lectureService;
	
	@Autowired
	DataService dataService;
	
	@Autowired
	LectureSearchService lectureSearchService;

	@Autowired
	StudentSearchService studentSearchService;

	private long lectureId;

	/**
	 * This method reads all lectures from the database, saves them 
	 * in a List and returns that List
	 * @return List with all lectures included
	 */
	@ModelAttribute("lectures")
	public List<Lecture> allLectures(){		
		return lectureSearchService.getAllLectures();
	}

	/**
	 * This method reads all Universities from the database, saves them 
	 * in a List and returns that List. If there's no University yet, 
	 * the default Universities UNIBE, ETHZ and EPFL are created.
	 * @return List with all Universities included
	 */
	@ModelAttribute("universities")
	public List<University> allUniversities() {
		if (dataService.universitiesAreEmpty()){
			dataService.initializeUniversities();
		}
		
		return dataService.getAllUniversities();

	}


	/**
	 * This method reads all Subjects from the database, saves them 
	 * in a List and returns that List. If there's no Subject yet, 
	 * the default Subjects UNIBE, ETHZ and EPFL are created.
	 * @return List with all Subjects included
	 */
	@ModelAttribute("subjects")
	public List<Subject> allSubjects() {
		if (dataService.subjectsAreEmpty()){
			dataService.initializeSubjects();
		}
		
		return dataService.getAllSubjects();
	}


	/**
	 * This method handles the request to access the addLecture page. 
	 * This page has been made available only to tutors, @see springSecurity.xml
	 * @return model with a new LectureForm
	 */
	@RequestMapping(value = "/addLecture", method = RequestMethod.GET)
	public ModelAndView addLecture(Principal principal) {
		ModelAndView model = new ModelAndView("addLecture");
		Student user = studentSearchService.getStudentByUsername(principal.getName());
		model.addObject("lectureForm", new LectureForm());
		model.addObject("user", user);
		return model;
	}

	/**
	 * If the Tutor has filled in the form and applied it, this method tries to
	 * process what he's filled in. If he did everything correctly, the right lecture
	 * gets added to his personal lectures. If a wrong grade is entered or the user 
	 * is not correct and if there are no other result errors, this should be the case.
	 * @param lectureForm
	 * @param result
	 * @param redirectAttributes
	 * @param principal
	 * @return the model with the user's new parameters
	 */
	@RequestMapping(value = "/addedLecture", method = RequestMethod.POST)
	public ModelAndView create(@Valid LectureForm lectureForm, BindingResult result,
			RedirectAttributes redirectAttributes, Principal principal) {
		ModelAndView model;
		if (!result.hasErrors()) {
			try {
				Student loggedInTutor = studentSearchService.getStudentByUsername(principal.getName());
				lectureService.saveFrom(lectureForm, loggedInTutor);
				model = new ModelAndView(new RedirectView("profile"), "userId", loggedInTutor.getId());
			} catch (InvalidUserException e) {
				model = new ModelAndView("addLecture");
				model.addObject("page_error", e.getMessage());
			}
		
			catch (InvalidGradeException exc) {
				model = new ModelAndView("addLecture");
				model.addObject("page_error", exc.getMessage());
			}
		}
		

		else {
			model = new ModelAndView("addLecture");
		}

		return model;
	}




	/**
	 * If the tutor has mistyped a lecture or filled something else in wrong, he can later edit
	 * this lecture. That's what this method is for. 
	 * @param lectureId The id (long) of the specific lecture to edit
	 * @param principal all information about current user etc. 
	 * @return the model with the to be edited lecture form
	 */
	@RequestMapping(value = "/editLecture", method = RequestMethod.GET)
	public ModelAndView editLecture(@RequestParam("id") long lectureId, Principal principal) {
		
		Student loggedInTutor = studentSearchService.getStudentByUsername(principal.getName());
		Lecture chosenLecture = lectureSearchService.findById(lectureId);
		ModelAndView model;
		
		if(!chosenLecture.getTutor().getUsername().contentEquals(loggedInTutor.getUsername())){
			model = new ModelAndView("accessDenied");
		}
		

		
		else{
		model = new ModelAndView("editLecture");
		this.lectureId = lectureId;
		LectureForm editForm = new LectureForm();
		editForm.setName(chosenLecture.getName());
		editForm.setSubject(chosenLecture.getSubject().getId());
		editForm.setUniversity(chosenLecture.getUniversity().getId());
		editForm.setGrade(chosenLecture.getGrade());
		
		model.addObject("lectureForm", editForm);
		model.addObject("user", loggedInTutor);
		}
		
		return model;
	}
	
	/**This method checks if the edited lecture was edited correctly and if so, it's saved in the db
	 * @param lectureForm 
	 * @param result
	 * @param redirectAttributes
	 * @param principal for user info
	 * @return model with edited lecture form
	 */
	@RequestMapping(value = "/editedLecture", method = RequestMethod.POST)
	public ModelAndView editedLecture(@Valid LectureForm lectureForm, BindingResult result,
			RedirectAttributes redirectAttributes, Principal principal) {
		ModelAndView model;
		if (!result.hasErrors()) {
			try {
				Student loggedInTutor = studentSearchService.getStudentByUsername(principal.getName());
				lectureService.editFrom(lectureForm, lectureId);
				model = new ModelAndView(new RedirectView("profile"), "userId", loggedInTutor.getId());
				return model;

			} catch (InvalidUserException e) {
				model = new ModelAndView("editLecture");
				model.addObject("page_error", e.getMessage());
			}
		
			catch (InvalidGradeException exc) {
				model = new ModelAndView("editLecture");
				model.addObject("page_error", exc.getMessage());
			}
		}
		

		else {
			model = new ModelAndView("editLecture");
		}

		return model;
	}
	
	/**
	 * If a tutor added a wrong lecture he can delete it afterwards
	 * @param lectureId id (long) of the specific to be deleted lecture
	 * @param principal
	 * @return model of profile page
	 */
	@RequestMapping(value = "/deleteLecture", method = RequestMethod.GET)
	public ModelAndView deletedLecture(@RequestParam("id") long lectureId, Principal principal) {
		Student loggedInTutor = studentSearchService.getStudentByUsername(principal.getName());
		
		ModelAndView model;
		
		Lecture lecture = lectureSearchService.findById(lectureId);

		if(!lecture.getTutor().getUsername().contentEquals(loggedInTutor.getUsername())){
			model = new ModelAndView("accessDenied");
		}
		else{
		
		lecture = lectureService.remove(lecture);
		
		model = new ModelAndView("redirect:" + "/profile?userId="+loggedInTutor.getId());
		}
		
		return model;
	}

}
