//import tester.Tester;
//import java.util.function.Predicate;
//
//class IsGreaterThanTwo implements Predicate<Integer> {
//  public boolean test(Integer val) {
//    return val > 2;
//  }
//}
//
//class IsMultipleOfTen implements Predicate<Integer> {
//  public boolean test(Integer val) {
//    return val % 10 == 0;
//  }
//}
//
//// Determines if the given ANode is the same as the ANode in this function object
//class SameNodeAsMine<T> implements Predicate<ANode<T>> {
//  ANode<T> compNode;
//
//  SameNodeAsMine(ANode<T> compNode) {
//    this.compNode = compNode;
//  }
//
//  public boolean test(ANode<T> otherNode) {
//    return otherNode.sameNode(this.compNode);
//  }
//}
//
//// Represents a list that points to its next and prev node
//class Deque<T> {
//  Sentinel<T> header;
//
//  // Constructor to initializes a new deque
//  Deque() {
//    this.header = new Sentinel<>();
//  }
//
//  // Constructor to initialize this Deque to the given Sentinel
//  Deque(Sentinel<T> header) {
//    this.header = header;
//  }
//
//  // Counts the number of nodes in this list deque
//  int size() {
//    return this.header.size();
//  }
//
//  // Inserts the given value of type T at the front of the list
//  void addAtHead(T value) {
//    this.header.addOntoHead(value);
//  }
//
//  // Inserts the given value of type T at the tail of the list
//  void addAtTail(T value) {
//    this.header.addOntoTail(value);
//  }
//
//  // Removes the first node from this Deque. Returns the value of the Node removed
//  // from the list
//  // Throws a RuntimeException if an attempt is made to remove from an empty list.
//  T removeFromHead() {
//    return this.header.removeFromHead();
//  }
//
//  // Removes the last node from this Deque. Returns the value of the Node removed
//  // from the list
//  // Throws a RuntimeException if an attempt is made to remove from an empty list.
//  T removeFromTail() {
//    return this.header.removeFromTail();
//  }
//
//  // Produces the first node in this Deque for which the given predicate returns
//  // true.
//  // If the predicate never returns true for any value in the Deque, return the
//  // header node
//  ANode<T> find(Predicate<T> pred) {
//    return this.header.find(pred);
//  }
//
//  // Removes the given node from this Deque.
//  // If the given node is the Sentinel header, the method does nothing.
//  void removeNode(ANode<T> toRemove) {
//    this.header.removeNode(toRemove);
//  }
//
//}
//
//// Represents a single node within our deque
//abstract class ANode<T> {
//  ANode<T> next;
//  ANode<T> prev;
//
//  // Constructor for an abstract node in our deque
//  ANode(ANode<T> next, ANode<T> prev) {
//    this.next = next;
//    this.prev = prev;
//  }
//
//  // Points this node's next to the given node
//  void assignNextTo(ANode<T> newNext) {
//    this.next = newNext;
//  }
//
//  // Points this node's prev to the given node
//  void assignPrevTo(ANode<T> newPrev) {
//    this.prev = newPrev;
//  }
//
//  // counts the number of nodes in a list Deque, not including the Sentinel
//  abstract int size();
//
//  // counts the number of nodes in a list Deque and stops recursion at the
//  // Sentinel
//  abstract int sizeIgnoreSent();
//
//  // Removes this ANode from the Deque and returns its value
//  abstract T removeMyself();
//
//  // Produces the first node in this Deque, after passing the initial
//  // Sentinel where the given predicate is true.
//  // If the predicate never returns true for any value in the Deque, return the
//  // header node
//  abstract ANode<T> findPastInitialSent(Predicate<T> pred);
//
//  // Removes the given node from this Deque.
//  // If the given node is the Sentinel header, the method does nothing.
//  abstract void removeNode(ANode<T> toRemove);
//
//  // Determines if the given ANode is the same as this ANode
//  abstract boolean sameNode(ANode<T> compNode);
//
//  // Determines if the given Sentinel is the same as this ANode
//  boolean sameSentinelAs(Sentinel<T> otherSent) {
//    return false;
//  }
//
//  // Determines if the given Node is the same as this ANode
//  boolean sameNodeAs(Node<T> otherNode) {
//    return false;
//  }
//}
//
//// Represents the front or back of the deque
//class Sentinel<T> extends ANode<T> {
//  // Constructor for this Sentinel
//  Sentinel() {
//    super(null, null);
//    this.next = this;
//    this.prev = this;
//  }
//
//  // counts the number of nodes in a list Deque, not including the Sentinel
//  public int size() {
//    if (this.next == null) {
//      return 0;
//    }
//    return this.next.sizeIgnoreSent();
//  }
//
//  // counts the number of nodes in a list Deque, not including the Sentinel
//  // Helper for size()
//  public int sizeIgnoreSent() {
//    return 0;
//  }
//
//  // consumes a value of type T and inserts it at the front of the list
//  void addOntoHead(T value) {
//    // Node<T> toAdd = new Node<T>(this.next, this, value);
//    Node<T> toAdd = new Node<T>(value);
//    toAdd.assignNextTo(this.next);
//    toAdd.assignPrevTo(this);
//    this.next.assignPrevTo(toAdd);
//    this.assignNextTo(toAdd);
//  }
//
//  // consumes a value of type T and inserts it at the tail of the list.
//  void addOntoTail(T value) {
//    Node<T> toAdd = new Node<T>(value);
//    toAdd.assignPrevTo(this.prev);
//    toAdd.assignNextTo(this);
//    this.prev.assignNextTo(toAdd);
//    this.assignPrevTo(toAdd);
//  }
//
//  // Removes the first node from this Deque. Returns the value of the Node removed
//  // from the list
//  // Throws a RuntimeException if an attempt is made to remove from an empty list.
//  T removeFromHead() {
//    if (this.size() == 0) {
//      throw new RuntimeException("Tried to remove item from an empty list");
//    }
//    return this.next.removeMyself();
//  }
//
//  // Removes the last node from this Deque. Returns the value of the Node removed
//  // from the list
//  // Throws a RuntimeException if an attempt is made to remove from an empty list.
//  T removeFromTail() {
//    if (this.size() == 0) {
//      throw new RuntimeException("Tried to remove item from an empty list");
//    }
//    return this.prev.removeMyself();
//  }
//
//  // Removes the value of this ANode from the Deque and returns it
//  public T removeMyself() {
//    throw new RuntimeException("Tried to remove from an empty list");
//  }
//
//  // Produces the first node in this Deque for which the given predicate returns
//  // true.
//  // If the predicate never returns true for any value in the Deque, return the
//  // header node
//  ANode<T> find(Predicate<T> pred) {
//    return this.next.findPastInitialSent(pred);
//  }
//
//  // Produces the first node in this Deque for which the given predicate returns
//  // true.
//  // If this method id invoked, there are no values in the Deque that return true
//  public ANode<T> findPastInitialSent(Predicate<T> pred) {
//    return this;
//  }
//
//  // Removes the given node from this Deque.
//  // If the given node is the Sentinel header, the method does nothing.
//  public void removeNode(ANode<T> toRemove) {
//
//    ANode<T> found = this.find(new SameNodeAsMine<T>(toRemove));
//
//    if (found != this) {
//      found.removeMyself();
//    }
//  }
//
//  // Determines if this Sentinel is the same as the given Node
//  public boolean sameNode(ANode<T> compNode) {
//    return compNode.sameSentinelAs(this);
//  }
//
//  // Determines if this Sentinel is the same as the given Sentinel
//  public boolean sameSentinelAs(Sentinel<T> otherSent) {
//    return this.next == otherSent.next && this.prev == otherSent.prev;
//  }
//
//}
//
//// Represents a node with data in our deque
//class Node<T> extends ANode<T> {
//  T data;
//
//  // Constructor initializes only this node's value. The next and prev are left
//  // null
//  Node(T data) {
//    super(null, null);
//    this.data = data;
//  }
//
//  // Constructor for this node of data
//  Node(T data, ANode<T> next, ANode<T> prev) {
//    super(next, prev);
//    if (this.next == null) {
//      throw new IllegalArgumentException("The next node you gave me was null");
//    }
//    else if (this.prev == null) {
//      throw new IllegalArgumentException("The prev node you gave me was null");
//    }
//    else {
//      this.prev.assignNextTo(this);
//      this.next.assignPrevTo(this);
//    }
//    this.data = data;
//  }
//
//  // counts the number of nodes in a list Deque, not including the Sentinel
//  public int size() {
//    // theoretically, users should never be directly calling this method & therefore
//    // should
//    // never have a null in either of these
//    if (this.next == null || this.prev == null) {
//      return 1; // size of itself
//    }
//    return 1 + this.next.sizeIgnoreSent();
//  }
//
//  // counts the number of nodes in a list Deque, not including the Sentinel
//  // Helper for size()
//  public int sizeIgnoreSent() {
//    return 1 + this.next.sizeIgnoreSent();
//  }
//
//  // Removes this Node from the Deque and returns the value of this Node
//  public T removeMyself() {
//    this.prev.assignNextTo(this.next);
//    this.next.assignPrevTo(this.prev);
//    return this.data;
//  }
//
//  // Produces the first node in this Deque for which the given predicate returns
//  // true.
//  // If the predicate never returns true for any value in the Deque, return the
//  // header node
//  ANode<T> findPastInitialSent(Predicate<T> pred) {
//    if (pred.test(this.data)) {
//      return this;
//    }
//    return this.next.findPastInitialSent(pred);
//  }
//
//  // Determines if this Node is the same as the given ANode
//  public boolean sameNode(ANode<T> compNode) {
//    return compNode.sameNodeAs(this);
//  }
//
//  // Determines if this Node is the same as the given Node
//  public boolean sameNodeAs(Node<T> otherNode) {
//    return this.data.equals(otherNode.data) && this.next == otherNode.next
//        && this.prev == otherNode.prev;
//  }
//
//}
//
//class ExamplesDeque {
//  Deque<String> deque1;
//  Deque<String> deque2;
//  Deque<Integer> deque3;
//
//  Sentinel<String> strSent;
//  Node<String> abc;
//  Node<String> bcd;
//  Node<String> cde;
//  Node<String> def;
//
//  Sentinel<Integer> intSent;
//  Node<Integer> one;
//  Node<Integer> two;
//  Node<Integer> three;
//  Node<Integer> four;
//  Node<Integer> five;
//
//  // initialize all the examples in this test suite
//  void init() {
//    deque1 = new Deque<>();
//
//    strSent = new Sentinel<>();
//    abc = new Node<String>("abc", strSent, strSent);
//    bcd = new Node<String>("bcd", strSent, abc);
//    cde = new Node<String>("cde", strSent, bcd);
//    def = new Node<String>("def", strSent, cde);
//    deque2 = new Deque<>(strSent);
//
//    intSent = new Sentinel<>();
//    one = new Node<Integer>(1, intSent, intSent);
//    two = new Node<Integer>(2, intSent, one);
//    three = new Node<Integer>(3, intSent, two);
//    five = new Node<Integer>(5, intSent, three);
//    // insertion
//    four = new Node<Integer>(4, five, three);
//    deque3 = new Deque<>(intSent);
//  }
//
//  // tests the functionality of initializing deques
//  void testConstruction(Tester t) {
//    this.init();
//
//    t.checkExpect(deque1.header, new Sentinel<>());
//
//    t.checkExpect(deque2.header == strSent, true);
//    t.checkExpect(strSent.next == abc, true);
//    t.checkExpect(strSent.prev == def, true);
//    t.checkExpect(bcd.next == cde, true);
//    t.checkExpect(bcd.prev == abc, true);
//
//    t.checkExpect(five.next == intSent, true);
//    t.checkExpect(four.prev == three, true);
//    t.checkExpect(three.next == four, true);
//    t.checkExpect(five.prev == four, true);
//  }
//
//  void testSize(Tester t) {
//    this.init();
//
//    t.checkExpect(deque1.size(), 0);
//    t.checkExpect(deque2.size(), 4);
//    t.checkExpect(deque3.size(), 5);
//
//    // user should never call these but technically WE can so are they needed to
//    // test?
//    t.checkExpect(strSent.size(), 4);
//    t.checkExpect(new Sentinel<>().size(), 0);
//    t.checkExpect(bcd.size(), 3);
//    t.checkExpect(new Node<Integer>(2).size(), 1); // should this be error?
//  }
//
//  void testAddToHead(Tester t) {
//    this.init();
//
//    deque1.addAtHead("Hello");
//
//    Sentinel<String> compSent = new Sentinel<>();
//    Deque<String> comp = new Deque<>(compSent);
//    Node<String> hiNode = new Node<String>("Hello", compSent, compSent);
//    t.checkExpect(deque1, comp);
//
//    deque1.addAtHead("Goodbye!");
//    Node<String> byeNode = new Node<String>("Goodbye!", hiNode, compSent);
//    t.checkExpect(deque1, comp);
//  }
//
//  void testAddToTail(Tester t) {
//    this.init();
//
//    deque1.addAtTail("End");
//
//    Sentinel<String> compSent = new Sentinel<>();
//    Deque<String> comp = new Deque<>(compSent);
//    Node<String> endNode = new Node<String>("End", compSent, compSent);
//    t.checkExpect(deque1, comp);
//
//    deque1.addAtTail("finish");
//    Node<String> finNode = new Node<String>("finish", compSent, endNode);
//    t.checkExpect(deque1, comp);
//  }
//
//  void testRemoveFromHead(Tester t) {
//    this.init();
//    t.checkExceptionType(RuntimeException.class, deque1, "removeFromHead");
//
//    t.checkExpect(deque2.removeFromHead(), "abc");
//    t.checkExpect(deque2.removeFromHead(), "bcd");
//  }
//
//  void testRemoveFromTail(Tester t) {
//    this.init();
//    t.checkExceptionType(RuntimeException.class, deque1, "removeFromTail");
//
//    t.checkExpect(deque3.removeFromTail(), 5);
//    t.checkExpect(deque3.removeFromTail(), 4);
//  }
//
//  void testFind(Tester t) {
//    this.init();
//
//    t.checkExpect(deque3.find(new IsGreaterThanTwo()), three);
//    t.checkExpect(deque3.find(new IsMultipleOfTen()), intSent);
//  }
//
//  void removeNode(Tester t) {
//
//  }
//
//}
