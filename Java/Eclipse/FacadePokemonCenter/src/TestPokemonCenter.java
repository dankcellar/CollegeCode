import java.util.*;

public class TestPokemonCenter 
{
	private static Scanner in;

	public static void main(String[] args) throws Exception
	{	
		TrainerIDCheck IDCheck = new TrainerIDCheck();
		TrainerNameCheck nameCheck = new TrainerNameCheck();	
		int id = IDCheck.getTrainerID();
		String name = nameCheck.getTrainerName();											
		FacadePokemonCenter center = new FacadePokemonCenter(id, name);
		String heal = "h";
		String with = "w";
		String desp = "d";
		
		center.printTeamAndHealth();
		System.out.println("\nWould you like to heal, desposit, or withdraw pokemon?");
		System.out.println("(h, d, or w)");	
		in = new Scanner (System.in);		   

		while (in.hasNext())
		{
			String typeReq = in.next();
			if (typeReq.equals(heal))
			{
				center.hnpc.healPokemon();
				center.printTeamAndHealth();
				System.out.println("\nWould you like to heal, desposit, or withdraw pokemon?");
				System.out.println("(h, d, or w)");				
			} 
			else if (typeReq.equals(with))
			{
				System.out.println("Enter #");
				in = new Scanner (System.in);	
				int i = in.nextInt();
				center.withdrawPokemon(i);
				center.printTeamAndHealth();
				System.out.println("\nWould you like to heal, desposit, or withdraw pokemon?");
				System.out.println("(h, d, or w)");	
			}
			else if (typeReq.equals(desp))
			{
				System.out.println("Enter #");
				in = new Scanner (System.in);	
				int i = in.nextInt();
				center.depositPokemon(i);
				center.printTeamAndHealth();
				System.out.println("\nWould you like to heal, desposit, or withdraw pokemon?");
				System.out.println("(h, d, or w)");	
			}
			else break;			
		}		
	}
	
	static String GetNameAndPID()
	{
		return( "Downey, Eric, e3118280");
		//Please replace Last, First, and PID with your relevant details.
		//This function will be called in main.
	}
}
