import java.util.Scanner;

public class InventoryTesting extends TypeOf {
	
	private static Scanner in;

	public static void main(String[] args) {
	   
		   Factory factory = new Factory();	
		   TypeOf thing = null;
		   System.out.println("What shall I make? (B, C, or A)"); 
		   in = new Scanner (System.in);		   
				   
		   while (in.hasNext()) 
		   {
			   String typeReq = in.next();
			   thing = factory.makeThing(typeReq);

			   if(thing != null) 
			   {	
				   System.out.println("Total amount of wheels required: " + factory.totalWheels);;
				   System.out.println("Total amount of engines required: " + factory.totalEngines);;
				   System.out.println("Total airplanes made: " + factory.totalAirplanes);
				   System.out.println("Total boats made: " + factory.totalBoats);
				   System.out.println("Total cars made: " + factory.totalCars);
				   System.out.println("\nWhat shall I make? (B, C, or A)");
			   } else
				   System.out.println("Enter B, C, or A");
		   }
	   }
}
