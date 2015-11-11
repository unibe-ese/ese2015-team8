package ch.unibe.ese.controller.exceptions;

/**
 * @author ESE Team 8
 * @version 1.0
 * @since 28.10.2015
 */
public class InvalidUserException extends RuntimeException {
	private static final long serialVersionUID = 5224978375261435026L;

	public InvalidUserException(String s) {
        super(s);
    }
}
