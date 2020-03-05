class Link<E> {  // Node class for singly linked list
	private E[] e = (E[]) new Object[8]; // elements for the node
	private Link<E> next;                // points to the next node
	
	// point to null, tail node
	Link(Link<E> next) {
		this.next = next;
	}
	
	E element(int index) { return e[index]; }
	E setElement(int index, E arg) { return e[index] = arg; }
	Link<E> retNext() { return next; }
	Link<E> setNext(Link<E> input) { return next = input; }
}

public class SinglyLinkedList<E> {
	private Link<E> head;
	private Link<E> tail;
	private int listSize;    // number of nodes in list
	private int elementSize; // number of elements in list
	
	// construct sll
	SinglyLinkedList() {
		clear();
	}
	
	// create new list
	public void clear() {
		head = tail = new Link<E>(null);
		listSize = 1;
	}
	
	// creates new node for enqueue method
	public boolean append() {
		tail.setNext(new Link<E>(null));
		tail = tail.retNext();
		listSize++;
		return true;
	}
	
	// remove first node for dequeue
	public Link<E> removeHead() {
		Link<E> temp = head;
		head = head.retNext();
		listSize--;
		return temp;
	}
	
	// size of linked list
	public int size() { return listSize; }
	
	// number of elements in list
	public int eSize() { return elementSize; }
	
	// enqueue element
	public void enqueue(int rear, E it) {
		// if the rear element is the last element in the node
		if( ((rear + 1) % 8) == 0 ) { 
			tail.setElement(7, it);
			append();
			elementSize++;
		} else { 
			tail.setElement(rear % 8, it);
			elementSize++;
		}
	}
	
	// dequeue element
	public E dequeue(int front) {
		// if the front element is the last element in the node
		if( ((front + 1) % 8) == 0 ) {
			E temp = head.element(7);
			removeHead();
			elementSize--;
			return temp;
		} else { 
			E temp = head.element(front % 8);
			head.setElement(front % 8, null);
			elementSize--;
			return temp;
		}
	}
	
	// checks if queue is empty
	public boolean isEmpty() {
		return elementSize == 0;
	}
	
	// element getters
	public E getFront(int front) {
		if(elementSize == 0) {
			return null;
		} return head.element(front);
	}
	public E getRear (int rear)  {
		if(elementSize == 0) {
			return null;
		} return tail.element((rear - 1) % 8);
	}
}
