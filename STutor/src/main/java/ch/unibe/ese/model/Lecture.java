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
	private Long id;
	
	private String name;
	private float grade;
	
	@ManyToOne
	private Subject subject;
	
	@ManyToOne
	private University university;
	
	@ManyToOne
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
	public float getGrade() {
		return grade;
	}
	public void setGrade(float grade) {
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
	
	
	public void setTutor(Student tutor) {
		this.tutor = tutor;
		
	}
	
	public Student getTutor() {
		return tutor;
	}
}
