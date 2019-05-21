import java.util.Comparator;

public class NameComparator implements Comparator{

	/**
	 * @return
	 * Returns an int after comparing the <CODE>name</CODE>s of the <CODE>Actors</CODE>s.
	 */
	public int compare(Object o1, Object o2)
	{
		Actor a1 = (Actor) o1;
		Actor a2 = (Actor) o2;
		
		return (a1.getName().compareTo(a2.getName()));
	}
}
