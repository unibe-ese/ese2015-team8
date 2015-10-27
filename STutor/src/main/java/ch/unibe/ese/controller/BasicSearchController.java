package ch.unibe.ese.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ch.unibe.ese.controller.exceptions.InvalidUserException;
import ch.unibe.ese.controller.service.SampleService;
import ch.unibe.ese.model.Lecture;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.dao.LectureDao;
import ch.unibe.ese.model.dao.StudentDao;

@Controller
public class BasicSearchController {

    @Autowired
    SampleService sampleService;
    
	@Autowired
	LectureDao lectureDao;
	
	@Autowired
	StudentDao studentDao;

    @RequestMapping(value = "/basicSearch", method = RequestMethod.GET)
    public ModelAndView basicSearch(@RequestParam("q") String term) {
    	System.err.println(term + "is the value");
    	ModelAndView model;
        try {
        	model = new ModelAndView("basicSearch");
        	List<Lecture> foundLectures = lectureDao.findByName(term);
        	List<Long> tutorIds = new ArrayList<Long>();
        	for (Lecture lecture : foundLectures) {
				tutorIds.add(lecture.getTutor().getId());
			}
        	
        	
        	
        	Student temp = studentDao.findOne(tutorIds.get(0));
        	
        	if(temp!=null)
        		model.addObject("basicSearch", temp.getUsername());
        	else
        		model.addObject("basicSearch", "no user found");

        } catch (InvalidUserException e) {
        	model = new ModelAndView("index");
        	model.addObject("page_error", e.getMessage());
        } 
    	return model;
    }
}

