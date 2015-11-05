package ch.unibe.ese.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ch.unibe.ese.admin.AdminData;
import ch.unibe.ese.controller.exceptions.InvalidUserException;
import ch.unibe.ese.model.Notification;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.dao.NotificationDao;
import ch.unibe.ese.model.dao.StudentDao;
import ch.unibe.ese.model.factory.NotificationFactory;

/**
 * 
 * @author Christian Zürcher
 * @version 1.0
 * @since 28.10.2015
 */
@Controller
public class NotificationController {
	
	@Autowired
	NotificationDao notificationDao;
	
	@Autowired
	StudentDao studentDao;
	
	private Student acctualStudent;
	private Notification acctualNotification;
	
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
        	acctualNotification = notificationDao.findOne(id);
        	acctualNotification.setStatus("");
        	acctualNotification = notificationDao.save(acctualNotification);
        	model = new ModelAndView("readNotification");
        	model.addObject("notification", acctualNotification);
        	model.addObject("tutorId",studentDao.findOne(acctualNotification.getFromStudentId()).getId());
        	
        } catch (InvalidUserException e) {
        	model = new ModelAndView("notifications");
        	model.addObject("page_error", e.getMessage());
        } 	
    	return model;
	}
	
	@RequestMapping("/notificationAccept")
	public ModelAndView notificationAccept() {
		ModelAndView model= new ModelAndView("notificationAccept");
		model.addObject("notification",acctualNotification);
		model.addObject("price",AdminData.getContactprice());
		model.addObject("cards",AdminData.getCreditCards());
		return model;
	}
	
	@RequestMapping("/paymentDone")
	public ModelAndView paymentDone() {
		
		acctualNotification.setStatus("_/");
		Student temp = studentDao.findOne(acctualNotification.getFromStudentId());
		temp.addNotification(NotificationFactory.getAcceptNotification(acctualStudent, acctualNotification.getFromStudentId()));
		temp = studentDao.save(temp);
		acctualNotification = notificationDao.save(acctualNotification);
		
		acctualStudent.addNotification(NotificationFactory.getStudentContactDetails(studentDao.findOne(acctualNotification.getFromStudentId()), acctualStudent.getId()));
		acctualStudent = studentDao.save(acctualStudent);
		
		ModelAndView model= new ModelAndView("/show");
		model.addObject("text","Payment Done!");
		return model;
	}
	
	@RequestMapping("/notificationDecline")
	public ModelAndView notificationDecline() {
		acctualNotification.setStatus("x");
		Student temp = studentDao.findOne(acctualNotification.getFromStudentId());
		temp.addNotification(NotificationFactory.getDeclineNotification(acctualStudent, acctualNotification.getFromStudentId()));
		temp = studentDao.save(temp);
		acctualNotification = notificationDao.save(acctualNotification);
		ModelAndView model= new ModelAndView("/show");
		model.addObject("text","Request Declined!");
		return model;
	}
}
