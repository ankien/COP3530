import java.util.Random;
public class Main {
  public static void main(String[] args) {

    System.out.println("Testing LinkedArrayQueue called LAQ");
    System.out.println("------------------------------");
    LinkedArrayQueue<Integer> laq = new LinkedArrayQueue<Integer>();
    System.out.println("LAQ size is: " + laq.size());
    System.out.println("LAQ is empty: " + laq.isEmpty());
    System.out.println("LAQ has " + laq.numArrays() + " arrays");
    System.out.println("\nAdding 10 random numbers to LAQ...");
    
    Random rand = new Random();
    rand.setSeed(123);

    for(int i=0; i<10; i++) {
        int r = rand.nextInt(1000);
        System.out.println("Adding " + r);
        laq.enqueue(r);
    }

    System.out.println("\nFirst element in LAQ: " + laq.first());
    System.out.println("Last element in LAQ: " + laq.last());
    System.out.println("LAQ size is: " + laq.size());
    System.out.println("LAQ is empty: " + laq.isEmpty());
    System.out.println("LAQ has " + laq.numArrays() + " arrays");

    System.out.println("\nRemoving 8 numbers from LAQ...");
    for(int i=0; i<8; i++) {
        System.out.println("Removed " + laq.dequeue());
    }

    System.out.println("\nFirst element in LAQ: " + laq.first());
    System.out.println("Last element in LAQ: " + laq.last());
    System.out.println("LAQ size is: " + laq.size());
    System.out.println("LAQ is empty: " + laq.isEmpty());
    System.out.println("LAQ has " + laq.numArrays() + " arrays");
  }
}