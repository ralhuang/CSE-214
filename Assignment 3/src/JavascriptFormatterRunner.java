/**
 * 
 * @author Ralph Huang
 * 	email: ralph.huang@stonybrook.edu
 * 	Stony Brook ID: 110905260
 *
 */
import java.util.Scanner;
import java.io.BufferedReader; //Similar to scanner
import java.io.FileReader; //Reads the file

public class JavascriptFormatterRunner {

	/**
	 * The main method, that the user will be interacting with
	 */
	public static void main(String[] args) throws Exception
	{
		boolean isRunning = true;
		JavascriptFormatter jsfm = new JavascriptFormatter();
		Scanner scan = new Scanner(System.in);
		while(isRunning)
		{
			System.out.println("Welcome to JavaScript Formatter!"
					+ "\n --------- Properly formatted program ---------");
			System.out.println("Enter the file path.");
			System.out.println("Or to quit, enter q");
			try {  //Tests for a file name, if no file found, it will prompt you to enter another without stopping the program.
				String path = scan.nextLine();
				if(path.equalsIgnoreCase("q")) //quitting option
				{
					System.out.println("Exiting... Have a nice day!");
					System.exit(0);
				}
				else
				{
					FileReader file = new FileReader(path);
					BufferedReader reader = new BufferedReader(file);

					String text = "";
					String line = reader.readLine();

					while (line != null)
					{
						text += line;
						line = reader.readLine();
					}

					System.out.println(jsfm.format(text)); //formats text
				}
			}catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("There is no file with that name.");

			}
		}
	}
}
