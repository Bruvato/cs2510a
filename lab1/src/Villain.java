
interface IVillain {

}

class Person implements IVillain {
  String name;

  public Person(String name) {
    this.name = name;
  }
}

class Mask implements IVillain {
  IVillain villain;
  String description;

  public Mask(IVillain villain, String description) {
    this.villain = villain;
    this.description = description;
  }
}

class ExamplesVillains {
  IVillain v1 = new Person("Bob");
  IVillain v2 = new Mask(new Mask(new Mask(new Person("bob"), "red"), "blue"), "yellow");
}