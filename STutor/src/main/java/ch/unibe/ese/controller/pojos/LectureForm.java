package ch.unibe.ese.controller.pojos;

import ch.unibe.ese.model.Student;

public class LectureForm {
	private long id;
	private String name;
	private Long subject;
	private Long university;
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
	public Long getSubject() {
		return subject;
	}
	public void setSubject(Long subject) {
		this.subject = subject;
	}
	public Long getUniversity() {
		return university;
	}
	public void setUniversity(Long university) {
		this.university = university;
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
