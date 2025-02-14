import lists.*;
import tester.Tester;

class ExamplesLoString {
  ILoString emptyList = new MtLoString();

  ILoString consList = new ConsLoString("a",
      new ConsLoString("b", new ConsLoString("c", new ConsLoString("d", emptyList))));
  ILoString consListRev = new ConsLoString("d",
      new ConsLoString("c", new ConsLoString("b", new ConsLoString("a", emptyList))));

  ILoString snocList = new SnocLoString(
      new SnocLoString(new SnocLoString(new SnocLoString(emptyList, "a"), "b"), "c"), "d");

  ILoString consSnocList = new ConsLoString("a",
      new ConsLoString("b", new SnocLoString(new SnocLoString(emptyList, "c"), "d")));

  ILoString snocConsList = new SnocLoString(
      new SnocLoString(new ConsLoString("a", new ConsLoString("b", emptyList)), "c"), "d");

  ILoString appendList1 = new AppendLoString(
      new ConsLoString("a", new ConsLoString("b", emptyList)),
      new ConsLoString("c", new ConsLoString("d", emptyList)));
  ILoString appendList2 = new AppendLoString(
      new ConsLoString("a", new ConsLoString("b", new ConsLoString("c", emptyList))),
      new ConsLoString("d", emptyList));
  ILoString appendList3 = new AppendLoString(
      new AppendLoString(new ConsLoString("a", emptyList), new ConsLoString("b", emptyList)),
      new ConsLoString("c", new ConsLoString("d", emptyList)));
  ILoString appendList4 = new AppendLoString(
      new ConsLoString("a", new ConsLoString("b", emptyList)),
      new AppendLoString(new ConsLoString("c", emptyList), new ConsLoString("d", emptyList)));
  ILoString appendList5 = new AppendLoString(
      new AppendLoString(new ConsLoString("a", emptyList), new ConsLoString("b", emptyList)),
      new AppendLoString(new ConsLoString("c", emptyList), new ConsLoString("d", emptyList)));

  ILoString scanConcatList = new ConsLoString("a",
      new ConsLoString("ab", new ConsLoString("abc", new ConsLoString("abcd", emptyList))));

//  boolean testReverseEmpty(Tester t) {
//    return t.checkExpect(emptyList.reverse(), emptyList);
//  }

//  boolean testReverseCons(Tester t) {
//    return t.checkExpect(consList.reverse().normalize(), consListRev);
//  }

//  boolean testReverseSnoc(Tester t) {
//    return t.checkExpect(snocList.reverse().normalize(), consListRev);
//  }

  boolean testReverseConsSnoc(Tester t) {
    return t.checkExpect(consSnocList.reverse().normalize(), consListRev)
        && t.checkExpect(snocConsList.reverse().normalize(), consListRev);
  }

//  boolean testReverseAppend(Tester t) {
//    return t.checkExpect(appendList1.reverse().normalize(), consListRev)
//        && t.checkExpect(appendList2.reverse().normalize(), consListRev)
//        && t.checkExpect(appendList3.reverse().normalize(), consListRev)
//        && t.checkExpect(appendList4.reverse().normalize(), consListRev)
//        && t.checkExpect(appendList5.reverse().normalize(), consListRev);
//  }

//  boolean testNormalizeEmpty(Tester t) {
//    return t.checkExpect(emptyList.normalize(), emptyList);
//  }

//  boolean testNormalizeCons(Tester t) {
//    return t.checkExpect(consList.normalize(), consList);
//  }

//  boolean testNormalizeSnoc(Tester t) {
//    return t.checkExpect(snocList.normalize(), consList);
//  }

  boolean testNormalizeConsSnoc(Tester t) {
    return t.checkExpect(consSnocList.normalize(), consList)
        && t.checkExpect(snocConsList.normalize(), consList);
  }

//  boolean testNormalizeAppend(Tester t) {
//    return t.checkExpect(appendList1.normalize(), consList)
//        && t.checkExpect(appendList2.normalize(), consList)
//        && t.checkExpect(appendList3.normalize(), consList)
//        && t.checkExpect(appendList4.normalize(), consList)
//        && t.checkExpect(appendList5.normalize(), consList);
//  }

//  boolean testScanConcatEmpty(Tester t) {
//    return t.checkExpect(emptyList.scanConcat(), emptyList);
//  }

//  boolean testScanConcatCons(Tester t) {
//    return t.checkExpect(consList.scanConcat().normalize(), scanConcatList);
//  }

  boolean testScanConcatSnoc(Tester t) {
    return t.checkExpect(snocList.scanConcat().normalize(), scanConcatList);
  }

  boolean testScanConcatConsSnoc(Tester t) {
    return t.checkExpect(consSnocList.scanConcat().normalize(), scanConcatList)
        && t.checkExpect(snocConsList.scanConcat().normalize(), scanConcatList);
  }

  boolean testScanConcatAppend(Tester t) {
    return t.checkExpect(appendList1.scanConcat().normalize(), scanConcatList)
        && t.checkExpect(appendList2.scanConcat().normalize(), scanConcatList)
        && t.checkExpect(appendList3.scanConcat().normalize(), scanConcatList)
        && t.checkExpect(appendList4.scanConcat().normalize(), scanConcatList)
        && t.checkExpect(appendList5.scanConcat().normalize(), scanConcatList);

  }
}