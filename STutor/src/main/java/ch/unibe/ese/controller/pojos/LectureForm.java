package ch.unibe.ese.controller.pojos;

/**
 * 
 * @author Christian Zürcher
 * @version 1.0
 * @since 28.10.2015
 */
public class LectureForm {

	private String name;
	private Long subject;
	private Long university;
	private float grade;
	
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
	public float getGrade() {
		return grade;
	}
	public void setGrade(float grade) {
		this.grade = grade;
	}

}
