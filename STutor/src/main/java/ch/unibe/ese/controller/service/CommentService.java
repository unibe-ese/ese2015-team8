package ch.unibe.ese.controller.service;

import ch.unibe.ese.controller.pojos.CommentForm;
import ch.unibe.ese.model.Comment;

/**
 * 
 * @author Christian Zürcher
 * @version 1.0
 * @since 4.11.2015
 */
public interface CommentService {

	public Comment getFrom(CommentForm commentForm);

}