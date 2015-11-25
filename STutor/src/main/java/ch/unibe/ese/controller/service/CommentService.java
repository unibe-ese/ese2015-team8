package ch.unibe.ese.controller.service;

import java.util.LinkedList;
import java.util.Set;

import ch.unibe.ese.controller.pojos.CommentForm;
import ch.unibe.ese.model.Comment;
import ch.unibe.ese.model.Student;

/**
 * 
 * @author ESE Team 8
 * @version 1.0
 * @since 4.11.2015
 */
public interface CommentService {

	public Comment getFrom(CommentForm commentForm);
	public Student findTutorById(Long id);
	public LinkedList<Comment> sortComments(Set<Comment> comments);
	public Object sortCommentsDecending(Set<Comment> comments);

}
