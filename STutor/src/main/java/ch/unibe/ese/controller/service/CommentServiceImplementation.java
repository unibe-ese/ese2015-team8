package ch.unibe.ese.controller.service;

import org.springframework.stereotype.Service;

import ch.unibe.ese.controller.pojos.CommentForm;
import ch.unibe.ese.model.Comment;

/**
 * 
 * @author ESE Team 8
 * @version 1.0
 * @since 4.11.2015
 */
@Service
public class CommentServiceImplementation implements CommentService {

	@Override
	public Comment getFrom(CommentForm commentForm) {
		Comment temp = new Comment();
		temp.setRating(commentForm.getRating());
		temp.setComment(commentForm.getComment());
		return temp;
	}

}
