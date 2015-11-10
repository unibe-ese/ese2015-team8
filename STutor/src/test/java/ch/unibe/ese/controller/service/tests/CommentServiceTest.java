package ch.unibe.ese.controller.service.tests;

import static org.junit.Assert.assertEquals;

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

}
