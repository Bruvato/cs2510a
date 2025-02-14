import tester.*;

interface ILoString {

  // Produces a list of strings that has the same elements as this list in reverse
  // order
  ILoString reverse();

  // Produces a list of strings that has the same elements as this list in reverse
  // order
  ILoString reverseHelp(ILoString acc);
  // represents the accumulated elements in reverse order
  // TODO
  // 1. represents the accumulated elements from the front of the list in reverse
  // order
  // 2. starts with the empty list
  // 3. cons the first of the list onto the front of the acc
  // 4. to produce the original list of string in reverse order

  // Produces a list of the same items in the same order, but that uses only
  // ConsLoString and MtLoString
  ILoString normalize();

  // Produces a list of the same items in the same order, but that uses only
  // ConsLoString and MtLoString
  ILoString normalizeHelp(ILoString acc);
  // represents the accumulated elements in normalized form

  // Left-scans across the list and concatenates all the strings, and produces a
  // list of the results
  ILoString scanConcat();

  // Left-scans across the list and concatenates all the strings, and produces a
  // list of the results
  ILoString scanConcatHelp(String acc);
  // represents the accumulated results after each update in the acc

  // TEMPLATE:
  // -----------------
  // Fields:
  // -----------------
  // Methods:
  // this.reverse() - ILoString
  // this.reverseHelp(ILoString acc) - ILoString
  // this.normalize() - ILoString
  // this.normalizeHelp(ILoString acc) - LoString
  // this.scanConcat() - ILoString
  // this.scanConcatHelp(String acc) - ILoString
  // -----------------
  // Methods for Fields:

}

class MtLoString implements ILoString {

  // TEMPLATE:
  // -----------------
  // Fields:
  // -----------------
  // Methods:
  // this.reverse() - ILoString
  // this.reverseHelp(ILoString acc) - ILoString
  // this.normalize() - ILoString
  // this.normalizeHelp(ILoString acc) - LoString
  // this.scanConcat() - ILoString
  // this.scanConcatHelp(String acc) - ILoString
  // -----------------
  // Methods for Fields:

  // Produces a list of strings that has the same elements as this empty list in
  // reverse order
  public ILoString reverse() {
    return this;
  }

  public ILoString reverseHelp(ILoString acc) {
    return acc;
  }

  public ILoString normalize() {
    return this;
  }

  public ILoString normalizeHelp(ILoString acc) {
    return acc;
  }

  // Left-scans across the list and concatenates all the strings, and produces a
  // list of the results
  public ILoString scanConcat() {
    return this;
  }

  public ILoString scanConcatHelp(String acc) {
    return this;
  }

}

class ConsLoString implements ILoString {
  String first;
  ILoString rest;

  public ConsLoString(String first, ILoString rest) {
    this.first = first;
    this.rest = rest;
  }

  // TEMPLATE:
  // -----------------
  // Fields:
  // -----------------
  // Methods:
  // this.reverse() - ILoString
  // this.reverseHelp(ILoString acc) - ILoString
  // this.normalize() - ILoString
  // this.normalizeHelp(ILoString acc) - LoString
  // this.scanConcat() - ILoString
  // this.scanConcatHelp(String acc) - ILoString
  // -----------------
  // Methods for Fields:
  // this.rest.reverseHelp(ILoString acc) - ILoString
  // this.rest.normalizeHelp(ILoString acc) - LoString
  // this.rest.scanConcatHelp(String acc) - ILoString

  // Produces a list of strings that has the same elements as this cons list in
  // reverse order
  public ILoString reverse() {
    return this.reverseHelp(new MtLoString());
  }

  public ILoString reverseHelp(ILoString acc) {
    return this.rest.reverseHelp(new ConsLoString(this.first, acc));
  }

  // Produces a list of the same items in the same order, but that uses only
  // ConsLoString and MtLoString
  public ILoString normalize() {
    return this.normalizeHelp(new MtLoString());
  }

  public ILoString normalizeHelp(ILoString acc) {
    return new ConsLoString(this.first, this.rest.normalizeHelp(acc));
  }
  // represents the accumulated elements in normalized form

  // Left-scans across the list and concatenates all the strings, and produces a
  // list of the results
  public ILoString scanConcat() {
    return this.scanConcatHelp("");
  }

  public ILoString scanConcatHelp(String acc) {
    String result = acc + this.first;
    return new ConsLoString(result, this.rest.scanConcatHelp(result));
  }

}

class SnocLoString implements ILoString {
  ILoString front;
  String last;

  public SnocLoString(ILoString front, String last) {
    this.front = front;
    this.last = last;
  }

  // TEMPLATE:
  // -----------------
  // Fields:
  // -----------------
  // Methods:
  // this.reverse() - ILoString
  // this.reverseHelp(ILoString acc) - ILoString
  // this.normalize() - ILoString
  // this.normalizeHelp(ILoString acc) - LoString
  // this.scanConcat() - ILoString
  // this.scanConcatHelp(String acc) - ILoString
  // -----------------
  // Methods for Fields:
  // this.front.reverseHelp(ILoString acc) - ILoString
  // this.front.normalizeHelp(ILoString acc) - LoString
  // this.front.scanConcatHelp(String acc) - ILoString

  // Produces a list of strings that has the same elements as this cons list in
  // reverse order
  public ILoString reverse() {
    return this.reverseHelp(new MtLoString());
  }

