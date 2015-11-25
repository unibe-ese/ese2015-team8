package ch.unibe.ese.controller;

import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ch.unibe.ese.controller.service.DataService;
import ch.unibe.ese.controller.service.StudentSearchService;
import ch.unibe.ese.model.Student;

/**
 * 
 *  Controller for the page after the login. This page is restricted to
 *  logged in users only.
 *  
 *  @see springSecurity.xml and web.xml for this. Loads the afterLogin.jsp
 *  file and displays it with the new information.
 *
 * @author ESE Team 8
 * @version 1.0
 * @since 21.10.2015
 */
@Controller
public class AfterLoginController {


	@Autowired
	DataService dataService;
	
	@Autowired
	StudentSearchService studentSearchService;

	/**
	 *
	 * 	     
	 * This page shows only a welcome message to the user and links to option
	 * pages etc.
	 * @param principal:
	 *            Part of spring security, returns the authenticated 'user'
	 *            (username)
	 * 
	 * @return The modelAndView
	 */
	@RequestMapping(value = "/afterLogin", method = RequestMethod.GET)
	public ModelAndView index(Principal principal) {

		// name of the principal = the *username* of the student/tutor who is
		// logged in.
		String username = principal.getName();
		
		Collection<? extends GrantedAuthority> list = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

		// we get the student through the dao
		Student loggedInStudent = studentSearchService.getStudentByUsername(username);

		GrantedAuthority authority = list.iterator().next();
		
		
		ModelAndView model;

		//depending on the role, a different jsp is loaded.
		if (authority.toString().contentEquals("ROLE_TUTOR")) {

			model = new ModelAndView("tutorMain");

			String welcomeText = "Hi " + username + ", welcome to Stutor.";

			model.addObject("welcomeMsg", welcomeText);

		} else {
			model = new ModelAndView("studentMain");

			String welcomeText = "Hi " + username + ", welcome to Stutor.";

			model.addObject("welcomeMsg", welcomeText);

		}
		
		model.addObject("user",loggedInStudent);
		
		return model;
	}

}