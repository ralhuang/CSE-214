/**
 * @author Ralph Huang
 * email: ralph.huang@stonybrook.edu
 * Stony Brook ID: 110905260
 */

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MovieManager {

	/**
	 * Field that holds all <CODE>Movie</CODE>s handled by this <CODE>MovieManager</CODE>.
	 */
	private List<Movie> movies;
	
	/**
	 * Field that holds all <CODE>Actor</CODE>s handled by this <CODE>MovieManager</CODE>.
	 */
	private List<Actor> actors;
	
	/**
	 * Default constructor with an empty, non-null <CODE>Actor</CODE>s and <CODE>Movie</CODE>s list.
	 */
	public MovieManager()
	{
		movies = new LinkedList();
		actors = new LinkedList();
	}
	
	/**
	 * <CODE>movies</CODE> accessor method.
	 * @return
	 * Returns the <CODE>movies</CODE> field.
	 */
	public List<Movie> getMovies()
	{
		return movies;
	}
	
	/**
	 * <CODE>actors</CODE> accessor method.
	 * @return
	 * Returns the <CODE>actors</CODE> field.
	 */
	public List<Actor> getActors()
	{
		return actors;
	}
	
	/**
	 * <CODE>actors</CODE> mutator method.
	 * @param newActors
	 * The new actors to be set.
	 */
	public void setActors(List<Actor> newActors)
	{
		for(int i = 0; i < newActors.size(); i++)
		{
			newActors.get(i).incCount();
			actors.add(newActors.get(i));
		}
	}
	
	/**
	 * @param comp
	 * The <CODE>Comparator</CODE> to be used to sort the list.
	 * @return
	 * Returns a sorted list of <CODE>movies</CODE>.
	 */
	public List<Movie> getSortedMovies(Comparator comp)
	{
		Collections.sort(movies, comp);
		return movies;
	}
	
	/**
	 * @param comp
	 * The <CODE>Comparator</CODE> to be used to sort the list.
	 * @return
	 * Returns a sorted list of <CODE>actors</CODE>.
	 */
	public List<Actor> getSortedActors(Comparator comp)
	{
		Collections.sort(actors, comp);
		return actors;
	}
}
