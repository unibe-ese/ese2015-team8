package ch.unibe.ese.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 *
 * This controller handles the mapping for the login request. Most of the work is done by spring-security,
 * @see springSecurity.xml and login.jsp, where the login form is defined.
 *
 * @author ESE Team 8
 * @version 1.0
 * @since 21.10.2015
 */
@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

}

