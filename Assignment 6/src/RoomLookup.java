import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class RoomLookup {

	/**
	 * A method to print out all the options for the main menu.
	 */
	public static void printMenu()
	{
		System.out.println("Main Menu: "
				+ "\n\tA) Add a building"
				+ "\n\tD) Delete a building"
				+ "\n\tE) Edit a building"
				+ "\n\tF) Find a room"
				+ "\n\tS) Search for rooms"
				+ "\n\tC) List all buildings on Campus"
				+ "\n\tL) List building details"
				+ "\n\tQ) Quit"
				+ "\nPlease select an option: ");
	}

	/**
	 * Checks to see if user input options are valid for the main menu.
	 * @param s
	 * The <CODE>String</CODE> to be checked
	 * @return
	 * Returns true if the parameter matches one of the options of the main menu, false if otherwise.
	 */
	public static boolean isValidMM(String s)
	{
		if(s.equalsIgnoreCase("A") || s.equalsIgnoreCase("D") || s.equalsIgnoreCase("E") 
				|| s.equalsIgnoreCase("F") || s.equalsIgnoreCase("S") || s.equalsIgnoreCase("C") 
				|| s.equalsIgnoreCase("L") || s.equalsIgnoreCase("Q"))
		{
			return true;
		}
		else return false;
	}

	/**
	 * Checks to see if there is a saved file to load. Default is false.
	 */
	public static boolean savedFile = false;

	/**
	 * Main method for the user to interact with.
	 */
	public static void main(String [] args)
	{
		boolean isRunning = true;
		Scanner scan = new Scanner(System.in);
		Campus userCampus = new Campus();
		try{
			System.out.println("Would you like to load a previously saved file? (y/n)");
			String yn = scan.nextLine();

			if(yn.equalsIgnoreCase("y"))
			{
				FileInputStream fileIn = new FileInputStream("campus.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				userCampus = (Campus) in.readObject();
				in.close();
				fileIn.close();
				savedFile = true;
			}
			if(savedFile)
			{
				System.out.println("Saved file loaded...");
				throw new ClassNotFoundException("Campus loaded.");
			}
			else
			{
				savedFile = false;
				throw new ClassNotFoundException("Campus not loaded, or not found.");
			}
		}
		catch(IOException i)
		{
			System.out.println(i);
		}
		catch(ClassNotFoundException c)
		{
			while(isRunning)
			{
				System.out.println("Welcome to SBGetARoom, Stony Brook's premium room lookup system.");

				printMenu();
				String x = scan.nextLine();
				if(isValidMM(x))
				{
					if (x.equalsIgnoreCase("A"))
					{
						System.out.println("Please enter building name: ");
						String addB = scan.nextLine();
						Building temp = new Building();
						userCampus.addBuilding(addB, temp);
						System.out.println(addB + " added.");
					}

					else if(x.equalsIgnoreCase("D"))
					{
						System.out.println("Please enter a building name: ");
						String removeB = scan.nextLine();
						userCampus.removeBuilding(removeB);
					}

					else if(x.equalsIgnoreCase("E"))
					{
						System.out.println("Please enter a building name: ");
						String name = scan.nextLine();
						if(userCampus.getBuilding(name) != null)
						{
							System.out.println("Building " + name + " selected. "
									+ "\nOptions: "
									+ "\n\tA) Add room"
									+ "\n\tD) Delete room"
									+ "\n\tE) Edit room"
									+ "\nPlease select an option: ");
							String editOption = scan.nextLine();
							if(editOption.equalsIgnoreCase("A"))
							{
								System.out.println("Please enter room number: ");
								int rm = scan.nextInt();
								scan.nextLine();
								System.out.println("Please enter number of seats: ");
								int ns = scan.nextInt();
								scan.nextLine();
								System.out.println("Please enter AV Equipment(if any), separated by commas: ");
								String avEquip = scan.nextLine();
								System.out.println("Does it have a whiteboard? (Y/n)");
								String boolWB = scan.nextLine();
								System.out.println("Does it have a chalkboard? (Y/n)");
								String boolCB = scan.nextLine();

								Classroom temp = new Classroom();

								temp.setSeats(ns);

								temp.setAVEquip(avEquip);

								if(boolWB.equalsIgnoreCase("y"))
								{
									temp.setWB(true);
								}

								if(boolCB.equalsIgnoreCase("y"))
								{
									temp.setCB(true);
								}

								userCampus.getBuilding(name).addClassroom(rm, temp);
							}
							else if(editOption.equalsIgnoreCase("D"))
							{
								System.out.println("Please enter room number: ");
								int rm = scan.nextInt();
								scan.nextLine();
								userCampus.getBuilding(name).removeClassroom(rm);
							}
							else if(editOption.equalsIgnoreCase("E"))
							{
								System.out.println("Please enter room number: ");
								int rm = scan.nextInt();
								if(userCampus.getBuilding(name).getClassroom(rm) != null)
								{
									scan.nextLine();
									System.out.println("Old number of seats: " + userCampus.getBuilding(name).getClassroom(rm).getSeats());
									System.out.println("Please enter number of seats, or press enter to skip: ");
									String editRoom = scan.nextLine();
									if(!(editRoom.equals("")))
									{
										//int temp = Integer.parseInt(editRoom);
										userCampus.getBuilding(name).getClassroom(rm).setSeats(1);
									}
									System.out.println("Old AV Equipment: " + userCampus.getBuilding(name).getClassroom(rm).AVEquipString());
									System.out.println("Please enter new AV Equipment (separated by commas), or press enter to skip: ");
									String editAVEquip = scan.nextLine();
									if(!(editAVEquip.equals("")))
									{
										userCampus.getBuilding(name).getClassroom(rm).setAVEquip(editAVEquip);
										System.out.println("AV Equipment updated.");
									}

									System.out.println("Does it have a whiteboard? (Y/n)");
									String boolWB = scan.nextLine();
									if(boolWB.equalsIgnoreCase("y") || boolWB.equalsIgnoreCase("n"))
									{
										if(boolWB.equalsIgnoreCase("y"))
											userCampus.getBuilding(name).getClassroom(rm).setWB(true);
										else
											userCampus.getBuilding(name).getClassroom(rm).setWB(false);
									}
									else if (!(boolWB.equals("")))
										System.out.println("That is not a valid option. Remains unchanged.");

									System.out.println("Does it have a chalkboard? (Y/n)");
									String boolCB = scan.nextLine();

									if(boolCB.equalsIgnoreCase("y") || boolCB.equalsIgnoreCase("n"))
									{
										if(boolCB.equalsIgnoreCase("y"))
											userCampus.getBuilding(name).getClassroom(rm).setCB(true);
										else
											userCampus.getBuilding(name).getClassroom(rm).setCB(false);
									}
									else if (!(boolCB.equals("")))
										System.out.println("That is not a valid option. Remains unchanged.");

									System.out.println(name + " " + rm + " updated");

								}
								else
									System.out.println("Room does not exist.");
							}

							else
								System.out.println("Not a valid option.");
						}
					}

					else if(x.equalsIgnoreCase("F"))
					{
						System.out.println("Please enter the room name: (Building Name [space] Room Number) ");
						String name = scan.nextLine();
						String[] arr = name.split(" ");
						int num = Integer.parseInt(arr[1]);
						Classroom answer = userCampus.getBuilding(arr[0]).getClassroom(num);

						String output = "";
						if(answer.hasWB())
							output += "\n\tHas Whiteboard";
						else
							output += "\n\tDoesn't have Whiteboard";

						if(answer.hasCB())
							output += "\n\tHas Chalkboard";
						else
							output += "\n\tDoesn't have Chalkboard";
						System.out.println("\nRoom Details: "
								+ "\n\tSeats: " + answer.getSeats()
								+ output 
								+ answer.AVEquipString());
					}

					else if(x.equalsIgnoreCase("S"))
					{
						System.out.println("\n\tOptions: "
								+ "\n\tB) Blackboard"
								+ "\n\tW) Whiteboard"
								+ "\n\tA) AV Equipment"
								+ "\nPlease select an option: ");
						String s = scan.nextLine();
						if(s.equalsIgnoreCase("B") || s.equalsIgnoreCase("W") || s.equalsIgnoreCase("A"))
						{
							if(s.equalsIgnoreCase("B"))
							{
								String answer = "";
								for(String value : userCampus.getCampus().keySet())
								{
									for(int roomValue : userCampus.getBuilding(value).getB().keySet())
									{
										if(userCampus.getBuilding(value).getClassroom(roomValue).hasCB())
											answer += ("\n" + value + ": " + roomValue);
									}
								}
								if(answer.equals(""))
									answer += "Room not found.";
								System.out.println(answer);
							}

							else if(s.equalsIgnoreCase("W"))
							{
								String answer = "";
								for(String value : userCampus.getCampus().keySet())
								{
									for(int roomValue : userCampus.getBuilding(value).getB().keySet())
									{
										if(userCampus.getBuilding(value).getClassroom(roomValue).hasWB())
											answer += ("\n" + value + ": " + roomValue);
									}
								}
								if(answer.equals(""))
									answer += "Room not found.";
								System.out.println(answer);
							}

							else if(s.equalsIgnoreCase("A"))
							{
								System.out.println("Please enter a keyword: ");
								String keyword = scan.nextLine();
								String answer = "";
								for(String value : userCampus.getCampus().keySet())
								{
									for(int roomValue : userCampus.getBuilding(value).getB().keySet())
									{
										String[] temp = userCampus.getBuilding(value).getClassroom(roomValue).getAVEquip();
										for(int i = 0; i < temp.length; i++)
										{
											if(temp[i].equals(keyword))
												answer += ("\n" + value + ": " + roomValue);
										}
									}
								}
								if(answer.equals(""))
									answer += "Room not found.";

								System.out.println(answer);
							}
						}
						else 
							System.out.println("That is not a valid option.");
					}

					else if(x.equalsIgnoreCase("C"))
					{
						String answer = "";
						for(String value : userCampus.getCampus().keySet())
						{
							for(int roomValue : userCampus.getBuilding(value).getB().keySet())
							{
								answer += ("\n" + value + ": " + roomValue);
							}
						}
						System.out.println(answer);

					}

					else if(x.equalsIgnoreCase("L"))
					{
						System.out.println("Please enter a building name: ");
						String bName = scan.nextLine();
						System.out.println("Details: ");
						String rooms = "\n\tRooms: ";
						String totalSeats = "\n\tTotal seats: ";
						String haveWBs = "\n\t";
						String haveCBs = "\n\t";
						String equip = "\n\tAV Equipment present: ";
						String[] avEquip;
						ArrayList<String> e = new ArrayList<String>();

						int rooms2 = 0;
						int totSeats = 0;
						int haveWhiteBoard = 0;
						int haveChalkBoard = 0;
						for(int value: userCampus.getBuilding(bName).getB().keySet())
						{
							rooms += (value + "  ");
							rooms2++;
							totSeats += userCampus.getBuilding(bName).getClassroom(value).getSeats();
							if(userCampus.getBuilding(bName).getClassroom(value).hasWB())
								haveWhiteBoard++;
							if(userCampus.getBuilding(bName).getClassroom(value).hasCB())
								haveChalkBoard++;

							avEquip = userCampus.getBuilding(bName).getClassroom(value).getAVEquip();
							for(int p = 0; p < avEquip.length; p++)
							{
								if(!e.contains(avEquip[p]))
									e.add(avEquip[p]);
							}
						}

						totalSeats += totSeats;

						String[] newArr = new String[e.size()];
						newArr = e.toArray(newArr);

						for(int i = 0; i < newArr.length; i++)
						{
							equip += newArr[i];
							if(i != (newArr.length - 1))
								equip += ", ";
						}

						double wbPercent = haveWhiteBoard/rooms2 * 100;
						double cbPercent = haveChalkBoard/rooms2 * 100;

						haveWBs += (wbPercent + "% of rooms have whiteboards");
						haveCBs += (cbPercent + "% of rooms have chalkboards");

						System.out.println(rooms + totalSeats + haveWBs + haveCBs + equip);
					}

					else if(x.equalsIgnoreCase("Q"))
					{
						System.out.println("Please select an option: "
								+ "\n\tS) Save"
								+ "\n\tD) Exit without saving");
						String quitOption = scan.nextLine();
						if(quitOption.equalsIgnoreCase("s"))
						{
							try{
								FileOutputStream fileOut = new FileOutputStream("campus.ser");
								ObjectOutputStream out = new ObjectOutputStream(fileOut);
								out.writeObject(userCampus);
								out.close();
								fileOut.close();
								System.out.println("Serialized data is now saved in campus.ser"
										+ "\nHave a nice day!");
								System.exit(0);
							}
							catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								System.out.println("IOException.");
							}

						}
						else if(quitOption.equalsIgnoreCase("d"))
						{
							System.out.println("Closing..."
									+ "\nHave a nice day!");
							scan.close();
							System.exit(0);
						}
					}
				}
				else
					System.out.println("Not a valid option.");
			}
		}
	}
}
