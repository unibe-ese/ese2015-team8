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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.unibe.ese.controller.exceptions.InvalidUserException;
import ch.unibe.ese.controller.pojos.AddLectureForm;
import ch.unibe.ese.controller.service.SampleService;
import ch.unibe.ese.model.Lecture;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.dao.LectureDao;
import ch.unibe.ese.model.dao.StudentDao;

@Controller
public class AddLectureController {

	@Autowired
	SampleService sampleService;

	@Autowired
	StudentDao studentDao;
	
	@Autowired
	LectureDao lectureDao;
	
	@ModelAttribute("lectures")
	public List<Lecture> allTeams(){
		List<Lecture> allLectures = new LinkedList<Lecture>();
		Iterable<Lecture> lectures = lectureDao.findAll();
		
		for (Lecture lecture : lectures) {
			allLectures.add(lecture);
		}
		
		return allLectures;
	}



	@RequestMapping(value = "/addLecture", method = RequestMethod.GET)
	public ModelAndView newAccount() {
		ModelAndView model = new ModelAndView("addLecture");
		model.addObject("addLectureForm", new AddLectureForm());
		return model;
	}

	@RequestMapping(value = "/addedLecture", method = RequestMethod.POST)
	public ModelAndView create(@Valid AddLectureForm lectureForm, BindingResult result,
			RedirectAttributes redirectAttributes, Principal principal) {
		ModelAndView model;
		if (!result.hasErrors()) {
			try {
				String username = principal.getName();
				Student loggedInTutor = studentDao.findByUsername(username);
				sampleService.saveTutorLecture(lectureForm, loggedInTutor);
				model = new ModelAndView("profile");
				return model;

			} catch (InvalidUserException e) {
				model = new ModelAndView("newAccount");
				model.addObject("page_error", e.getMessage());
			
				
			}
		} else {
			model = new ModelAndView("newAccount");
		}

		return model;
	}
	

//	@RequestMapping(value = "/tutor_signUp", method = RequestMethod.GET)
//	public ModelAndView tutorSignUp() {
//		ModelAndView model = new ModelAndView("tutor_signUp");
//		model.addObject("tutorSignupForm", new TutorSignupForm());
//		return model;
//	}
//
//	@RequestMapping(value = "/createTutor", method = RequestMethod.POST)
//	public ModelAndView createTutor(@Valid TutorSignupForm tutorSignupForm, BindingResult result,
//			RedirectAttributes redirectAttributes) {
//		ModelAndView model;
//		try {
//			sampleService.saveTutorFrom(tempSignupForm, tutorSignupForm);
//			tempSignupForm = null;
//			model = new ModelAndView("show");
//		} catch (InvalidUserException e) {
//			model = new ModelAndView("tutor_signUp");
//			model.addObject("page_error", e.getMessage());
//		}
//		return model;
//	}

}
