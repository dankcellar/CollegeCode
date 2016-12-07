
public class FacadePokemonCenter 
{
	//Declare a private int and a private String up here for the trainer's ID and name.
	
	//Also, be sure to declare variables of the following type:
	//		- TrainerIDCheck
	//		- TrainerNameCheck
	//		- HealAndPC
	//		- Welcome
	//Don't instantiate them yet - that can happen in the constructor.
	
	private int trainerID;
	private String trainerName;
	TrainerIDCheck IDCheck;
	TrainerNameCheck nameCheck;
	HealAndPC hnpc;
	Welcome welcome;

	
	public FacadePokemonCenter(int newID, String newName)
	{
		trainerID = newID;
		trainerName = newName;
		IDCheck = new TrainerIDCheck();
		nameCheck = new TrainerNameCheck();;
		hnpc = new HealAndPC();;
		welcome = new Welcome();				
	}
	
	public int getTrainerID()
	{
		return IDCheck.getTrainerID();
	}
	
	public String getTrainerName()
	{
		return nameCheck.getTrainerName();
	}
	
	public void healPokemon()
	{
		//This should have a few steps.
		//		- Check that the ID is valid with your instance of TrainerIDCheck.
		//		- Check that the name is valid with your instance of TrainerNameCheck.
		//		- Check if the team has at least one Pokemon.
		//If every case is true, call the heal function in your instance of HealAndPC.
		//		In addition, print that the healing was successful.
		//Otherwise, do nothing, and say why the healing was not done.
		//Make sure you print out a statement on whether healing was successful or not!
		//This is crucial to how we will grade your assignment
		
		if (IDCheck.trainerActive(trainerID) && nameCheck.trainerActive(trainerName))
		{
			if (hnpc.isTeamEmpty())
			{
				System.out.println("Failed - Team empty");
			}
			else				
				hnpc.healPokemon();			
		}
		else
			System.out.println("Failed - Wrong trainer credentials");					

	}
	
	public void depositPokemon(int dexNum)
	{
		//This should have a few steps.
		//		- Check that the ID is valid with your instance of TrainerIDCheck.
		//		- Check that the name is valid with your instance of TrainerNameCheck.
		//		- Check if the team has at least one Pokemon.
		//		- Check if the Pokedex number is valid (that is to say, < 722).
		//		- Check if the team contains the given Pokemon.
		//If every case is true, call the deposit function in your instance of HealAndPC.
		//		In addition, print that the deposit was successful.
		//Otherwise, do nothing, and say why the deposit was not made.
		//Make sure you print out a statement on whether the deposit was successful or not!
		//This is crucial to how we will grade your assignment
		
		if (IDCheck.trainerActive(trainerID) && nameCheck.trainerActive(trainerName))
		{
			if (hnpc.isTeamEmpty())
			{
				System.out.println("Failed - Team empty");				
			} 
			else 
				if (dexNum < 722)
				{
					if (hnpc.containsPokemon(dexNum))
					{													
						hnpc.depositPokemon(dexNum);
					}
					else
						System.out.println("Failed - Not on team");				
			}
			else 
				System.out.println("Failed - Out of range");				
		}
		else
			System.out.println("Failed - Wrong trainer credentials");					
	}
	
	public void withdrawPokemon(int dexNum)
	{
		//This should have a few steps.
		//		- Check that the ID is valid with your instance of TrainerIDCheck.
		//		- Check that the name is valid with your instance of TrainerNameCheck.
		//		- Check if the team has space for another Pokemon.
		//		- Check if the Pokedex number is valid (that is to say, < 722).
		//If every case is true, call the withdrawal function in your instance of HealAndPC.
		//		In addition, print that the withdrawal was successful.
		//Otherwise, do nothing, and say why the withdrawal was not made.
		//Make sure you print out a statement on whether withdrawing was successful or not!
		//This is crucial to how we will grade your assignment
		
		if (IDCheck.trainerActive(trainerID) && nameCheck.trainerActive(trainerName))
		{
			if (dexNum < 722)
			{								
					if (hnpc.isTeamFull())
					{
						System.out.println("Failed - Team full");				
					}
					else
						hnpc.withdrawPokemon(dexNum);		
			}
			else 
				System.out.println("Failed - Out of range");				
		}
	else
		System.out.println("Failed - Wrong trainer credentials");					
	}
	
	public void printTeamAndHealth()
	{
		//This should just call printTeamAndHealth() in your instance of HealAndPC.
		hnpc.printTeamAndHealth();
	}
}
