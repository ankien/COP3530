
// Abstract class for tickets which have a fixed price regardless of days purchased in advance.
public abstract class FixedPriceTicket extends Ticket{
	private int fixedPriceInfo;
	
	public FixedPriceTicket(int price) {
		fixedPriceInfo = price;
	}
	
	// Returns the fixed price of the desired ticket type.
	public int getPrice() { return fixedPriceInfo; }

}
