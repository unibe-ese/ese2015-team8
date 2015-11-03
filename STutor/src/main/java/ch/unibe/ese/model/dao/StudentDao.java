package ch.unibe.ese.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.unibe.ese.model.Student;

public interface StudentDao extends CrudRepository<Student,Long> {

	Student findByUsername(String username);
	
	Student findByIdAndGender(long id, String gender);
	
}
