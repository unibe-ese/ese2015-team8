package ch.unibe.ese.model;

import java.text.DecimalFormat;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import ch.unibe.ese.controller.exceptions.NotTutorException;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * 
 * @author Christian Zürcher
 * @version 1.0
 * @since 21.10.2015
 */
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
    
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @Cascade(CascadeType.ALL)
	private Set<Notification> notifications;
    
    //Only for isTutor=true
    private String gender;
    
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @Cascade(CascadeType.ALL)
	private Set<Lecture> lectures;
    
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @Cascade(CascadeType.ALL)
	private Set<Comment> comments;
    
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @Cascade(CascadeType.ALL)
	private Set<Timelaps> timelapses;
    
    @ManyToOne
    private Town town;
    
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

	public Set<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(Set<Notification> notifications) {
		this.notifications = notifications;
	}
	
	public void addNotification(Notification notification) {
		notifications.add(notification);
	}

	public boolean getIsTutor() {
		return isTutor;
	}

	public void setIsTutor(boolean isTutor) {
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

	public Set<Lecture> getLectures() {
		return lectures;
	}
	
	public void setLectures(Set<Lecture> lectures) {
		this.lectures = lectures;
	}

	public void addLecture(Lecture lecture){
		lectures.add(lecture);
	}

	public Set<Comment> getComments() {
		if(isTutor==false)
			throw new NotTutorException("getComments");
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		if(isTutor==false)
			throw new NotTutorException("setComments");
		this.comments = comments;
	}
	
	public void addComment(Comment comment) {
		comments.add(comment);
		double temp = 0;
		for(Comment acctual : comments)
			temp += acctual.getRating();
		rating = Double.parseDouble(new DecimalFormat("#.##").format(temp/comments.size()));
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
	
	public Set<Timelaps> getTimelapses() {
		if(isTutor==false)
			throw new NotTutorException("getTimelapses");
		return timelapses;
	}

	public void setTimelapses(Set<Timelaps> timelapses) {
		if(isTutor==false)
			throw new NotTutorException("setTimelapses");
		this.timelapses = timelapses;
	}
	
	public void addTimelaps(Timelaps timelaps) {
		if(isTutor==false)
			throw new NotTutorException("addTimelaps");
		timelapses.add(timelaps);
	}
	
	public Town getTown() {
		return town;
	}

	public void setTown(Town town) {
		this.town = town;
	}
	//------------------------------------------------
}
