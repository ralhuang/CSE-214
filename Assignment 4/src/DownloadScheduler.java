/**
 * 
 * @author Ralph Huang
 * 		email: ralph.huang@stonybrook.edu
 * 		Stony Brook ID: 110905260
 *
 */
public class DownloadScheduler {
	
	private DownloadQueue regularQ = new DownloadQueue(); //regular job waiting list
	private DownloadQueue premiumQ = new DownloadQueue(); //premium job waiting list
	private int currentTime ; //timestep counter
	private int simulationTime; //is the number of timesteps + 1
	private DownloadRandomizer random;
	private DownloadJob[] CurrentJobs; //array of servers
	private int downloadSpeed; //the download dSpeed
	private int numServers; //the number of servers
	private double pReg; //the probability of a regular job
	private double pPrem; //the probability of a premium job
	
	//Simulation variables
	int regJobs = 0; //number of regular jobs finished
	int premJobs = 0; //number of premium jobs finished
	int totalJobs = regJobs + premJobs; //total jobs finished
	int totalPremData = 0; //total premium data served
	int totalRegData = 0; //total regular data served
	int totalData = totalPremData + totalRegData; //total data served
	int regTime = 0; //total time serving regular jobs
	int premTime = 0; //total time serving premium jobs
	double avgRegTime = 0; //average wait time of regular jobs
	double avgPremTime = 0; //average wait time of premium jobs

	/**
	 * Constructs a <CODE>DownloadScheduler<CODE>
	 * 
	 * @param servers
	 * 		The number of servers the simulation will have
	 * @param dSpeed
	 * 		The speed of which <CODE>DownloadJob<CODE>s will download at.
	 * @param time
	 * 		The total number of timesteps that the simulation will run
	 * @param reg
	 * 		The probability of a job being regular status
	 * @param prem
	 * 		The probability of a job being premium status
	 */
	public DownloadScheduler(int servers, int dSpeed, int time, double reg, double prem)
	{
		CurrentJobs = new DownloadJob[servers];
		numServers = servers;
		downloadSpeed = dSpeed;
		simulationTime = time;
		pReg = reg;
		pPrem = prem;
		random = new DownloadRandomizer(pPrem, pReg);
	}

