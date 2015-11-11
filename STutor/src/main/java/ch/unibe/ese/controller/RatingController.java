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
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.dao.StudentDao;

/**
 * Tutors can be rated by all Students they have given lessons to. 
 * Of all ratings a medium is taken and visible to all Students.
 * @author ESE Team 8
 * @version 1.0
 * @since 4.11.2015
 */
@Controller
public class RatingController {
	
	@Autowired StudentDao studentDao;
	
	@Autowired CommentService commentService;
	
	private Student acctualTutor;
	
	/**
	 * form to rate Tutor
	 * @param id
	 * @return model
	 */
	@RequestMapping(value="/rateTutor", method = RequestMethod.GET)
	public ModelAndView rateTutor(@RequestParam("tutorId") long id) {
		acctualTutor = studentDao.findOne(id);
		ModelAndView model = new ModelAndView("rateTutor");
		model.addObject("commentForm",new CommentForm());
		model.addObject("tutor",acctualTutor);
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
			acctualTutor.addComment(commentService.getFrom(commentForm));
			acctualTutor = studentDao.save(acctualTutor);
			model = new ModelAndView("/show");
			model.addObject("text","Comment Added Sucessfully!");
		}catch(InvalidDataException e){
			model = new ModelAndView("rateTutor");
        	model.addObject("page_error", e.getMessage());
		}
		
		return model;
	}
}
