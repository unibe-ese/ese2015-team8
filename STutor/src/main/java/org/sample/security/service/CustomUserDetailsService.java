package org.sample.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.sample.model.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;



/**
 * TEMPORARY EXPLANATION
 * 
 * This class is needed for the login. In the login form , you enter your username and your PW. (@see login.jsp)
 * 
 * Based on the XML config in @see springSecurity.xml this serves as a "authentication provider"
 * 
 * It means that this class gives the needed dao (studentDao) in which springSec. can find the user
 * by (here) the username.
 * 
 * It doesn't return a student however, but "userDetails", which is the user's name, password
 * and so called "authorities". Depending on your authorities you're restricted to view certain pages. 
 * for example: Unless you're logged in (= then you have the authority role: "ROLE_USER")
 * you cannot load the page /tutorMain or /studentMain.
 *
 *
 */
@Component
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	StudentDao studentDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		org.sample.model.Student user = studentDao.findByUsername(username);
		System.err.println("User found is " + user.getUsername());
	
		return new org.springframework.security.core.userdetails.User(user.getUsername(), 
													user.getPassword(), getAuthorities());
		
	}

	
	/**
	 * TEMPORARY! We could load the authorities by looking at the student and see if he is a tutor.
	 * In this simple case, every logged in user just gets the authority "ROLE_USER" (can be arbitrary).
	 * @return a collection of authorities the logged in student/tutor has. 
	 */
	private Collection<? extends GrantedAuthority> getAuthorities() {
	
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		
		SimpleGrantedAuthority roles = new SimpleGrantedAuthority("ROLE_USER");
		
		list.add(roles);
				
		return list;
	}

}
