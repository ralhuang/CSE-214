/**
 * Simulates a download server and instantiates a queue of <code>DownloadJob</code> instances.
 * 
 * @author Nicholas Chen
 * 		email: nicholas.chen@stonybrook.edu
 * 		SBUID: 110875455
 *
 */
public class DownloadScheduler {
	private DownloadQueue regularQ;
	private DownloadQueue premiumQ;
	private int currentTime; //starts at 0
	private int simulationTime; //total number of timesteps + 1
	private DownloadRandomizer random;
	private DownloadJob[] CurrentJobs; //jobs in the servers
	private int downloadSpeed;
	private int numberServers;
	private double regR; //the probability of a regular job
	private double regP; //the probability of a premium job

	/**
	 * Implements a <code>DownloadScheduler</code> instance.
	 * 
	 * @param servers
	 * 		Number of servers.
	 * 
	 * @param speed
	 * 		The download speed.
	 * 
	 * @param time
	 * 		The total number of timesteps.
	 * 
	 * @param regular
	 * 		The probability of a regular job.
	 * 
	 * @param premium
	 * 		The probability of a premium job.
	 */
	public DownloadScheduler(int servers, int speed, int time, double regular, double premium)
	{
		numberServers = servers;
		downloadSpeed = speed;
		simulationTime = time;
		regR = regular;
		regP = premium;
		CurrentJobs = new DownloadJob[servers];
	}

