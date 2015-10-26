package ch.unibe.ese.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ch.unibe.ese.controller.exceptions.InvalidUserException;
import ch.unibe.ese.controller.service.SampleService;
import ch.unibe.ese.model.dao.StudentDao;

@Controller
public class ProfileController {

    @Autowired
    SampleService sampleService;
    
	@Autowired
	StudentDao studentDao;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile(@RequestParam("userId") long id) {
    	ModelAndView model;
        try {
        	model = new ModelAndView("profile");
        	model.addObject("lecture", studentDao.findOne(id).getLectures().get(0).toString());

        } catch (InvalidUserException e) {
        	model = new ModelAndView("index");
        	model.addObject("page_error", e.getMessage());
        } 	
    	return model;
    }
}