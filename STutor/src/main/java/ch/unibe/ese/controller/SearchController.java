package ch.unibe.ese.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ch.unibe.ese.controller.exceptions.InvalidUserException;
import ch.unibe.ese.controller.service.SampleService;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.dao.SearchDao;

@Controller
public class SearchController {

//    @RequestMapping("/search")
//	public String search(Model model) {
//		return "/search";
//	}
    
    
    
    @Autowired
    SampleService sampleService;
    
	@Autowired
	SearchDao searchDao;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView search(String search) {
    	ModelAndView model;
        try {
        	model = new ModelAndView("search");
        	Student temp = searchDao.findByLectures(search);
        	if(temp != null)
        		model.addObject("search", temp.getUsername());
        	else
        		model.addObject("search", "no user found");

        } catch (InvalidUserException e) {
        	model = new ModelAndView("index");
        	model.addObject("page_error", e.getMessage());
        } 
    	return model;
    }
}

