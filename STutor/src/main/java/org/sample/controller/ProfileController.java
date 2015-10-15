package org.sample.controller;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.service.SampleService;
import org.sample.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController {

    @Autowired
    SampleService sampleService;
    
	@Autowired
	UserDao userDao;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile(@RequestParam("userId") long id) {
    	ModelAndView model;
        try {
        	model = new ModelAndView("profile");
        	model.addObject(userDao.findOne(id));
        } catch (InvalidUserException e) {
        	model = new ModelAndView("index");
        	model.addObject("page_error", e.getMessage());
        } 	
    	return model;
    }
}