package ch.unibe.ese.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Christian Zürcher
 * @version 1.0
 * @since 4.11.2015
 */

@Entity
public class Comment {
	
	@Id
    @GeneratedValue
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
