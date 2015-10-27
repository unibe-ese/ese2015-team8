package ch.unibe.ese.controller.pojos;

import ch.unibe.ese.model.University;

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
