package ch.unibe.ese.controller.exceptions;

public class NotTutorException extends RuntimeException {
	private static final long serialVersionUID = 5224978375261435026L;

	public NotTutorException(String s) {
        super("User doesn't have Tutor rights for: "+s);
    }
}
