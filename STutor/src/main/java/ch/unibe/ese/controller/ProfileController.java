package ch.unibe.ese.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ch.unibe.ese.controller.exceptions.InvalidUserException;
import ch.unibe.ese.controller.service.SignUpService;
import ch.unibe.ese.controller.service.StudentSearchService;
import ch.unibe.ese.model.Student;

/**
 * Handles the user's request to see his personal profile. It shows his
 * personal information.
 * @author ESE Team 8
 * @version 1.0
 * @since 21.10.2015
 */
@Controller
public class ProfileController {

    @Autowired
    SignUpService signUpService;
    
	@Autowired
	StudentSearchService studentSearchService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile(Principal principal, @RequestParam("userId") long id) {
    	ModelAndView model;
        try {
        	Student visitor = studentSearchService.getStudentByUsername(principal.getName());
    		
    		//not allowed to see other user's profile page!
    		if(!visitor.getId().equals(id)){
    			return new ModelAndView("accessDenied");
    		}
        	model = new ModelAndView("profile");
        	Student student = studentSearchService.findTutorById(id);
      
        	model.addObject("student",student);
        	
        	if(student.getIsTutor()){
          	model.addObject("lectures", student.getLectures().toArray());
        	model.addObject("timeframes", student.getTimeframes().toArray());
        	}

        } catch (InvalidUserException e) {
        	model = new ModelAndView("index");
        	model.addObject("page_error", e.getMessage());
        } 	catch (NullPointerException e) {
        	model = new ModelAndView("index");
        	model.addObject("page_error", e.getMessage());
        }
    	return model;
    }
}