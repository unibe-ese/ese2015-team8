package ch.unibe.ese.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.unibe.ese.controller.exceptions.InvalidUserException;
import ch.unibe.ese.controller.pojos.SignupForm;
import ch.unibe.ese.controller.service.SignUpService;
import ch.unibe.ese.security.service.CustomUserDetailsService;

/**
 * This Controller handles the request to create a new account. If the 
 * sign up form is filled in correctly, a new user with the filled in
 * information is added to the database. If there are errors, like one
 * field left empty, the page is reloaded.
 * @author ESE Team 8
 * @version 1.0
 * @since 21.10.2015
 */
@Controller
public class SignUpController {

	@Autowired
	SignUpService signUpService;

	@Autowired
	CustomUserDetailsService userDetailsService;

	/**
	 * redirects to the login page if "/" is the address
	 * @return model with login view
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("login");
		return model;
	}

	/**
	 * Shows the sign up form to create a new account
	 * @return the model with the sign up form
	 */
	@RequestMapping(value = "/newAccount", method = RequestMethod.GET)
	public ModelAndView newAccount() {
		ModelAndView model = new ModelAndView("newAccount");
		model.addObject("signupForm", new SignupForm());
		return model;
	}

	/**
	 * Handles the filled in form. If everything's filled in correctly,
	 * a new user is added to the database.
	 * @param signupForm
	 * @param result
	 * @param redirectAttributes
	 * @return model that redirects to the afterlogin page
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create(@Valid SignupForm signupForm, BindingResult result,
			RedirectAttributes redirectAttributes) {
		ModelAndView model;
		if (!result.hasErrors()) {
			try {
				signUpService.saveStudentFrom(signupForm);

				UserDetails userDetails = userDetailsService.loadUserByUsername(signupForm.getUsername());

				Authentication auth = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
						userDetails.getPassword(), userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);
				model = new ModelAndView("redirect:/afterLogin");

				return model;

			} catch (InvalidUserException e) {
				model = new ModelAndView("newAccount");
				model.addObject("page_error", e.getMessage());	
			
			} 
			
		} else {
			model = new ModelAndView("newAccount");
		}

		return model;
	}

	@RequestMapping(value = "/security-error", method = RequestMethod.GET)
	public String securityError(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("page_error", "You do have have permission to do that!");
		return "redirect:/";
	}

}
