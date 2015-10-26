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
import ch.unibe.ese.model.dao.SearchDao;
import ch.unibe.ese.model.dao.StudentDao;

@Controller
public class SearchController {

    @RequestMapping("/search")
	public String search(Model model) {
		return "/search";
	}
    
    
//    
//    @Autowired
//    SampleService sampleService;
//    
//	@Autowired
//	SearchDao searchDao;
//
//    @RequestMapping(value = "/profile", method = RequestMethod.GET)
//    public ModelAndView search(@RequestParam("search") long search) {
//    	ModelAndView model;
//        try {
//        	model = new ModelAndView("search");
//        	model.addObject("search", searchDao.findOne(search).getUsername());
//
//        } catch (InvalidUserException e) {
//        	model = new ModelAndView("index");
//        	model.addObject("page_error", e.getMessage());
//        } 	
//    	return model;
//    }
}

