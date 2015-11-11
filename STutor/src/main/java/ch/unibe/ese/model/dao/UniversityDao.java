package ch.unibe.ese.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.unibe.ese.model.University;

/**
 * 
 * @author ESE Team 8
 * @version 1.0
 * @since 28.10.2015
 */
public interface UniversityDao extends CrudRepository<University,Long> {

}