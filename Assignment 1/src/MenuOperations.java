
/**
 * 
 * @author Ralph Huang  9/13/2016
 *	e-mail: ralph.huang@stonybrook.edu
 *	Stony Brook ID: 110905260
 * <dt><b>Assignment:</b><dd>
 *    Homework #1 for CSE 214
 *    
 */

import java.util.Scanner;

public class MenuOperations {
	/**
	 * A boolean that checks to see if an operation is valid.
	 * 
	 * @param s
	 *            The user input
	 * @return Returns <CODE>true</CODE> if the operation is valid (equal to A,
	 *         G, R, P, S, D, C, O, I, V, or Q) -case insensitive-. Otherwise,
	 *         it returns <CODE>false<CODE>.
	 */
	public static boolean isOption(String s) {
		if (s.equals("A") || s.equals("G") || s.equals("R") || s.equals("P") || s.equals("S") || s.equals("D")
				|| s.equals("C") || s.equals("O") || s.equals("I") || s.equals("V") || s.equals("Q") || s.equals("a")
				|| s.equals("g") || s.equals("r") || s.equals("p") || s.equals("s") || s.equals("d") || s.equals("c")
				|| s.equals("o") || s.equals("i") || s.equals("v") || s.equals("q"))
			return true;
		return false;
	}

	/**
	 * The method to print out the operations
	 */
	public static void printOptions() {
		System.out.printf("\nMain Menu:\n\nA) Add Item\nG) Get Item\nR) Remove Item\n"
				+ "P) Print All Items\nS) Size\nD) Update Description\n"
				+ "C) Update Price\nO) Add to Order\nI) Remove from Order\n" + "V) View Order\nQ) Quit\n");
	}

	/**
	 * The main method for the class, allowing the user to operate, create, and
	 * control a <CODE>Menu</CODE> and interact with it to order.
	 */
	public static void main(String[] args) {
		boolean isRunning = true;
		Scanner scan = new Scanner(System.in);
		Menu orderMenu = new Menu();
		Menu newMenu = new Menu();
		while (isRunning) {
			printOptions();
			System.out.println("Select an operation:");
			String in1 = scan.nextLine();
			if (isOption(in1)) {
				if (in1.equals("A") || in1.equals("a")) {
					System.out.println("Enter the name of the item:");
					String in1AName = scan.nextLine();
					System.out.println("Enter the description for " + in1AName + ": ");
					String in1ADesc = scan.nextLine();
					System.out.println("Enter the price: ");
					Double in1APrice = scan.nextDouble();
					try {
						if (in1APrice < 0) {
							throw new IllegalArgumentException("Price cannot be negative.");
						}
						System.out.println("Enter the position: ");
						int in1APosition = scan.nextInt();
						MenuItem userOrder = new MenuItem(in1AName, in1ADesc, in1APrice);
						try {
							newMenu.addItem(userOrder, in1APosition);
							System.out.println("Added " + in1AName + ": " + in1ADesc + " for $"
									+ String.format("%-5.2f", in1APrice) + " at position " + in1APosition);
						} catch (FullListException e) {
							System.out.println(e);
						} catch (IllegalArgumentException e) {
							System.out.println(e);
						}
					} catch (IllegalArgumentException e) {
						System.out.println(e);
					}
					in1 = scan.nextLine();
				} else if (in1.equals("G") || in1.equals("g")) {
					System.out.println("Enter the position: ");
					int in1GPosition = scan.nextInt();
					try {
						MenuItem temp = newMenu.getItem(in1GPosition);
						System.out.println("#     " + "Name                     "
								+ "Description                                                                "
								+ "Price\n" + "----------------------------------------------------------"
								+ "------------------------------------------------------\n"
								+ String.format("%-6d%-25s%-75s%-5.2f", in1GPosition, temp.getName(),
										temp.getDescript(), temp.getPrice()));

					} catch (IllegalArgumentException e) {
						System.out.print(e);
					}

					in1 = scan.nextLine();

				} else if (in1.equalsIgnoreCase("R")) {
					System.out.println("Enter the Name: ");
					String in1RName = scan.nextLine();
					boolean removed = false;
					int tempSize = 51;
					for (int i = 0; i < tempSize; i++) {
						tempSize = newMenu.size();
						if (in1RName.equals(newMenu.menus[i].getName())) {
							System.out.println("Removed " + newMenu.menus[i].getName() + " from order.");
							newMenu.removeItem(i + 1);
							removed = true;
						}
					}
					if (!removed)
						System.out.println("The item was not found");
				} else if (in1.equals("P") || in1.equals("p")) {
					newMenu.printAllItems();
				} else if (in1.equals("S") || in1.equals("s")) {
					System.out.println(newMenu.size());
				} else if (in1.equals("D") || in1.equals("d")) {
					try {
						System.out.println("Enter the position: ");
						int in1DPos = scan.nextInt();
						if (in1DPos < 0 || in1DPos > 50 || newMenu.menus[in1DPos - 1] == null)
							throw new IllegalArgumentException("Invalid position.");
						System.out.println("Enter the new description: ");
						scan.nextLine();
						String in1DDesc = scan.nextLine();
						newMenu.menus[in1DPos - 1].setDescript(in1DDesc);
						System.out.println("New description set.");
					} catch (IllegalArgumentException e) {
						System.out.println(e);
						scan.nextLine();
					}

				} else if (in1.equals("C") || in1.equals("c")) {
					System.out.println("Enter the name of the item: ");
					String in1CName = scan.nextLine();
					System.out.println("Enter the new price: ");
					boolean priceChanged = false;
					Double in1CPrice = scan.nextDouble();
					for (int i = 0; i < newMenu.size(); i++) {
						if (in1CName.equals(newMenu.menus[i].getName())) {
							try {
								newMenu.menus[i].setPrice(in1CPrice);
								priceChanged = true;
								System.out.println("Changed the price of " + newMenu.menus[i].getName() + " to "
										+ newMenu.menus[i].getPrice());
								break;
							} catch (IllegalArgumentException e) {
								priceChanged = true;
								System.out.println(e);
							}
						}
					}
					if (!priceChanged) {
						System.out.println("No item with name found.");
					}
					scan.nextLine();
				} else if (in1.equals("O") || in1.equals("o")) {
					System.out.println("Enter position of item to add to order: ");
					int in1OPos = scan.nextInt();
					try {
						orderMenu.addItem(newMenu.menus[in1OPos - 1], orderMenu.size() + 1);
						System.out.println("Added " + newMenu.menus[in1OPos - 1].getName() + " to the order.");
					} catch (FullListException e) {
						System.out.println(e);
					} catch (IllegalArgumentException e) {
						System.out.println(e);
					} catch (NullPointerException e) {
						System.out.println(e);
					}
					scan.nextLine();
				} else if (in1.equals("I") || in1.equals("i")) {
					try {
						System.out.println("Enter the position: ");
						int in1IPos = scan.nextInt();
						String name = orderMenu.menus[in1IPos - 1].getName();
						orderMenu.removeItem(in1IPos);
						System.out.println("Removed " + name + " from order.");
					} catch (NullPointerException e) {
						System.out.println(e);
					} catch (IllegalArgumentException e) {
						System.out.println(e);
					}
					scan.nextLine();

				} else if (in1.equals("V") || in1.equals("v")) {
					orderMenu.printAllItems();
				} else if (in1.equals("Q") || in1.equals("q")) {
					System.out.println("Exitting menu...");
					System.out.println("Have a nice day!");
					System.exit(0);
				}
			} else {
				System.out.println("Not a valid operation.");
			}
		}
		scan.close();
	}
}
