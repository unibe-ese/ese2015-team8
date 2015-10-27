package ch.unibe.ese.controller.pojos;

public class SearchForm {
	private String name;
	private Long University;
	private Long Subject;
	
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
}