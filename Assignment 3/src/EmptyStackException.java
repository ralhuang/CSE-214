/**
 * 
 * @author Ralph Huang
 * 	email: ralph.huang@stonybook.edu
 *  Stony Brook ID: 110905260
 *
 */

public class EmptyStackException extends RuntimeException {
	
	/**
	 * Passes the exception to the method called, with a string.
	 * @param e
	 * The string to be printed when exception is thrown.
	 */
	public EmptyStackException(String e)
	{
		super(e);
	}
}
