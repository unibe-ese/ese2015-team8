package ch.unibe.ese.controller.exceptions;

/**
 * @author ESE Team 8
 * @version 1.0
 * @since 28.10.2015
 */
public class InvalidGradeException extends Exception {

	private static final long serialVersionUID = -6814364822647302279L;

	public InvalidGradeException(String s){
	super(s);
	}
}
