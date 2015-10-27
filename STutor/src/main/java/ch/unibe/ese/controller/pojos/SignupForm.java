package ch.unibe.ese.controller.pojos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import ch.unibe.ese.model.dao.StudentDao;

public class SignupForm {

	@Autowired
	StudentDao userDao;

	private Long id;

	@Pattern(regexp = ".+", message = "First Name can't be empty")
	private String firstName;

	@Pattern(regexp = ".+", message = "Last Name can't be empty")
	private String lastName;

	@Pattern(regexp = ".+", message = "Username can't be empty")
	private String username;

	@Pattern(regexp = ".+", message = "Password can't be empty")
	private String password;
	private boolean isTutor;

	private String gender;

	@NotNull
	@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Must be valid email address")
	private String email;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
