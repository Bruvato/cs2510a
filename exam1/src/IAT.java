import tester.Tester;

interface IAT {

  boolean containsName(String name);

  boolean duplicateNames();
}

class Unknown implements IAT {

  public boolean containsName(String name) {
    return false;
  }

  public boolean duplicateNames() {
    return false;
  }
}

class Person implements IAT {
  String name;
  IAT dad, mom;

  Person(String name, IAT dad, IAT mom) {
    this.name = name;
    this.dad = dad;
    this.mom = mom;
  }

  public boolean containsName(String name) {
    return this.name.equals(name) || this.mom.containsName(name) || this.dad.containsName(name);
  }

  public boolean duplicateNames() {
    return this.mom.containsName(this.name) || this.dad.containsName(this.name)
        || this.mom.duplicateNames() || this.dad.duplicateNames();
  }
}

class ExamplesIAT {
  IAT davisSr = new Person("Davis", new Unknown(), new Unknown());
  IAT edna = new Person("Edna", new Unknown(), new Unknown());
  IAT davisJr = new Person("Davis", davisSr, edna);
  IAT carl = new Person("Carl", new Unknown(), new Unknown());
  IAT candace = new Person("Candace", davisJr, new Unknown());
  IAT claire = new Person("Claire", new Unknown(), new Unknown());
  IAT bill = new Person("Bill", carl, candace);
  IAT bree = new Person("Bree", new Unknown(), claire);
  IAT anthony = new Person("Anthony", bill, bree);

  void test(Tester t) {
    t.checkExpect(anthony.containsName("Davis"), true);
    t.checkExpect(anthony.containsName("d"), false);
    
    t.checkExpect(anthony.duplicateNames(), null);

  }

}