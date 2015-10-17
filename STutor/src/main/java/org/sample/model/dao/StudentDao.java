package org.sample.model.dao;

import org.sample.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentDao extends CrudRepository<Student,Long> {

	Student findByUsername(String username);
}
