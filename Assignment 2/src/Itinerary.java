/**
 * 
 * @author Ralph Huang
 * 	email: ralph.huang@stonybook.edu
 *  Stony Brook ID: 110905260
 *
 */
public class Itinerary {
	private TripStopNode head;
	private TripStopNode tail;
	private TripStopNode cursor;
	// The above variables will hold links into a doubly linked list of TripStopNodes
	// where each node links to the previous and next ones, or nulls if there are none.
	
	private int stops; //a stored integer to count how many stops are in the list
	private int totalDistance; //a stored integer to count the add up the distances of the TripStop(s) in the list

	/**
	 * The <CODE>head</CODE> accessor method.
	 * @return
	 * The return value is the <CODE>head</CODE> field.
	 */
	public TripStopNode getHead()
	{
		return head;
	}

	/**
	 * The <CODE>tail</CODE> accessor method.
	 * @return
	 * The return value is the <CODE>tail</CODE> field.
	 */
	public TripStopNode getTail()
	{
		return tail;
	}

	/**
	 * The <CODE>cursor</CODE> accessor method.
	 * @return
	 * The return value is the <CODE>cursor</CODE> field.
	 */
	public TripStopNode getCursor()
	{
		return cursor;
	}

	 /**
	  * Constructor: initializes an empty <CODE>Itinerary</CODE> with no elements -- <CODE>head</CODE>, <CODE>tail</CODE>, and <CODE>cursor</CODE> are set to null.
	  */
	public Itinerary()
	{
		head = null;
		tail = null;
		cursor = null;
	}

	/**
	 * @return
	 * The return value is the total number of <CODE>TripStopNodes</CODE> in the <CODE>Itinerary</CODE>.
	 */
	public int getStopsCount()
	{
		return stops;
	}

	/**
	 * @return
	 * The return value is the sum of <CODE>distance</CODE>s over all <CODE>TripStopNode</CODE>s.
	 */
	public int getTotalDistance()
	{
		return totalDistance;
	}

	/**
	 * 
	 * @return
	 * The return value is a reference to the <CODE>TripStop</CODE> wrapped by the <CODE>TripStopNode</CODE> that <CODE>cursor</CODE> points to.
	 * @exception IllegalArgumentException
	 * This exception is thrown when the <CODE>cursor</CODE> is pointing to a null <CODE>TripStopNode</CODE>.
	 */
	public TripStop getCursorStop()
	{
		if(cursor != null)
			return cursor.getData();
		else
			throw new IllegalArgumentException("Null.");
	}

	/**
	 * The <CODE>cursor</CODE> is set back to the start of the list, or <CODE>head</CODE>.
	 * <dt><b>Postconditions:</b>
	 * <dd>If <CODE>head</CODE> is not null, the <CODE>cursor</CODE> now references the first <CODE>TripStopNode</CODE> in this list. 
	 * If head is null, the cursor is set to null as well (there are no TripStop objects in this list).
	 */
	public void resetCursorToHead()
	{
		if(head != null)
			cursor = head;
		else
		{
			cursor = null;
			head = cursor;
		}
		System.out.println("Set to head.");
	}

	/**
	 * The <CODE>cursor</CODE> is set back to the end of the list, or <CODE>tail</CODE>.
	 * <dt><b>Postconditions:</b>
	 * <dd>If <CODE>tail</CODE> is not null, the <CODE>cursor</CODE> now references the last <CODE>TripStopNode</CODE> in this list. 
	 * If head is null, the cursor is set to null as well (there are no TripStop objects in this list).
	 */
	public void resetCursorToTail()
	{
		if(tail != null)
			cursor = tail;
		else
		{
			cursor = null;
			tail = cursor;
		}
		System.out.println("Set to tail.");
	}

	/**
	 * Moves the <CODE>cursor</CODE> to select the next <CODE>TripStopNode</CODE> in this list. If <CODE>cursor == tail</CODE>, throw an exception.
	 * @exception EndOfItineraryException
	 * This exception is thrown if <CODE>cursor</CODE> is at the <CODE>tail</CODE> of the list.
	 */
	public void cursorForward()
	{
		if(cursor == tail)
			throw new EndOfItineraryException("Cursor cannot move forward.");
		cursor = cursor.getNext();
	}

