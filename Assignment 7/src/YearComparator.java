import java.util.Comparator;

public class YearComparator implements Comparator {

	/**
	 * @return
	 * Returns an int depending on the <CODE>year</CODE> of the <CODE>movie</CODE>s compared.
	 */
	public int compare(Object o1, Object o2)
	{
		Movie m1 = (Movie) o1;
		Movie m2 = (Movie) o2;
		if (m1.getYear() == m2.getYear())
			return 0;
		else if (m1.getYear() > m2.getYear())
			return 1;
		else
			return -1;
	}
}
