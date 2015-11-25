package ch.unibe.ese.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ch.unibe.ese.controller.service.CommentService;


/**
 * 
 * @author ESE Team 8
 * @version 2.0
 * @since 24.11.2015
 */
@Controller
public class CommentController {
	
	@Autowired CommentService commentService;
	
	@RequestMapping(value = "/showComments", method = RequestMethod.GET)
    public ModelAndView showComments(Principal principal, @RequestParam("tutorId") long id) {
    	ModelAndView model;
        model = new ModelAndView("showComments");
        model.addObject("comments",commentService.findTutorById(id).getComments());
        model.addObject("tutorId",id);
        return model;
	}
	
	@RequestMapping(value = "/showSortedCommentsUp", method = RequestMethod.GET)
    public ModelAndView showCommentsSortedByHighestRating(Principal principal, @RequestParam("tutorId") long id) {
    	ModelAndView model;
        model = new ModelAndView("showComments");
        model.addObject("comments",commentService.sortComments(commentService.findTutorById(id).getComments()));
        model.addObject("tutorId",id);
        return model;
	}
	
	@RequestMapping(value = "/showSortedCommentsDown", method = RequestMethod.GET)
    public ModelAndView showCommentsSortedByLowestRating(Principal principal, @RequestParam("tutorId") long id) {
    	ModelAndView model;
        model = new ModelAndView("showComments");
        model.addObject("comments",commentService.sortCommentsDecending(commentService.findTutorById(id).getComments()));
        model.addObject("tutorId",id);
        return model;
	}
}
