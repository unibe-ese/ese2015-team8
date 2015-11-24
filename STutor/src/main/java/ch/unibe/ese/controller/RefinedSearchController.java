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
import ch.unibe.ese.controller.service.DataService;
import ch.unibe.ese.controller.service.LectureSearchService;
import ch.unibe.ese.controller.service.StudentSearchService;
import ch.unibe.ese.model.Lecture;
import ch.unibe.ese.model.Subject;
import ch.unibe.ese.model.University;

/**
 * A User has to be able to search for a Tutor to fit his needs. For that, he
 * must be able to search for a lecture and subject, define the University, set
 * a minimum grade and be gender specific for those who prefer a certain gender
 * for a Tutor.
 * 
 * @author ESE Team 8
 * @version 1.0
 * @since 4.11.2015
 */
@Controller
public class RefinedSearchController {

	@Autowired
	DataService dataService;

	@Autowired
	StudentSearchService studentSearchService;
	
	@Autowired
	LectureSearchService lectureSearchService;

	/**
	 * This method reads all Lectured from the database, saves them in a List
	 * and returns that List.
	 * 
	 * @return List with all Lectures included
	 */
	@ModelAttribute("lectures")
	public List<Lecture> allLectures() {
		return lectureSearchService.getAllLectures();
	}

	/**
	 * This method reads all Universities from the database, saves them in a
	 * List and returns that List.
	 * 
	 * @return List with all Universities included
	 */
	@ModelAttribute("universities")
	public List<University> allUniversities() {
		return dataService.getAllUniversities();
	}

	/**
	 * This method reads all Subjects from the database, saves them in a List
	 * and returns that List.
	 * 
	 * @return List with all Subjects included
	 */
	@ModelAttribute("subjects")
	public List<Subject> allSubjects() {
		return dataService.getAllSubjects();
	}

	/**
	 * choices to either choose gender or let it open
	 * 
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
	 * 
	 * Notice that due to the double standard in Java, we can't search for
	 * greaterOrEqual, but rather search for a greater grade than the minGrade,
	 * which is decreased by a very small number, this way it's basically the
	 * same as a greaterOrEqual search.
	 * 
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
				
				String sortBy = refSearchForm.getSortBy();
				String lectureName = refSearchForm.getName();
				Double minGrade = refSearchForm.getMinGrade();
				Iterable<Lecture> lecturesTemp = null;

				// ids with -1 as value represent nonexistent values, so it's
				// like "doesn't matter" which subject/uni etc.

				if ((refSearchForm.getSubject() == -1) && (refSearchForm.getUniversity() == -1)) {
					lecturesTemp = lectureSearchService.findByNameAndGradeGreaterThan(lectureName,
							minGrade, sortBy);
				}

				else if ((refSearchForm.getSubject() == -1) && (refSearchForm.getUniversity() != -1)) {
					lecturesTemp = lectureSearchService.findByNameAndUniversityAndGradeGreaterThan(lectureName,
							refSearchForm.getUniversity(), minGrade, sortBy);
				}

				else if ((refSearchForm.getSubject() != -1) && (refSearchForm.getUniversity() == -1)) {
					lecturesTemp = lectureSearchService.findByNameAndSubjectAndGradeGreaterThan(lectureName,
							refSearchForm.getSubject(), minGrade, sortBy);

				}

				// subject and university given:
				else {
					lecturesTemp = lectureSearchService.findByNameAndUniversityAndSubjectAndGradeGreaterThan(
							lectureName, refSearchForm.getUniversity(), refSearchForm.getSubject(),
							minGrade, sortBy);
				}

				List<Lecture> lectures = new LinkedList<Lecture>();
				
				for (Lecture lecture : lecturesTemp) {
					lectures.add(lecture);
				}

				model = new ModelAndView("searchResult");
				model.addObject("lectures", lectures);

				RefinedSearchForm refSearchFormNew = new RefinedSearchForm();

				refSearchFormNew.setName(refSearchForm.getName());
				refSearchFormNew.setSubject(refSearchForm.getSubject());
				refSearchFormNew.setUniversity(refSearchForm.getUniversity());
				refSearchFormNew.setGender(refSearchForm.getGender());
				refSearchFormNew.setMinGrade(refSearchForm.getMinGrade());
				refSearchFormNew.setSortBy(refSearchForm.getSortBy());

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