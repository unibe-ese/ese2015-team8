package ch.unibe.ese.model;

/**
 * Not used yet
 * 
 * @author Christian
 *
 */

public class Comment {
	private Long id;
	private int rating;
	private String comment;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
