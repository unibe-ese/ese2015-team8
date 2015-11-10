package ch.unibe.ese.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * (temp.)
 * Unlike in our sign up, we do not need any sort of form handling for the login. Spring security does that for us.
 * We just load the corresponding login form (@see login.jsp) and declare the needed things in the XML files. (web & springSecurity.xml) 
 *
 * @author Stefan Jonas
 * @version 1.0
 * @since 21.10.2015
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
	public String login(Model model) {
		return "/login";
	}
    
    
	/**
	 * redirects to the login page if "/" is the address
	 * @return model with login view
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("login");
		return model;
	}



}

