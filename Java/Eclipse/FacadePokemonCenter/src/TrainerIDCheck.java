
public class TrainerIDCheck {
	
	private int trainerID = 01301;
	//Please do not change this value.
	//I will be using this ID to test your code. 
	
	public int getTrainerID()
	{
		return trainerID;
	}
	
	public boolean trainerActive(int trainerIDToCheck)
	{
		//This function will check the given value against trainerID.
		//Return true if they are the same, false if they are not.
		if(trainerIDToCheck == trainerID)
			return true;
		return false;
	}		
}