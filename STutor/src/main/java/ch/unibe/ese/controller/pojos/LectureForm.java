package ch.unibe.ese.controller.pojos;

import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.Subject;

public class LectureForm {
	private long id;
	private String name;
	private Subject subject;
	private long grade;
	private Student tutor;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public long getGrade() {
		return grade;
	}
	public void setGrade(long grade) {
		this.grade = grade;
	}
	public Student getTutor() {
		return tutor;
	}
	public void setTutor(Student tutor) {
		this.tutor = tutor;
	}
}
