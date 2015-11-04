package ch.unibe.ese.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import ch.unibe.ese.controller.exceptions.InvalidUserException;
import ch.unibe.ese.controller.pojos.TimelapsForm;
import ch.unibe.ese.controller.service.TimelapsService;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.dao.StudentDao;

@Controller
public class TimelapsController {
	
	@Autowired TimelapsService timelapsService;
	@Autowired StudentDao studentDao;
	
	@RequestMapping(value = "/addTimelaps", method = RequestMethod.GET)
	public ModelAndView addLecture() {
		ModelAndView model = new ModelAndView("selectTimelaps");
		model.addObject("timelapsForm", new TimelapsForm());
		return model;
	}

	@RequestMapping(value = "/addedTimelaps", method = RequestMethod.POST)
	public ModelAndView create(@Valid TimelapsForm timelapsForm, BindingResult result, RedirectAttributes redirectAttributes, Principal principal) {
		ModelAndView model;
		if (!result.hasErrors()) {
			try {
				Student loggedInTutor = studentDao.findByUsername(principal.getName());
				timelapsService.saveFrom(timelapsForm, loggedInTutor);
				
				model = new ModelAndView(new RedirectView("profile"), "userId", loggedInTutor.getId());
				return model;

			} catch (InvalidUserException e) {
				model = new ModelAndView("addTimelaps");
				model.addObject("page_error", e.getMessage());				
			}
		} else {
			model = new ModelAndView("addTimelaps");
		}

		return model;
	}
}
