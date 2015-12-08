package ch.unibe.ese.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Everytime a user (logged in or not) tries to access a page he
 * is not allowed to access, he gets redirected to this page. If a user
 * iss not logged in, for instance, he can only access the login, 
 * the create account and the beginning of the search page.
 *  
 * 
 * @author ESE Team 8
 * @version 1.0
 * @since 21.10.2015
 */
@Controller
public class DeniedAccessController {

	/*
	 * @see springSecurity.xml, where it's defined that unrestricted access leads to this url, 
	 * which the controller maps to the accessDenied ModelAndView.
	 * 
	 * 
	 */
	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView("accessDenied");

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() + ", you do not have permission to access this page!");
		} else {
			model.addObject("msg","You do not have permission to access this page!");
		}
		return model;

	}

}

