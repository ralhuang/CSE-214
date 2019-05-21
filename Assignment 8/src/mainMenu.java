import java.util.Scanner;
import java.util.concurrent.TimeUnit;
public class mainMenu {
	public static void main (String [] args)
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter your password.");
		int input = scan.nextInt();
		System.out.println("Bruteforcing...");
		passwordCrack pC = new passwordCrack(input);
		long startTime = System.nanoTime();
		pC.printPass();
		long difference = System.nanoTime() - startTime;
		System.out.println("It took " + difference + " nano seconds to crack your password.");
	}
	
	
}
