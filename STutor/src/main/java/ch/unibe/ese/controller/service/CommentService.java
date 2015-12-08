package ch.unibe.ese.controller.service;

import java.util.LinkedList;
import java.util.Set;

import ch.unibe.ese.controller.pojos.CommentForm;
import ch.unibe.ese.model.Comment;

/**
 * Provides processing of comments that are given to a tutor by the students
 * @author ESE Team 8
 * @version 1.0
 * @since 4.11.2015
 */
public interface CommentService {

	/**
	 * @param commentForm
	 * @return form with commentForm's rating and comment
	 */
	public Comment getFrom(CommentForm commentForm);
	/**
	 * @param comments
	 * @return LinkedList with comments sorted by rating
	 */
	public LinkedList<Comment> sortComments(Set<Comment> comments);
	/**
	 * @param comments
	 * @return LinkedList with comments sorted by rating reversed
	 */
	public LinkedList<Comment> sortCommentsDecending(Set<Comment> comments);

}
