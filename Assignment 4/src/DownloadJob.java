/**
 * @author Ralph Huang
 * 		email: ralph.huang@stonybrook.edu
 * 		Stony Brook ID: 110905260
 */

public class DownloadJob implements Comparable<DownloadJob>{
	
	private int downloadSize;
	private int downloadSizeRemaining;
	private int timeRequested;
	private boolean isPremium;
	private int id = 1;
	private int jobNumber = 1;
	
	/**
	 * <CODE>DownloadJob</CODE> constructor
	 * @param ds
	 * 		The download size of the job
	 * @param tr
	 * 		The timestep when the job was requested
	 * @param isP
	 * 		True if premium, false if regular
	 * @param ID
	 * 		The job number of the job.
	 * 
	 */
	public DownloadJob(int ds, int tr, boolean isP, int ID)
	{
		downloadSize = ds;
		downloadSizeRemaining = ds;
		timeRequested = tr;
		isPremium = isP;
		id = ID;
	}
	
	/**
	 * <CODE>downloadSize</CODE> accessor method
	 * @return
	 * 		Returns <CODE>downloadSize<CODE>.
	 * 
	 */
	public int getSize()
	{
		return downloadSize;
	}
	
	/**
	 * <CODE>downloadSizeRemaining</CODE> accessor method.
	 * @return
	 * 		Returns the variable downloadSizeRemaining.
	 * 
	 */
	public int getDSR()
	{
		return downloadSizeRemaining;
	}
	
	/**
	 * <CODE>timeRequested</CODE> accessor method
	 * @return
	 * The return value is the <CODE>timeRequested</CODE> of the respective <CODE>DownloadJob</CODE>.
	 */
	public int getTime()
	{
		return timeRequested;
	}
	
	/**
	 * Indicates whether or not the <CODE>DownloadJob</CODE> is premium or regular.
	 * @return
	 * Returns true if the <CODE>DownloadJob</CODE> is premium, or false if it is regular.
	 */
	public boolean isP()
	{
		return isPremium;
	}
	
	/**
	 * <CODE>id<CODE> accessor method
	 * @return
	 * Returns the variable id.
	 */
	public int getId()
	{
		return id;
	}
	
	/**
	 * Decreases the <CODE>downloadSizeRemaining<CODE> by the parameter, and returns the new size.
	 * @param subtractSize
	 * 		The download speed to be subtracted from <CODE>downloadSizeRemaining<CODE>.
	 * @return
	 * 		Returns the <CODE>downloadSizeRemaining</CODE>.
	 */
	public int download(int subtractSize)
	{
		downloadSizeRemaining -= subtractSize;
		return getDSR();
	}
	
	/**
	 * Increments the <CODE>jobID</CODE> by 1
	 * <dt>Postcondition:
	 * 	<dd>The variable id has been incremented by 1.
	 */
	public void plusID()
	{
		id++;
	}
	
	/**
	 * Increments <CODE>jobNumber</CODE>
	 * @param n
	 * the integer to be added to the <CODE>jobNumber</CODE>
	 */
	public void addJob(int n)
	{
		jobNumber += n;
	}
	

	/**
	 * <CODE>jobNumber</CODE> accessor method
	 * @return
	 * 	The return value is the <CODE>jobNumber</CODE> field.
	 */
	public int getJob()
	{
		return jobNumber;
	}

	/**
	 * Required implemented method for <code>Comparable</code> interface.
	 * @param
	 * 		<code>DownloadJob</code> is in the queue.
	 * @return
	 * 		Returns 0 if the objects are of type <code>DownloadJob</code>
	 */
	public int compareTo(DownloadJob arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}

