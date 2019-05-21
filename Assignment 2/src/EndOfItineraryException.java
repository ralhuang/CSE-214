/**
 * 
 * @author Ralph Huang
 * 	email: ralph.huang@stonybook.edu
 *  Stony Brook ID: 110905260
 *
 */
public class EndOfItineraryException extends RuntimeException
{
	/**
	 * Passes the exception to the method called, with a string.
	 * @param e
	 * The string to be printed.
	 */
	public EndOfItineraryException(String e)
	{
		super(e);
	}
}
