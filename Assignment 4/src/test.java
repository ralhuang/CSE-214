
public class test {


/*public class DownloadScheduler {

	private DownloadQueue regularQ = new DownloadQueue();
	private DownloadQueue premiumQ = new DownloadQueue();
	private int currentTime;
	private int simulationTime;
	private DownloadRandomizer random;
	private DownloadJob[] CurrentJobs;
	private int downloadSpeed;
	private int numServers;
	private int idCounter = 1;
	private DownloadJob newJob;
	private DownloadJob newJobP;
	private boolean[] jobFinished;

	//SIMULATION VARIABLES
	int premJobs = 0;
	int regJobs = 0;
	int totalJobs = premJobs + regJobs;
	int totalPremData = 0;
	int totalRegData = 0;
	int totalData = totalPremData + totalRegData;
	int PremTime = 0;
	int RegTime = 0;

	public DownloadScheduler(int servers, int dspeed, int time, double pReg, double pPrem)
	{
		random = new DownloadRandomizer(pReg, pPrem);
		numServers = servers;
		downloadSpeed = dspeed;
		simulationTime = time;
		CurrentJobs = new DownloadJob[servers];
		jobFinished = new boolean[servers];
	}

	public void QtoArr() //
	{
		if(premiumQ.isEmpty() && !regularQ.isEmpty())
		{
			for(int i = 0; i < numServers; i++)
			{
				if(CurrentJobs[i] == null)
				{
					try {
						CurrentJobs[i] = regularQ.dequeue();
					} catch (EmptyQueueException e) {
						CurrentJobs[i] = null;
					}
				}
			}
		}
		else if(!premiumQ.isEmpty())
		{
			for(int i = 0; i < numServers; i++)
			{
				if(CurrentJobs[i] == null)
				{
					try {
						CurrentJobs[i] = premiumQ.dequeue();
					} catch (EmptyQueueException e) {
						// TODO Auto-generated catch block
						CurrentJobs[i] = null;
					}
				}
			}
		}
		else if(premiumQ.isEmpty() && regularQ.isEmpty())
		{
			return;
		}
	}

	public String simulate()
	{

		String start = "\n--------------------------Simulation Starting--------------------------";
		String answer = "";
		for(currentTime = 0; currentTime < simulationTime; currentTime++)
		{
			int fileSR = random.getRegular(); //file size regular
			int fileSP = random.getPremium(); //file size premium
			if (fileSR > 0 && fileSP < 0) //regular and no premium
			{
				//Job status
				newJob = new DownloadJob(fileSR, currentTime, false, (idCounter + currentTime));
				regularQ.enqueue(newJob);
				QtoArr();
				answer += "\nTimestep " + currentTime + ": "
						+ "\n	New Regular Job: Job#" + newJob.getId() 
						+ ": Size: " + newJob.getDSize() + "Mb"
						+ "\n	New Premium Job: n/a";

				//Queue status
				if(regularQ.isEmpty())
					answer += "\n	RegularQueue:empty";
				else
				{
					String checkRQs = "";
					for(int i = 0; i < regularQ.size(); i++)
					{
						checkRQs += "[" + regularQ.get(i).getId() + ":" + regularQ.get(i).getDSize() + "Mb]";
					}
					answer += "\n	RegularQueue:" + checkRQs;
				}

				if(premiumQ.isEmpty())
					answer += "\n	PremiumQueue:empty";
				else
				{
					String checkPQs = "";
					for(int i = 0; i < regularQ.size(); i++)
					{
						checkPQs += "[" + premiumQ.get(i).getId() + ":" + premiumQ.get(i).getDSize() + "Mb]";
					}
					answer += "\n	PremiumQueue:" + checkPQs;
				}

				//Server Status
				for(int j = 0; j < numServers; j++)
				{
					if(CurrentJobs[j] != null && CurrentJobs[j].getDSize() > 0)
					{
						answer += "\n	Server " + j + ":[#" + CurrentJobs[j].getId() + ": " + CurrentJobs[j].getDSize() + "Mb total, "
								+ CurrentJobs[j].getDSizeR() + "Mb remaining, " + "Request Time: " + CurrentJobs[j].getTimeR();
						if(CurrentJobs[j].isP())
							answer += ", Premium";
						else
							answer += ", Regular";

						//Downloading

						if(CurrentJobs[j].getDSizeR() > 0)
						{
							CurrentJobs[j].download(downloadSpeed);
							if(CurrentJobs[j].isP())
								totalPremData += downloadSpeed;
							else
								totalRegData += downloadSpeed;
						}
						else
						{
							jobFinished[j] = true;
						}
					}
					else
					{
						answer += "\n	Server " + j + ":idle";
					}
				}

				//if Job finished
				for(int x = 0; x < numServers; x++)
				{
					if(jobFinished[x])
					{
						if(CurrentJobs[x].isP())
						{
							PremTime +=  (currentTime - CurrentJobs[x].getTimeR());
							System.out.println("\nJob " + CurrentJobs[x].getId() + " finished, Premium job. " + CurrentJobs[x].getDSize() + " served, Total wait time: " + (currentTime - CurrentJobs[x].getTimeR()));
							premJobs++;
						}
						else
						{
							RegTime +=  (currentTime - CurrentJobs[x].getTimeR());
							System.out.println("\nJob " + CurrentJobs[x].getId() + " finished, Regular job. " + CurrentJobs[x].getDSize() + " served, Total wait time: " + (currentTime - CurrentJobs[x].getTimeR()));
							regJobs++;
						}
					}
				}

				answer += "-----------------------------------------------------------------------";
			}

			else if (fileSR > 0 && fileSP > 0) //regular and premium
			{
				//Job status
				newJob = new DownloadJob(fileSR, currentTime, false, (idCounter+currentTime+1));
				newJobP = new DownloadJob(fileSP, currentTime, true, (idCounter+currentTime));
				regularQ.enqueue(newJob);
				premiumQ.enqueue(newJobP);
				QtoArr();
				answer += "\nTimestep " + currentTime + ": "
						+ "\n	New Regular Job: Job#" + newJob.getId() 
						+ ": Size: " + newJob.getDSize() + "Mb"
						+ "\n	New Premium Job: Job#" + newJobP.getId();

				//Queue status
				if(regularQ.isEmpty())
					answer += "\n	RegularQueue:empty";
				else
				{
					String checkRQs = "";
					for(int i = 0; i < regularQ.size(); i++)
					{
						checkRQs += "[" + regularQ.get(i).getId() + ":" + regularQ.get(i).getDSize() + "Mb]";
					}
					answer += "\n	RegularQueue:" + checkRQs;
				}

				if(premiumQ.isEmpty())
					answer += "\n	PremiumQueue:empty";
				else
				{
					String checkPQs = "";
					for(int i = 0; i < regularQ.size(); i++)
					{
						checkPQs += "[" + premiumQ.get(i).getId() + ":" + premiumQ.get(i).getDSize() + "Mb]";
					}
					answer += "\n	PremiumQueue:" + checkPQs;
				}

				//Server Status
				for(int j = 0; j < numServers; j++)
				{
					if(CurrentJobs[j] != null && CurrentJobs[j].getDSize() > 0)
					{
						answer += "\n	Server " + j + ":[#" + CurrentJobs[j].getId() + ": " + CurrentJobs[j].getDSize() + "Mb total, "
								+ CurrentJobs[j].getDSizeR() + "Mb remaining, " + "Request Time: " + CurrentJobs[j].getTimeR();
						if(CurrentJobs[j].isP())
							answer += ", Premium";
						else
							answer += ", Regular";

						//Downloading

						if(CurrentJobs[j].getDSize() > 0)
						{
							CurrentJobs[j].download(downloadSpeed);
							if(CurrentJobs[j].isP())
								totalPremData += downloadSpeed;
							else
								totalRegData += downloadSpeed;
						}
						else
						{
							jobFinished[j] = true;
						}
					}
					else
					{
						answer += "\n	Server " + j + ":idle";
					}
				}

				//if Job finished
				for(int x = 0; x < numServers; x++)
				{
					if(CurrentJobs[x].isP())
					{
						PremTime +=  (currentTime - CurrentJobs[x].getTimeR());
						System.out.println("\nJob " + CurrentJobs[x].getId() + " finished, Premium job. " + CurrentJobs[x].getDSize() + " served, Total wait time: " + (currentTime - CurrentJobs[x].getTimeR()));
						premJobs++;
					}
					else
					{
						RegTime +=  (currentTime - CurrentJobs[x].getTimeR());
						System.out.println("\nJob " + CurrentJobs[x].getId() + " finished, Regular job. " + CurrentJobs[x].getDSize() + " served, Total wait time: " + (currentTime - CurrentJobs[x].getTimeR()));
						regJobs++;
					}
				}

				answer += "-----------------------------------------------------------------------";

			}

			else if (fileSR < 0 && fileSP > 0) //no regular, premium
			{
				//Job status
				newJobP = new DownloadJob(fileSP, currentTime, true, (idCounter+currentTime));
				premiumQ.enqueue(newJobP);
				QtoArr();

				//Queue status
				if(regularQ.isEmpty())
					answer += "\n	RegularQueue:empty";
				else
				{
					String checkRQs = "";
					for(int i = 0; i < regularQ.size(); i++)
					{
						checkRQs += "[" + regularQ.get(i).getId() + ":" + regularQ.get(i).getDSize() + "Mb]";
					}
					answer += "\n	RegularQueue:" + checkRQs;
				}

				if(premiumQ.isEmpty())
					answer += "\n	PremiumQueue:empty";
				else
				{
					String checkPQs = "";
					for(int i = 0; i < regularQ.size(); i++)
					{
						checkPQs += "[" + premiumQ.get(i).getId() + ":" + premiumQ.get(i).getDSize() + "Mb]";
					}
					answer += "\n	PremiumQueue:" + checkPQs;
				}

				//Server Status
				for(int j = 0; j < numServers; j++)
				{
					if(CurrentJobs[j] != null && CurrentJobs[j].getDSize() > 0)
					{
						answer += "\n	Server " + j + ":[#" + CurrentJobs[j].getId() + ": " + CurrentJobs[j].getDSize() + "Mb total, "
								+ CurrentJobs[j].getDSizeR() + "Mb remaining, " + "Request Time: " + CurrentJobs[j].getTimeR();
						if(CurrentJobs[j].isP())
							answer += ", Premium";
						else
							answer += ", Regular";

						//Downloading

						if(CurrentJobs[j].getDSize() > 0)
						{
							CurrentJobs[j].download(downloadSpeed);
							if(CurrentJobs[j].isP())
								totalPremData += downloadSpeed;
							else
								totalRegData += downloadSpeed;
						}
						else
						{
							jobFinished[j] = true;
						}
					}
					else
					{
						answer += "\n	Server " + j + ":idle";
					}
				}

				//if Job finished
				for(int x = 0; x < numServers; x++)
				{
					if(jobFinished[x])
					{
						if(CurrentJobs[x].isP())
						{
							PremTime +=  (currentTime - CurrentJobs[x].getTimeR());
							System.out.println("\nJob " + CurrentJobs[x].getId() + " finished, Premium job. " + CurrentJobs[x].getDSize() + " served, Total wait time: " + (currentTime - CurrentJobs[x].getTimeR()));
							premJobs++;
						}
						else
						{
							RegTime +=  (currentTime - CurrentJobs[x].getTimeR());
							System.out.println("\nJob " + CurrentJobs[x].getId() + " finished, Regular job. " + CurrentJobs[x].getDSize() + " served, Total wait time: " + (currentTime - CurrentJobs[x].getTimeR()));
							regJobs++;
						}
						CurrentJobs[x] = null;
					}
				}

				answer += "-----------------------------------------------------------------------";
			}
		}
		
		answer += "\nSimulation Ended:"
				+ "\n\tTotal Jobs served: " + totalJobs
				+ "\n\tTotal Premium Jobs Served: " + premJobs
				+ "\n\tTotal Regular Jobs Served: " + regJobs
				+ "\n\tTotal Data Served: " + totalData
				+ "\n\tTotal Premium Data Served: " + totalPremData + "Mb"
				+ "\n\tTotal Regular Data Served: " + totalRegData + "Mb"
				+ "\n\tAverage Premium Wait Time: " + avgPremTime()
				+ "\n\tAverage Regular Wait Time: " + avgRegTime()
				+ "\n----------------------Thank You For Running the Simulator--------------";
				
		return start + answer;
	}
	
	public int avgPremTime()
	{
		if(PremTime != 0)
		return (totalPremData/PremTime);
		else return 0;
	}
	
	public int avgRegTime()
	{
		if(RegTime != 0)
		return (totalRegData/RegTime);
		else return 0;
	}
}*/
}
