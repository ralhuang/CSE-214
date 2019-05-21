/**
 * @author Ralph Huang
 * email: ralph.huang@stonybrook.edu
 * Stony Brook ID: 110905260
 */

import java.util.Comparator;

public class CountComparator implements Comparator{

	/**
	 * @return
	 * Returns an int after comparing the <CODE>count</CODE> of the <CODE>Actor</CODE>s.
	 */
	public int compare(Object o1, Object o2)
	{
		Actor a1 = (Actor) o1;
		Actor a2 = (Actor) o2;
		
		if(a1.getCount() == a2.getCount())
			return 0;
		else if(a1.getCount() > a2.getCount())
			return 1;
		else
			return -1;
	}
}
