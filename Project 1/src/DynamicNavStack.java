public class DynamicNavStack<E> {
  private E[] navStack;
  private int undoI;
  private int redoI;
  private int initialCapacity;
  private int capacity;
  private static final int DEFAULT_CAPACITY = 2;

  DynamicNavStack() { this(DEFAULT_CAPACITY); }

  @SuppressWarnings("unchecked")
  DynamicNavStack(int c) { 
	  if(c <= 0) {
		  initialCapacity = DEFAULT_CAPACITY;
	  } else {
		  initialCapacity = c;
	  }
	  capacity = initialCapacity;
	  redoI = initialCapacity - 1; // redo stack starts at navStack end
	  
	  // Create navStack
	  navStack = (E[])new Object[initialCapacity];
  }

  public int size() { // number of elements in navStack
	  return undoI + (capacity - redoI - 1);
  }

  public boolean canUndo() { 
	  if(undoI > 0) { return true; }
	  return false;
  }

  public boolean canRedo() { 
	  if((capacity - redoI - 1) > 0) { return true; }
	  return false;
  }

  public E undoTop() { 
	  if(undoI == 0) { return null; }
	  return navStack[undoI - 1];
  }

  public E redoTop() {
	  if((capacity - redoI - 1) == 0) { return null; }
	  return navStack[redoI + 1];  
  }

  public boolean isEmpty() { return size() == 0; }

  public int capacity() { return capacity; }

  public void push(E e) { 
	  // push and increment undo stack size
	  navStack[undoI++] = e;
	  
	  // empty redo stack
	  redoI = undoI;
	  for(int i = redoI; i < capacity; i++) {
		  navStack[i] = null;
	  }
	  
	  if(undoI >= capacity) { // if the size of the stack is >= capacity
		  capacity *= 2;	  // multiply current capacity by 2
		  resize(capacity);
	  } else if((capacity >= 4) &&			 // minimum capacity for reduction = 4
			    (undoI <= (capacity / 4))) { // if stack is <= 1/4 of capacity
		  capacity /= 2; 					 // reduce current capacity by 1/2
		  resize(capacity);
	  }
	  
	  redoI = capacity - 1;
  }

  public E undo() { 
	  if(canUndo()) {
		  E temp = navStack[undoI - 1];
		  navStack[undoI - 1] = null;	// set popped space to null
		  undoI--;
		  navStack[redoI--] = temp;		// push popped element to redo stack
		  return navStack[redoI + 1];
	  } return null; // if undo stack is empty, return null
  }

  public E redo() { 
	  if(canRedo()) {
		  E temp = navStack[redoI + 1];
		  navStack[redoI + 1] = null;	// set popped space to null
		  redoI++;
		  navStack[undoI++] = temp;		// push popped element to undo stack
		  return navStack[undoI - 1];
	  } return null; // if redo stack is empty, return null
  }

  private void resize(int newSize) { 
	  @SuppressWarnings("unchecked")
	  // create array with new capacity
	  E[] copyStack = (E[])new Object[newSize]; 
	  
	  // copy elements into new array
	  System.arraycopy(navStack, 0, copyStack, 0, undoI);
	  navStack = copyStack;
  }

  public String toString() {
    String ret = "Array Looks Like this: [";
    for (int i=0; i<capacity; i++)
      if (navStack[i] != null)
        ret += navStack[i].toString() + " ";
      else
        ret += "null ";
    ret += "]\n";
    ret += "undo stack: [";
    for (int i=0; i<undoI; i++)
      ret += navStack[i].toString() + " ";
    ret += "]\n";
    ret += "redo stack: [";
    for (int i=capacity-1; i>redoI; i--)
      ret += navStack[i].toString() + " ";
    ret += "]";
    return ret;
  }
}