	/**
	 * Returns a string with simulation
	 * @return
	 * 		Returns a string representing the simulation.
	 * 
	 */
	public String simulate()
	{
		regularQ = new DownloadQueue();
		premiumQ = new DownloadQueue();
		String answer = ""; //the final answer
		String ifReg = "";  //string to be added to if job is regular
		String ifPrem = ""; //string to be added to if job is premium
		String jobFinished = "";  //string to be added to if job is finished

		answer += "\n---------------Simulation Starting---------------";
		for(currentTime = 0; currentTime <= simulationTime; currentTime++)
		{
			answer += "\nTimestep: " + currentTime;
			int fileSR = random.getRegular();
			int fileSP = random.getPremium();

			//server and queue status
			
			for(int i = 0; i < numServers; i++)
			{
				if(CurrentJobs[i] != null)
				{
					CurrentJobs[i].download(downloadSpeed);
					if(CurrentJobs[i].getDSR() <= 0)
						if(!premiumQ.isEmpty()) //if premium queue is not empty, sends that into servers first
						{
							DownloadJob temp = premiumQ.dequeue();
							jobFinished += "Job#" + CurrentJobs[i].getJob() + " finished,"
									+ " Premium job. " + CurrentJobs[i].getSize() + "Mb served,"
									+ " Total wait time: " + currentTime + "\n";
							premJobs++;
							premTime += CurrentJobs[i].getTime();
							totalPremData += CurrentJobs[i].getSize();
							CurrentJobs[i] = temp;
						}
					
						else if(!regularQ.isEmpty()) //if premium queue is empty and regular queue isn't
						{
							DownloadJob temp = regularQ.dequeue();
							jobFinished += "Job#" + CurrentJobs[i].getJob() + " finished,"
									+ " Regular job. " + CurrentJobs[i].getSize()+ "Mb served,"
									+ " Total wait time: " + currentTime + "\n";
							regJobs++;
							regTime += CurrentJobs[i].getTime();
							totalRegData += CurrentJobs[i].getSize();
							CurrentJobs[i] = temp;
						}
					
						else
						{
							if(CurrentJobs[i].isP()) //if the job is premium
							{
								jobFinished += "Job#" + CurrentJobs[i].getJob() + ", Premium job. " + CurrentJobs[i].getSize()
										+ "Mb served, Total wait time: " + currentTime + "\n";
								premJobs++;
								premTime += CurrentJobs[i].getTime();
								totalPremData += CurrentJobs[i].getSize();
							}
							
							else //the job is regular
							{
								jobFinished += "Job#" + CurrentJobs[i].getJob() + ", Regular job. " + CurrentJobs[i].getSize()
										+ "Mb served, Total wait time: " + currentTime + "\n";
								regJobs++;
								regTime += CurrentJobs[i].getTime();
								totalRegData += CurrentJobs[i].getSize();
							}
							CurrentJobs[i] = null;
						}
				}
			}

			//Regular Job status
			
			answer += "\nNew Regular Job: ";
			if(fileSR == -1)
				ifReg = "n/a";
			else
			{
				boolean checkPlaced = false;
				ifReg = "Job#" + (totalJobs+1) + ": Size: " + fileSR + "Mb";
				DownloadJob newReg = new DownloadJob(fileSR, currentTime, false, currentTime);
				for(int i = 0; i < numServers; i++)
				{
					if(CurrentJobs[i] == null)
					{
						checkPlaced = true;
						CurrentJobs[i] = newReg;
						break;
					}
				}
				if(!checkPlaced)
					regularQ.enqueue(newReg);	
				newReg.addJob(totalJobs);
				totalJobs++;
			}
			answer += ifReg;
			
			//Premium Job status
			
			answer += "\nNew Premium Job: ";
			if(fileSP == -1)
				ifPrem = "n/a";
			else
			{
				boolean checkPlaced = false;
				ifPrem = "Job#" + (totalJobs+1) + ": Size: " + fileSP + "Mb";
				DownloadJob newPrem = new DownloadJob(fileSP, currentTime, true, currentTime);
				for(int i = 0; i < numServers; i++)
				{
					if(CurrentJobs[i] == null)
					{
						checkPlaced = true;
						CurrentJobs[i] = newPrem;
						break;
					}
				}
				if(!checkPlaced)
					premiumQ.enqueue(newPrem);			
				newPrem.addJob(totalJobs);
				totalJobs++;
			}
			answer += ifPrem;

			//queue print
			
			DownloadQueue tempReg = new DownloadQueue();
			DownloadQueue tempPrem = new DownloadQueue();
			answer += "\nRegularQueue: ";
			
			tempReg.addAll(regularQ); //new regular queue with same elements as before
			tempPrem.addAll(premiumQ); //new premium queue with same elements as before
			
			int sizeReg = regularQ.size();
			int sizePrem = premiumQ.size();
			if(!regularQ.isEmpty())
			{
				for(int i = 0; i < sizeReg; i++)
				{
					answer += "[#" + tempReg.peek().getJob() + ": " + tempReg.peek().getSize() + "]  ";
					tempReg.dequeue();
				}
			}
			
			else
				answer += "empty";

			answer += "\nPremiumQueue: ";
			if(!premiumQ.isEmpty())
			{
				for(int i = 0; i < sizePrem; i++)
				{
					answer += "[#" + tempPrem.peek().getJob() + ": " + tempPrem.peek().getSize() + "]  ";
					tempPrem.dequeue();
				}
				answer+= "\n";
			}
			
			else
				answer += "empty\n";

			//Server Status
			for(int j = 0; j < numServers; j++)
			{
				if(CurrentJobs[j] != null && CurrentJobs[j].getDSR() > 0)
				{
					answer += "\n	Server " + j + ":[#" + CurrentJobs[j].getId() + ": " + CurrentJobs[j].getDSR() + "Mb total, "
							+ CurrentJobs[j].getDSR() + "Mb remaining, " + "Request Time: " + CurrentJobs[j].getTime();
					if(CurrentJobs[j].isP())
						answer += ", Premium";
					else
						answer += ", Regular";

					//Downloading

					if(CurrentJobs[j].getDSR() > 0)
					{
						CurrentJobs[j].download(downloadSpeed);
						if(CurrentJobs[j].isP())
							totalPremData += downloadSpeed;
						else
							totalRegData += downloadSpeed;
					}
					else
					{
					}
				}
				else
				{
					answer += "\n	Server " + j + ":idle";
				}
			}
			answer += "-----------------------------------------------------------------------";
		}		

		//calculates premimum averages
		if(premJobs != 0)
		{
			avgPremTime = (premTime/premJobs);
		}
		else
			avgPremTime = 0;
		
		//caluculates regular averages
		if(regJobs != 0)
		{
			avgRegTime = regTime/regJobs;
		}
		else
			avgRegTime = 0;

		//summary
		answer += "\nSimulation Ended:"
				+ "\nTotal Jobs Served: " + totalJobs 
				+ "\nTotal Premium Jobs Served: " + premJobs
				+ "\nTotal Regular Jobs Served: " + regJobs 
				+ "\nTotal Data Served: " + totalData 
				+ "\nTotal Premium Data Served: " + totalPremData 
				+ "\nTotal Regular Data Served " + totalRegData 
				+ "\nAverage Premium Wait Time: " + avgPremTime
				+ "\nAverage Regular Wait Time: " + avgRegTime
				+ "\n----------------------Thank You For Running the Simulator--------------";
		return answer;
	}
}
