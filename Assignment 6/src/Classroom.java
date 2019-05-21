import java.io.Serializable;

public class Classroom implements Serializable {

	/**
	 * This field is true if the classroom has a whiteboard, false otherwise.
	 */
	private boolean hasWhiteboard = false;
	
	/**
	 * This field is true if the classroom has a chalkboard, false otherwise.
	 */
	private boolean hasChalkboard = false;
	
	/**
	 * This field holds the number of seats the classroom has.
	 */
	private int numSeats;
	
	/**
	 * This field holds the names of the AV Equipment that are supported in the room.
	 */
	private String[] AVEquipmentList;
	
	/**
	 * <CODE>hasWhiteboard</CODE> accessor method
	 * @return
	 * The return value is the <CODE>hasWhiteboard</CODE> field.
	 */
	public boolean hasWB()
	{
		return hasWhiteboard;
	}
	
	/**
	 * <CODE>hasChalkboard</CODE> accessor method
	 * @return
	 * The return value is the <CODE>hasChalkboard</CODE> field.
	 */
	public boolean hasCB()
	{
		return hasChalkboard;
	}
	
	/**
	 * <CODE>numSeats</CODE> accessor method
	 * @return
	 * The return value is the <CODE>numSeats</CODE> field.
	 */
	public int getSeats()
	{
		return numSeats;
	}
	
	/**
	 * <CODE>AVEquipmentList</CODE> accessor method
	 * @return
	 * The return value is the <CODE>AVEquipmentList</CODE> field.
	 */
	public String[] getAVEquip()
	{
		return AVEquipmentList;
	}
	
	/**
	 * <CODE>hasWhiteboard</CODE> mutator method.
	 * @param x
	 * The boolean to replace the <CODE>hasWhiteboard</CODE> field.
	 */
	public void setWB(boolean x)
	{
		hasWhiteboard = x;
	}
	
	/**
	 * <CODE>hasChalkboard</CODE> mutator method.
	 * @param x
	 * The boolean to replace the <CODE>hasChalkboard</CODE> field.
	 */
	public void setCB(boolean x)
	{
		hasChalkboard = x;
	}
	
	/**
	 * <CODE>numSeats</CODE> mutator method.
	 * @param s
	 * The int that replaces <CODE>numSeats</CODE>.
	 */
	public void setSeats(int s)
	{
		numSeats = s;
	}
	
	/**
	 * <CODE>AVEquipmentList</CODE> mutator method.
	 * @param input
	 * A <CODE>String</CODE> that will be split into an array that will replace <CODE>AVEquipmentList</CODE>.
	 */
	public void setAVEquip(String input)
	{
		String[] x = input.split(",");
		AVEquipmentList = x;
	}
	
	/**
	 * Converts <CODE>AVEquipmentList</CODE> into a string.
	 * @return
	 * The return value is now a <CODE>String<CODE> representation of the <CODE>AVEquipmentList</CODE> array.
	 */
	public String AVEquipString()
	{
		String x = "";
		for (int i = 0; i < AVEquipmentList.length; i++)
		{
			if (AVEquipmentList[i] != null)
			{
				x += AVEquipmentList[i];
			}
			if(i != (AVEquipmentList.length - 1))
				x += ", ";
		}
		return x;
	}
}
