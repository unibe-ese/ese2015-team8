package ch.unibe.ese.controller.pojos;

/**
 * 
 * @author Stefan Jonas
 * @version 1.0
 * @since 4.11.2015
 */
public class RefinedSearchForm {
	private String name;
	private Long University;
	private Long Subject;
	
	private String gender;
	
	private float minGrade;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getUniversity() {
		return University;
	}
	public void setUniversity(Long university) {
		University = university;
	}
	public Long getSubject() {
		return Subject;
	}
	public void setSubject(Long subject) {
		Subject = subject;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public float getMinGrade(){
		return minGrade;
	}
	
	public void setMinGrade(float minGrade){
		this.minGrade = minGrade;
	}
}