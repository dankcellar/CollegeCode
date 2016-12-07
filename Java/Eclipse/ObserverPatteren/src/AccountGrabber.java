import java.util.ArrayList;

public class AccountGrabber implements Subject{
	
	private ArrayList<Observer> observers;
	private int subCount;
	private int vidCount;
	private int viewerCount;
	
	public AccountGrabber(){
				
		observers = new ArrayList<Observer>();
	}
	
	public void register(Observer newObserver) {
				
		observers.add(newObserver);
	}

	public void unregister(Observer deleteObserver) {
				
		int observerIndex = observers.indexOf(deleteObserver);		
		System.out.println("Observer " + (observerIndex+1) + " deleted");		
		observers.remove(observerIndex);
	}
	
	public void notifyObserver() {

		for(Observer observer : observers) {
			observer.update(subCount, vidCount, viewerCount);
		}
	}
	
	public void notifyObserver(ArrayList<Integer> sleepMode) {

		for(int index = 0; index < observers.size(); index++) {
			Observer observer = observers.get(index);
					
			if (sleepMode.get(index).intValue() == 1) {
				
			} else
				observer.update(subCount, vidCount, viewerCount);
			
			index++;
		}
	}
		
	public void setsubCount(int newsubCount){
		
		this.subCount = newsubCount;
	}
	
	public void setvidCount(int newvidCount){
		
		this.vidCount = newvidCount;
	}

	public void setviewerCount(int newviewerCount){
	
		this.viewerCount = newviewerCount;
	}
	
	public Observer getObserver(int i){
		
		return observers.get(i);
	}
}