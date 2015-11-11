package ch.unibe.ese.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.unibe.ese.model.Student;

/**
 * 
 * @author ESE Team 8
 * @version 1.0
 * @since 21.10.2015
 */
public interface StudentDao extends CrudRepository<Student,Long> {

	Student findByUsername(String username);
	Student findByIdAndGender(long id, String gender);
	Student findByEmail(String email);
	
}
