package ch.unibe.ese.model;

import java.text.DecimalFormat;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import ch.unibe.ese.controller.exceptions.NotTutorException;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *  A Student is the main User Object.
 * <p>It contains:
 * <ul>
 * <li>id (Long), unique and auto generated: it defines the Student.</li>
 * <li>firstName (String), the First Name of the Student. This should stay Private</li>
 * <li>lastName (String), the Last Name of the Student. This should stay Private</li>
 * <li>username (String), the Username of the Student. The user is identified by id and/or username. The username should be unique.</li>
 * <li>email (String), the E-Mail address of the Student. Should only be revealed to a Tutor who received a {@link Notification}, accepted it and payed for it.</li>
 * <li>password (String), login password of the Student. It is encrypted with SpringSecurity.</li>
 * <li>notifications (Set<{@link Notification}>), the collection of all {@link Notification}s the Student has received. This collection is not Lazy and Cascades to Student.</li>
 * <li>isTutor (boolean), defines if a Student has Tutor rights or not. If set to "true", the Student also has:</li>
 * 		<ul>
 * 		<li>lectures (Set<{@link Lecture}>), the collection of all {@link Lecture}s the Tutor proposes. This collection is not Lazy and Cascades to Student.</li>
 * 		<li>comments (Set<{@link Comment}>), the collection of all {@link Comment}s the Tutor has received. This collection is not Lazy and Cascades to Student.</li>
 * 		<li>timelapses (Set<{@link Timelaps}>), the collection of all {@link Timelaps}s where the Tutor is free. This collection is not Lazy and Cascades to Student.</li>
 * 		<li>rating (double), a general rating of the Tutor based on the {@link Comment}s he received. The result is rounded to 2 digits after the decimal.</li>
 * 		</ul>
 * </ul>
 * @author ESE Team 8
 * @version 1.0
 * @since 4.11.2015
 * @see ch.unibe.ese.controller.IndexController
 * @see ch.unibe.ese.controller.ProfileController
 * @see ch.unibe.ese.controller.pojos.SignupForm
 * @see ch.unibe.ese.controller.service.SignUpServiceImplementation
 * @see ch.unibe.ese.model.dao.StudentDao
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
    
    // RESTRICTED to isTutor=true ---------------------------------------------------------------------
    
    private String gender;
    
        
    @OneToMany(mappedBy="tutor")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Lecture> lectures;
    
    
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @Cascade(CascadeType.ALL)
	private Set<Comment> comments;
    
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @Cascade(CascadeType.ALL)
	private Set<Timelaps> timelapses;
           
	private double rating;
	//-------------------------------------------------------------------------------------------------
    
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
	
	// RESTRICTED to isTutor=true ---------------------------------------------------------------------
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
	
	/**
	 * This method adds a {@link Comment} to the Set of all Comments. This also actualizes the rating of the Tutor.
	 * @param comment
	 */
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
//		if(isTutor==false)
//			throw new NotTutorException("getTimelapses");
		return timelapses;
	}

	public void setTimelapses(Set<Timelaps> timelapses) {
//		if(isTutor==false)
//			throw new NotTutorException("setTimelapses");
		this.timelapses = timelapses;
	}
	
	public void addTimelaps(Timelaps timelaps) {
		if(isTutor==false)
			throw new NotTutorException("addTimelaps");
		timelapses.add(timelaps);
	}
	
	//-----------------------------------------------------------------------------------------------
}
