/**
 * 
 * @author Ralph Huang  9/13/2016
 *	e-mail: ralph.huang@stonybrook.edu
 *	Stony Brook ID: 110905260
 *
 */

public class Menu {
	/**
	 * The integer that is counted for the <CODE>size</CODE> method
	 */
	private int countSize = 0;

	/**
	 * The maximum valid integer element for the array of <CODE>MenuItem</CODE>
	 * objects
	 */
	final int MAX_ITEMS = 50;

	MenuItem[] menus = new MenuItem[MAX_ITEMS]; // Array for storing
												// <CODE>MenuItems</CODE>
												// objects

	/**
	 * Constructs an instance of the <CODE>Menu</CODE> class that is empty menu.
	 */
	public Menu() {

	}

	/**
	 * Generates a copy of this <CODE>Menu</CODE> object.
	 * 
	 * @return The return value is a copy of this <CODE>Menu</CODE>. Subsequent
	 *         changes to the copy will not affect the original, nor vice versa.
	 *         Note that the return value must be typecast to a
	 *         <CODE>Menu</CODE> before it can be used.
	 */
	public Object cloneMenu() {
		Menu clone = new Menu();
		for (int i = 0; i < clone.menus.length; i++) {
			clone.menus[i] = (MenuItem) menus[i].clone(menus[i]);
		}
		clone.countSize = size();
		return clone;
	}

	/**
	 * Compares this <CODE>Menu</CODE> to another object for equality.
	 * 
	 * @return A return value of <CODE>true</CODE> indicates that obj refers to
	 *         a <CODE>Menu</CODE> object with the same elements as this
	 *         <CODE>Menu</CODE>. Otherwise, the return value is
	 *         <CODE>false</CODE>.
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Menu) {
			Menu testMenu = (Menu) obj;
			for (int i = 0; i < MAX_ITEMS; i++) {
				if (!(menus[i].equals((MenuItem) testMenu.menus[i]))) {
					return false;
				}
			}
			return true;
		} else
			return false;
	}

	/**
	 * Gives the number of elements inside the <CODE>menus</CODE> array field.
	 * 
	 * <dt><b>Precondition:</b>
	 * <dd>This method is called on a <CODE>Menu</CODE> object.
	 * 
	 * @return The return value is the number of elements inside the
	 *         <CODE>menus</CODE> array.
	 * 
	 */
	public int size() {
		return countSize;
	}

	/**
	 * Adds a new element to the <CODE>menus</CODE> array field.
	 * 
	 * <dt><b>Precondition:</b>
	 * <dd>This <CODE>Menu</CODE> has been instantiated and element is within
	 * correct range.
	 * <dt><b>Postcondition:</b>
	 * <dd>The element is added to the set.
	 * 
	 * @param item
	 *            The <CODE>MenuItem</CODE> object to be added to the
	 *            <CODE>menus</CODE> array field.
	 * @param position
	 *            The integer in which position of the <CODE>menus</CODE> array
	 *            (+1) refers to.
	 * @throws FullListException
	 *             This exception is thrown when the <CODE>menus</CODE> is
	 *             already full.
	 * @exception IllegalArgumentException
	 *                This exception is thrown when the <CODE>position</CODE> is
	 *                greater than <CODE>MAX_ITEMS</CODE> or negative.
	 * 
	 */
	public void addItem(MenuItem item, int position) throws FullListException {
		if (position > 0 && position < (MAX_ITEMS + 1)) {
			if (size() < 50) {
				if (menus[position - 1] != null) {
					while (position > size()) {
						position--;
					}
					System.arraycopy(menus, position - 1, menus, position, size() - position + 1);
					menus[position - 1] = item;
					countSize++;
				} else {
					while (position > size()) {
						position--;
					}
					menus[position] = item;
					countSize++;
				}
			} else
				throw new FullListException("Your menu is already full");
		} else
			throw new IllegalArgumentException("That is not a valid position.");
	}

