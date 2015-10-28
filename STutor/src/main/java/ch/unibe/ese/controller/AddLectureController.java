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
import ch.unibe.ese.controller.pojos.LectureForm;
import ch.unibe.ese.controller.service.SampleService;
import ch.unibe.ese.model.Lecture;
import ch.unibe.ese.model.Subject;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.University;
import ch.unibe.ese.model.dao.LectureDao;
import ch.unibe.ese.model.dao.StudentDao;
import ch.unibe.ese.model.dao.SubjectDao;
import ch.unibe.ese.model.dao.UniversityDao;

@Controller
public class AddLectureController {

	@Autowired
	SampleService sampleService;

	@Autowired
	StudentDao studentDao;
	
	@Autowired
	LectureDao lectureDao;
	
	@Autowired
	UniversityDao universityDao;
	
	@Autowired
	SubjectDao subjectDao;
	
	@ModelAttribute("lectures")
	public List<Lecture> allLectures(){
		List<Lecture> allLectures = new LinkedList<Lecture>();
		Iterable<Lecture> lectures = lectureDao.findAll();
		
		for (Lecture lecture : lectures) {
			allLectures.add(lecture);
		}
		
		return allLectures;
	}
	
	@ModelAttribute("universities")
	public List<University> allUniversities(){
		List<University> allUniversities = new LinkedList<University>();
		Iterable<University> universities = universityDao.findAll();
		
		for (University university : universities) {
			allUniversities.add(university);
		}
		
		return allUniversities;
	}
	
	@ModelAttribute("subjects")
	public List<Subject> allSubjects(){
		List<Subject> allSubjects = new LinkedList<Subject>();
		Iterable<Subject> subjects = subjectDao.findAll();
		
		for (Subject subject : subjects) {
			allSubjects.add(subject);
		}
		
		return allSubjects;
	}

	@RequestMapping(value = "/addLecture", method = RequestMethod.GET)
	public ModelAndView addLecture() {
		ModelAndView model = new ModelAndView("addLecture");
		model.addObject("lectureForm", new LectureForm());
		return model;
	}

	@RequestMapping(value = "/addedLecture", method = RequestMethod.POST)
	public ModelAndView create(@Valid LectureForm lectureForm, BindingResult result, RedirectAttributes redirectAttributes, Principal principal) {
		ModelAndView model;
		if (!result.hasErrors()) {
			try {
				String username = principal.getName();
				Student loggedInTutor = studentDao.findByUsername(username);
				sampleService.saveFrom(lectureForm, loggedInTutor);
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
}