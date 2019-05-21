import java.util.Comparator;

public class TitleComparator implements Comparator{

	/**
	 * 
	 * @return
	 * The int to be returned for the collections sort method to use.
	 */
	public int compare(Object o1, Object o2)
	{
		Movie m1 = (Movie) o1;
		Movie m2 = (Movie) o2;
		
		return (m1.getTitle().compareTo(m2.getTitle()));
	}
}
