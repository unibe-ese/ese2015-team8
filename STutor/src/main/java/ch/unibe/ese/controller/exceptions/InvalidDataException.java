package ch.unibe.ese.controller.exceptions;

/**
 * 
 * @author Christian Zürcher
 * @version 1.0
 * @since 28.10.2015
 */
public class InvalidDataException extends RuntimeException {
	private static final long serialVersionUID = 8617823571334956695L;

	public InvalidDataException(String s) {
        super(s);
    }
}
