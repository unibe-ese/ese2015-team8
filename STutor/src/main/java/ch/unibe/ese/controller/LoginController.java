package ch.unibe.ese.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * (temp.)
 * Unlike in our sign up, we do not need any sort of form handling for the login. Spring security does that for us.
 * We just load the corresponding login form (@see login.jsp) and declare the needed things in the XML files. (web & springSecurity.xml) 
 *
 */

@Controller
public class LoginController {

    @RequestMapping("/login")
	public String login(Model model) {
		return "/login";
	}



}

