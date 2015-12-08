package ch.unibe.ese.controller.service;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.unibe.ese.controller.pojos.CommentForm;
import ch.unibe.ese.model.Comment;
import ch.unibe.ese.model.dao.StudentDao;

/**
 * 
 * @author ESE Team 8
 * @version 1.0
 * @since 4.11.2015
 */
@Service
public class CommentServiceImplementation implements CommentService {
	
	@Autowired StudentDao studentDao;

	@Override
	public Comment getFrom(CommentForm commentForm) {
		Comment temp = new Comment();
		temp.setRating(commentForm.getRating());
		temp.setComment(commentForm.getComment());
		return temp;
	}
	
	
	public LinkedList<Comment> sortComments(Set<Comment> comments){
		LinkedList<Comment> sortedComments = new LinkedList<Comment>(comments);
		Comparator<Comment> comparator = new Comparator<Comment>(){
			@Override
			public int compare(Comment o1, Comment o2) {
				return Integer.compare(o1.getRating(),o2.getRating());
			}
		};
		sortedComments.sort(comparator);
		return sortedComments;
	}

	@Override
	public LinkedList<Comment> sortCommentsDecending(Set<Comment> comments) {
		LinkedList<Comment> sortedComments = new LinkedList<Comment>(comments);
		Comparator<Comment> comparator = new Comparator<Comment>(){
			@Override
			public int compare(Comment o1, Comment o2) {
				return Integer.compare(o1.getRating(),o2.getRating());
			}
		};
		sortedComments.sort(comparator.reversed());
		return sortedComments;
	}
}
