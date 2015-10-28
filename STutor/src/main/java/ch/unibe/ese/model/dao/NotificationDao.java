package ch.unibe.ese.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ch.unibe.ese.model.Notification;

public interface NotificationDao extends CrudRepository<Notification,Long> {
	List<Notification> getByToStudentId(Long toStudentId);
}
