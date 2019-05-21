/**
 * 
 * @author Ralph Huang
 * 	email: ralph.huang@stonybook.edu
 *  Stony Brook ID: 110905260
 *
 */
public class EndOfListException extends RuntimeException
{
	/**
	 * Passes the exception to the method called, with a string.
	 * @param e
	 * The string to be printed.
	 */
	public EndOfListException(String e)
	{
		super(e);
	}
}
