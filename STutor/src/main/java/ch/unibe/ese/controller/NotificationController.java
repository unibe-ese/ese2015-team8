package ch.unibe.ese.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ch.unibe.ese.controller.exceptions.InvalidUserException;
import ch.unibe.ese.model.Notification;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.dao.NotificationDao;
import ch.unibe.ese.model.dao.StudentDao;

@Controller
public class NotificationController {
	
	@Autowired
	NotificationDao notificationDao;
	
	@Autowired
	StudentDao studentDao;
	
	private Student acctualStudent;
	
	@RequestMapping("/notifications")
	public ModelAndView notifications(@RequestParam("userId") long id) {
		ModelAndView model= new ModelAndView("notifications");
		acctualStudent = studentDao.findOne(id);
		model.addObject("notificationList", notificationDao.getByToStudentId(acctualStudent.getId()));
		return model;
	}
	
	@RequestMapping("/readNotification")
	public ModelAndView notification(@RequestParam("notificationId") long id) {
		ModelAndView model;
        try {
        	Notification temp = notificationDao.findOne(id);
        	temp.setStatus("");
        	temp = notificationDao.save(temp);
        	model = new ModelAndView("readNotification");
        	model.addObject("notification", temp);
        	
        } catch (InvalidUserException e) {
        	model = new ModelAndView("notifications");
        	model.addObject("page_error", e.getMessage());
        } 	
    	return model;
	}
}
