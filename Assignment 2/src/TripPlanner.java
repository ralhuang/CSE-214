/**
 * 
 * @author Ralph Huang
 * 	email: ralph.huang@stonybook.edu
 *  Stony Brook ID: 110905260
 *
 */
import java.util.Scanner;

public class TripPlanner {

	/**
	 * A method to check if user input is valid
	 * @param s
	 * The string that is checked and compared to the other values
	 * @return
	 * True if it matches one of the listed options below, or false if otherwise.
	 */
	public static boolean isOption(String s)
	{
		if(s.equalsIgnoreCase("f") || s.equalsIgnoreCase("b") || s.equalsIgnoreCase("i") || s.equalsIgnoreCase("a") ||
		   s.equalsIgnoreCase("d") || s.equalsIgnoreCase("h") || s.equalsIgnoreCase("t") || s.equalsIgnoreCase("e") || 
		   s.equalsIgnoreCase("s") || s.equalsIgnoreCase("o") || s.equalsIgnoreCase("r") || s.equalsIgnoreCase("p") ||
		   s.equalsIgnoreCase("c") || s.equalsIgnoreCase("q"))
		{
			return true;
		}
		else
			return false;
	}

	/**
	 * Prints a menu of options for the user.
	 */
	public static void printMenu()
	{
		System.out.println("Menu:\n"
				+ "F-Cursor forward\n"
				+ "B-Cursor backward\n"
				+ "I-Insert before cursor\n"
				+ "A-Append to tail\n"
				+ "D-Delete and move cursor forward\n"
				+ "H-Cursor to Head\n"
				+ "T-Cursor to Tail\n"
				+ "E-Edit cursor\n"
				+ "S-Switch itinerary\n"
				+ "O-Insert cursor from other itinerary after cursor from this itinerary\n"
				+ "R-Replace this itinerary with a copy of the other itinerary\n"
				+ "P-Print current itinerary, including summary\n"
				+ "C-Clear current itinerary\n"
				+ "Q-Quit\n");
	}
	/**
	 * the main method, that the user will be interacting with
	 */
	public static void main(String[] args) {
		boolean isRunning = true;
		Scanner scan = new Scanner(System.in);
		int whichItin = 1;
		Itinerary itin1 = new Itinerary();
		Itinerary itin2 = new Itinerary();
		while(isRunning)
		{
			System.out.println("Welcome to TripPlanner!\n");
			printMenu();
			System.out.println("Please select an option from the menu: ");
			String menuOption = scan.nextLine();
			if (isOption(menuOption))
			{
				if(menuOption.equalsIgnoreCase("F"))
				{
					try{
						if(whichItin == 1)
							itin1.cursorForward();
						if(whichItin == 2)
							itin2.cursorForward();
						System.out.println("Cursor moved forward.");
					}
					catch (EndOfItineraryException e)
					{
						System.out.println(e);
					}
				}

				else if(menuOption.equalsIgnoreCase("B"))
				{
					try{
						if(whichItin == 1)
							itin1.cursorBackward();
						else if(whichItin == 2)
							itin2.cursorBackward();
						System.out.println("Cursor moved backward.");
					}
					catch (EndOfItineraryException e)
					{
						System.out.println(e);
					}
				}

				else if(menuOption.equalsIgnoreCase("I"))
				{
					try{
						System.out.println("Enter Location: ");
						String locationI = scan.nextLine();
						System.out.println("Enter Activity: ");
						String activityI = scan.nextLine();
						System.out.println("Enter Distance: ");
						int distanceI = scan.nextInt();
						TripStop newStop = new TripStop(locationI, distanceI, activityI);
						if(whichItin == 1)
						{
							itin1.insertBeforeCursor(newStop);
							System.out.println("Added.");
						}
						else if(whichItin == 2)
						{
							itin2.insertBeforeCursor(newStop);
							System.out.println("Added.");
						}
					}
					catch (IllegalArgumentException e)
					{
						System.out.println(e);
					}
					scan.nextLine();
				}

				else if(menuOption.equalsIgnoreCase("A"))
				{
					try{
						System.out.println("Enter Location: ");
						String locationA = scan.nextLine();
						System.out.println("Enter Activity: ");
						String activityA = scan.nextLine();
						System.out.println("Enter Distance: ");
						int distanceA = scan.nextInt();
						TripStop appendStop = new TripStop(locationA, distanceA, activityA);
						try{
							if(whichItin == 1)
							{
								itin1.appendToTail(appendStop);
								System.out.println("Added.");

							}
							if(whichItin == 2)
							{
								itin2.appendToTail(appendStop);
								System.out.println("Added.");
							}
						}
						catch (IllegalArgumentException e)
						{
							System.out.println(e);
						}
					}
					catch (IllegalArgumentException e)
					{
						System.out.println(e);
					}
					scan.nextLine();
				}

				else if(menuOption.equalsIgnoreCase("D"))
				{
					try{
						if(whichItin == 1)
						{
							itin1.removeCursor();
							System.out.println("Deleted cursor.");
						}
						if(whichItin == 2)
						{
							itin2.removeCursor();
							System.out.println("Deleted cursor.");
						}
					}
					catch (EndOfListException e)
					{
						System.out.println(e);
					}
				}

				else if(menuOption.equalsIgnoreCase("H"))
				{
					if(whichItin == 1)
						itin1.resetCursorToHead();
					if(whichItin == 2)
						itin2.resetCursorToHead();
				}

				else if(menuOption.equalsIgnoreCase("T"))
				{
					if(whichItin == 1)
						itin1.resetCursorToTail();
					if(whichItin == 2)
						itin2.resetCursorToTail();
				}

				else if(menuOption.equalsIgnoreCase("E"))
				{
					try {
						System.out.println("Edit Location, or press enter without typing anything to keep: ");
						String locationE = scan.nextLine();
						System.out.println("Edit Activity, or press enter without typing anything to keep: ");
						String activityE = scan.nextLine();
						System.out.println("Edit Distance, or press -1 without typing anything to keep: ");
						int distanceE = scan.nextInt();
						if (distanceE != -1)
						{
							if(whichItin == 1)
							{
								if(itin1.getCursorStop() != null)
								{
									itin1.getCursorStop().setLocation(locationE);
									itin1.getCursorStop().setActivity(activityE);
									itin1.getCursorStop().setDistance(distanceE);
								}
								else
									throw new IllegalArgumentException("Cursor is null.");
							}
							if(whichItin == 2)
							{
								if(itin2.getCursorStop() != null)
								{
									itin2.getCursorStop().setLocation(locationE);
									itin2.getCursorStop().setActivity(activityE);
									itin2.getCursorStop().setDistance(distanceE);
								}
								else
									throw new IllegalArgumentException("Cursor is null.");
							}
						}
						else
						{
							if(whichItin == 1)
							{
								if(itin1.getCursorStop() != null)
								{
									itin1.getCursorStop().setLocation(locationE);
									itin1.getCursorStop().setActivity(activityE);
								}
								else
									throw new IllegalArgumentException("Cursor is null.");
							}
							if(whichItin == 2)
							{
								if(itin2.getCursorStop() != null)
								{
									itin2.getCursorStop().setLocation(locationE);
									itin2.getCursorStop().setActivity(activityE);
								}
								else
									throw new IllegalArgumentException("Cursor is null.");
							}
						}
					}
					catch(IllegalArgumentException e)
					{
						System.out.println(e);
					}
					scan.nextLine();
				}

				else if(menuOption.equalsIgnoreCase("S"))
				{
					if(whichItin == 1)
						whichItin++;
					else
						whichItin--;
				}

				else if(menuOption.equalsIgnoreCase("O"))
				{
					try {
						if(whichItin == 1)
						{
							itin1.insertBeforeCursor(itin2.getCursorStop());
							System.out.println("Added.");
						}
						if(whichItin == 2)
						{
							itin2.insertBeforeCursor(itin1.getCursorStop());
							System.out.println("Added.");
						}
					} catch (IllegalArgumentException e) {
						System.out.println(e);
					}
				}

				else if(menuOption.equalsIgnoreCase("R"))
				{
					try{
						if(whichItin == 1)
						{
							itin1 = new Itinerary();
							itin2.resetCursorToHead();
							if(itin2.getCursorStop() != null)
							{
								for(int i = 0; i < itin2.getStopsCount(); i++)
								{
									TripStop placeHolder = new TripStop(itin2.getCursorStop().getLocation(), itin2.getCursorStop().getDistance() , itin2.getCursorStop().getActivity());
									//placeHolder = itin2.getCursorStop();
									itin1.appendToTail(placeHolder);
									if(itin2.getCursor() != itin2.getTail())
									{
										itin2.cursorForward();
									}
								}
							}
						}
						if(whichItin == 2)
						{
							itin2 = new Itinerary();
							itin1.resetCursorToHead();
							if(itin1.getCursorStop() != null)
							{
								for(int i = 0; i < itin1.getStopsCount(); i++)
								{
									TripStop placeHolder = new TripStop(itin1.getCursorStop().getLocation(), itin1.getCursorStop().getDistance() , itin1.getCursorStop().getActivity());
									//placeHolder = itin2.getCursorStop();
									itin2.appendToTail(placeHolder);
									if(itin1.getCursor() != itin1.getTail())
									{
										itin1.cursorForward();
									}
								}
							}
						}
					}
					catch(IllegalArgumentException e)
					{
						System.out.println("Replaced with an empty itinerary.");
					}
				}

				else if(menuOption.equalsIgnoreCase("P"))
				{
					if(whichItin == 1)
						System.out.println(itin1.toString());
					else if(whichItin == 2)
						System.out.println(itin2.toString());
				}

				else if(menuOption.equalsIgnoreCase("C"))
				{
					try {
						if(whichItin == 1)
						{
							itin1 = new Itinerary();
							System.out.println("Cleared.");
						}
						if(whichItin == 2)
						{
							itin2 = new Itinerary();
							System.out.println("Cleared.");
						}
					} 
					catch (EndOfListException e) {
						System.out.println(e);
					}
				}

				else if(menuOption.equalsIgnoreCase("Q"))
				{
					System.out.println("Exitting trip planner...");
					System.out.println("Have a nice day!");
					System.exit(0);
				}
			}
			else
				System.out.println("That is not a valid operation.");
		}
		scan.close();
	}
}
