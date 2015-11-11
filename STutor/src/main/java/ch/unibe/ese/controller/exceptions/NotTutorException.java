package ch.unibe.ese.controller.exceptions;

/**
 * @author ESE Team 8
 * @version 1.0
 * @since 28.10.2015
 */
public class NotTutorException extends RuntimeException {
	private static final long serialVersionUID = 5224978375261435026L;

	public NotTutorException(String s) {
        super("User doesn't have Tutor rights for: "+s);
    }
}
