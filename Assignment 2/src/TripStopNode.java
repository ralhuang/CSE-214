/**
 * 
 * @author Ralph Huang
 * 	email: ralph.huang@stonybook.edu
 *  Stony Brook ID: 110905260
 *
 */
public class TripStopNode {
	/**
	 * The <CODE>TripStop</CODE> to be wrapped in.
	 */
	private TripStop data;

	/**
	 * The next <CODE>TripStopNode</CODE>, linked "in front" of the current <CODE>TripStopNode</CODE>.
	 */
	private TripStopNode next;

	/**
	 * The previous <CODE>TripStopNode</CODE>, linked "behind" of the current <CODE>TripStopNode</CODE>.
	 */
	private TripStopNode prev;

	/**
	 * The constructor that wraps the <CODE>TripStop</CODE>.
	 * <dt><b>Precondition:</b>
	 * <dd>This <CODE>initData</CODE> is not null.
	 * <dt><b>Postcondition:</b>
	 * <dd>This <CODE>TripStopNode</CODE> has been initialized to wrap the indicated TripStop, and <CODE>prev</CODE> and next have been set to null.
	 * @param initData
	 * The <CODE>TripStop</CODE> that is wrapped in the constructor, set equal to the <CODE>data</CODE>.
	 * @exception IllegalArgumentException
	 * This exception is thrown when the <CODE>TripStop</CODE> parameter is null.
	 */
	public TripStopNode(TripStop initData)
	{
		if(initData != null)
		{
			data = initData;
			next = null;
			prev = null;
		}
		else
			throw new IllegalArgumentException("Null.");
	}

	/**
	 * <CODE>data</CODE> accessor method.
	 * @return The return value is the <CODE>data</CODE>. Returns the reference to the data member variable of the list node.
	 * @exception
	 * This exception is thrown when <CODE>data</CODE> has a value of null.
	 */
	public TripStop getData()
	{
		if(data == null)
			throw new IllegalArgumentException("Null data.");
		return data;
	}

	/**
	 * <CODE>data</CODE> mutator method.
	 * <dt><b>Precondition:</b>
	 * <dd><CODE>newData</CODE> is not null.
	 * @param newData 
	 * The <CODE>TripStop</CODE> that would replace the <CODE>data</CODE> field.
	 * @exception IllegalArgumentException
	 * This exception is thrown when the parameter of type <CODE>TripStop</CODE> have a value of null.
	 */
	public void setData(TripStop newData)
	{
		if(newData != null)
		{
			data = newData;
		}
		else
			throw new IllegalArgumentException("Null.");
	}

	/**
	 * <CODE>next</CODE> accessor method.
	 * @return
	 * The return value is the <CODE>next</CODE> field.
	 */
	public TripStopNode getNext()
	{
		return next;
	}

	/**
	 * <CODE>next</CODE> mutator method.
	 * @param newNext
	 * The <CODE>TripStopNode</CODE> to replace the <CODE>next</CODE> field. It is okay for this parameter to be null, since it is okay to have no next <CODE>TripStopNode</CODE> (this means we've reached the tail of the list!).
	 */
	public void setNext(TripStopNode newNext)
	{
		next = newNext;
	}

	/**
	 * <CODE>prev</CODE> accessor method.
	 * @return
	 * The return value is the <CODE>prev</CODE> field.
	 */
	public TripStopNode getPrev()
	{
		return prev;
	}

	/**
	 * <CODE>prev</CODE> mutator method.
	 * @param newPrev
	 * The <CODE>TripStopNode</CODE> to replace the <CODE>prev</CODE>
	 */
	public void setPrev(TripStopNode newPrev)
	{
		prev = newPrev;
	}
}
