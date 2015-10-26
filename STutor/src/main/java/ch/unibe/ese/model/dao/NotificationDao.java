package ch.unibe.ese.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.unibe.ese.model.Notification;

public interface NotificationDao extends CrudRepository<Notification,Long> {

}
