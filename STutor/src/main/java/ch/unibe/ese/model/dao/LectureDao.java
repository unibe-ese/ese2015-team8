package ch.unibe.ese.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ch.unibe.ese.model.Lecture;

public interface LectureDao extends CrudRepository<Lecture,Long> {
	
	List<Lecture> findByName(String name);
	
}
