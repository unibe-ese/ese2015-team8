package ch.unibe.ese.controller.exceptions;

public class InvalidDataException extends RuntimeException {
	private static final long serialVersionUID = 8617823571334956695L;

	public InvalidDataException(String s) {
        super(s);
    }
}