	/**
	 * Moves the <CODE>cursor</CODE> to select the previous <CODE>TripStopNode</CODE> in this list. If <CODE>cursor == head</CODE>, throw an exception.
	 * @exception EndOfItineraryException
	 * This exception is thrown if <CODE>cursor</CODE> is at the <CODE>head</CODE> of the list.
	 */
	public void cursorBackward()
	{
		if(cursor == head)
			throw new EndOfItineraryException("Cursor cannot move backward.");
		cursor = cursor.getPrev();
	}

	/**
	 * Inserts the indicated <CODE>TripStop</CODE> before the <CODE>cursor</CODE>.
	 * <dt><b>Preconditions:</b>
	 * <dd><CODE>newStop</CODE> is not null
	 * <dt><b>Postconditions:</b>
	 * <dd>newStop has been wrapped in a new <CODE>TripStopNode</CODE> object
	 * <dd>If <CODE>cursor</CODE> was previously not null, the newly created <CODE>TripStopNode</CODE> has been inserted into the list before the <CODE>cursor</CODE>.
	 * <dd>If <CODE>cursor</CODE> was previously null, the newly created <CODE>TripStopNode</CODE> has been set as the new <CODE>head</CODE> of the list (as well as <CODE>the tail</CODE>).
	 * <dd>The <CODE>cursor</CODE> now references the newly created <CODE>TripStopNode</CODE>.
	 * @param newStop
	 * The <CODE>TripStop</CODE> object to be wrapped and inserted into the list before the <CODE>cursor</CODE>.
	 * @exception IllegalArgumentException
	 * This exception is thrown if <CODE>newStop</CODE> is null.
	 */
	public void insertBeforeCursor(TripStop newStop)
	{
		if(newStop != null)
		{
			TripStopNode newNode = new TripStopNode(newStop);
			if(cursor != head)
			{
				cursor.getPrev().setNext(newNode);
				newNode.setNext(cursor);
				newNode.setPrev(cursor.getPrev());
				cursor.setPrev(newNode);
				cursor = newNode;
				totalDistance += newStop.getDistance();
				stops++;
			}
			else
			{
				if(getStopsCount() == 0)
				{
					cursor = newNode;
					head = newNode;
					tail = newNode;
					totalDistance += newStop.getDistance();
					stops++;
				}
				else
				{
					newNode.setNext(cursor);
					cursor.setPrev(newNode);
					head = newNode;
					cursor = newNode;
					totalDistance += newStop.getDistance();
					stops++;
				}
			}
		}
		else
			throw new IllegalArgumentException("That trip is null");
	}

	/**
	 * Inserts the indicated <CODE>TripStop</CODE> after the <CODE>tail</CODE> of the list.
	 * <dt><b>Preconditions:</b>
	 * <dd><CODE>newStop</CODE> is not null
	 * <dt><b>Postconditions:</b>
	 * <dd><CODE>newStop</CODE> has been wrapped in a new <CODE>TripStopNode</CODE> object
	 * <dd>If <CODE>tail</CODE> was previously not null, the newly created <CODE>TripStopNode</CODE> has been inserted into the list after the <CODE>tail</CODE>.
	 * <dd>If <CODE>tail</CODE> was previously null, the newly created <CODE>TripStopNode</CODE> has been set as the new <CODE>head</CODE> of the list (as well as the <CODE>tail</CODE>).
	 * <dd>The <CODE>cursor</CODE> now references the newly created <CODE>TripStopNode</CODE>.
	 * @param newStop
	 * The <CODE>TripStop</CODE> object to be wrapped and inserted into the list after the <CODE>tail</CODE>.
	 * @exception IllegalArgumentException
	 * This exception is thrown if <CODE>newStop</CODE> is null.
	 */
	public void appendToTail(TripStop newStop)
	{
		TripStopNode newNode = new TripStopNode(newStop);
		if(tail != null)
		{
			//TripStopNode temp = tail;
			tail.setNext(newNode);
			newNode.setPrev(tail);
			tail = newNode;
			totalDistance += newStop.getDistance();
			stops++;
		}
		else
		{
			head = newNode;
			tail = newNode;
			cursor = newNode;
			totalDistance += newStop.getDistance();
			stops++;
		}
	}

