
class Main {
  public static void main(String[] args) {
    TicketOrder t = new TicketOrder();
    t.add(new ComplementaryTicket());
    t.add(new AdvanceTicket(2));
    t.add(new AdvanceTicket(20));
    t.add(new StudentAdvanceTicket(2));
    t.add(new StudentAdvanceTicket(20));
    t.add(new WalkupTicket());
    System.out.println(t);
    System.out.println("total: $"+t.totalPrice());
  }
}