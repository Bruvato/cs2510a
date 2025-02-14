//import lists.*;
//import tester.Tester;
//
//class ExamplesLoInt {
//  ILoInt empty = new MtLoInt();
//
//  ILoInt list0 = new ConsLoInt(1, new ConsLoInt(1,
//      new ConsLoInt(1, new ConsLoInt(2, new ConsLoInt(2, new ConsLoInt(3, empty))))));
//  ILoInt list1 = new ConsLoInt(9, new ConsLoInt(5,
//      new ConsLoInt(3, new ConsLoInt(2, new ConsLoInt(2, new ConsLoInt(5, empty))))));
//  ILoInt list2 = new ConsLoInt(9,
//      new ConsLoInt(9, new ConsLoInt(2,
//          new ConsLoInt(2, new ConsLoInt(2, new ConsLoInt(1, new ConsLoInt(9, new ConsLoInt(9,
//              new ConsLoInt(2, new ConsLoInt(2, new ConsLoInt(2, new ConsLoInt(1, empty))))))))))));
//  ILoInt list3 = new ConsLoInt(1, new ConsLoInt(1,
//      new ConsLoInt(1, new ConsLoInt(2, new ConsLoInt(2, new ConsLoInt(3, empty))))));
//
//  ILoInt nat0 = new ConsLoInt(1, empty);
//  ILoInt nat1 = new ConsLoInt(1, new ConsLoInt(2, empty));
//  ILoInt nat2 = new ConsLoInt(1, new ConsLoInt(2, new ConsLoInt(3, empty)));
//  ILoInt nat3 = new ConsLoInt(1, new ConsLoInt(2, new ConsLoInt(3, new ConsLoInt(4, empty))));
//  ILoInt nat4 = new ConsLoInt(1,
//      new ConsLoInt(2, new ConsLoInt(3, new ConsLoInt(4, new ConsLoInt(5, empty)))));
//
//  ILoInt fib2 = new ConsLoInt(0, new ConsLoInt(1, new ConsLoInt(1, empty)));
//  ILoInt fib5 = new ConsLoInt(0, new ConsLoInt(1,
//      new ConsLoInt(1, new ConsLoInt(2, new ConsLoInt(3, new ConsLoInt(5, empty))))));
//  ILoInt lucas5 = new ConsLoInt(2, new ConsLoInt(1,
//      new ConsLoInt(3, new ConsLoInt(4, new ConsLoInt(7, new ConsLoInt(11, empty))))));
//
//  ILoInt pell2 = new ConsLoInt(1, new ConsLoInt(2, new ConsLoInt(5, empty)));
//  ILoInt pell4 = new ConsLoInt(1,
//      new ConsLoInt(2, new ConsLoInt(5, new ConsLoInt(12, new ConsLoInt(29, empty)))));
//  ILoInt pellLucas4 = new ConsLoInt(2,
//      new ConsLoInt(6, new ConsLoInt(14, new ConsLoInt(34, new ConsLoInt(82, empty)))));
//
//  ILoInt negaFib8 = new ConsLoInt(1,
//      new ConsLoInt(1, new ConsLoInt(0, new ConsLoInt(1, new ConsLoInt(-1,
//          new ConsLoInt(2, new ConsLoInt(-3, new ConsLoInt(5, new ConsLoInt(-8, empty)))))))));
//
//  ILoInt jocabsthal8 = new ConsLoInt(0,
//      new ConsLoInt(1, new ConsLoInt(1, new ConsLoInt(3, new ConsLoInt(5,
//          new ConsLoInt(11, new ConsLoInt(21, new ConsLoInt(43, new ConsLoInt(85, empty)))))))));
//
//  // at most 2 elements -> true
//  boolean testIsFibLike(Tester t) {
//    return t.checkExpect(empty.isFibLike(), true) && t.checkExpect(nat0.isFibLike(), true)
//        && t.checkExpect(nat1.isFibLike(), true) && t.checkExpect(nat2.isFibLike(), true)
//        && t.checkExpect(nat3.isFibLike(), false) && t.checkExpect(fib2.isFibLike(), true)
//        && t.checkExpect(fib5.isFibLike(), true) && t.checkExpect(lucas5.isFibLike(), true);
//  }
//
//  boolean testIsPellLike(Tester t) {
//    return t.checkExpect(empty.isPellLike(), true) && t.checkExpect(nat0.isPellLike(), true)
//        && t.checkExpect(nat1.isPellLike(), true) && t.checkExpect(nat2.isPellLike(), false)
//        && t.checkExpect(nat3.isPellLike(), false) && t.checkExpect(pell2.isPellLike(), true)
//        && t.checkExpect(pell4.isPellLike(), true) && t.checkExpect(pellLucas4.isPellLike(), true);
//  }
//
//  boolean testIsNegaFibLike(Tester t) {
//    return t.checkExpect(empty.isNegaFibLike(), true) && t.checkExpect(nat0.isNegaFibLike(), true)
//        && t.checkExpect(nat1.isNegaFibLike(), true) && t.checkExpect(nat2.isNegaFibLike(), false)
//        && t.checkExpect(nat3.isNegaFibLike(), false)
//        && t.checkExpect(negaFib8.isNegaFibLike(), true);
//  }
//
//  boolean testIsJacobsthalLike(Tester t) {
//    return t.checkExpect(empty.isJacobsthalLike(), true)
//        && t.checkExpect(nat0.isJacobsthalLike(), true)
//        && t.checkExpect(nat1.isJacobsthalLike(), true)
//        && t.checkExpect(nat2.isJacobsthalLike(), false)
//        && t.checkExpect(nat3.isJacobsthalLike(), false)
//        && t.checkExpect(jocabsthal8.isJacobsthalLike(), true);
//  }
//
//  boolean testSecondLargestNum(Tester t) {
//    // at least 2 elements
//    return t.checkExpect(nat1.secondLargestNum(), 1) && t.checkExpect(nat2.secondLargestNum(), 2)
//        && t.checkExpect(nat3.secondLargestNum(), 3) && t.checkExpect(fib2.secondLargestNum(), 1);
//  }
//
//  boolean testFifthLargestNum(Tester t) {
//    // at least 5 elements
//    return t.checkExpect(nat4.fifthLargestNum(), 1) && t.checkExpect(fib5.fifthLargestNum(), 1)
//        && t.checkExpect(lucas5.fifthLargestNum(), 2);
//  }
//
//  boolean testMostCommonNum(Tester t) {
//    // at least 1 element
//    return t.checkExpect(list2.mostCommonNum(), 2) && t.checkExpect(nat0.mostCommonNum(), 1)
//        && t.checkExpect(list0.mostCommonNum(), 1) && t.checkOneOf(list1.mostCommonNum(), 2, 5)
//        && t.checkExpect(nat0.mostCommonNum(), 1) && t.checkOneOf(nat1.mostCommonNum(), 1, 2)
//        && t.checkOneOf(nat2.mostCommonNum(), 1, 2, 3) && t.checkExpect(fib2.mostCommonNum(), 1)
//        && t.checkExpect(fib5.mostCommonNum(), 1);
//  }
//
//  boolean testThirdMostCommonNum(Tester t) {
//    // at least 3 elements
//    return t.checkExpect(list2.thirdMostCommonNum(), 1)
//        && t.checkOneOf(nat2.thirdMostCommonNum(), 1, 2, 3)
//        && t.checkExpect(list3.thirdMostCommonNum(), 3);
//  }
//
//}