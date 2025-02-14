import tester.*;

class Store {
  String name;
  int yearOpened;
  String inventory;
  Location location;

  public Store(String name, int yearOpened, String inventory, Location location) {
    this.name = name;
    this.yearOpened = yearOpened;
    this.inventory = inventory;
    this.location = location;
  }

}

class Location {
  String city;
  String state;

  public Location(String city, String state) {
    this.city = city;
    this.state = state;
  }
}

class ExamplesStore {
  Store amazon = new Store("Amazon", 1994, "miscellany", new Location("Bellevue", "WA"));
  Store subway = new Store("Subway", 1965, "sandwiches", new Location("Boston", "MA"));
  Store outlier = new Store("Outlier", 2008, "clothing", new Location("New York City", "NY"));
}