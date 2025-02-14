
interface IAttraction {
}

class Show implements IAttraction {
  String name;
  int price;
  int minimumAge;

  public Show(String name, int price, int minimumAge) {
    this.name = name;
    this.price = price;
    this.minimumAge = minimumAge;
  }
}

class FoodVendor implements IAttraction {
  String name;
  int price;
  String description;

  public FoodVendor(String name, int price, String description) {
    this.name = name;
    this.price = price;
    this.description = description;
  }
}

class RollerCoaster implements IAttraction {
  String name;
  int price;
  int minimumHeight;
  int ratingExciting;
  int ratingNausea;

  public RollerCoaster(String name, int price, int minimumHeight, int ratingExciting,
      int ratingNausea) {

    this.name = name;
    this.price = price;
    this.minimumHeight = minimumHeight;
    this.ratingExciting = ratingExciting;
    this.ratingNausea = ratingNausea;
  }

}

class ExamplesAttractions{
  IAttraction show1 = new Show("cool show", 10, 9);
}
