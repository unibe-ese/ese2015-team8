package ch.unibe.ese.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import ch.unibe.ese.controller.exceptions.NotTutorException;

@Entity
public class Student{

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private boolean isTutor;
    
    //Only for isTutor=true
    private String gender;
	private LinkedList<Lecture> lectures;
	private LinkedList<Comment> comments;
	private double rating;
	//-------------------------
    
	public Long getId() {
        return id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public String getFirstName() {
        return firstName;
    }

	public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

	public String getLastName() {
        return lastName;
    }

	public void setLastName(String lastName) {
        this.lastName = lastName;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
        return email;
    }

	public void setEmail(String email) {
        this.email = email;
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		ShaPasswordEncoder pwEncoder = new ShaPasswordEncoder();
		this.password = pwEncoder.encodePassword(password, this.getUsername());
	}

	public boolean getIsTutor() {
		return isTutor;
	}

	public void setIsTutor(boolean isTutor) {
		this.isTutor = isTutor;
	}
	
	public void setTutor(boolean isTutor) {
		this.isTutor = isTutor;
	}
	
	//Only for isTutor=true --------------------
	public String getGender() {
		if(isTutor==false)
			throw new NotTutorException("getGender");
		return gender;
	}

	public void setGender(String gender) {
		if(isTutor==false)
			throw new NotTutorException("setSexe");
		this.gender = gender;
	}

	public List<Lecture> getLectures() {
	
		return lectures;
	}

	public void addLecture(Lecture lecture){
		lectures.add(lecture);
	}

	public LinkedList<Comment> getComments() {
		if(isTutor==false)
			throw new NotTutorException("getComments");
		return comments;
	}

	public void setComments(LinkedList<Comment> comments) {
		if(isTutor==false)
			throw new NotTutorException("setComments");
		this.comments = comments;
	}

	public double getRating() {
		if(isTutor==false)
			throw new NotTutorException("getRating");
		return rating;
	}

	public void setRating(double rating) {
		if(isTutor==false)
			throw new NotTutorException("setRating");
		this.rating = rating;
	}
	//------------------------------------------------
	
}
