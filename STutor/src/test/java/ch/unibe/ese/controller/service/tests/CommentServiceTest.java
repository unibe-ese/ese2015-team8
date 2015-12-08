package ch.unibe.ese.controller.service.tests;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.unibe.ese.controller.pojos.CommentForm;
import ch.unibe.ese.controller.service.CommentService;
import ch.unibe.ese.model.Comment;
// 100% Coverage
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/test.xml"})
public class CommentServiceTest {
	
	@Autowired private CommentService commentService;
	
	private CommentForm commentForm;
	private int testRating = 6;
	private String testComment = "test";
	
	@Before
	public void setUpData() {
		commentForm = new CommentForm();
		commentForm.setRating(testRating);
		commentForm.setComment(testComment);
	}
	
	@Test
	public void getCommentFromForm() {
		Comment test = commentService.getFrom(commentForm);
		assertEquals(testRating,test.getRating());
		assertEquals(testComment,test.getComment());
	}
	
	@Test
	public void sorting(){
		//let's create a set of 2 comments with different ratings which we will sort
		
		Comment c1 = new Comment();
		c1.setRating(10);
		Comment c2 = new Comment();
		c2.setRating(5);
		
		Set<Comment> comments = new HashSet<>();
		comments.add(c1);
		comments.add(c2);
		
		//as we sort in a ascending order, the LOWER rated comment (c2) should be first
		List<Comment> cResults = commentService.sortComments(comments);
		assertEquals(c2, cResults.get(0));
		assertEquals(c1, cResults.get(1));
		
		//let's try in a descending order, where the HIGHER rated comment should be first
		List<Comment> cResultsD = commentService.sortCommentsDecending(comments);
		assertEquals(c1, cResultsD.get(0));
		assertEquals(c2, cResultsD.get(1));
	}
	
}
