package org.sample.controller;

import java.security.Principal;

import org.sample.model.Student;
import org.sample.model.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class afterLoginController {

	// as we get the username in the mapping, we can find the student by looking
	// through the repo with the username.
	@Autowired
	StudentDao studentdao;

	/**
	 * 
	 * [TEMPORARY DOCUMENTATION]
	 * 
	 * Controller for the page after the login. This page is restricted to
	 * logged in users only,
	 * 
	 * @see springSecurity.xml and web.xml for this. Loads the afterLogin.jsp
	 *      file and displays it with the new information.
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

		// we get the student through the dao
		Student loggedInStudent = studentdao.findByUsername(username);

		
		
		/*
		 * JUST FOR DEMONSTRATION. It's possible to load different pages/models.
		 * Here, it loads a different page based on whether you're a student or
		 * a tutor.
		 * 
		 * Not necessary, technically both could start at the same main page.
		 * 
		 * 
		 * It's also possible to load different things on the same page (i.e all
		 * on /main but different things loaded by javaScript.)
		 */
		ModelAndView model;

		if (loggedInStudent.getIsTutor()) {

			model = new ModelAndView("tutorMain");

			String welcomeText = "Hi " + username + ", you're logged in as a TUTOR.";

			model.addObject("welcomeMsg", welcomeText);

		} else {
			model = new ModelAndView("studentMain");

			String welcomeText = "Hi " + username + ", you're logged in as a STUDENT.";

			model.addObject("welcomeMsg", welcomeText);

		}
		
		model.addObject("user",loggedInStudent);

		return model;
	}

}