package ch.unibe.ese.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ch.unibe.ese.controller.exceptions.InvalidUserException;
import ch.unibe.ese.controller.service.SignUpService;
import ch.unibe.ese.model.dao.StudentDao;

/**
 * Handles the user's request to see his personal profile. It shows his
 * personal information.
 * @author Christian Zürcher
 * @version 1.0
 * @since 21.10.2015
 */
@Controller
public class ProfileController {

    @Autowired
    SignUpService sampleService;
    
	@Autowired
	StudentDao studentDao;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile(@RequestParam("userId") long id) {
    	ModelAndView model;
        try {
        	model = new ModelAndView("profile");
        	model.addObject("lectures", studentDao.findOne(id).getLectures().toArray());
        	model.addObject("timelapses", studentDao.findOne(id).getTimelapses().toArray());
        	model.addObject("student",studentDao.findOne(id));

        } catch (InvalidUserException e) {
        	model = new ModelAndView("index");
        	model.addObject("page_error", e.getMessage());
        } 	
    	return model;
    }
}