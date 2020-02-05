
public class WalkupTicket extends FixedPriceTicket{
	private static int walkupPrice = 50;

	public WalkupTicket() {
		super(walkupPrice); // The price of each walkup ticket is $50.
		super.getPrice();
	}
}
