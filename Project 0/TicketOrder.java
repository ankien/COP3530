import java.util.ArrayList;

/*  Contains the list of tickets.
 *  Also holds the add, toString (print format), and totalPrice implementation.
 */ 

public class TicketOrder {
	private int sumOfTickets;
	private ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
	
	// Adds tickets to an ArrayList, catches errors and runtime exceptions.
	public boolean add(Ticket t) {
		try {
			ticketList.add(t);
		} catch(Error e) {
			return false;
		} catch(RuntimeException e) {
			return false;
		}
		return true;
	}
	
	// Formats the list of tickets to print out.
	public String toString() {
		String listOfPrices = "";
		for(Ticket t : ticketList) {
			listOfPrices += t.toString() + "\n";
		}
		return listOfPrices;
	}

	// Calculates and returns the total price.
	public int totalPrice() {
		for(int i = 0; i < ticketList.size(); i++) {
			sumOfTickets += ticketList.get(i).getPrice();
		}
		return sumOfTickets;
	}
}
