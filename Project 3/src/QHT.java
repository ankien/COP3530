public class QHT<K, V> {
  
  public class KVPair<K, V> {
    
    /*
    Generic key-value pair class
    */
  
    K k;
    V v;

    KVPair(K key, V val) {
      k = key;
      v = val;
    }

    public K key() {
      return k;
    }

    public V value() {
      return v;
    }
  }

  /*
    instance variables.
    DO NOT CHANGE, ADD OR REMOVE INSTANCE VARIABLES
  */

  KVPair[] htable;                                  //The Hash table which is an array of KVPairs
  int size;                                         //Number of elements in the hash table
  int initCap;                                      //Initial capacity of the hash table
  static final int DEFAULT_EXP = 2;                 //Default exponent if it's not specified
  final KVPair TOMBSTONE = new KVPair(null, null);  //The Tombstone to be used when deleting an element

  QHT() {
    /*
      ***TO-DO***
      Default constructor
      should initialize the hash table with default capacity
    */
    this(DEFAULT_EXP);
  }

  QHT(int exp) {
    /*
      ***TO-DO***
      Single-parameter constructor. The capacity of the hash table
      should be 2^exp. if exp < 2, use default exponent.
      initialize size and initCap accordingly
    */
    int loopTimes;
    if(exp < 2) { loopTimes = DEFAULT_EXP; }
    else { loopTimes = exp; }
    
    initCap = 1;
    for(int i = 0; i < loopTimes; i++) { initCap *= 2; } 
    htable = new KVPair[initCap];
    size = 0;
  }

  public int size() {
    /*
      ***TO-DO***
      return the number of elements currently stored in the 
      hash table. Shouldn't include TOMBSTONES
      Should run in O(1)
    */
    return size;
  }

  public int capacity() {
    /*
      ***TO-DO***
      return the capacity of the hash table
      Should run in O(1)
    */
    return htable.length;
  }

  public boolean isEmpty() {
    /*
      ***TO-DO***
      return true if hash table is empty,
      false otherwise
      Should run in O(1)
    */
    return size == 0;
  }

  public double loadFactor() {
    /*
      ***TO-DO***
      return the load factor of this hash table.
      load factor is the ratio of size to capacity
      Should run in O(1). Note that the return type is double.
    */
    return (double)size / (double)capacity();
  }

  private int h(K k) {
    /*
      The hash function. returns an integer for an arbitrary key
      Should run in O(1)
    */
    
    return (k.hashCode() + capacity()) % capacity() ;
  }

  private int p(K k, int i) {
    /*
      The probe function. returns an integer. i is 
      the number of collisions seen so far for the key
      Should run in O(1)
    */
    
    return i/2 + (i*i)/2 + (i%2);
  }
  
  private boolean isTombstone(KVPair pair) {
    if(pair != null) {
      if((pair.key() == null) && (pair.value() == null)) { return true; }
      else { return false; }
    } else { return false; }
  }

  public void insert(K k, V v) {
    /*
      ***TO-DO***
      should insert the given key and value as a 
      KVPair in the hash table.
      if load factor > 0.5, increase capacity by a factor of 2
    */
    if(size() == capacity()) throw new IllegalStateException();
    
    int home;
    int pos = home = h(k);
    if(htable[pos] != null) {
      if(k == htable[pos].key()) { return; }
    } 
    if((pos + 1) < size) {
        if(htable[pos + 1] != null) {
            if(k == htable[pos + 1].key()) { return; }
        } 
    }
    // if table position isn't empty and isn't tombstone, probe
    for(int i = 1; (htable[pos] != null) && !isTombstone(htable[pos]); i++) {
      pos = (home + p(k, i)) % capacity();
      if((htable[pos] != null) && !isTombstone(htable[pos])) {
          if(k == htable[pos].key()) { return; }
      }
    }
    
    htable[pos] = new KVPair(k, v);
    size++;
    // reinsert elements to bigger hash table
    if(loadFactor() > 0.5) {
      int tempCap = capacity();
      tempCap *= 2;
      KVPair[] tempTable = htable;
      htable = new KVPair[tempCap];
      int tempSize = size;
      size = 0;
      int elementsReinserted = 0;
      for(int i = 0; elementsReinserted != tempSize; i++) {
        if(tempTable[i] != null) { 
          if((tempTable[i].key() != null) && (tempTable[i].value() != null)) {
            insert((K)tempTable[i].key(), (V)tempTable[i].value());
            elementsReinserted++;
          }
        }
      } tempTable = null;
    }
    
  }

  public V remove(K k) {
    
    /*
      ***TO-DO***
      if k is found in the hash table, remove KVPair
      and return the value. Otherwise, return null.
      if load factor < 0.25 then reduce capacity in half.
    */
    int home;
    int pos = home = h(k);
    V keyValue = null;
    for(int i = 0; htable[pos] != null; i++) {
      pos = (home + p(k, i)) % capacity();
      if((htable[pos] != null) && !isTombstone(htable[pos])) {
        if(k == htable[pos].key()) {
          keyValue = (V)htable[pos].value();
          htable[pos] = TOMBSTONE;
          size--;
        }
      }
    }
    
    if((loadFactor() < 0.25) && ((capacity() / 2) >= initCap)) {
      int tempCap = capacity();
      tempCap /= 2;
      KVPair[] tempTable = htable;
      htable = new KVPair[tempCap];
      int tempSize = size;
      size = 0;
      int elementsReinserted = 0;
      for(int i = 0; elementsReinserted != tempSize; i++) {
        if(tempTable[i] != null) { 
          if((tempTable[i].key() != null) && (tempTable[i].value() != null)) {
            insert((K)tempTable[i].key(), (V)tempTable[i].value());
            elementsReinserted++;
          }
        }
      } tempTable = null;
    }
    
    return keyValue;
  }

  public V find(K k) {
    /*
      ***TO-DO***
      if k is found in the hash table, return the value. 
      Otherwise, return null.
    */
    int home;
    int pos = home = h(k);
    for(int i = 1; i <= capacity(); i++) {
      if((htable[pos] != null) && !isTombstone(htable[pos])) { // if not empty and not a tombstone
        if(k == (K)htable[pos].key()) { return (V)htable[pos].value(); } // if value isn't found, probe more
      }
      pos = (home + p(k, i)) % capacity();
    } return null;
  }

  public KVPair get(int i) {
    /*
      return the KVPair at index i of the hash table
    */
    
    if (i >= capacity())
      return null;
    
    return htable[i];
  }

  public String toString() {
    /*
      return a string representation of the hash table.
    */
    
    String ret = "\n\n";
  
    for (int i = 0; i < capacity(); i++) {
      if (get(i) != null) {
        if (get(i).key() != null) 
          ret += i + "\t" + get(i).key() + "\t->\t" + get(i).value() + "\n";
        else
          ret += i + "\tTOMBSTONE\n";
      }
      else {
        ret += i + "\tnull\n";
      }
    }

    return ret;
  }
}