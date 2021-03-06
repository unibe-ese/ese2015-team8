package ch.unibe.ese.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import ch.unibe.ese.controller.exceptions.InvalidUserException;
import ch.unibe.ese.controller.pojos.OptionForm;
import ch.unibe.ese.controller.service.OptionService;
import ch.unibe.ese.controller.service.SignUpService;
import ch.unibe.ese.controller.service.StudentSearchService;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.security.service.CustomUserDetailsService;

/**
 * A user can edit his information by using the option page that is 
 * handled by this controller. The user can even change his being a 
 * Tutor or not and add a gender.
 * @author ESE Team 8
 * @version 1.0
 * @since 4.11.2015
 */
@Controller
public class OptionController {
	
	@Autowired OptionService optionService;
	@Autowired SignUpService signUpService;
	@Autowired CustomUserDetailsService userDetailsService;
	@Autowired StudentSearchService studentSearchService;
	
	/**
	 * loads option form for given user
	 * @param principal
	 * @return model with form
	 */
	@RequestMapping("/options")
	public ModelAndView notifications(Principal principal) {
		ModelAndView model= new ModelAndView("options");
		try{
			model.addObject("student", studentSearchService.getStudentByUsername(principal.getName()));
			model.addObject("optionForm",optionService.getFrom(principal.getName()));
		}catch(NullPointerException e){
			model = new ModelAndView("redirect:/");
		}
		return model;
	}
	
	/**
	 * Saves the user's new parameters if there are no errors and redirects to profile page.
	 * <p>Password has to be handled specially because of the the Spring Security.
	 * @param optionForm
	 * @param result
	 * @param redirectAttributes
	 * @param principal
	 * @return model with profile page etc.
	 */
	@RequestMapping("/optionsSaved")
	public ModelAndView saveOptions(@Valid OptionForm optionForm, BindingResult result,
			RedirectAttributes redirectAttributes, Principal principal) {
		ModelAndView model;
		Student student = studentSearchService.getStudentByUsername(principal.getName());
		
		if (!result.hasErrors()) {
			try {
				if((optionForm.getPassword()).equals(""))
					optionService.saveStudentFrom(student,optionForm,false);
				else{
					optionService.saveStudentFrom(student,optionForm,true);
					userDetailsService.encryptePassword(principal.getName());
				}
				model = new ModelAndView(new RedirectView("profile"), "userId", student.getId());
			}catch(InvalidUserException e) {
				model = new ModelAndView("options");
				model.addObject("page_error", e.getMessage());
			}
		}else{
			model = new ModelAndView("options");
			model.addObject("student", student);
			model.addObject("optionForm",optionForm);
		}
		return model;
	}
}