	/**
	 * Runs a simulation and returns it as a string.
	 * 
	 * @return
	 * 		Returns the string representing the simulation.
	 * 
	 */
	public String simulate()
	{
		random = new DownloadRandomizer(regP, regR);
		regularQ = new DownloadQueue();
		premiumQ = new DownloadQueue();
		String finalString = "";
		String regTrue = "";
		String preTrue = "";
		int jobTotal = 0; //total number of jobs 
		int regFinish = 0; //total number of finished regular jobs
		int preFinish = 0; //total number of finished premium jobs
		int totalPre = 0; //total data of finished premium jobs
		int totalReg = 0; //total data of finished regular jobs
		int regWait = 0; //total number of seconds of finished regular jobs
		int preWait = 0; //total number of seconds of finished premium jobs
		String jobDone = ""; //added to the end of each timestep indicating any finished jobs

		finalString += "\n---------------Simulation Starting---------------\n";
		for(currentTime = 0; currentTime <= simulationTime; currentTime++)
		{
			finalString += "Timestep " + currentTime + "\n";
			int sizeR = random.getRegular();
			int sizeP = random.getPremium();

			//change in servers and queues
			for(int i = 0; i < numberServers; i++)
			{
				if(CurrentJobs[i] != null)
				{
					CurrentJobs[i].changeSize(downloadSpeed);
					if(CurrentJobs[i].getDownloadRemaining() <= 0)
						if(!premiumQ.isEmpty())
						{
							DownloadJob temp = premiumQ.dequeue();
							jobDone += "Job#" + CurrentJobs[i].getJob() + ", Premium job. " + CurrentJobs[i].getSize()
									+ "Mb served, Total wait time: " + currentTime + "\n";
							preFinish++;
							preWait += CurrentJobs[i].getTime();
							totalPre += CurrentJobs[i].getSize();
							CurrentJobs[i] = temp;
						}
						else if(!regularQ.isEmpty())
						{
							DownloadJob temp = regularQ.dequeue();
							jobDone += "Job#" + CurrentJobs[i].getJob() + ", Regular job. " + CurrentJobs[i].getSize()
									+ "Mb served, Total wait time: " + currentTime + "\n";
							regFinish++;
							regWait += CurrentJobs[i].getTime();
							totalReg += CurrentJobs[i].getSize();
							CurrentJobs[i] = temp;
						}
						else
						{
							if(CurrentJobs[i].checkPremium())
							{
								jobDone += "Job#" + CurrentJobs[i].getJob() + ", Premium job. " + CurrentJobs[i].getSize()
										+ "Mb served, Total wait time: " + currentTime + "\n";
								preFinish++;
								preWait += CurrentJobs[i].getTime();
								totalPre += CurrentJobs[i].getSize();
							}
							else
							{
								jobDone += "Job#" + CurrentJobs[i].getJob() + ", Regular job. " + CurrentJobs[i].getSize()
										+ "Mb served, Total wait time: " + currentTime + "\n";
								regFinish++;
								regWait += CurrentJobs[i].getTime();
								totalReg += CurrentJobs[i].getSize();
							}
							CurrentJobs[i] = null;
						}
				}
			}



			//prints out new regular and premium jobs
			finalString += "New Premium Job: ";
			if(sizeP == -1)
				preTrue = "n/a\n";
			else
			{
				boolean checkPlaced = false;
				preTrue = "Job#" + (jobTotal+1) + ": Size: " + sizeP + "Mb\n";
				DownloadJob newPrem = new DownloadJob(sizeP, currentTime, true, currentTime);
				for(int i = 0; i < numberServers; i++)
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
				newPrem.addJob(jobTotal);
				jobTotal++;
			}
			finalString += preTrue;



			//adds to currentJob or queue
			finalString += "New Regular Job: ";
			if(sizeR == -1)
				regTrue = "n/a\n";
			else
			{
				boolean checkPlaced = false;
				regTrue = "Job#" + (jobTotal+1) + ": Size: " + sizeR + "Mb\n";
				DownloadJob newReg = new DownloadJob(sizeR, currentTime, false, currentTime);
				for(int i = 0; i < numberServers; i++)
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
				newReg.addJob(jobTotal);
				jobTotal++;
			}
			finalString += regTrue;



			//prints out queues
			finalString += "RegularQueue: ";
			DownloadQueue tempReg = new DownloadQueue();
			DownloadQueue tempPrem = new DownloadQueue();
			tempReg.addAll(regularQ); //cloning of regular queue
			tempPrem.addAll(premiumQ); //cloning of premium queue
			int size1 = regularQ.size();
			int size2 = premiumQ.size();
			if(!regularQ.isEmpty())
			{
				for(int i = 0; i < size1; i++)
				{
					finalString += "[#" + tempReg.peek().getJob() + ": " + tempReg.peek().getSize() + "]  ";
					tempReg.dequeue();
				}
			}
			else
				finalString += "empty";

			finalString += "\nPremiumQueue: ";
			if(!premiumQ.isEmpty())
			{
				for(int i = 0; i < size2; i++)
				{
					finalString += "[#" + tempPrem.peek().getJob() + ": " + tempPrem.peek().getSize() + "]  ";
					tempPrem.dequeue();
				}
				finalString+= "\n";
			}
			else
				finalString += "empty\n";



			//prints out servers
			for(int i = 0; i < numberServers; i++)
			{
				if(CurrentJobs[i] != null)
				{
					String check = "";
					if(CurrentJobs[i].checkPremium())
						check += "Premium";
					else
						check+= "Regular";
					finalString += "Server " + (i+1) + ": [#" + CurrentJobs[i].getJob() + ": " + CurrentJobs[i].getSize() + "Mb total, " + CurrentJobs[i].getDownloadRemaining() +
							"Mb remaining, Request Time: " + (CurrentJobs[i].getID()) + ", " + check + "]\n";
				}
				else
					finalString += "Server " + (i+1) + ": idle\n";
			}
			finalString += "\n" + jobDone + "\n";
			jobDone = "";
		}		

		//calculates averages
		double regA = 0; //average wait time of regular jobs
		double preA = 0; //average wait time of premium jobs
		if(preFinish != 0)
		{
			preA = (preWait/preFinish);
		}
			
		if(regFinish != 0)
		{
			regA = regWait/regFinish;
		}

		//prints final summary
		finalString += "\nSimulation Ended:\nTotal Jobs Served: " + (regFinish + preFinish) + "\nTotal Premium Jobs Served: " + preFinish
				+ "\nTotal Regular Jobs Served: " + regFinish + "\nTotal Data Served: " + (totalPre + totalReg) + "\nTotal Premium Data Served: "
				+ totalPre + "\nTotal Regular Data Served " + totalReg + "\nAverage Premium Wait Time: " + preA + " seconds" + "\nAverage Regular Wait Time: " + regA
				+ " seconds\n";	
		return finalString;
	}
}
