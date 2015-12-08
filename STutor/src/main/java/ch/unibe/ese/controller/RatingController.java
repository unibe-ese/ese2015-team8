package ch.unibe.ese.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.unibe.ese.controller.exceptions.InvalidDataException;
import ch.unibe.ese.controller.pojos.CommentForm;
import ch.unibe.ese.controller.service.CommentService;
import ch.unibe.ese.controller.service.NotificationService;
import ch.unibe.ese.controller.service.StudentSearchService;
import ch.unibe.ese.model.Notification;
import ch.unibe.ese.model.Student;

/**
 * Tutors can be rated by all Students they have given lessons to. 
 * Of all ratings a medium is taken and visible to all Students.
 * @author ESE Team 8
 * @version 1.0
 * @since 4.11.2015
 */
@Controller
public class RatingController {

	
	@Autowired StudentSearchService studentSearchService;
	@Autowired CommentService commentService;
	@Autowired NotificationService notificationService;
	
	private Student actualTutor;
	private Notification actualNotification;
	
	/**
	 * form to rate Tutor
	 * @param id
	 * @return model
	 */
	@RequestMapping(value="/rateTutor", method = RequestMethod.GET)
	public ModelAndView rateTutor(@RequestParam("notificationId") long id) {
		actualNotification = notificationService.findById(id);
		actualTutor = studentSearchService.findTutorById(actualNotification.getFromStudentId());
		ModelAndView model = new ModelAndView("rateTutor");
		model.addObject("commentForm",new CommentForm());
		model.addObject("tutor",actualTutor);
		return model;
	}
	
	/**
	 * saves the rating if there are no errors, like empty field or wrong number for rating
	 * @param commentForm
	 * @param result
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="/saveTutorRating")
	public ModelAndView saveTutorRating(@Valid CommentForm commentForm, BindingResult result, RedirectAttributes redirectAttributes) {
		ModelAndView model;
		try{
			actualTutor.addComment(commentService.getFrom(commentForm));
			actualTutor = studentSearchService.saveStudentIntoDB(actualTutor);
			notificationService.remove(actualNotification, studentSearchService.findTutorById(actualNotification.getToStudentId()));
			model = new ModelAndView("/show");
			model.addObject("text","Comment Added Sucessfully!");
		}catch(InvalidDataException e){
			model = new ModelAndView("rateTutor");
        	model.addObject("page_error", e.getMessage());
		}
		return model;
	}
}
