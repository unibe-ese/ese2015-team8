package ch.unibe.ese.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
/**
 * @author Christian
 *
 */

@Entity
public class Lecture {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	private long grade;
	
	@ManyToOne
	private Subject subject;
	
	@ManyToOne
	private University university;
	
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
	
	public University getUniversity(){
		return university;
	}
	
	public void setUniversity(University university){
		this.university = university;
	}
	@Override
	public String toString() {
		return name + ", " + subject.getName() + ", " + university.getName();
	}
}
