import tester.Tester;

import java.util.ArrayList;
import java.util.Comparator;

// TODO:
// note:
// comparing in purpose statements refer to the following behavior:
// return negative if the first is "less" than the second
// return 0 if the first and second are the "same"
// return positive if the first is "greater" than the second

// a huffman coding
class Huffman {

  // letters of the alphabet
  ArrayList<String> letters;
  // strings always size 1
  // never equal to "?"

  // relative frequencies of these letters
  ArrayList<Integer> freqs;
  // always positive

  // a forest, list of binary trees
  ArrayList<BinTree> forest;

  public Huffman(ArrayList<String> letters, ArrayList<Integer> freqs) {

    if (letters.size() != freqs.size()) {
      throw new IllegalArgumentException("invalid lists: lists are of different lengths");
    }

    if (letters.size() < 2) {
      throw new IllegalArgumentException(
          "invalid lists: length of the list of strings is less than 2");
    }

    this.letters = letters;
    this.freqs = freqs;
    this.forest = new ArrayList<BinTree>();

    // EFFECT: initializes this forest with the initial leaves
    for (int i = 0; i < letters.size(); i++) {

      // gets the letter at index i
      String letter = this.letters.get(i);
      // gets the frequency of the letter at index i
      int freq = this.freqs.get(i);

      Leaf leaf = new Leaf(letter, freq);
      // add a new leaf into this forest
      this.forest.add(leaf);
    }

    SortByFreq sortByFreq = new SortByFreq();

    // EFFECT: sorts this forest by increasing frequency using a stable sort
    // algorithm
    this.forest.sort(sortByFreq);

    // EFFECT: converts this forest into a list of a single tree using the Huffman
    // encoding algorithm
    while (this.forest.size() > 1) {

      // gets the first element of this forest
      BinTree first = this.forest.get(0);
      // gets the second element of this forest
      BinTree second = this.forest.get(1);

      // create a new node with value equal to the sum of its subtrees
      BinTree node = new Node(first.sum(second), first, second);

      // remove the first element of this forest
      this.forest.remove(0);
      // remove the first element of this forest again
      this.forest.remove(0);

      // add the new node into this forest
      this.forest.add(node);

      // EFFECT: converts this forest into a list of a single tree using the Huffman
      // encoding algorithm
      this.forest.sort(sortByFreq);

    }
  }

  // produces a list of booleans where 0 is represented by false and 1 by true
  // given a string
  ArrayList<Boolean> encode(String str) {

    ArrayList<Boolean> result = new ArrayList<Boolean>();

    for (int i = 0; i < str.length(); i++) {

      String s = str.substring(i, i + 1);

      if (!this.letters.contains(s)) {
        throw new IllegalArgumentException(
            "Tried to encode " + s + " but that is not part of the language.");
      }

//      return this.forest.get(0).pathTo(s);

    }

    return null;

  }

}

// a comparator object that compares two given binary trees
class SortByFreq implements Comparator<BinTree> {

  // compares two given binary trees
  public int compare(BinTree bt1, BinTree bt2) {
    return bt1.compareTo(bt2);
  }
}

// a binary tree
interface BinTree {

  // compares this BT with another
  int compareTo(BinTree other);

  // compares this BT with a given leaf
  int compareToLeaf(Leaf other);

  // compares this BT with a given node
  int compareToNode(Node other);

  // computes the sum of this BT and a given BT
  int sum(BinTree other);

  // computes the sum of this BT and a given leaf
  int sumLeaf(Leaf other);

  // computes the sum of this BT and a given node
  int sumNode(Node other);

  // produces a list of booleans representing the path to a letter
  ArrayList<Boolean> pathTo(String s, ArrayList<Boolean> path);

}

// a leaf with a char and its frequency
class Leaf implements BinTree {
  String letter;
  int freq;

  public Leaf(String letter, int freq) {
    this.letter = letter;
    this.freq = freq;
  }

  // compares this leaf with a given BT
  public int compareTo(BinTree other) {
    return other.compareToLeaf(this);
  }

  // compares this leaf with a given leaf
  public int compareToLeaf(Leaf other) {
    return other.freq - this.freq;
  }

  // compares this leaf with a given node
  public int compareToNode(Node other) {
    return other.value - this.freq;
  }

  // computes the sum of this leaf and a given BT
  public int sum(BinTree other) {
    return other.sumLeaf(this);
  }

  // computes the sum of this leaf and a given leaf
  public int sumLeaf(Leaf other) {
    return this.freq + other.freq;
  }

  // computes the sum of this leaf and a given node
  public int sumNode(Node other) {
    return this.freq + other.value;
  }

  // produces a list of booleans representing the path to a letter
  public ArrayList<Boolean> pathTo(String s, ArrayList<Boolean> path) {
    if (this.letter.equals(s)) {
      return path;
    }
    return new ArrayList<Boolean>();
  }

}

// a node with the sum of  its subtrees
class Node implements BinTree {
  int value;
  BinTree left;
  BinTree right;

