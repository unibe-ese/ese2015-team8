package ch.unibe.ese.controller.service;

import ch.unibe.ese.model.Student;

public interface StudentSearchService {

	public Student findTutorById(Long id);
	
	public Student getStudentByUsername(String name);
	
	public Student saveStudentIntoDB(Student student);


	
}
