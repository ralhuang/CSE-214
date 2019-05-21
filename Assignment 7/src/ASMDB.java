/**
 * @author Ralph Huang
 * email: ralph.huang@stonybrook.edu
 * Stony Brook ID: 110905260
 */

import java.util.List;
import java.util.Scanner;
import big.data.DataSource;

public class ASMDB {

	/**
	 * Prints the main menu for the user to use.
	 */
	public static void mainMenu()
	{
		System.out.println("Main Menu: "
				+ "\n\tI) Import a Movie"
				+ "\n\tD) Delete a Movie"
				+ "\n\tA) Sort Actors"
				+ "\n\tM) Sort Movies"
				+ "\n\tQ) Quit"
				+ "\nPlease select an option: ");
	}
	
	/**
	 * The main method the user interacts with.
	 */
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to the S Mile E Movie Data Base, the best way to manage your movies.");
		boolean isRunning = true;
		MovieManager userMovies = new MovieManager();
		while(isRunning)
		{
			mainMenu();
			String mmOption = scan.nextLine();

			//Import
			if(mmOption.equalsIgnoreCase("I"))
			{
				System.out.println("Please enter a movie title: ");
				String newTitle = scan.nextLine();

				//Generate URL for movie
				String prefix = "http://www.omdbapi.com/?t=";
				String postfix = "&y=&plot=short&r=xml";
				if(newTitle.length() > 0)
				{
					String newURL = (prefix + newTitle.replace(' ', '+') + postfix);
					DataSource ds = DataSource.connectXML(newURL);
					ds.load();
					Movie newMovie = new Movie(newURL);
					userMovies.getMovies().add(newMovie);
					List<Actor> newActors = newMovie.getStars();

					if(userMovies.getActors().size() == 0)
						userMovies.setActors(newActors);
					else
					{
						int x = userMovies.getActors().size();
						for(int i = 0; i < newActors.size(); i++)
						{
							boolean actorExists = false;
							int j = 0;
							while(j < x)
							{
								if(newActors.get(i).getName().equals(userMovies.getActors().get(j).getName()))
								{
									userMovies.getActors().get(j).incCount();
									actorExists = true;
								}
								j++;
							}
							
							if(actorExists == false)
							{
								newActors.get(i).incCount();
								userMovies.getActors().add(newActors.get(i));
							}
						}
					}
					System.out.println(newTitle + " added.");
				}
			}

			//Delete
			else if(mmOption.equalsIgnoreCase("D"))
			{
				System.out.println("Please enter the movie title to be deleted: ");
				String deleted = scan.nextLine();
				boolean removed = false;
				for(int i = 0; i < userMovies.getMovies().size(); i++)
				{
					if(userMovies.getMovies().get(i).getTitle().equals(deleted))
					{
						for(int j = 0; j < userMovies.getMovies().get(i).getStars().size(); j++)
						{
							for(int k = 0; k < userMovies.getActors().size(); k++)
							{
								if(userMovies.getMovies().get(i).getStars().get(j).getName() == userMovies.getActors().get(k).getName())
								{
									if(userMovies.getActors().get(k).getCount() == 1)
									{
										userMovies.getActors().remove(k);
										k--;
									}
									else
										userMovies.getActors().get(k).decCount();
								}		
							}
						}
						userMovies.getMovies().remove(i);
						System.out.println('"' + deleted + '"' + " deleted.");
						removed = true;
					}
				}
				if (removed == false)
					System.out.println("Failed to remove.");
			}

			//Sort Movies
			else if(mmOption.equalsIgnoreCase("M"))
			{
				System.out.println("Movie sorting options: "
						+ "\n\tTA) Title Ascending (A-Z)"
						+ "\n\tTD) Title Desecending (Z-A)"
						+ "\n\tYA) Year Ascending"
						+ "\n\tYD) Year Descending"
						+ "\nPlease select an sort method: ");
				String movieSort = scan.nextLine();

				if(movieSort.equalsIgnoreCase("TA"))
				{
					TitleComparator titleAscen = new TitleComparator();
					List<Movie> sortedMovies = userMovies.getSortedMovies(titleAscen);
					System.out.printf("%-50s%-6s%-55s\n", "Title", "Year", "Actors"
							+ "\n-------------------------------------------------------------------------------------------");
					
					for(int i = 0; i < sortedMovies.size(); i++)
					{
						String actorsToS = "";
						for(int j = 0; j < sortedMovies.get(i).getStars().size(); j++)
						{
							actorsToS += (sortedMovies.get(i).getStars().get(j).getName() + ", ");
						}
						System.out.printf("%-50s%-6s%-55s\n", sortedMovies.get(i).getTitle(), sortedMovies.get(i).getYear(), actorsToS);
					}
				}

				else if(movieSort.equalsIgnoreCase("TD"))
				{
					TitleComparator titleDescen = new TitleComparator();
					List<Movie> sortedMovies = userMovies.getSortedMovies(titleDescen);
					System.out.printf("%-50s%-6s%-55s\n", "Title", "Year", "Actors"
							+ "\n-------------------------------------------------------------------------------------------");
					
					for(int i = (sortedMovies.size() - 1); i >= 0; i--)
					{
						String actorsToS = "";
						for(int j = 0; j < sortedMovies.get(i).getStars().size(); j++)
						{
							actorsToS += (sortedMovies.get(i).getStars().get(j).getName() + ", ");
						}
						System.out.printf("%-50s%-6s%-55s\n", sortedMovies.get(i).getTitle(), sortedMovies.get(i).getYear(), actorsToS);
					}
				}

				else if(movieSort.equalsIgnoreCase("YA"))
				{
					YearComparator yearAscen = new YearComparator();
					List<Movie> sortedMovies = userMovies.getSortedMovies(yearAscen);
					System.out.printf("%-50s%-6s%-55s\n", "Title", "Year", "Actors"
							+ "\n-------------------------------------------------------------------------------------------");
					
					for(int i = 0; i < sortedMovies.size(); i++)
					{
						String actorsToS = "";
						for(int j = 0; j < sortedMovies.get(i).getStars().size(); j++)
						{
							actorsToS += (sortedMovies.get(i).getStars().get(j).getName() + ", ");
						}
						System.out.printf("%-50s%-6s%-55s\n", sortedMovies.get(i).getTitle(), sortedMovies.get(i).getYear(), actorsToS);
					}
				}

				else if(movieSort.equalsIgnoreCase("YD"))
				{
					YearComparator yearDescen = new YearComparator();
					List<Movie> sortedMovies = userMovies.getSortedMovies(yearDescen);
					System.out.printf("%-50s%-6s%-55s\n", "Title", "Year", "Actors"
							+ "\n-------------------------------------------------------------------------------------------");
					
					for(int i = (sortedMovies.size() - 1); i >= 0; i--)
					{
						String actorsToS = "";
						for(int j = 0; j < sortedMovies.get(i).getStars().size(); j++)
						{
							actorsToS += (sortedMovies.get(i).getStars().get(j).getName() + ", ");
						}
						System.out.printf("%-50s%-6s%-55s\n", sortedMovies.get(i).getTitle(), sortedMovies.get(i).getYear(), actorsToS);
					}
				}
				else
					System.out.println("Invalid option.");
			}

			//Sort Actors
			else if(mmOption.equalsIgnoreCase("A"))
			{
				System.out.println("Actor Sorting Options: "
						+ "\n\tAA) Alphabetically Ascending"
						+ "\n\tAD) Alphabetically Descending"
						+ "\n\tNA) By Number of Movies They Are In (Ascending)"
						+ "\n\tND) By Number of Movies They Are In (Descending)"
						+ "\nPlease select a sort method: ");
				String actorSort = scan.nextLine();

				if(actorSort.equalsIgnoreCase("AA"))
				{
					NameComparator nameAscen = new NameComparator();
					List<Actor> sortedActors = userMovies.getSortedActors(nameAscen);
					System.out.printf("%-25s%-10s\n", "Actor", "Number of Movies"
							+ "\n------------------------------------------------------------");
					for(int i = 0; i < userMovies.getActors().size(); i++)
					{
						System.out.printf("%-25s%-10s\n", userMovies.getActors().get(i).getName(), userMovies.getActors().get(i).getCount());
					}
				}

				else if(actorSort.equalsIgnoreCase("AD"))
				{
					NameComparator nameDescen = new NameComparator();
					List<Actor> sortedActors = userMovies.getSortedActors(nameDescen);
					System.out.printf("%-25s%-10s\n", "Actor", "Number of Movies"
							+ "\n------------------------------------------------------------");
					for(int i = (userMovies.getActors().size() - 1); i >= 0 ; i--)
					{
						System.out.printf("%-25s%-10s\n", userMovies.getActors().get(i).getName(), userMovies.getActors().get(i).getCount());
					}
				}

				else if(actorSort.equalsIgnoreCase("NA"))
				{
					CountComparator countAscen = new CountComparator();
					List<Actor> sortedActors = userMovies.getSortedActors(countAscen);
					System.out.printf("%-25s%-10s\n", "Actor", "Number of Movies"
							+ "\n------------------------------------------------------------");
					for(int i = 0; i < userMovies.getActors().size(); i++)
					{
						System.out.printf("%-25s%-10s\n", userMovies.getActors().get(i).getName(), userMovies.getActors().get(i).getCount());
					}
				}

				else if(actorSort.equalsIgnoreCase("ND"))
				{
					CountComparator countDescen = new CountComparator();
					List<Actor> sortedActors = userMovies.getSortedActors(countDescen);
					System.out.printf("%-25s%-10s\n", "Actor", "Number of Movies"
							+ "\n------------------------------------------------------------");
					for(int i = (userMovies.getActors().size() - 1); i >= 0 ; i--)
					{
						System.out.printf("%-25s%-10s\n", userMovies.getActors().get(i).getName(), userMovies.getActors().get(i).getCount());
					}
				}

				else
					System.out.println("Invalid option");
			}

			//Quit
			else if(mmOption.equalsIgnoreCase("Q"))
			{
				scan.close();
				System.out.println("Closing..."
						+ "\nHave a nice day!");
				System.exit(0);
			}

			else
				System.out.println("That is not a valid option.");

		}

	}
}
