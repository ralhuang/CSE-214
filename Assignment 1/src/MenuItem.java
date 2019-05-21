/**
 * 
 * @author Ralph Huang  9/13/2016
 *	e-mail: ralph.huang@stonybrook.edu
 *	Stony Brook ID: 110905260
 *
 */

public class MenuItem{

	//fields
	private String itemName;
	private String itemDescript;
	private double itemPrice;

	//Constructor
	public MenuItem(String name, String desc, double price)
	{
		itemName = name;
		itemDescript = desc;
		itemPrice = price;
	}

	//accessors
	public String getName()
	{
		return itemName;
	}

	public String getDescript()
	{
		return itemDescript;
	}

	public double getPrice()
	{
		return itemPrice;
	}

	//mutators
	public void setName(String n)
	{
		itemName = n;
	}

	public void setDescript(String d)
	{
		itemDescript = d;
	}

	public void setPrice(double p)
	{
		itemPrice = p;
		if(itemPrice < 0)
		{
			throw new IllegalArgumentException("Price cannot be negative"); 
		}
	}

	/**
	 * Compares this <CODE>MenuItem</CODE> to another object for equality.
	 * 
	 * @return A return value of <CODE>true</CODE> indicates that obj refers to
	 *         a <CODE>MenuItem</CODE> object with the same elements as this
	 *         <CODE>MenuItem</CODE>. Otherwise, the return value is
	 *         <CODE>false</CODE>.
	 */
	public boolean equals (Object obj)
	{
		if(obj instanceof MenuItem)
		{
			MenuItem test = (MenuItem)obj;
			if(test.getName().equals(itemName))
			{
				if(test.getDescript().equals(itemDescript))
				{
					if(test.getPrice() == (itemPrice))
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Generates a copy of this <CODE>Menu</CODE> object.
	 * @param c The parameter type of <CODE>MenuItem</CODE> is needed
	 * to recreate itself.
	 * 
	 * @return The return value is a copy of this <CODE>MenuItem</CODE>. Subsequent
	 *         changes to the copy will not affect the original, nor vice versa.
	 *         Note that the return value must be typecast to a
	 *         <CODE>MenuItem</CODE> before it can be used.
	 */
	public Object clone(MenuItem c)
	{
		MenuItem cloneItem = new MenuItem(itemName, itemDescript, itemPrice);
		cloneItem.itemName = c.getName();
		cloneItem.itemDescript = c.getDescript();
		cloneItem.itemPrice = c.getPrice();
		return cloneItem;
	}
}
