
public class StudentAdvanceTicket extends Ticket{
	private int studentAdvanceTicketPrice;
	
	// Student tickets are the same price as tickets ordered in advance, but halved.
	public StudentAdvanceTicket(int daysInAdvance) {
		if (daysInAdvance >= 10) {
			studentAdvanceTicketPrice = AdvanceTicket.getEarlyTicketPrice() / 2;
		} else {
			studentAdvanceTicketPrice = AdvanceTicket.getLateTicketPrice() / 2;
		}
	}
	
	// Appends "(student)" tag to the end of ticket information
	public String toString() {
		return super.toString() + " (student)";
	}

	public int getPrice() { return studentAdvanceTicketPrice; }
}
