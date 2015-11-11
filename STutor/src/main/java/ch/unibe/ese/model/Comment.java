package ch.unibe.ese.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * A Comment is an Object attached to a {@link Student}.
 * <p>It contains:
 * <ul>
 * <li>id (Long), unique and auto generated: it defines the comment.</li>
 * <li>rating (int), an Integer between 1 and 6 which helps rating a {@link Student}.</li>
 * <li>comment (String), Contains additional informations about the {@link Student}, next to the rating.</li>
 * </ul>
 * @author ESE Team 8
 * @version 1.0
 * @since 4.11.2015
 * @see ch.unibe.ese.controller.RatingController
 * @see ch.unibe.ese.controller.pojos.CommentForm
 * @see ch.unibe.ese.controller.service.CommentServiceImplementation
 * @see ch.unibe.ese.model.dao.CommentDao
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
