package ch.unibe.ese.model.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import ch.unibe.ese.model.Lecture;

/**
 * 
 * @author ESE Team 8
 * @version 1.0
 * @since 28.10.2015
 */
public interface LectureDao extends CrudRepository<Lecture,Long> {
	
	List<Lecture> findByName(String name);
	List<Lecture> findByNameAndGradeGreaterThan(String name, double grade, Sort sort);
	List<Lecture> findByNameAndUniversity_idAndSubject_id(String name, Long university_id, Long subject_id, Sort sort);
	List<Lecture> findByNameAndUniversity_idAndSubject_idAndGradeGreaterThan(String name, Long university_id, Long subject_id, double grade, Sort sort);
	List<Lecture> findByNameAndUniversity_idAndGradeGreaterThan(String name, Long university_id, double grade, Sort sort);
	List<Lecture> findByNameAndSubject_idAndGradeGreaterThan(String name,Long subject_id, double grade, Sort sort);
}
