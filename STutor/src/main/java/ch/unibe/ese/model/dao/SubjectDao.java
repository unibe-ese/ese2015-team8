package ch.unibe.ese.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.unibe.ese.model.Subject;

/**
 * 
 * @author Christian Zürcher
 * @version 1.0
 * @since 28.10.2015
 */
public interface SubjectDao extends CrudRepository<Subject,Long> {
	
}