
// Represents a resource
interface IResource {

}

// Represents a captain
class Captain implements IResource {
  String name;
  int battles;

  public Captain(String name, int battles) {
    this.name = name;
    this.battles = battles;
  }

}

// Represents a crew member
class Crewmember implements IResource {
  String name;
  String description;
  int wealth;

  public Crewmember(String name, String description, int wealth) {
    this.name = name;
    this.description = description;
    this.wealth = wealth;
  }

}

// Represents a ship
class Ship implements IResource {
  String purpose;
  boolean hostile;

  public Ship(String purpose, boolean hostile) {
    this.purpose = purpose;
    this.hostile = hostile;
  }

}

// Represents an action
interface IAction {

}

// Represents a purchase
class Purchase implements IAction {
  int cost;
  IResource item;

  public Purchase(int cost, IResource item) {
    this.cost = cost;
    this.item = item;
  }
}

// Represents a barter
class Barter implements IAction {
  IResource sold;
  IResource acquired;

  public Barter(IResource sold, IResource acquired) {
    this.sold = sold;
    this.acquired = acquired;
  }
}

// Examples for the classes representing resources and actions in DryDock
class ExamplesGame {
  IResource jackSparrow = new Captain("Jack Sparrow", 89);
  IResource hectorBarbossa = new Crewmember("Hector Barbossa", "first mate", 52);
  IResource flyingDutchman = new Ship("sail the oceans forever", true);
  IResource cap = new Captain("Cap", 102);
  IResource cm = new Crewmember("Crew Member", "default crew member", 30);
  IResource ship = new Ship("survive from the storm", false);

  IAction purchase1 = new Purchase(89, jackSparrow);
  IAction purchase2 = new Purchase(52, hectorBarbossa);
  IAction barter1 = new Barter(ship, hectorBarbossa);
  IAction barter2 = new Barter(flyingDutchman, cap);
}
