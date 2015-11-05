package ch.unibe.ese.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 
 * @author Christian Zürcher
 * @version 1.0
 * @since 28.10.2015
 */
@Entity
public class Subject{

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String level;
    
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
    
    public String getLevel(){
    	return level;
    }
    
    public void setLevel(String level){
    	this.level = level;
    }
    
	public String toString(){
		return name;
	}
    
}
