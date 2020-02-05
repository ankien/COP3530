
public class AdvanceTicket extends Ticket{
	private int advanceTicketPrice;
	private static int earlyTicketPrice = 30;
	private static int lateTicketPrice = 40;
	
	/*  If the ticket is ordered 10 or more days in advance, the price is $30.
	 *  Otherwise, it is $40. 
	 */
	public AdvanceTicket(int daysInAdvance) {
		if (daysInAdvance >= 10) {
			advanceTicketPrice = earlyTicketPrice;
		} else {
			advanceTicketPrice = lateTicketPrice;
		}
	}

	public int getPrice() { return advanceTicketPrice; }
	public static int getEarlyTicketPrice() { return earlyTicketPrice; }
	public static int getLateTicketPrice() { return lateTicketPrice; }
}
