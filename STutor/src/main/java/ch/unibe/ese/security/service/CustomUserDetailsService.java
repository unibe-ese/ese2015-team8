package ch.unibe.ese.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import ch.unibe.ese.model.Student;
import ch.unibe.ese.model.dao.StudentDao;

/**
 * 
 * @author ESE Team 8
 * @version 1.0
 *
 * This class is needed for Spring Security and linked in its XML File @see springSecurity.xml
 * 
 * Based on that link, it forwards the username from the login form to this, where through the studentDao the 
 * correct student is found (if the student exists in the DB). 
 * 
 * The students gets converted to a spring security user and is being returned as UserDetails 
 * (containing username, password and authorities).
 * 
 */
@Component
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired StudentDao studentDao;
	
	//finds the user/student in the studentDao, converts the student to userdetails
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Student user = studentDao.findByUsername(username);
	
		return new org.springframework.security.core.userdetails.User(user.getUsername(), 
													user.getPassword(), getAuthorities(user));
	}

	
	/**
	 * Authorities handle mostly the access to restricted sites, e.g. a student cannot access the adding lectures page.
	 * Here, a student is given these authorities based on wheter he is a tutor or not. 
	 * If the student is a tutor, he receives the "ROLE_TUTOR" authority, otherwise the "ROLE_STUDENT" authority.
	 * 
	 * For the use of them, check @see springSecurity.xml or @see afterLoginController
	 * 
	 * @param Student: the student that gets converted to userdetails is missing authorities which he is given to here.
	 * @return A collection of authorities
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
	
	/**
	 * Encryptes and saves password to User
	 * @param principal
	 */
	public void encryptePassword(String user) {
		UserDetails userDetails = loadUserByUsername(user);

		Authentication auth = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
				userDetails.getPassword(), userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

}