	/**
	 * Removes an <CODE>MenuItem</CODE> object from the <CODE>menus</CODE> array
	 * field.
	 * <dt><b>Precondition:</dt></b> The <CODE>menus</CODE> array must be
	 * initiated and the <CODE>position</CODE> must be positive or less than
	 * <CODE>MAX_ITEMS</CODE>.
	 * <dt><b>Postcondition:</dt></b> The <CODE>MenuItem</CODE> is removed from
	 * the <CODE>menus</CODE> array.
	 * 
	 * @param position
	 *            The integer in which position of the <CODE>menus</CODE> array
	 *            (+1) refers to.
	 * 
	 * @exception NullPointerException
	 *                This exception is thrown when the position referred to is
	 *                null in the <CODE>menus</CODE> array.
	 * @exception IllegalArgumentException
	 *                This exception is thrown when the <CODE>position</CODE> is
	 *                greater than <CODE>MAX_ITEMS</CODE> or negative.
	 */
	public void removeItem(int position) {
		if (position > 0 && position < (MAX_ITEMS + 1)) {
			if (menus[position] != null) {
				System.arraycopy(menus, position, menus, position - 1, size() - position);
				countSize--;
			} else if (menus[position] == null) {
				menus[position - 1] = null;
				countSize--;
			} else if (menus[position - 1] == null) {
				throw new NullPointerException("There is nothing in that position.");
			}
		} else
			throw new IllegalArgumentException("That is not a valid position.");
	}

	/**
	 * Finds the corresponding <CODE>MenuItem</CODE> with the given
	 * <CODE>position</CODE>.
	 * 
	 * @param position
	 *            The position (+1) of the <CODE>menus</CODE> array that the
	 *            method is searching for.
	 * @return The return value is a <CODE>MenuItem</CODE> that is found in the
	 *         <CODE>menus</CODE> given the </CODE>position</CODE>.
	 */
	public MenuItem getItem(int position) {
		if (position > 0 && position < (MAX_ITEMS + 1) && menus[position - 1] != null) {
			return menus[position - 1];
		} else
			throw new IllegalArgumentException("That's not a valid position.\n");

	}

	/**
	 * Finds the corresponding <CODE>MenuItem</CODE> for the given
	 * <CODE>name</CODE> in the <CODE>menus</CODE> array.
	 * 
	 * @param name
	 *            The string in which you are searching for in the
	 *            <CODE>menu</CODE> array.
	 * @return The return value is a <CODE>MenuItem</CODE> object found in the
	 *         <CODE>menus</CODE> array.
	 * @exception IllegalArgumentException
	 *                This exception in thrown when there is no
	 *                <CODE>MenuItem</CODE> on the <CODE>menu</CODE> with that
	 *                <CODE>name</CODE>.
	 */
	public MenuItem getItemByName(String name) {
		int sizeTemp = size();
		for (int i = 0; i < sizeTemp; i++) {
			if (menus[i].getName().equals(name))
				return menus[i];
		}
		throw new IllegalArgumentException("There is no item on the menu with that name.");
	}

	/**
	 * Prints the entire <CODE>menu</CODE> to the console.
	 */
	public void printAllItems() {
		System.out.println(toString());
	}

	/**
	 * @return The return value is a <CODE>String</CODE>, in a format, including
	 *         the <CODE>name</CODE>, description, and price.
	 */
	public String toString() {
		String all = "";
		for (int i = 0; i < size(); i++) {
			if (menus[i] != null) {
				all += String.format("%-6d%-25s%-75s%-5.2f%1s", i + 1, menus[i].getName(), menus[i].getDescript(),
						menus[i].getPrice(), "\n");
			}
		}
		return "\n#     " + "Name                     " + "Description                                 "
				+ "                               " + "Price\n" + "-------------------------------------------"
				+ "---------------------------------------------------------------------\n" + all + "\n";
	}
}
