import java.io.Serializable;
import java.util.HashMap;

public class Building implements Serializable {

	/**
	 * This field is the <CODE>HashMap</CODE> to be stored within every <CODE>Building</CODE> object.
	 */
	private HashMap<Integer, Classroom> building = new HashMap<Integer, Classroom>();
	
	/**
	 * This method adds a <CODE>Classroom</CODE> into the <CODE>building</CODE> using the specified room number as the key.
	 * @param roomNumber
	 * the key for the <CODE>building</CODE> <CODE>HashMap</CODE>
	 * @param classroom
	 * the value for the <CODE>building</CODE> <CODE>HashMap</CODE>
	 * @IllegalArgumentException
	 * This exception is thrown when the <CODE>roomNumber</CODE> is negative, or when the <CODE>Classroom</CODE> with that key value already exists.
	 */
	public void addClassroom(int roomNumber, Classroom classroom)
	{		
		if(roomNumber < 0 || building.containsKey(roomNumber))
			throw new IllegalArgumentException("Room already exists, or room number is invalid.");
		building.put(roomNumber, classroom);
	}
	
	/**
	 * <CODE>Classroom</CODE> retriever method
	 * @param roomNumber
	 * the key to be used to retrieve values from the <CODE>building</CODE>
	 * @return
	 * The return value is the value of the <CODE>Classroom</CODE>, or null if the <CODE>Classroom</CODE> does not exist.
	 */
	public Classroom getClassroom(int roomNumber)
	{
		if(building.get(roomNumber) != null)
			return building.get(roomNumber);
		else
		{
			System.out.println("No room with that number");
			return null;
		}
	}
	
	/**
	 * Removes a <CODE>Classroom</CODE> from the <CODE>building</CODE> 
	 * @param roomNumber
	 * the key of the <CODE>Classroom</CODE> to be removed
	 * @IllegalArgumentException
	 * This exception is thrown when the <CODE>roomNumber</CODE> is negative, or when the <CODE>building</CODE> does not contain a room with specified key.
	 */
	public void removeClassroom(int roomNumber)
	{
		if(roomNumber < 0 || !(building.containsKey(roomNumber)))
			throw new IllegalArgumentException("Room does not exist.");
		building.remove(roomNumber);
	}
	
	/**
	 * <CODE>building</CODE> accessor method.
	 * @return
	 * The return value is the <CODE>building</CODE> field.
	 */
	public HashMap<Integer,Classroom> getB()
	{
		return building;
	}
	
	
}
