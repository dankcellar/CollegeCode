
public class Factory {
	
	TypeOf thing = null;
	int totalWheels = 0;
	int totalEngines = 0;
	int totalCars = 0;
	int totalAirplanes = 0;
	int totalBoats = 0;
	
	public TypeOf makeThing(String typeReq) {
			      		
		if (typeReq.equals("A")) {						            
			thing = new Airplane();
			totalWheels += thing.getWheels();
			totalEngines += thing.getEngines();
			totalAirplanes++;
			return thing;		 
			
		} else if (typeReq.equals("B")) {			             
			thing = new Boat();
			totalWheels += thing.getWheels();
			totalEngines += thing.getEngines();
			totalBoats++;
			return thing;
			
		} else if (typeReq.equals("C")) {
			thing = new Car();
			totalWheels += thing.getWheels();
			totalEngines += thing.getEngines();
			totalCars++;
			return thing;	
			
		} else 
			return null;
	}
}
