package ch.unibe.ese.controller.pojos;

/**
 * Form for refined search. The user can specify what
 * kind of Tutor he wants or what exact kind of lecture he's looking for
 * He can set the lecture name, University, Subject, gender and minimum grade 
 * but he can't  search for a tutor name.
 * @author ESE Team 8
 * @version 1.0
 * @since 4.11.2015
 */
public class RefinedSearchForm {
	private String name;
	private Long University;
	private Long Subject;
	
	private String gender;
	
	private double minGrade;
	
	private String sortBy;
	
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
	
	public double getMinGrade(){
		return minGrade;
	}
	
	public void setMinGrade(double minGrade){
		this.minGrade = minGrade;
	}
	
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
}