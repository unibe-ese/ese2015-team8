package ch.unibe.ese.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ch.unibe.ese.admin.AdminData;
import ch.unibe.ese.controller.exceptions.InvalidUserException;
import ch.unibe.ese.controller.service.NotificationService;
import ch.unibe.ese.controller.service.StudentSearchService;
import ch.unibe.ese.model.Notification;
import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.factory.NotificationFactory;

/**
 * If a Student wants to contact a Tutor, he has to send a request to him.
 * This triggers a Notification on the Tutor's page. The Tutor can either 
 * accept or decline this request. If he accepts, he gets redirected to
 * a payment page where he has to pay a fee of 5 Fr. After that, the Student
 * gets notified if he was accepted or declined. If he indeed was accepted,
 * the Tutor gets the Student's email address for further contact.
 * This Controller handles these Notifications.
 * @author ESE Team 8
 * @version 1.0
 * @since 28.10.2015
 */
@Controller
public class NotificationController {
	
	@Autowired
	NotificationService notificationService;
	
	@Autowired
	StudentSearchService studentSearchService;
	
	private Student acctualStudent;
	private Notification acctualNotification;
	
	/**
	 * loads the notification page of a specific user
	 * @param id of the user
	 * @return model with user's notifications 
	 */
	@RequestMapping("/notifications")
	public ModelAndView notifications(Principal principal, @RequestParam("userId") long id) {
		
		Student visitor = studentSearchService.getStudentByUsername(principal.getName());
		
		//not allowed to see other tutor's notifications!
		if(!visitor.getId().equals(id)){
			return new ModelAndView("accessDenied");
		}
		
		
		ModelAndView model= new ModelAndView("notifications");
		acctualStudent = studentSearchService.findTutorById(id);
		model.addObject("notificationList", notificationService.getNotificationsByStudentId(acctualStudent.getId()));
		return model;
	}
	
	@RequestMapping("/readNotification")
	public ModelAndView notification(@RequestParam("notificationId") long id) {
		ModelAndView model;
        try {
        	acctualNotification = notificationService.findById(id);
        	acctualNotification.setStatus("");
        	acctualNotification = notificationService.saveNotification(acctualNotification);
        	model = new ModelAndView("readNotification");
        	model.addObject("notification", acctualNotification);
        	model.addObject("tutor",studentSearchService.findTutorById(acctualNotification.getFromStudentId()));

        	
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
		Student temp = studentSearchService.findTutorById(acctualNotification.getFromStudentId());
		temp.addNotification(NotificationFactory.getAcceptNotification(acctualStudent, acctualNotification.getFromStudentId()));
		temp = studentSearchService.saveStudentIntoDB(temp);
		acctualNotification = notificationService.saveNotification(acctualNotification);
		
		acctualStudent.addNotification(NotificationFactory.getStudentContactDetails(studentSearchService.findTutorById(acctualNotification.getFromStudentId()), acctualStudent.getId()));
		acctualStudent = studentSearchService.saveStudentIntoDB(acctualStudent);
		
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
		Student temp = studentSearchService.findTutorById(acctualNotification.getFromStudentId());
		temp.addNotification(NotificationFactory.getDeclineNotification(acctualStudent, acctualNotification.getFromStudentId()));
		temp = studentSearchService.saveStudentIntoDB(temp);
		acctualNotification = notificationService.saveNotification(acctualNotification);
		ModelAndView model= new ModelAndView("/show");
		model.addObject("text","Request Declined!");
		return model;
	}
	
	@RequestMapping(value = "/deletedNotification", method = RequestMethod.GET)
	public ModelAndView deletedLecture(@RequestParam("notificationId") long notificationId, Principal principal) {
		Student loggedInTutor = studentSearchService.getStudentByUsername(principal.getName());
		
		ModelAndView model;
		
		Notification chosenNotification = notificationService.findById(notificationId);
		
		if(!loggedInTutor.getNotifications().contains(chosenNotification)){
			model = new ModelAndView("accessDenied");
		}
		else{
		
			chosenNotification = notificationService.remove(chosenNotification, loggedInTutor);
		
		model = new ModelAndView("redirect:" + "/notifications?userId="+loggedInTutor.getId());
		}
		
		return model;
	}
	
	
	
	
}
