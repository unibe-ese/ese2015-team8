package ch.unibe.ese.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ch.unibe.ese.model.Lecture;

public interface LectureDao extends CrudRepository<Lecture,Long> {
	
	List<Lecture> findByName(String name);
	List<Lecture> findByNameAndUniversity_idAndSubject_id(String name, Long university_id, Long subject_id);
}
