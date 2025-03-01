//import huffman.*;
//import tester.Tester;
//
//import java.util.ArrayList;
//
//class ExamplesHuffman {
//
//  Huffman aedcbg;
//
//  ArrayList<String> letters;
//  ArrayList<Integer> freqs;
//  ArrayList<Boolean> coded;
//
//  // throw exception:
//  // - lists are different lengths
//  // - length of list of strings is less than 2
//
//  void initHuffmans() {
//    letters = new ArrayList<String>();
//    freqs = new ArrayList<Integer>();
//    coded = new ArrayList<Boolean>();
//
//    aedcbg = null;
//  }
//
//  void initMyExample() {
//    letters.add("a");
//    freqs.add(18);
//
//    letters.add("e");
//    freqs.add(27);
//
//    letters.add("d");
//    freqs.add(15);
//
//    letters.add("c");
//    freqs.add(11);
//
//    letters.add("b");
//    freqs.add(17);
//
//    letters.add("g");
//    freqs.add(17);
//
//    aedcbg = new Huffman(letters, freqs);
//  }
//
//  void testInitializeHuffman(Tester t) {
//    this.initHuffmans();
//    letters.add("a");
//    freqs.add(18);
//    
//    // less than 2 letters
//    t.checkConstructorExceptionType(IllegalArgumentException.class, "Huffman", letters, freqs);
//    
//    letters.add("e");
//    letters.add("d");
//    letters.add("c");
//    freqs.add(22);
//    freqs.add(15);
//    
//    // different lengths in lists
//    t.checkConstructorExceptionType(IllegalArgumentException.class, "Huffman", letters, freqs);
//    
//    freqs.add(11);
//    
//    // now should be a valid contruction
//    // aedcbg = new Huffman(letters, freqs); // shouldn't crash
//    t.checkConstructorNoException("testInitializeHuffman", "Huffman", letters, freqs);
//  }
//
//  // chaff 0
//  void testEncodeSimple(Tester t) {
//    this.initHuffmans();
//    this.initMyExample();
//
//    t.checkExceptionType(IllegalArgumentException.class, aedcbg, "encode", "A");
//    // "A" -> empty is not in the alphabet
//
//    // t.checkExpect(aedcbg.encode(""), coded);
//
//    coded.add(false);
//    coded.add(false);
//    t.checkExpect(aedcbg.encode("a"), coded);
//    coded.add(false);
//    coded.add(true);
//    coded.add(true);
//    t.checkExpect(aedcbg.encode("ad"), coded);
//  }
//
//  // chaffs 0, 2, 3
//  void testEncodeDups(Tester t) {
//    this.initHuffmans();
//    this.initMyExample();
//
//    coded.add(false);
//    coded.add(false);
//    coded.add(false);
//    coded.add(true);
//    coded.add(true);
//    coded.add(false);
//    coded.add(true);
//    coded.add(true);
//    coded.add(true);
//    coded.add(true);
//    coded.add(false);
//    
//    t.checkExpect(aedcbg.encode("addb"), coded);
//  }
//
//  void testDecode(Tester t) {
//    this.initHuffmans();
//    this.initMyExample();
//
//    coded.add(false);
//    t.checkExpect(aedcbg.decode(coded), "?");
//    coded.add(false);
//    t.checkExpect(aedcbg.decode(coded), "a");
//    coded.add(true);
//    coded.add(true);
//    t.checkExpect(aedcbg.decode(coded), "a?");
//    coded.add(false);
//
//    t.checkExpect(aedcbg.decode(coded), "ab");
//  }
//
//  void testOrdering(Tester t) {
//    this.initHuffmans();
//    letters.add("a");
//    freqs.add(13);
//
//    letters.add("p");
//    freqs.add(14);
//
//    letters.add("e");
//    freqs.add(14);
//
//    Huffman simple = new Huffman(letters, freqs);
//
//    coded.add(true);
//    coded.add(true);
//    
////    coded.add(false);
////    coded.add(true);
//    
////    coded.add(true);
//    t.checkExpect(simple.decode(coded), "p");
//
//
//
//
//  }
//  
//  void test(Tester t) {
//    this.initHuffmans();
//    letters.add("a");
//    freqs.add(1);
//
//    letters.add("b");
//    freqs.add(1);
//
//    letters.add("c");
//    freqs.add(2);
//
//
//    Huffman simple = new Huffman(letters, freqs);
//
//    coded.add(false);
//    
//    t.checkExpect(simple.encode("c"), coded);
//    
//
//
//  }
//
//}