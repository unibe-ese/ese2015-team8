package ch.unibe.ese.controller.pojos;

/**
 * 
 * @author Christian Zürcher
 * @version 1.0
 * @since 4.11.2015
 */
public class CommentForm {
	private int rating;
	private String comment;
	
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
