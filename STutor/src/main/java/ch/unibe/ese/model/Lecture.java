package ch.unibe.ese.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Lecture {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	
    @LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany
	private List<Student> tutors = new LinkedList<Student>();
	
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
	public University getUniversity() {
		return university;
	}
	public void setUniversity(University university) {
		this.university = university;
	}
	
	public List<Student> getTutors(){
		return tutors;
	}
	
	public void setTutors(List<Student> tutors){
		this.tutors = tutors;
	}
	
	public void addTutor(Student tutor){
		tutors.add(tutor);
	}
	
	
	public String toString(){
		return name + ", " + subject + ", " +university; 
	}

}
