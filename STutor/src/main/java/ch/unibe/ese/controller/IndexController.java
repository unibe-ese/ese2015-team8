package ch.unibe.ese.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.unibe.ese.controller.exceptions.InvalidUserException;
import ch.unibe.ese.controller.pojos.SignupForm;
import ch.unibe.ese.controller.pojos.TutorSignupForm;
import ch.unibe.ese.controller.service.SampleService;

@Controller
public class IndexController {
	
	private SignupForm tempSignupForm;

    @Autowired
    SampleService sampleService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
    	ModelAndView model = new ModelAndView("login");
        return model;
    }
    
    @RequestMapping(value = "/newAccount", method = RequestMethod.GET)
    public ModelAndView newAccount() {
    	ModelAndView model = new ModelAndView("newAccount");
    	model.addObject("signupForm", new SignupForm());
        return model;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid SignupForm signupForm, BindingResult result, RedirectAttributes redirectAttributes) {
    	ModelAndView model;    	
    	if (!result.hasErrors()) {
            try {
            	if(signupForm.getIsTutor()){
            		model = new ModelAndView("tutor_signUp");
            		tempSignupForm = signupForm;
            	}else{
            		sampleService.saveStudentFrom(signupForm);
            		model = new ModelAndView("show");
            	}
            } catch (InvalidUserException e) {
            	model = new ModelAndView("newAccount");
            	model.addObject("page_error", e.getMessage());
            }
        } else {
        	model = new ModelAndView("newAccount");
        }   	
    	return model;
    }
    
    @RequestMapping(value = "/tutor_signUp", method = RequestMethod.GET)
    public ModelAndView tutorSignUp() {
    	ModelAndView model = new ModelAndView("tutor_signUp");
    	model.addObject("tutorSignupForm", new TutorSignupForm());
        return model;
    }
    
    @RequestMapping(value = "/createTutor", method = RequestMethod.POST)
    public ModelAndView createTutor(@Valid TutorSignupForm tutorSignupForm, BindingResult result, RedirectAttributes redirectAttributes) {
    	ModelAndView model;
        try {
            sampleService.saveTutorFrom(tempSignupForm, tutorSignupForm);
            tempSignupForm = null;
            model = new ModelAndView("show");
        } catch (InvalidUserException e) {
           	model = new ModelAndView("tutor_signUp");
           	model.addObject("page_error", e.getMessage());
        }	
    	return model;
    }
    
    @RequestMapping(value = "/security-error", method = RequestMethod.GET)
    public String securityError(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("page_error", "You do have have permission to do that!");
        return "redirect:/";
    }

}


