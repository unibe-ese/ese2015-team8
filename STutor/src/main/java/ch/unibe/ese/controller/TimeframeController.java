package ch.unibe.ese.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import ch.unibe.ese.controller.exceptions.InvalidUserException;
import ch.unibe.ese.controller.pojos.TimeframeForm;
import ch.unibe.ese.controller.service.StudentSearchService;
import ch.unibe.ese.controller.service.TimeframeService;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.Timeframe;


/**
 * A Tutor is able to specify when he has time to tutor. This Controller 
 * handles his doing so.
 * @author ESE Team 8
 * @version 1.0
 * @since 4.11.2015
 *
 */
@Controller
public class TimeframeController {
	
	@Autowired TimeframeService timeframeService;
	@Autowired StudentSearchService studentSearchService;
	
	/**
	 * loads form to specify time
	 * @return model with form
	 */
	@RequestMapping(value = "/addTimeframe", method = RequestMethod.GET)
	public ModelAndView addLecture() {
		ModelAndView model = new ModelAndView("addTimeframe");
		model.addObject("timeframeForm", new TimeframeForm());
		return model;
	}

	/**
	 * saves filled in time if there are no errors, like empty fields
	 * @param timelapsForm
	 * @param result
	 * @param redirectAttributes
	 * @param principal
	 * @return model with profile
	 */
	@RequestMapping(value = "/addedTimeframe", method = RequestMethod.POST)
	public ModelAndView create(@Valid TimeframeForm timeframeForm, BindingResult result, RedirectAttributes redirectAttributes, Principal principal) {
		ModelAndView model;
		if (!result.hasErrors()) {
			try {
				Student loggedInTutor = studentSearchService.getStudentByUsername(principal.getName());
				timeframeService.saveFrom(timeframeForm, loggedInTutor);
				
				model = new ModelAndView(new RedirectView("profile"), "userId", loggedInTutor.getId());
				return model;

			} catch (InvalidUserException e) {
				model = new ModelAndView("addTimeframe");
				model.addObject("page_error", e.getMessage());				
			}
		} else {
			model = new ModelAndView("addTimeframe");
		}

		return model;
	}
	
	@RequestMapping(value = "/editTimeframe", method = RequestMethod.GET)
	public ModelAndView editLecture(@RequestParam("id") long timeframeId, Principal principal) {
		
		Student loggedInTutor = studentSearchService.getStudentByUsername(principal.getName());
		Timeframe chosenTimeframe = timeframeService.findTimeframeById(timeframeId);
		ModelAndView model;

		
		if(!loggedInTutor.getTimeframes().contains(chosenTimeframe)){
			model = new ModelAndView("accessDenied");
		}
		else{
		model = new ModelAndView("editTimeframe");
		TimeframeForm editForm = new TimeframeForm();
		
		editForm.setDay(chosenTimeframe.getDay());
		editForm.setFromTime(chosenTimeframe.getFromTime());
		editForm.setToTime(chosenTimeframe.getToTime());
		editForm.setId(chosenTimeframe.getId());
		model.addObject("timeframeForm", editForm);
		}
		return model;
	}
	
	@RequestMapping(value = "/editedTimeframe", method = RequestMethod.POST)
	public ModelAndView editedTime (@Valid TimeframeForm timeframeForm, BindingResult result, RedirectAttributes redirectAttributes, Principal principal) {
		ModelAndView model;
		if (!result.hasErrors()) {
			try {
				Student loggedInTutor = studentSearchService.getStudentByUsername(principal.getName());
				timeframeService.editFrom(timeframeForm, timeframeForm.getId());
				
				model = new ModelAndView(new RedirectView("profile"), "userId", loggedInTutor.getId());
				return model;

			} catch (InvalidUserException e) {
				model = new ModelAndView("editTimeframe");
				model.addObject("page_error", e.getMessage());				
			}
		} else {
			model = new ModelAndView("editTimeframe");
		}

		return model;
	}
	
	
	@RequestMapping(value = "/deleteTimeframe", method = RequestMethod.GET)
	public ModelAndView deleteLecture(@RequestParam("id") long timeframeId, Principal principal) {
		Student loggedInTutor = studentSearchService.getStudentByUsername(principal.getName());
		Timeframe timeframe = timeframeService.findTimeframeById(timeframeId);
		ModelAndView model;
		if(!loggedInTutor.getTimeframes().contains(timeframe)){
			model = new ModelAndView("accessDenied");
		}
		else{
		model = new ModelAndView("deleteTimeframe");
		model.addObject("timeframe", timeframe);
		}
		return model;
	}
	
	
	
	@RequestMapping(value = "/deletedTimeframe", method = RequestMethod.GET)
	public ModelAndView deletedLecture(@RequestParam("id") long timeframeId, Principal principal) {
		Student loggedInTutor = studentSearchService.getStudentByUsername(principal.getName());
		
		ModelAndView model;
		
		Timeframe timeframe = timeframeService.findTimeframeById(timeframeId);
		
		if(!loggedInTutor.getTimeframes().contains(timeframe)){
			model = new ModelAndView("accessDenied");
		}
		else{
		
		timeframeService.remove(timeframe, loggedInTutor);
		
		model = new ModelAndView("redirect:" + "/profile?userId="+loggedInTutor.getId());
		}
		
		return model;
	}
}
