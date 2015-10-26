package ch.unibe.ese.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.unibe.ese.model.Lecture;


public interface LectureDao extends CrudRepository<Lecture,Long> {

}
