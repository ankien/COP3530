public class LinkedArrayQueue<E> {
  private SinglyLinkedList<E> linkedList;
  private int front;
  private int back;

  //the default constructor
  public LinkedArrayQueue() { 
	  linkedList = new SinglyLinkedList<E>(); 
  }

  //return the number of elements in the queue
  public int size() { return linkedList.eSize(); }

  //return the number of arrays currently storing elements
  public int numArrays() { 
	  if(linkedList.isEmpty()) {
		  return 0;
	  } return linkedList.size();
  }

  //test if the queue is empty
  public boolean isEmpty() { return linkedList.isEmpty(); }
  
  //return the element at the front of the queue. return null if queue is empty
  public E first() { return linkedList.getFront(front); }

  //return the element at the back of the queue. return null if queue is empty
  public E last() { return linkedList.getRear(back); }

  //push e to the back of the queue.
  public void enqueue(E e) {
	  linkedList.enqueue(back, e);
	  back++;
  }

  //pop and return the element at the front of the queue. return null if queue is empty
  public E dequeue(){
	  E temp = linkedList.dequeue(front);
	  if( ((front + 1) % 8) == 0 ) {
		  front = 0;
	  } else { front++; }
	  return temp;
  }

}