package ch.unibe.ese.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.unibe.ese.model.Student;

public interface SearchDao  extends CrudRepository<Student,Long> {
	
	Student findByLectures(String lecture);
}
