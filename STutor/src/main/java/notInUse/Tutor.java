package notInUse;

import java.util.LinkedList;

import org.sample.model.Comment;
import org.sample.model.Lecture;
import org.sample.model.Student;

/**
 * Not in use
 * @author Christian
 *
 */
public class Tutor extends Student {
	private String sexe;
	private LinkedList<Lecture> lectures;
	private LinkedList<Comment> comments;
	private double rating;

	public String getGender() {
		return sexe;
	}

	public void setGender(String sexe) {
		this.sexe = sexe;
	}

	public LinkedList<Lecture> getLectures() {
		return lectures;
	}

	public void setLectures(LinkedList<Lecture> lectures) {
		this.lectures = lectures;
	}

	public LinkedList<Comment> getComments() {
		return comments;
	}

	public void setComments(LinkedList<Comment> comments) {
		this.comments = comments;
		
		double rating = 0;
		if(comments.isEmpty()){
			this.rating = -1;
		}else{
			for(Comment temp : comments){
				rating += temp.getRating();
			}
			this.rating = rating/comments.size();
		}
	}
	
	public double getRating(){
		return rating;
	}
}
