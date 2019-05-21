import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
public class DecisionTreeClassifier 
{
	/**
	 * Function to check if the user input is a valid function.
	 * @param input
	 * The user input
	 * @return
	 * Returns true if the input is an option in the main menu, false if not.
	 */
	public static boolean isValidMenu(String input)
	{
		if (input.equalsIgnoreCase("I") || input.equalsIgnoreCase("E") 
				|| input.equalsIgnoreCase("C") || input.equalsIgnoreCase("P") 
				|| input.equalsIgnoreCase("Q"))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Function to check if the user input is a valid function.
	 * @param input
	 * The user input
	 * @return
	 * Returns true if the input is an option in the sub-menu, false if not.
	 */
	public static boolean isValidEdit(String input)
	{
		if (input.equalsIgnoreCase("E") || input.equalsIgnoreCase("C") 
				|| input.equalsIgnoreCase("D") || input.equalsIgnoreCase("N") 
				|| input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("R") 
				|| input.equalsIgnoreCase("P") || input.equalsIgnoreCase("M"))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * The main method that the user will be interacting with.
	 */
	public static void main (String [] args) throws Exception
	{
		TreeNavigator userTree = new TreeNavigator();
		Scanner scan = new Scanner(System.in);
		boolean isRunning = true;
		System.out.println("Welcome to the DecisionTree Classifier!");

		while(isRunning)
		{
			System.out.println("Menu: \n\tI)Import a tree from a file"
					+ "\n\tE)Edit current tree"
					+ "\n\tC)Classify a Description"
					+ "\n\tP)Show decision path for a Description"
					+ "\n\tQ)Quit"
					+ "\nPlease select an option: ");
			String menuOption = scan.nextLine();
			if(isValidMenu(menuOption))
			{
				if(menuOption.equalsIgnoreCase("I"))
				{
					System.out.println("Please enter a filename: ");
					String userFile = scan.nextLine();

					FileReader file;
					try 
					{
						file = new FileReader(userFile);
						BufferedReader reader = new BufferedReader(file);
						
						String text = "";
						String line = reader.readLine();
						
						while (line != null)
						{
							text += line;
							line = reader.readLine();
						}
					
						TreeNavigator.buildTree(text);
						System.out.println("Tree Loaded.");
					} 
					catch (FileNotFoundException e) 
					{
						System.out.println("File not found");
					}
				}
				
				else if(menuOption.equalsIgnoreCase("E"))
				{
					boolean isEditting = true;
					userTree.resetCursor();
					while(isEditting)
					{
					System.out.println("\nPlease select an option: "
							+ "\n\tE)Edit Keywords"
							+ "\n\tC)Add Children"
							+ "\n\tD)Delete Children, and Make Leaf"
							+ "\n\tN)Cursor to No Child"
							+ "\n\tY)Cursor to Yes Child"
							+ "\n\tR)Cursor to Root"
							+ "\n\tP)Cursor to Parent"
							+ "\n\tM)Main Menu");
					
					String userSubM = scan.nextLine();
					if(isValidEdit(userSubM))
					{
						if(userSubM.equalsIgnoreCase("e"))
						{
							System.out.println("Please enter keywords for this node, separated by a comma: ");
							String userE = scan.nextLine();
							userTree.editCursor(userE);
						}
						
						else if(userSubM.equalsIgnoreCase("c"))
						{
							System.out.println("Please enter terminal text for the no leaf: ");
							String userCNo = scan.nextLine();
							TreeNode tempCursor = userTree.getCursor();
							
							TreeNode tempLeft = new TreeNode();
							tempCursor.setLeft(tempLeft);
							userTree.cursorLeft();
							userTree.editCursor(userCNo);
							userTree.setCursor(tempCursor);
							
							System.out.println("Please enter terminal text for the yes leaf: ");
							String userCYes = scan.nextLine();
							TreeNode tempRight = new TreeNode();
							tempCursor.setRight(tempRight);
							userTree.cursorRight();
							userTree.editCursor(userCYes);
							
							userTree.setCursor(tempCursor);
							System.out.println("Children are: no - " + userTree.getCursor().getLeft().toString()
									+ " and yes - " + userTree.getCursor().getRight().toString());
							
						}
						
						else if(userSubM.equalsIgnoreCase("d"))
						{
							if(userTree.getCursor().getLeft() != null && userTree.getCursor().getRight() != null)
							{
								System.out.println("Please enter a terminal text for this node: ");
								userTree.getCursor().setLeft(null);
								userTree.getCursor().setRight(null);
								
								String terminalT = scan.nextLine();
								userTree.editCursor(terminalT);
							}
							
						}
						
						else if(userSubM.equalsIgnoreCase("n"))
						{
							userTree.cursorLeft();
							System.out.println("Current node keywords: " + userTree.getCursor().toString());
						}
						
						else if(userSubM.equalsIgnoreCase("y"))
						{
							userTree.cursorRight();
							System.out.println("Current node keywords: " + userTree.getCursor().toString());
						}
						
						else if(userSubM.equalsIgnoreCase("r"))
						{
							userTree.resetCursor();
						}
						
						else if(userSubM.equalsIgnoreCase("p"))
						{
							userTree.cursorParent();
							System.out.println("Current keywords" + userTree.getCursor().toString());
						}
						
						else if(userSubM.equalsIgnoreCase("m"))
						{
							isEditting = false;
						}
					}
					else
						System.out.println("That is not a valid option.");
				}
				}
				
				else if(menuOption.equalsIgnoreCase("C"))
				{
					System.out.println("Please enter some text");
					String classifyInput = scan.nextLine();
					if(userTree.getCursor() != null)
						System.out.println("Your request is classified as: " + userTree.classify(classifyInput));
					else
						System.out.println("There is no tree.");
				}
				
				else if(menuOption.equalsIgnoreCase("P"))
				{
					System.out.println("Please enter some text");
					String newPath = scan.nextLine();
					String x = userTree.classify(newPath);
					TreeNode newNode = userTree.getAnything();
					userTree.setCursor(newNode);
					System.out.println(userTree.getPath());
				}
				
				else if(menuOption.equalsIgnoreCase("Q"))
				{
					System.out.println("Thank you for using the Decision Tree Classifier!"
							+ "\n Have a nice day!"
							+ "\n Shutting down...");
					scan.close();
					System.exit(0);
				}
			}
			else
			{
				System.out.println("That is not a valid option for the main menu.");
			}
		}
	}
}
