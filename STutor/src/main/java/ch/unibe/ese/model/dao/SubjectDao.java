package ch.unibe.ese.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.unibe.ese.model.Subject;



public interface SubjectDao extends CrudRepository<Subject,Long> {

}
