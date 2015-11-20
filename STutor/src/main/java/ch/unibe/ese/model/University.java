package ch.unibe.ese.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * A University is an Object attached to a {@link Lecture}.
 * <p>It contains:
 * <ul>
 * <li>id (Long), unique and auto generated: it defines the University.</li>
 * <li>name (String), name of the University.</li>
 * </ul>
 * @author ESE Team 8
 * @version 1.0
 * @since 28.10.2015
 * @see ch.unibe.ese.controller.LectureController
 * @see ch.unibe.ese.controller.service.CommentServiceImplementation
 * @see ch.unibe.ese.model.dao.UniversityDao
 */
@Entity
public class University{

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    
    public Long getId(){
    	return id;
    }
    
    public void setId(long id){
    	this.id=id;
    }
    
    public String getName(){
    	return name;
    }
    
    public void setName(String name){
    	this.name = name;
    }
    
	public String toString(){
		return name;
	}
}
