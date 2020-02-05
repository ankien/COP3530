import java.util.ArrayList;
import java.util.Random;

// The base class for all tickets, all ticket serial number and information strings are obtained here.
public abstract class Ticket {
	private int serialNum;
	private static ArrayList<Integer> serialNumbers = new ArrayList<Integer>();
	
	// Randomly generates a serial number between the range (0 - 10000), or [1 - 9999], until a new number not previously used is generated.
	public Ticket() {
		boolean duplicateSerial;
		
		do {
			duplicateSerial = false;
			Random randomNum = new Random();
			
			serialNum = 1 + randomNum.nextInt(10000);
			
			for (int i = 0; i < serialNumbers.size(); i++) {
				if(serialNum == serialNumbers.get(i)) {
					duplicateSerial = true;
				}
			}
		} while (duplicateSerial == true);
		
		serialNumbers.add(serialNum);
	}
	
	public int getSN() { return serialNum; }
	
	// Abstract getter method for non-fixed price tickets.
	public abstract int getPrice();
	
	public String toString() {
		return "SN: " + getSN() + ", $" + getPrice();
	}
}
