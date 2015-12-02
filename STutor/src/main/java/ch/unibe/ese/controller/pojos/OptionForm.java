package ch.unibe.ese.controller.pojos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import ch.unibe.ese.model.dao.StudentDao;

/**
 * Form for editing own parameters. There are "not empty"-restrictions on 
 * first-, last- and username. The email must have the form "x@x.x". The
 * user can also change his password gender and being Tutor.
 * @author ESE Team 8
 * @version 1.0
 * @since 4.11.2015
 */
public class OptionForm {

	@Autowired
	StudentDao userDao;

	@Pattern(regexp = ".+", message = "First Name can't be empty")
	private String firstName;

	@Pattern(regexp = ".+", message = "Last Name can't be empty")
	private String lastName;

	@Pattern(regexp = ".+", message = "Username can't be empty")
	private String username;

	private String password;
	private boolean isTutor;

	private String gender;

	@NotNull
	@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Must be valid email address")
	private String email;

	@Pattern(regexp = "^[0-9]*?(\\.)?[0-9]{0,2}", message = "Please enter a valid price")
	private String wage;
	
	public String getWage() {
		if(wage == null)
			return "1000";
		return wage;
	}

	public void setWage(String wage) {
		if(wage == null)
			wage = "1000";
		else
			this.wage = wage;
		
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getIsTutor() {
		return isTutor;
	}

	public void setIsTutor(boolean isTutor) {
		this.isTutor = isTutor;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
