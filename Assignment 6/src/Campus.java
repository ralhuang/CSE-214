import java.io.Serializable;
import java.util.HashMap;

public class Campus implements Serializable{

	/**
	 * This field is the <CODE>HashMap</CODE> to be stored within every <CODE>Campus</CODE> object.
	 */
	private HashMap<String, Building> campus = new HashMap<String, Building>();
	
	/**
	 * This method adds a <CODE>Building</CODE> into the <CODE>campus</CODE> using the specified building name as the key.
	 * @param buildingName
	 * the key for the <CODE>campus</CODE> <CODE>HashMap</CODE>
	 * @param building
	 * the value for the <CODE>campus</CODE> <CODE>HashMap</CODE>
	 * @IllegalArgumentException
	 * This exception is thrown when the <CODE>buildingName</CODE> is null, or when the <CODE>Building</CODE> with that key value already exists.
	 */
	public void addBuilding(String buildingName, Building building)
	{
		if(campus.isEmpty())
			campus.put(buildingName, building);
		else if(buildingName == null || campus.containsKey(buildingName))
			throw new IllegalArgumentException("Building already exists, or building name is invalid.");
		else
			campus.put(buildingName, building);
	}
	
	/**
	 * <CODE>Building</CODE> retriever method
	 * @param buildingName
	 * the key to be used to retrieve values from the <CODE>campus</CODE>
	 * @return
	 * The return value is the value of the <CODE>Building</CODE> using the specified <CODE>buildingName</CODE>, or null if the <CODE>Classroom</CODE> does not exist.
	 */
	public Building getBuilding(String buildingName)
	{
		if(campus.get(buildingName) != null)
			return campus.get(buildingName);
		else
		{
			System.out.println("No building with that name.");
			return null;
		}
	}
	
	/**
	 * Removes a <CODE>Building</CODE> from the <CODE>campus</CODE> 
	 * @param buildingName
	 * the key of the <CODE>Building</CODE> to be removed
	 * @IllegalArgumentException
	 * This exception is thrown when the <CODE>buildingName</CODE> is null, or when the <CODE>campus</CODE> does not contain a <CODE>Building</CODE> with specified key.
	 */
	public void removeBuilding(String buildingName)
	{
		if(buildingName == null || !(campus.containsKey(buildingName)))
			throw new IllegalArgumentException("Building does not exist.");
		campus.remove(buildingName);
	}
	
	/**
	 * <CODE>campus</CODE> accessor method.
	 * @return
	 * The return value is the <CODE>campus</CODE> field.
	 */
	public HashMap<String,Building> getCampus()
	{
		return campus;
	}
	
}