	/**
	 * Removes the <CODE>TripStop</CODE> at the <CODE>cursor</CODE>.
	 * <dt><b>Preconditions:</b>
	 * <dd><CODE>cursor</CODE> is not null
	 * <dt><b>Postconditions:</b>
	 * <dd>The <CODE>TripStopNode</CODE> referenced by <CODE>cursor</CODE> has been removed from the list.
	 * <dd>All other <CODE>TripStopNodes</CODE> in the list exist in the same order as before.
	 * <dd>The <CODE>cursor</CODE> now references the next <CODE>TripStopNode</CODE>.
	 * @return
	 * The return value is the <CODE>TripStop</CODE> that was removed.
	 * @exception EndOfListException
	 * This exception is thrown if <CODE>cursor</CODE> is null.
	 */
	public TripStop removeCursor()
	{
		if(cursor != null)
		{
			TripStopNode removed = cursor;
			if(cursor != tail)
			{
				if(cursor != head)
				{
					cursor.getNext().setPrev(cursor.getPrev());
					cursor.getPrev().setNext(cursor.getNext());
					cursor.setNext(null);
					cursor.setPrev(null);
					cursor = removed.getNext();
					stops--;
					totalDistance -= removed.getData().getDistance();
					return removed.getData();
				}
				else
				{
					head = cursor.getNext();
					cursor.getNext().setPrev(null);
					cursor.setNext(null);
					cursor = head;
					stops--;
					totalDistance -= removed.getData().getDistance();
					return removed.getData();
				}
			}
			else
			{
				if(cursor != head)
				{
					tail = cursor.getPrev();
					TripStopNode temp = cursor.getPrev();
					cursor.getPrev().setNext(null);
					cursor.setPrev(null);
					cursor = temp;
					stops--;
					totalDistance -= removed.getData().getDistance();
					return removed.getData();
				}
				else
				{
					head = null;
					cursor = null;
					tail = null;
					stops--;
					totalDistance -= removed.getData().getDistance();
					return removed.getData();
				}
			}
		}
		else
		{
			throw new EndOfListException("Null cursor.");
		}
	}
	
	/**
	 * @return
	 * The return value is a string that formats the <CODE>Itinerary</CODE> list as a string.
	 */
	public String toString()
	{
		if(getStopsCount() == 0)
		{
			return "No trips planned.";
		}
		else
		{
			String curs;
			String entTrip = "";
			TripStopNode temp = cursor;
			resetCursorToHead();
			for(int i = 0; i < getStopsCount(); i++)
			{
				if(cursor.equals(temp))
					curs = "  > ";
				else
					curs = "    ";
				entTrip += String.format("%-4s%-25s%-25s%-6d\n", curs, cursor.getData().getLocation(), 
						cursor.getData().getActivity(), cursor.getData().getDistance());
				if(cursor.getNext() != null)
					cursorForward();
			}
			cursor = temp;
			entTrip += "\nSummary: There are " + getStopsCount() + " stop(s), with a total of " + getTotalDistance()
			+ " miles. \n There are " + stopsBefore()
			+ " stops before " + temp.getData().getLocation() + " and " + stopsAfter()
			+ " stops after.\n";
			return entTrip;
		}
	}
	
	/**
	 * @return
	 * The return value is an integer that counts the number of <CODE>TripStopNode</CODE>s before the <CODE>cursor</CODE>.
	 */
	public int stopsBefore()
	{
		int x = 0;
		TripStopNode temp = cursor;
		while(cursor != null)
		{
			if(cursor.getPrev() == null)
				break;
			cursor = cursor.getPrev();
			x++;
		}
		cursor = temp;
		return x;
	}

	/**
	 * @return
	 * The return value is an integer that counts the number of <CODE>TripStopNode</CODE>s after the <CODE>cursor</CODE>.
	 */
	public int stopsAfter()
	{
		int y = 0;
		TripStopNode tempA = cursor;
		while(cursor != null)
		{
			if(cursor.getNext() == null)
				break;
			cursor = cursor.getNext();
			y++;
		}
		cursor = tempA;
		return y;
	}
}