  public ILoString reverseHelp(ILoString acc) {
    return new ConsLoString(this.last, this.front.reverseHelp(acc));
  }

  // Produces a list of the same items in the same order, but that uses only
  // ConsLoString and MtLoString
  public ILoString normalize() {
    return this.normalizeHelp(new MtLoString());
  }

  public ILoString normalizeHelp(ILoString acc) {
    return this.front.normalizeHelp(new ConsLoString(this.last, acc));
  }

  // Left-scans across the list and concatenates all the strings, and produces a
  // list of the results
  public ILoString scanConcat() {
    return this.scanConcatHelp("");
  }

  public ILoString scanConcatHelp(String acc) {
    return this.normalize().scanConcatHelp(acc);
  }

}

class AppendLoString implements ILoString {
  ILoString front;
  ILoString back;

  public AppendLoString(ILoString front, ILoString back) {
    this.front = front;
    this.back = back;
  }

  // TEMPLATE:
  // -----------------
  // Fields:
  // -----------------
  // Methods:
  // this.reverse() - ILoString
  // this.reverseHelp(ILoString acc) - ILoString
  // this.normalize() - ILoString
  // this.normalizeHelp(ILoString acc) - LoString
  // this.scanConcat() - ILoString
  // this.scanConcatHelp(String acc) - ILoString
  // -----------------
  // Methods for Fields:
  // this.front.reverseHelp(ILoString acc) - ILoString
  // this.front.normalizeHelp(ILoString acc) - LoString
  // this.front.scanConcatHelp(String acc) - ILoString
  // this.back.reverseHelp(ILoString acc) - ILoString
  // this.back.normalizeHelp(ILoString acc) - LoString
  // this.back.scanConcatHelp(String acc) - ILoString

  // Produces a list of strings that has the same elements as this cons list in
  // reverse order
  public ILoString reverse() {
    return this.reverseHelp(new MtLoString());
  }

  public ILoString reverseHelp(ILoString acc) {
    return this.back.reverseHelp(this.front.reverseHelp(acc));
  }

  // Produces a list of the same items in the same order, but that uses only
  // ConsLoString and MtLoString
  public ILoString normalize() {
    return this.normalizeHelp(new MtLoString());
  }

  public ILoString normalizeHelp(ILoString acc) {
    return this.front.normalizeHelp(this.back.normalizeHelp(acc));
  }

  // Left-scans across the list and concatenates all the strings, and produces a
  // list of the results
  public ILoString scanConcat() {
    return this.scanConcatHelp("");
  }

  public ILoString scanConcatHelp(String acc) {
    return this.normalize().scanConcatHelp(acc);
  }
}

// TODO
// In a comment, explain whether your method implementations
// follow the same design as reverse did in Fundies 1, and explain why.
// yes, in fundies 1, when we try to reverse a list, we also implement an
// accumulator
// to accumulate the rest of the list bottom-up and cons it with the first item
// in the list
// in java, we also need to use an accumulator in order to track the items in
// the list

// TODO
// Be sure to explain in a comment what your accumulator represents, if you use one.

class ExamplesMoreLists {
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

  boolean testReverse(Tester t) {
    return t.checkExpect(emptyList.reverse(), emptyList)
        && t.checkExpect(consList.reverse(), consListRev)
        && t.checkExpect(snocList.reverse(), consListRev)
        && t.checkExpect(consSnocList.reverse(), consListRev)
        && t.checkExpect(snocConsList.reverse(), consListRev)
        && t.checkExpect(appendList1.reverse(), consListRev)
        && t.checkExpect(appendList2.reverse(), consListRev)
        && t.checkExpect(appendList3.reverse(), consListRev)
        && t.checkExpect(appendList4.reverse(), consListRev)
        && t.checkExpect(appendList5.reverse(), consListRev);
  }

  boolean testNormalize(Tester t) {
    return t.checkExpect(emptyList.normalize(), emptyList)
        && t.checkExpect(consList.normalize(), consList)
        && t.checkExpect(snocList.normalize(), consList)
        && t.checkExpect(consSnocList.normalize(), consList)
        && t.checkExpect(snocConsList.normalize(), consList)
        && t.checkExpect(appendList1.normalize(), consList)
        && t.checkExpect(appendList2.normalize(), consList)
        && t.checkExpect(appendList3.normalize(), consList)
        && t.checkExpect(appendList4.normalize(), consList)
        && t.checkExpect(appendList5.normalize(), consList);
  }

  boolean testScanConcat(Tester t) {
    return t.checkExpect(emptyList.scanConcat(), emptyList)
        && t.checkExpect(consList.scanConcat(), scanConcatList)
        && t.checkExpect(snocList.scanConcat(), scanConcatList)
        && t.checkExpect(consSnocList.scanConcat(), scanConcatList)
        && t.checkExpect(snocConsList.scanConcat(), scanConcatList)
        && t.checkExpect(appendList1.scanConcat(), scanConcatList)
        && t.checkExpect(appendList2.scanConcat(), scanConcatList)
        && t.checkExpect(appendList3.scanConcat(), scanConcatList)
        && t.checkExpect(appendList4.scanConcat(), scanConcatList)
        && t.checkExpect(appendList5.scanConcat(), scanConcatList);
  }

}
