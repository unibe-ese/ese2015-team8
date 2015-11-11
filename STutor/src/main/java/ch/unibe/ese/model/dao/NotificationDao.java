package ch.unibe.ese.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ch.unibe.ese.model.Notification;

/**
 * 
 * @author ESE Team 8
 * @version 1.0
 * @since 28.10.2015
 */
public interface NotificationDao extends CrudRepository<Notification,Long> {
	List<Notification> getByToStudentId(Long toStudentId);
}
