/**
 * 
 * @author Ralph Huang
 * 		email: ralph.huang@stonybrook.edu
 * 		Stony Brook ID: 110905260
 *
 */
import java.util.Scanner;
public class DownloadManager 
{
	/**
	 * Main method for user to interact with
	 */
	public static void main(String [] args)
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Hello and welcome to the Download Scheduler.");
		System.out.println("Please enter a number of servers: ");
		int numS = scan.nextInt();
		System.out.println("Please enter a download speed: ");
		int ds = scan.nextInt();
		System.out.println("Please enter a length of time: ");
		int t = scan.nextInt();
		System.out.println("Please enter a probability of new premium job per timestep: ");
		double pp = scan.nextDouble();
		System.out.println("Please neter a probability of new regular job per timestep: ");
		double rp = scan.nextDouble();

		DownloadScheduler userSchedule = new DownloadScheduler(numS, ds, t, pp, rp);
		try {
			System.out.println(userSchedule.simulate());
		} catch (EmptyQueueException e) {
			System.out.println(e);
		}
	}
}
