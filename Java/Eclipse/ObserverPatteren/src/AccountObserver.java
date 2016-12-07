
public class AccountObserver implements Observer {
	
	private int subCount;
	private int vidCount;
	private int viewerCount;
		
	private static int observerIDTracker = 0;	
	private int observerID;
	
	public AccountObserver(Subject accGrabber){
		
		this.observerID = ++observerIDTracker;
		System.out.println("Create Account " + this.observerID);		
		accGrabber.register(this);
	}
		
	public void update(int subCount, int vidCount, int viewerCount) {
		
		this.subCount = subCount;
		this.vidCount = vidCount;
		this.viewerCount = viewerCount;
		printTheStats();
	}
	
	public void printTheStats(){
		
		System.out.println(observerID + "\nSubcriber Count: " + subCount + "\nVideo Count: " + 
				vidCount + "\nViewer Count: " + viewerCount + "\n");
	}
}