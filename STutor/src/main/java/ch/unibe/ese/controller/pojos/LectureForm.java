package ch.unibe.ese.controller.pojos;


/**
 * form to add a lecture. The Tutor has to set the University
 * where he took this lecture, the grade he got, the name of 
 * the lecture and the subject (eg. Computer Science) this lecture
 * belongs to
 * @author Christian Zürcher
 * @version 1.0
 * @since 28.10.2015
 */
public class LectureForm {

	private String name;
	private Long subject;
	private Long university;
	private double grade;

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

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

}
