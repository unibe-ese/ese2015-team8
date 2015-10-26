package ch.unibe.ese.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

