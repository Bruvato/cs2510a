import tester.*;

// Represents a list of numbers
interface ILoInt {

  boolean contains(int value);

  int length();
}

// Represents an empty list of numbers
class MtLoInt implements ILoInt {

  public boolean contains(int value) {
    return false;
  }

  public int length() {
    return 0;
  }

}

// Represents a non-empty list of numbers
class ConsLoInt implements ILoInt {
  int first;
  ILoInt rest;

  ConsLoInt(int first, ILoInt rest) {
    this.first = first;
    this.rest = rest;
  }

  public boolean contains(int value) {
    return (this.first == value) || this.rest.contains(value);
  }

  public int length() {
    return 1 + rest.length();
  }
}

class ExamplesLists {
  ILoInt empty = new MtLoInt();
  ILoInt fundies = new ConsLoInt(2,
      new ConsLoInt(5, new ConsLoInt(1, new ConsLoInt(0, new MtLoInt()))));

  boolean testContains(Tester t) {
    return t.checkExpect(fundies.contains(0), true) && t.checkExpect(fundies.contains(-1), false);
  }

  boolean testLength(Tester t) {
    return t.checkExpect(fundies.length(), 4);
  }

}