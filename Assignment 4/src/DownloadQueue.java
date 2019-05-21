/**
 * 
 * @author Ralph Huang
 * 		email: ralph.huang@stonybrook.edu
 * 		Stony Brook ID: 110905260
 *
 */
import java.util.Collection;
import java.util.Iterator;
import java.util.PriorityQueue;

public class DownloadQueue extends PriorityQueue{
	
	/**
	 * Uses PriorityQueue super class to add an instance of <code>DownloadJob</code> to the queue.
	 * 
	 * <dt>Precondition:
	 * <dd><code>d</code> must be a <code>DownloadJob</code> instance.
	 * 
	 * @param d
	 * 	New <code>DownloadJob</code> instance to be added to queue.
	 * 
	 */
	public void enqueue(DownloadJob d)
	{
		add(d);
	}
	
	/**
	 * Removes a <code>DownloadJob</code> ,using the superclass, from the queue and returns it.
	 * 
	 * <dt>Postcondition:
	 * <dd>The instance of <code>DownloadJob</code> has been removed from the head of the queue.
	 * @exception EmptyQueueException
	 * 		This exception is thrown when the <CODE>DownloadQueue<CODE> is empty.
	 * @return
	 * 		The return value is the <code>DownloadJob</code> that was removed from the queue.
	 */
	public DownloadJob dequeue()
	{
		DownloadJob removed = (DownloadJob) peek();
		if(removed == null)
			throw new EmptyQueueException("Queue is empty");
		remove(peek());
		return removed;		
	}
	
	/**
	 * Returns the <code>DownloadJob</code> at the top of the queue.
	 * @exception EmptyQueueException
	 * 		This exception is thrown when the <CODE>DownloadQueue<CODE> is empty.
	 * @return
	 * 		The return value is the <code>DownloadJob</code> at the top of the queue.
	 * 
	 */
	public DownloadJob peek()
	{
		DownloadJob top = (DownloadJob) super.peek();
		if (top == null)
			throw new EmptyQueueException("Queue is empty.");
		return top;
	}
	
	/**
	 * Returns a boolean that is true when the <CODE>DownloadQueue<CODE> is empty and false when not.
	 * @return
	 * 		Returns true if the <CODE>DownloadQueue<CODE> is empty, or false if not.
	 */
	public boolean isEmpty()
	{
		return super.isEmpty();
	}
}

