package ch.unibe.ese.controller;

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
import ch.unibe.ese.controller.pojos.RefinedSearchForm;
import ch.unibe.ese.model.Lecture;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.Subject;
import ch.unibe.ese.model.University;
import ch.unibe.ese.model.dao.LectureDao;
import ch.unibe.ese.model.dao.NotificationDao;
import ch.unibe.ese.model.dao.StudentDao;
import ch.unibe.ese.model.dao.SubjectDao;
import ch.unibe.ese.model.dao.UniversityDao;

/**
 * A User has to be able to search for a Tutor to fit his needs.
 * For that, he must be able to search for a lecture and subject, define the 
 * University, set a minimum grade and be gender specific for those who
 * prefer a certain gender for a Tutor.
 * @author Stefan Jonas
 * @version 1.0
 * @since 4.11.2015
 */
@Controller
public class RefinedSearchController {

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

	/**
	 * This method reads all Lectured from the database, saves them 
	 * in a List and returns that List. 
	 * @return List with all Lectures included
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
	 * in a List and returns that List. 
	 * @return List with all Universities included
	 */
	@ModelAttribute("universities")
	public List<University> allUniversities() {
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
	public List<Subject> allSubjects() {
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
	public List<String> allGender() {
		List<String> allGender = new LinkedList<String>();
		allGender.add("doesn't matter");
		allGender.add("male");
		allGender.add("female");

		return allGender;
	}

	/**
	 * searches for Tutors with given parameters
	 * @param refSearchForm
	 * @param result
	 * @param redirectAttributes
	 * @return model with the results
	 */
	@RequestMapping(value = "/searchWFilters", method = RequestMethod.POST)
	public ModelAndView create(@Valid RefinedSearchForm refSearchForm, BindingResult result,
			RedirectAttributes redirectAttributes) {
		ModelAndView model;
		if (!result.hasErrors()) {
			try {
				
				Iterable<Lecture> lecturesTemp = null;
	

				if ((refSearchForm.getSubject() == -1) && (refSearchForm.getUniversity() == -1)) {
					lecturesTemp = lectureDao.findByNameAndGradeGreaterThan(refSearchForm.getName(), (refSearchForm.getMinGrade() - 0.01));
				}
				
				else if ((refSearchForm.getSubject() == -1) && (refSearchForm.getUniversity() != -1)){
					lecturesTemp = lectureDao.findByNameAndUniversity_idAndGradeGreaterThan(
							refSearchForm.getName(), refSearchForm.getUniversity(), (refSearchForm.getMinGrade()-0.01));
				} 
				
				else if ((refSearchForm.getSubject() != -1) && (refSearchForm.getUniversity() == -1)){
					lecturesTemp = lectureDao.findByNameAndSubject_idAndGradeGreaterThan(
							refSearchForm.getName(), refSearchForm.getSubject(),
							(refSearchForm.getMinGrade() - 0.01));
					
				}
				
				//subject and university given:
				else{
					 lecturesTemp = lectureDao.findByNameAndUniversity_idAndSubject_idAndGradeGreaterThan(
							refSearchForm.getName(), refSearchForm.getUniversity(), refSearchForm.getSubject(),
							(refSearchForm.getMinGrade() - 0.01) );
				}
				
				List<Student> tutors = new LinkedList<Student>();
				List<Lecture> lectures = new LinkedList<Lecture>();

				for (Lecture temp : lecturesTemp) {
					Student tempTut = studentDao.findOne(temp.getTutor().getId());

					if ((refSearchForm.getGender().equals(allGender().get(0)))
							|| refSearchForm.getGender().equals(tempTut.getGender())) {
						tutors.add(studentDao.findOne(temp.getTutor().getId()));
						lectures.add(temp);
					}
				}
				model = new ModelAndView("searchResult");

				model.addObject("tutors", tutors);
				model.addObject("lectures", lectures);

				RefinedSearchForm refSearchFormNew = new RefinedSearchForm();

				refSearchFormNew.setName(refSearchForm.getName());
				refSearchFormNew.setSubject(refSearchForm.getSubject());
				refSearchFormNew.setUniversity(refSearchForm.getUniversity());
				refSearchFormNew.setGender(refSearchForm.getGender());
				refSearchFormNew.setMinGrade(refSearchForm.getMinGrade());

				model.addObject("refinedSearchForm", refSearchFormNew);

				return model;

			} catch (InvalidUserException e) {
				model = new ModelAndView("searchInitialisation");
				model.addObject("page_error", e.getMessage());

			}
		} else {
			model = new ModelAndView("afterLogin");
		}

		return model;
	}

}