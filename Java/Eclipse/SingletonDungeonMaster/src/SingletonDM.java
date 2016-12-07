import java.util.*;

public class SingletonDM 
{
	//Here, put the specific private static variable that makes this class follow the singleton pattern.	
	private static SingletonDM firstInstance = null;
	static boolean firstThread = true;
			
	//The array of randomized character sheets.
	//Feel free to hardcode a few of these for your testing.
	private PlayerCharacter sheets[] = 
	{
		new PlayerCharacter(1,1), new PlayerCharacter(), new PlayerCharacter(), 
		new PlayerCharacter(1,0), new PlayerCharacter(), new PlayerCharacter(), 
		new PlayerCharacter(), new PlayerCharacter(), new PlayerCharacter(),
		new PlayerCharacter(), new PlayerCharacter(), new PlayerCharacter(), 
		new PlayerCharacter(), new PlayerCharacter(), new PlayerCharacter(), 
		new PlayerCharacter(), new PlayerCharacter(), new PlayerCharacter(), 
		new PlayerCharacter(), new PlayerCharacter(), new PlayerCharacter()
	};
	
	//The sheets array remade as a list for convenience.
	private LinkedList<PlayerCharacter> sheetList = new LinkedList<PlayerCharacter>(Arrays.asList(sheets));	
	
	//Here, put the specific kind of constructor that makes this class follow the singleton pattern.
	private  SingletonDM() {}

	public static SingletonDM getInstance()
	{
		//This will act as the "true" constructor for this class.
		//Its details should include but not be limited to the following:
		//		- Check if this is the first thread.
		//		- Check the private static variable at the top of the class.
		//		- Have the "synchronized" key word in there somewhere.
		//		- Return some kind of SingletonDM
		
		if (firstInstance == null) {
			if (firstThread) {
				firstThread = false;
				try {
					Thread.currentThread();
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			synchronized (SingletonDM.class) {
				if (firstInstance ==  null) {
					firstInstance = new SingletonDM();
				}
			}
		}
		
		return firstInstance;
	}
	
	public String GetNameAndPID()
	{
		return( "Downey, Eric, e3118280");
		//Please replace Last, First, and PID with your relevant details.
		//This function will be called in main.
	}
	
	public LinkedList<PlayerCharacter> getSheetList()
	{
		return firstInstance.sheetList;
	}
	
	public LinkedList<PlayerCharacter> getSheetsOfLevel(int level)
	{
		//This should find all characters of a certain level in the list, and return them in a separate list.
		//Note: do not remove these characters from the list itself!
		//Just find them and put them in their own list, then return that list.
		
		LinkedList<PlayerCharacter> pcLevel = new LinkedList<PlayerCharacter>();
		PlayerCharacter pc = new PlayerCharacter();
		
		for (int i = 0; i < sheetList.size(); i++) {
			pc = sheetList.get(i);
			if (pc.getLevel() == level) {
				pcLevel.add(pc);
			}
		}
		
		return pcLevel;		
	}
	
	public LinkedList<PlayerCharacter> getSheetsOfType(String type)
	{
		//This should find all characters of a certain type in the list, and return them in a separate list.
		//Note: do not remove these characters from the list itself!
		//Just find them and put them in their own list, then return that list.
		
		LinkedList<PlayerCharacter> pcType = new LinkedList<PlayerCharacter>();
		PlayerCharacter pc = new PlayerCharacter();
		
		for (int i = 0; i < sheetList.size(); i++) {
			pc = sheetList.get(i);
			String pcCheck = pc.gettype();
			if (pcCheck.equals(type)) {
				pcType.add(pc);
			}
		}
		
		return pcType;	
	}
}
