/**
 * @author Ralph Huang
 * email: ralph.huang@stonybrook.edu
 * Stony Brook ID: 110905260
 */

public class Actor {

	/**
	 * Field that holds the name of the <CODE>Actor</CODE>.
	 */
	private String name;
	
	/**
	 * Field that represents the number of movies this <CODE>Actor</CODE> is in.
	 * Default value should be 0.
	 */
	private int count = 0;
	
	/**
	 * Overloaded constructor for creating a new <CODE>Actor</CODE> object with the specified name.
	 * @param moviename
	 * The <CODE>Actor</CODE>'s name.
	 */
	public Actor(String moviename)
	{
		name = moviename;
	}
	
	/**
	 * <CODE>name</CODE> accessor method.
	 * @return
	 * Returns the <CODE>name</CODE> of the actor.
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * <CODE>count</CODE> accessor method.
	 * @return
	 * Returns the <CODE>count</CODE> of the actor.
	 */
	public int getCount()
	{
		return count;
	}
	
	/**
	 * <CODE>name</CODE> mutator method.
	 * @param newName
	 * The new name to be set.
	 */
	public void setName(String newName)
	{
		name = newName;
	}
	
	/**
	 * <CODE>count</CODE> mutator method.
	 * @param newCount
	 * The new count to be set.
	 */
	public void setCount(int newCount)
	{
		count = newCount;
	}
	
	/**
	 * Increments <CODE>count</CODE> by one.
	 */
	public void incCount()
	{
		count++;
	}
	
	/**
	 * Decrements <CODE>count</CODE> by one.
	 */
	public void decCount()
	{
		count--;
	}
	
}
