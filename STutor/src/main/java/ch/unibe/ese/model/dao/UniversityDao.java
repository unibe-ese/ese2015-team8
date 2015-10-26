package ch.unibe.ese.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.unibe.ese.model.University;


public interface UniversityDao extends CrudRepository<University,Long> {

}
