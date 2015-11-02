package ch.unibe.ese.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import ch.unibe.ese.controller.exceptions.InvalidUserException;
import ch.unibe.ese.controller.pojos.OptionForm;
import ch.unibe.ese.controller.service.OptionService;
import ch.unibe.ese.controller.service.SampleService;
import ch.unibe.ese.model.dao.StudentDao;
import ch.unibe.ese.security.service.CustomUserDetailsService;

@Controller
public class OptionController {
	
	@Autowired
	OptionService optionService;
	
	@Autowired
	SampleService sampleService;
	
	@Autowired
	CustomUserDetailsService userDetailsService;
	
	@Autowired
	StudentDao studentDao;
	
	@RequestMapping("/options")
	public ModelAndView notifications(Principal principal) {
		ModelAndView model= new ModelAndView("options");
		model.addObject("student", studentDao.findByUsername(principal.getName()));
		model.addObject("optionForm",optionService.getFrom(principal.getName()));
		return model;
	}
	
	@RequestMapping("/optionsSaved")
	public RedirectView redirect(@Valid OptionForm optionForm, BindingResult result,
			RedirectAttributes redirectAttributes, Principal principal) {
		RedirectView model;
		
		if (!result.hasErrors()) {
			try {
				if(optionForm.getPassword()=="")
					optionService.saveTutorFrom(studentDao.findByUsername(principal.getName()),optionForm,false);
				else{
					optionService.saveTutorFrom(studentDao.findByUsername(principal.getName()),optionForm,true);
					
					UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());

					Authentication auth = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
							userDetails.getPassword(), userDetails.getAuthorities());
					SecurityContextHolder.getContext().setAuthentication(auth);	
				}
		
				model = new RedirectView("profile");
				model.getAttributesMap().put("userId",studentDao.findByUsername(principal.getName()).getId());
				return model;
			}catch(InvalidUserException e) {
				return new RedirectView("options");
			}
		}else{
			return new RedirectView("options");
		}
	}
}