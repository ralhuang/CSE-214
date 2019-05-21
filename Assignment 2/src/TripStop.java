/**
 * 
 * @author Ralph Huang
 * 	email: ralph.huang@stonybook.edu
 *  Stony Brook ID: 110905260
 *
 */

public class TripStop {
	
	//fields
	private String location;
	private int distance;
	private String activity;

	/**
	 * Default empty constructor
	 */
	public TripStop()
	{

	}

	/**
	 * Constructs a TripStop containing a <CODE>location</CODE>, <CODE>distance</CODE>, and <CODE>activity</CODE>
	 * @param l for <CODE>String location</CODE>
	 * @param d for <CODE>int distance</CODE>
	 * @param a for <CODE>String activity</CODE>
	 * @exception IllegalArgumentException
	 * 	This exception is thrown when the <CODE>distance</CODE> is negative.
	 * 
	 */
	public TripStop(String l, int d, String a)
	{
		location = l;
		distance = d;
		activity = a;
		if (distance < 0)
			throw new IllegalArgumentException("Distance cannot be negative.");
	}

	/**
	 * <CODE>location</CODE> accessor method
	 * @return The return value is the <CODE>location</CODE> of the <CODE>TripStop</CODE> class.
	 */
	public String getLocation()
	{
		return location;
	}

	/**
	 * <CODE>distance</CODE> accessor method
	 * @return The return value is the <CODE>distance</CODE> of the <CODE>TripStop</CODE> class.
	 */
	public int getDistance()
	{
		return distance;
	}

	/**
	 * <CODE>activity</CODE> accessor method
	 * @return The return value is the <CODE>activity</CODE> of the <CODE>TripStop</CODE> class.
	 */
	public String getActivity()
	{
		return activity;
	}
	
	/**
	 * <CODE>location</CODE> mutator method
	 * @param str
	 * The <CODE>String</CODE> name of the <CODE>location</CODE> to be changed.
	 */
	public void setLocation(String str)
	{
		location = str;
	}

	/**
	 * <CODE>distance</CODE> mutator method
	 * @param num
	 * The integer in which <CODE>distance</CODE> will be changed to.
	 * @exception IllegalArgumentException
	 * This exception is thrown when the <CODE>distance</CODE> parameter is negative.
	 */
	public void setDistance(int num)
	{
		distance = num;
		if(distance < 0)
			throw new IllegalArgumentException("Distance cannot be negative.");
	}

	/**
	 * <CODE>activity</CODE> mutator method
	 * @param str
	 * The <CODE>String<CODE> description of the <CODE>activity</CODE> to be changed.
	 */
	public void setActivity(String str)
	{
		activity = str;
	}

}
