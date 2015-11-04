package ch.unibe.ese.controller.service;

import org.springframework.stereotype.Service;

import ch.unibe.ese.controller.pojos.CommentForm;
import ch.unibe.ese.model.Comment;

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
