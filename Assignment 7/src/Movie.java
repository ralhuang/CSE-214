/**
 * @author Ralph Huang
 * email: ralph.huang@stonybrook.edu
 * Stony Brook ID: 110905260
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import big.data.DataSource;

public class Movie {

	/**
	 * Field tthat holds the title of the movie.
	 */
	private String title;
	
	/**
	 * Field that holds the year the movie was released.
	 */
	private int year;
	
	/**
	 * Field that holds the list of factors who were in the movie, represented as <CODE>Actors</CODE>s.
	 */
	private List<Actor> stars = new LinkedList();
	
	/**
	 * Overloaded constructor for creating a new <CODE>Movie</CODE> with given details and an empty, non-null <CODE>Actor</CODE>s list.
	 * @param title
	 * @param year
	 */
	public Movie(String title, int year)
	{
		stars = new LinkedList();
	}
	
	/**
	 * Overloaded constructor for creating a new <CODE>Movie</CODE> through the passed URL.
	 * @param url
	 * 	The URL in which the <CODE>Movie</CODE> uses to access a database and XML file.
	 */
	public Movie(String url)
	{
		DataSource ds = DataSource.connectXML(url);
		ds.load();
		
		title = ds.fetchString("movie/title");
		year = ds.fetchInt("movie/year");
		String[] actorsNames = ds.fetchString("movie/actors").split(", ");
		for(int i = 0; i < actorsNames.length; i++)
		{
			Actor temp = new Actor(actorsNames[i]);
			stars.add(temp);
		}
	}
	
	/**
	 * <CODE>title</CODE> accessor method.
	 * @return
	 * Returns the <CODE>title</CODE> of the movie.
	 */
	public String getTitle()
	{
		return title;
	}
	
	/**
	 * <CODE>year</CODE> accessor method.
	 * @return
	 * Returns the <CODE>year</CODE> the movie was released.
	 */
	public int getYear()
	{
		return year;
	}
	
	/**
	 * <CODE>stars</CODE> accessor method.
	 * @return
	 * Returns the <CODE>star</CODE>s of the movie.
	 */
	public List<Actor> getStars()
	{
		return stars;
	}
	
	/**
	 * <CODE>title</CODE> mutator method.
	 * @param newTitle
	 * The new title to be set.
	 */
	public void setTitle(String newTitle)
	{
		title = newTitle;
	}
	
	/**
	 * <CODE>year</CODE> mutator method.
	 * @param newYear
	 * The new year to be set.
	 */
	public void setYear(int newYear)
	{
		year = newYear;
	}
	
	/**
	 * <CODE>stars</CODE> mutator method.
	 * @param newStars
	 * The new stars to be set.
	 */
	public void setStars(List<Actor> newStars)
	{
		stars = newStars;
	}

}
