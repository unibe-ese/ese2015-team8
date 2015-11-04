package ch.unibe.ese.controller.service;

import ch.unibe.ese.controller.pojos.CommentForm;
import ch.unibe.ese.model.Comment;

public interface CommentService {

	public Comment getFrom(CommentForm commentForm);

}
