
public class TreeNode {
	
	/**
	 * The data inside the node (organized in an array), that helps user identify which node is which, in the tree.
	 */
	private String[] keywords;
	
	/**
	 * The node to the left of the current node.
	 */
	private TreeNode left;
	
	/**
	 * The node to the right of the current node.
	 */
	private TreeNode right;
	
	private TreeNode parent;
	
	/**
	 * The id given to every <CODE>TreeNode</CODE> to determine the position in the tree.
	 */
	private String id;
	
	/**
	 * Checks to see if the specified <CODE>TreeNode</CODe> is a leaf
	 * <dt><b>Preconditions:</b>
	 * <dd><CODE>TreeNode</CODE> is initialized.
	 * <dt><b>Postconditions:</b>
	 * <dd>The <CODE>TreeNode</CODE> is unchanged.
	 * @return
	 * The return value is true if both <CODE>left</CODE> and <CODE>right</CODE> are both null,
	 * and false if not.
	 */
	public Boolean isLeaf()
	{
		if (left == null & right == null)
			return true;
		return false;
	}
	
	/**
	 * <CODE>id</CODE> accessor method.
	 * @return
	 * The return value is the <CODE>id</CODE> field.
	 */
	public String getId()
	{
		return id;
	}
	
	/**
	 * <CODE>left</CODE> accessor method.
	 * @return
	 * The return value is the <CODE>left</CODE> field.
	 */
	public TreeNode getLeft()
	{
		return left;
	}
	
	/**
	 * <CODE>right</CODE> accessor method.
	 * @return
	 * The return value is the <CODE>right</CODE> field.
	 */
	public TreeNode getRight()
	{
		return right;
	}
	
	public TreeNode getParent()
	{
		if(parent == null)
			return null;
		return parent;
	}
	/**
	 * <CODE>keywords</CODE> accessor method.
	 * @return
	 * The return value is the <CODE>keywords</CODE> field.
	 */
	public String[] getKeywords()
	{
		return keywords;
	}
	
	/**
	 * <CODE>left</CODE> mutator method.
	 * @param newleft
	 * The <CODE>TreeNode</CODE> to replace the <CODE>left</CODE> field.
	 */
	public void setLeft(TreeNode newleft)
	{
		left = newleft;
		left.setParent(this);
	}
	
	/**
	 * <CODE>right</CODE> mutator method.
	 * @param newright
	 * The <CODE>TreeNode</CODE> to replace the <CODE>right</CODE> field.
	 */
	public void setRight(TreeNode newright)
	{
		right = newright;
		right.setParent(this);
	}
	
	public void setParent(TreeNode newparent)
	{
		parent = newparent;
	}
	/**
	 * <CODE>keywords</CODE> mutator method.
	 * @param newleft
	 * The <CODE>String</CODE> array to replace the <CODE>keywords</CODE> field.
	 */
	public void setKeywords(String[] newkeywords)
	{
		keywords = newkeywords;
	}
	
	/**
	 * <CODE>id</CODE> mutator method.
	 * @param newid
	 * The <CODE>String</CODE> to replace the <CODE>id</CODE> field.
	 */
	public void setId(String newid)
	{
		id = newid;
	}
	
	public String toString()
	{
		String answer = "";
		if(keywords != null)
		{
			for(int i = 0; i < keywords.length; i++)
			{
				answer+= keywords[i] + ",";
				if(i == (keywords.length-1))
					answer = answer.substring(0, answer.length()-1);
			}
			return answer;
		}
		else
			return "tree is empty";
	}
	
	/*public TreeNode search(String keyword, TreeNode temp)
	{
		if(temp.toString().equals(keyword))
			return temp;
		
		if(temp.getLeft() != null)
			{
				temp = temp.getLeft();
				return search(keyword, temp);
			}
		else if(temp.getRight() != null)
			{
				temp = temp.getRight();
				return search(keyword, temp);
			}
	}
	*/
}
