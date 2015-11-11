package ch.unibe.ese.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import ch.unibe.ese.controller.exceptions.InvalidGradeException;

/**
 * A Lecture is an Object attached to a {@link Student}.
 * <p>It contains:
 * <ul>
 * <li>id (Long), unique and auto generated: it defines the Lecture.</li>
 * <li>name (String), name of the Lecture and can be chosen freely by the {@link Student} (Tutor).</li>
 * <li>grade (float), grade that the given {@link Student} (Tutor) achieved at the exam of this Lecture.</li>
 * <li>subject ({@link Subject}), helps sorting lectures into categories. Multiple Lectures can have the same {@link Subject}.</li>
 * <li>subject ({@link University}), also helps sorting lectures into categories. Multiple Lectures can have the same {@link University}.</li>
 * <li>tutor ({@link Student}), helps to backtrack to the {@link Student} (Tutor) who gives the Lecture. Multiple Lectures can have the same {@link Student}.</li>
 * </ul>
 * @author ESE Team 8
 * @version 1.0
 * @since 28.10.2015
 * @see ch.unibe.ese.controller.AddLectureController
 * @see ch.unibe.ese.controller.pojos.LectureForm
 * @see ch.unibe.ese.controller.service.SignUpServiceImplementation
 * @see ch.unibe.ese.model.dao.LectureDao
 */

@Entity
public class Lecture {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	private double grade;
	
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
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) throws InvalidGradeException {
		if((grade > 6.0) || (grade < 0)){
			throw new InvalidGradeException("You've entered an invalid grade");
		}
		this.grade = grade;
		
	}
	public University getUniversity(){
		return university;
	}
	public void setUniversity(University university){
		this.university = university;
	}
	public void setTutor(Student tutor) {
		this.tutor = tutor;
	}
	public Student getTutor() {
		return tutor;
	}
	
	@Override
	public String toString() {
		return name + ", " + subject.getName() + ", " + university.getName();
	}
}
