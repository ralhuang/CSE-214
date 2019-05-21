/**
 * 
 * @author Ralph Huang
 * 	email: ralph.huang@stonybrook.edu
 * 	Stony Brook ID: 110905260
 *
 */
import java.util.Stack;

public class JSStack extends Stack{

	private BlockTypeNode top; //The top of the stack to be accessed
	private int numIn = 0; //the number of elements inside the stack
	
	/**
	 * Constructs a TripStop containing a null <CODE>top</CODE> and <CODE>numIn</CODE> set to 0.
	 */
	public JSStack()
	{
		top = null;
		numIn = 0;
	}

	/**
	 * Adds an object of <CODE>BlockType</CODE> to the <CODE>top</CODE> of the <CODE>JSStack</CODE>,
	 *  and increments <CODE>numIn</CODE> by one.
	 */
	public void push(BlockType b)
	{
		BlockTypeNode newNode = new BlockTypeNode(b);
		newNode.setNext(top);
		top = newNode;
		numIn++;
	}

	/**
	 * @return
	 * The return value is the <CODE>BlockType</CODE> to be removed.
	 * @exception EmptyStackException
	 * This exception is thrown if there is nothing in the <CODE>JSStack</CODE>.
	 */
	public BlockType pop()
	{
		if(top != null)
		{
			BlockTypeNode removed = top;
			if(numIn >= 2)
			{
				top = top.getNext();
				numIn--;
				return removed.getIn();
			}
			else
			{
				numIn--;
				top = null;
				return removed.getIn();
			}
		}
		else
		{
			throw new EmptyStackException("Empty Stack.");
		}
	}

	/**
	 * @return
	 * The return value is the <CODE>top</CODE> of the <CODE>JSStack</CODE>,
	 *  if null, it will also return null.
	 */
	public BlockType peek()
	{
		if(top == null)
			return null;
		return top.getIn();
	}

	/**
	 * @return
	 * Returns true if the <CODE>top</CODE> of the stack is null, or false if otherwise.
	 */
	public boolean isEmpty()
	{
		return(top == null);
	}
}
