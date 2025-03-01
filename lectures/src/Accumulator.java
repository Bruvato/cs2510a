import tester.Tester;

interface IAT {

  // To compute the number of known ancestors of this ancestor tree
  // (excluding this ancestor tree itself)
  int count();

  int countHelp();

  // To compute how many ancestors of this ancestor tree (excluding this
  // ancestor tree itself) are women older than 40 (in the current year)?
  int femaleAncOver40();

  // To compute whether this ancestor tree is well-formed: are all known
  // people younger than their parents?
  boolean wellFormed();

  boolean wellFormedHelp(int yob);

  // To compute the names of all the known ancestors in this ancestor tree
  // (including this ancestor tree itself)
  ILoString ancNames();

  IAT youngerIAT(IAT other);

  IAT youngerIAT(Unknown other);

  IAT youngerIAT(Person other);

  // To compute this ancestor tree's youngest grandparent
  IAT youngestGrandparent();
}

class Unknown implements IAT {
  Unknown() {
  }

  public int count() {
    return 0;
  }

  public int countHelp() {
    return 0;
  }

  @Override
  public int femaleAncOver40() {
    // TODO Auto-generated method stub
    return 0;
  }

  public boolean wellFormed() {
    return true;
  }

  public boolean wellFormedHelp(int yob) {
    return true;
  }

  @Override
  public ILoString ancNames() {
    // TODO Auto-generated method stub
    return null;
  }

  public IAT youngerIAT(IAT other) {
    return other.youngerIAT(this);
  }

  public IAT youngerIAT(Unknown other) {
    return this;
  }

  public IAT youngerIAT(Person other) {
    return other;
  }

  @Override
  public IAT youngestGrandparent() {
    // TODO Auto-generated method stub
    return null;
  }

}

class Person implements IAT {
  String name;
  int yob;
  boolean isMale;
  IAT mom;
  IAT dad;

  Person(String name, int yob, boolean isMale, IAT mom, IAT dad) {
    this.name = name;
    this.yob = yob;
    this.isMale = isMale;
    this.mom = mom;
    this.dad = dad;
  }

  public int count() {
    return this.mom.countHelp() + this.dad.countHelp();
  }

  public int countHelp() {
    return 1 + this.mom.countHelp() + this.dad.countHelp();
  }

  @Override
  public int femaleAncOver40() {
    // TODO Auto-generated method stub
    return 0;
  }

  public boolean wellFormed() {
    return this.mom.wellFormedHelp(this.yob) && this.dad.wellFormedHelp(this.yob);
  }

  public boolean wellFormedHelp(int yob) {
    return this.yob <= yob && this.mom.wellFormedHelp(this.yob)
        && this.dad.wellFormedHelp(this.yob);
  }

  @Override
  public ILoString ancNames() {
    // TODO Auto-generated method stub
    return null;
  }

  public IAT youngerIAT(IAT other) {
    return other.youngerIAT(this);
  }

  public IAT youngerIAT(Unknown other) {
    return this;
  }

  public IAT youngerIAT(Person other) {
    if (this.yob > other.yob) {
      return this;
    }
    return other;
  }

  @Override
  public IAT youngestGrandparent() {
    // TODO Auto-generated method stub
    return null;
  }

}

interface ILoString {

}

class ConsLoString implements ILoString {
  String first;
  ILoString rest;

  ConsLoString(String first, ILoString rest) {
    this.first = first;
    this.rest = rest;
  }
}

class MtLoString implements ILoString {
  MtLoString() {
  }
}

class Examples {
  void test(Tester t) {
    IAT u = new Unknown();
//    t.checkExpect(
//        new Person("a", 0, true, new Person("mom", 0, true, u, u), new Person("dad", 0, true, u, u))
//            .count(),
//        null);
//    t.checkExpect(new Person("a", 2005, true, u, u).youngerIAT(new Person("b", 2010, true, u, u)),
//        null);

  }
}