package ch.unibe.ese.model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class University{

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    
    @OneToMany
    private List<Subject> subjects;
    
    @OneToMany
    private List<Lecture> lectures;
    
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
    
    public List<Subject> getSubjects(){
    	return subjects;
    }
    
    public void setSubjects(List<Subject> subjects){
    	this.subjects = subjects;
    }
    
    public List<Lecture> getLectures(){
    	return lectures;
    }
    
    public void setLectures(List<Lecture> lectures){
    	this.lectures = lectures;
    }
    
	public String toString(){
		return name;
	}
}
