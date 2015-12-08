package ch.unibe.ese.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ch.unibe.ese.controller.service.CommentService;
import ch.unibe.ese.controller.service.StudentSearchService;


/**
 * 
 * @author ESE Team 8
 * @version 2.0
 * @since 24.11.2015
 * Controller for showing the comments made by students to rate a tutor 
 * Once a tutor accepted a student's contact request, this student can rate the tutor exactly 
 * once. That means that he can give him a grade from 1 to 6 and make a comment.
 * Every user can see a tutor's rating and all the comments and even sort the ratings by grade
 */
@Controller
public class CommentController {
	
	@Autowired CommentService commentService;
	@Autowired StudentSearchService studentSearchService;
	
	@RequestMapping(value = "/showComments", method = RequestMethod.GET)
    public ModelAndView showComments(Principal principal, @RequestParam("tutorId") long id) {
    	ModelAndView model;
        model = new ModelAndView("showComments");
        model.addObject("comments",studentSearchService.findTutorById(id).getComments());
        model.addObject("tutorId",id);
        return model;
	}
	
	@RequestMapping(value = "/showSortedCommentsUp", method = RequestMethod.GET)
    public ModelAndView showCommentsSortedByHighestRating(Principal principal, @RequestParam("tutorId") long id) {
    	ModelAndView model;
        model = new ModelAndView("showComments");
        model.addObject("comments",commentService.sortComments(studentSearchService.findTutorById(id).getComments()));
        model.addObject("tutorId",id);
        return model;
	}
	
	@RequestMapping(value = "/showSortedCommentsDown", method = RequestMethod.GET)
    public ModelAndView showCommentsSortedByLowestRating(Principal principal, @RequestParam("tutorId") long id) {
    	ModelAndView model;
        model = new ModelAndView("showComments");
        model.addObject("comments",commentService.sortCommentsDecending(studentSearchService.findTutorById(id).getComments()));
        model.addObject("tutorId",id);
        return model;
	}
}
