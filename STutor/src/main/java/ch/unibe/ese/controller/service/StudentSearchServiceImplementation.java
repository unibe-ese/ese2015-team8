package ch.unibe.ese.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.dao.StudentDao;

@Service("StudentSearchService")
public class StudentSearchServiceImplementation implements StudentSearchService {

	@Autowired
	StudentDao studentDao;

	public Student findTutorById(Long id) {
		return studentDao.findOne(id);
	}

	public Student getStudentByUsername(String name) {
		Student student = studentDao.findByUsername(name);
		return student;
	}

	@Transactional
	public Student saveStudentIntoDB(Student student) {

		student = studentDao.save(student);

		return student;
	}

}
