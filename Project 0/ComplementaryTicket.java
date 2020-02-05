
public class ComplementaryTicket extends FixedPriceTicket{
	private static int complementaryPrice = 0;

	public ComplementaryTicket() {
		super(complementaryPrice); // Each complementary ticket is free, $0.
		super.getPrice();
	}
}
