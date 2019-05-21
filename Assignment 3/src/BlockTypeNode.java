/**
 * 
 * @author Ralph Huang
 * 	email: ralph.huang@stonybrook.edu
 * 	Stony Brook ID: 110905260
 *
 */
public class BlockTypeNode {
	/**
	 * The <CODE>BlockType</CODE> to be wrapped inside the node.
	 */
	private BlockType in;
	
	/**
	 * The next <CODE>BlockTypeNode</CODE>, linked "in front" of the current <CODE>BlockTypeNode</CODE>.
	 */
	private BlockTypeNode next;

	/**
	 * The constructor that wraps the <CODE>BlockType</CODE>.
	 * <dt><b>Precondition:</b>
	 * <dd>The <CODE>BlockType</CODE> is not null.
	 * <dt><b>Postcondition:</b>
	 * <dd>This <CODE>BlockTypeNode</CODE> has been initialized to wrap the indicated <CODE>BlockType</CODE>, and have the <CODE>next</CODE> field set to null.
	 * 
	 * @param p
	 * The <CODE>BlockType</CODE> that is wrapped in the constructor, set equal to the value <CODE>in</CODE>.
	 * @exception IllegalArgumentException
	 * This exception is thrown when the <CODE>BlockType</CODE> parameter is null.
	 */
	public BlockTypeNode(BlockType p)
	{
		if(p != null)
		{
			in = p;
			next = null;
		}
		else
			throw new IllegalArgumentException("No enum.");
	}

	/**
	 * 
	 * <CODE>next</CODE> accessor method.
	 * @return
	 * The return value is the <CODE>next</CODE> field.
	 */
	public BlockTypeNode getNext()
	{
		return next;
	}

	/**
	 * <CODE>next</CODE> mutator method.
	 * @param nextNode
	 * The <CODE>BlockTypeNode</CODE> to replace the <CODE>next</CODE> field. It is okay for this to be null since it means we have reached the end of the stack).
	 */
	public void setNext(BlockTypeNode nextNode)
	{
		next = nextNode;
	}

	/**
	 * <CODE>in</CODE> accessor method.
	 * @return The return value is the <CODE>in</CODE> data value. Returns the reference to the <CODE>in</CODE> data value of the <CODE>BlockType</CODE>.
	 */
	public BlockType getIn()
	{
		return in;
	}

}
