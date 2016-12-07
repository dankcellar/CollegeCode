import java.util.ArrayList;
import java.util.Scanner;

public class GrabAccounts{
	
	private static Scanner in;
	static AccountGrabber accountGrabber = new AccountGrabber();
	private static AccountObserver observer = null;
	private static int counter = 0;

	public static void main(String[] args) throws Exception{
		
		ArrayList<Integer> sleepMode = new ArrayList<Integer>();
		
		System.out.println(	"Main Menu\n"
				+ 			"1. Create Account\n"
				+ 			"2. Toggle SleepMode on an account\n"
				+ 			"3. Edit YouTube account info\n"
				+ 			"4. Delete account\n"
				+ 			"5. Exit\n");
		
		in = new Scanner (System.in);	

		while (in.hasNext()) {
			
			int i = in.nextInt();
			
			if (i == 1) {
				
				observer = new AccountObserver(accountGrabber);
				sleepMode.add(counter, 0);
				counter++;
			}
			
			else if (i == 2) {
				
				System.out.println("Sleep which observer?");
				int index = in.nextInt();
				observer = (AccountObserver) accountGrabber.getObserver(index - 1);
				
				if (sleepMode.get(index - 1).intValue() == 0) {
					sleepMode.remove(index - 1);
					sleepMode.add(index - 1, 1);
					
				} else {
					sleepMode.remove(index - 1);
					sleepMode.add(index - 1, 0);
				}
				
				accountGrabber.notifyObserver(sleepMode);
			}
			
			else if (i == 3) {
				
				System.out.print("Enter Subcriber Count: ");
				int subCount = in.nextInt();

				System.out.print("Enter Video Count: ");
				int vidCount = in.nextInt();

				System.out.print("Enter Viewer Count: ");
				int viewerCount = in.nextInt();

				accountGrabber.setsubCount(subCount);
				accountGrabber.setvidCount(vidCount);
				accountGrabber.setviewerCount(viewerCount);
				
				System.out.println();
				accountGrabber.notifyObserver();
			}
			
			else if (i == 4) {
				
				System.out.println("Delete which observer?");
				int index = in.nextInt();
				observer = (AccountObserver) accountGrabber.getObserver(index - 1);
				accountGrabber.unregister(observer);
				sleepMode.remove(0);
			}
			
			else if (i == 5) {
				System.exit(0);
			} else System.out.println("Try another option... ");
			
		}		
	}
}