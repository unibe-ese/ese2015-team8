package ch.unibe.ese.controller.pojos;

import ch.unibe.ese.model.University;

/**
 * Special Tutor form for setting gender and University.
 * @author Christian Zürcher
 * @version 1.0
 * @since 21.10.2015
 */
public class TutorSignupForm{
	
	private String gender;
	private University university;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}
}
