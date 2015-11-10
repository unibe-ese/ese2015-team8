package ch.unibe.ese.controller;

import java.security.Principal;

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
 * If a Student wants to contact a Tutor, he has to send a request to him.
 * This triggers a Notification on the Tutor's page. The Tutor can either 
 * accept or decline this request. If he accepts, he gets redirected to
 * a payment page where he has to pay a fee of 5 Fr. After that, the Student
 * gets notified if he was accepted or declined. If he indeed was accepted,
 * the Tutor gets the Student's email address for further contact.
 * This Controller handles these Notifications.
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
	
	/**
	 * loads the notification page of a specific user
	 * @param id of the user
	 * @return model with user's notifications 
	 */
	@RequestMapping("/notifications")
	public ModelAndView notifications(Principal principal, @RequestParam("userId") long id) {
		
		Student visitor = studentDao.findByUsername(principal.getName());
		if(!visitor.getId().equals(id)){
			return new ModelAndView("accessDenied");
		}
		
		
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
	
	/**
	 * If the Tutor accepts, he has to to pay a fee before contact
	 * @return model with payment fields
	 */
	@RequestMapping("/notificationAccept")
	public ModelAndView notificationAccept() {
		ModelAndView model= new ModelAndView("notificationAccept");
		model.addObject("notification",acctualNotification);
		model.addObject("price",AdminData.getContactprice());
		model.addObject("cards",AdminData.getCreditCards());
		return model;
	}
	
	/**
	 * shows real information when payment is done correctly
	 * @return model with information
	 */
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
	
	/**
	 * If the request is declined, the Student gets notified about it.
	 * @return model with declined request
	 */
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
