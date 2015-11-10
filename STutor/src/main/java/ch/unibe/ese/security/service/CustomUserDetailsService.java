package ch.unibe.ese.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.dao.StudentDao;



/**
 * TEMPORARY EXPLANATION
 * 
 * This class is needed for the login. In the login form, you enter your username and your PW. (@see login.jsp)
 * 
 * Based on the XML config in @see springSecurity.xml this serves as an "authentication provider"
 * 
 * It means that this class gives the needed dao (studentDao) in which springSec. can find the user
 * by (here) the username.
 * 
 * It doesn't return a student however, but "userDetails", which is the user's name, password
 * and so called "authorities". Depending on your authorities you're restricted to view certain pages. 
 * for example: Unless you're logged in (= then you have the authority role: "ROLE_USER")
 * you cannot load the page /tutorMain or /studentMain.
 *
 * @author Stefan Jonas
 * @version 1.0
 * @since 21.10.2015
 */
@Component
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	StudentDao studentDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Student user = studentDao.findByUsername(username);
	
		return new org.springframework.security.core.userdetails.User(user.getUsername(), 
													user.getPassword(), getAuthorities(user));
		
	}

	
	/**
	 * TEMPORARY! We could load the authorities by looking at the student and see if he is a tutor.
	 * In this simple case, every logged in user just gets the authority "ROLE_USER" (can be arbitrary).
	 * TODO: Have roles to a student and tutor, --->if<--- page restriction will become important
	 * @return a collection of authorities the logged in student/tutor has. 
	 */
	private Collection<? extends GrantedAuthority> getAuthorities(Student student) {
	
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		SimpleGrantedAuthority roles;
		
		if(student.getIsTutor()){
		roles = new SimpleGrantedAuthority("ROLE_TUTOR");}
		
		else{
			roles = new SimpleGrantedAuthority("ROLE_STUDENT");
		}
		
		
		list.add(roles);
				
		return list;
	}

}
