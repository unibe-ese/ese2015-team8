package ch.unibe.ese.model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Subject{

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    
    private String level;
    
    
    @ManyToOne
    private University university;
    
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
    
    public String getLevel(){
    	return level;
    }
    
    public void setLevel(String level){
    	this.level = level;
    }
    
    public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
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