  public Node(int value, BinTree left, BinTree right) {
    this.value = value;
    this.left = left;
    this.right = right;
  }

  // compares this node with a given BT
  public int compareTo(BinTree other) {
    return other.compareToNode(this);
  }

  // compares this node with a given leaf
  public int compareToLeaf(Leaf other) {
    return other.freq - this.value;
  }

  // compares this node with a given node
  public int compareToNode(Node other) {
    return other.value - this.value;
  }

  // computes the sum of this node and a given BT
  public int sum(BinTree other) {
    return other.sumNode(this);
  }

  // computes the sum of this node and a given leaf
  public int sumLeaf(Leaf other) {
    return this.value + other.freq;
  }

  // computes the sum of this node and a given node
  public int sumNode(Node other) {
    return this.value + other.value;
  }

  // produces a list of booleans representing the path to a letter
  public ArrayList<Boolean> pathTo(String s, ArrayList<Boolean> path) {
    ArrayList<Boolean> leftPath = new ArrayList<Boolean>(path);
    leftPath.add(false);

    ArrayList<Boolean> rightPath = new ArrayList<Boolean>(path);
    rightPath.add(true);

    ArrayList<Boolean> result = this.pathTo(s, leftPath);
    result.addAll(this.pathTo(s, rightPath));

    return result;
  }

}

// ----------------------- EXAMPLES ----------------------------------------

// testing methods with side effects:
// 1. create a test fixture in a consistent, well-known state
// 2. run the method
// 3. check for the expected side effects

class HuffmanExamples {

  Huffman huffman;
  ArrayList<String> letters;
  ArrayList<Integer> freqs;
  ArrayList<BinTree> forest;

  void initEmpty() {
    letters = new ArrayList<String>();
    freqs = new ArrayList<Integer>();
  }

  void initData() {
    letters.add("a");
    freqs.add(18);

    letters.add("e");
    freqs.add(27);

    letters.add("d");
    freqs.add(15);

    letters.add("c");
    freqs.add(11);

    letters.add("b");
    freqs.add(17);

    letters.add("g");
    freqs.add(17);

    // c 11, d 15, b 17, g 17, a 18, e 27

    huffman = new Huffman(letters, freqs);
    forest = huffman.forest;
  }

  void testConstructor(Tester t) {
    initEmpty();
    letters.add("a");
    freqs.add(18);

    // less than 2 letters
    t.checkConstructorExceptionType(IllegalArgumentException.class, "Huffman", letters, freqs);

    letters.add("e");
    letters.add("d");
    letters.add("c");
    freqs.add(22);
    freqs.add(15);

    // different lengths in lists
    t.checkConstructorExceptionType(IllegalArgumentException.class, "Huffman", letters, freqs);

    freqs.add(11);

    // valid
    t.checkConstructorNoException("testConstructor", "Huffman", letters, freqs);

  }

  void testCompareTo(Tester t) {
    BinTree l = new Leaf("a", 1);

    // leaf leaf
    t.checkExpect(new Leaf("a", 1).compareTo(new Leaf("b", 2)), -1);
    t.checkExpect(new Leaf("a", 5).compareTo(new Leaf("b", 3)), 2);
    t.checkExpect(new Leaf("a", 9).compareTo(new Leaf("b", 9)), 0);

    // leaf node
    t.checkExpect(new Leaf("a", 1).compareTo(new Node(2, l, l)), -1);
    t.checkExpect(new Leaf("a", 5).compareTo(new Node(3, l, l)), 2);
    t.checkExpect(new Leaf("a", 9).compareTo(new Node(9, l, l)), 0);

    // node leaf
    t.checkExpect(new Node(1, l, l).compareTo(new Leaf("a", 2)), -1);
    t.checkExpect(new Node(5, l, l).compareTo(new Leaf("a", 3)), 2);
    t.checkExpect(new Node(9, l, l).compareTo(new Leaf("a", 9)), 0);

    // node node
    t.checkExpect(new Node(1, l, l).compareTo(new Node(2, l, l)), -1);
    t.checkExpect(new Node(5, l, l).compareTo(new Node(3, l, l)), 2);
    t.checkExpect(new Node(9, l, l).compareTo(new Node(9, l, l)), 0);
  }

  void testSort(Tester t) {
    initEmpty();
    initData();

    ArrayList<BinTree> f = new ArrayList<BinTree>();

    f.add(new Node(105,
        new Node(44, new Leaf("a", 18), new Node(26, new Leaf("c", 11), new Leaf("d", 15))),
        new Node(61, new Leaf("e", 27), new Node(34, new Leaf("b", 17), new Leaf("g", 17)))));

    t.checkExpect(forest, f);
  }

  void testEncode(Tester t) {
    initEmpty();
    initData();

    t.checkExceptionType(IllegalArgumentException.class, this.huffman, "encode", "A");
  }

  void testPathTo(Tester t) {
    initEmpty();
    initData();

    ArrayList<Boolean> empty = new ArrayList<Boolean>();

    t.checkExpect(this.forest.get(0).pathTo("a", empty), null);
  }

}
