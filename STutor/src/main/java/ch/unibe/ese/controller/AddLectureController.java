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
import org.springframework.web.servlet.view.RedirectView;

import ch.unibe.ese.controller.exceptions.InvalidGradeException;
import ch.unibe.ese.controller.exceptions.InvalidUserException;
import ch.unibe.ese.controller.pojos.LectureForm;
import ch.unibe.ese.controller.service.AddLectureService;
import ch.unibe.ese.model.Lecture;
import ch.unibe.ese.model.Subject;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.University;
import ch.unibe.ese.model.dao.LectureDao;
import ch.unibe.ese.model.dao.StudentDao;
import ch.unibe.ese.model.dao.SubjectDao;
import ch.unibe.ese.model.dao.UniversityDao;

/**
 * 
 * @author Christian Zürcher
 * @version 1.0
 * @since 28.10.2015
 * This Controller handles the request to add a lecture, which can only
 * be done by a tutor, of course.
 */
@Controller
public class AddLectureController {

	@Autowired
	AddLectureService addLectureService;

	@Autowired
	StudentDao studentDao;

	@Autowired
	LectureDao lectureDao;

	@Autowired
	UniversityDao universityDao;

	@Autowired
	SubjectDao subjectDao;

	/**
	 * This method reads all lectures from the database, saves them 
	 * in a List and returns that List
	 * @return List with all lectures included
	 */
	@ModelAttribute("lectures")
	public List<Lecture> allLectures() {
		List<Lecture> allLectures = new LinkedList<Lecture>();
		Iterable<Lecture> lectures = lectureDao.findAll();

		for (Lecture lecture : lectures) {
			allLectures.add(lecture);
		}

		return allLectures;
	}

	/**
	 * This method reads all Universities from the database, saves them 
	 * in a List and returns that List. If there's no University yet, 
	 * the default Universities UNIBE, ETHZ and EPFL are created.
	 * @return List with all Universities included
	 */
	@ModelAttribute("universities")
	public List<University> allUniversities() {
		if (universityDao.findOne((long) 1) == null)
			initializeUniversities();
		List<University> allUniversities = new LinkedList<University>();
		Iterable<University> universities = universityDao.findAll();

		for (University university : universities) {
			allUniversities.add(university);
		}

		return allUniversities;
	}

	/**
	 * creates default Universities
	 */
	private void initializeUniversities() {
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

	/**
	 * This method reads all Subjects from the database, saves them 
	 * in a List and returns that List. If there's no Subject yet, 
	 * the default Subjects UNIBE, ETHZ and EPFL are created.
	 * @return List with all Subjects included
	 */
	@ModelAttribute("subjects")
	public List<Subject> allSubjects() {
		if (subjectDao.findOne((long) 1) == null)
			initializeSubjects();
		List<Subject> allSubjects = new LinkedList<Subject>();
		Iterable<Subject> subjects = subjectDao.findAll();

		for (Subject subject : subjects) {
			allSubjects.add(subject);
		}

		return allSubjects;
	}

	/**
	 * creates default Subjects
	 */
	private void initializeSubjects() {
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

	/**
	 * This method handles the request to access the addLecture page. If
	 * the user is only a Student, his access is denied. If he's a Tutor,
	 * the model with the right lecture form gets returned
	 * @param principal
	 * @return model with a new LectureForm
	 */
	@RequestMapping(value = "/addLecture", method = RequestMethod.GET)
	public ModelAndView addLecture() {
		ModelAndView model = new ModelAndView("addLecture");
		model.addObject("lectureForm", new LectureForm());
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
				Student loggedInTutor = studentDao.findByUsername(principal.getName());
				addLectureService.saveFrom(lectureForm, loggedInTutor);
				model = new ModelAndView(new RedirectView("profile"), "userId", loggedInTutor.getId());
				return model;

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
}
