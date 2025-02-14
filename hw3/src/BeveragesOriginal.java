//import tester.Tester;
//
//// Represents a drink you might get at a coffee shop
//interface IBeverage {
//
//  // Determines if this beverage is de-caffeinated
//  boolean isDecaf();
//
//  // Determines if a given ingredient has been mixed into this beverage
//  boolean containsIngredient(String ingredient);
//
//  // Produces a String showing how this beverage would appear on a menu
//  String format();
//}
//
//// Represents a bubble-tea drink, with various mixins
//class BubbleTea implements IBeverage {
//
//  String variety; // Black tea, Oolong, Green tea, etc.
//
//  int size; // in ounces
//
//  ILoString mixins; // boba, extra sugar, milk, etc
//
//  BubbleTea(String variety, int size, ILoString mixins) {
//    this.variety = variety;
//    this.size = size;
//    this.mixins = mixins;
//  }
//
//  // Determines if this bubble tea beverage is de-caffeinated
//  public boolean isDecaf() {
//    if (this.variety.equals("Rooibos")) {
//      return true;
//    }
//    return false;
//  }
//
//  // Determines if a given ingredient has been mixed into this bubble tea beverage
//  public boolean containsIngredient(String ingredient) {
//    return this.mixins.contains(ingredient);
//  }
//
//  // Produces a String showing how this would appear on a menu
//  public String format() {
//    String mixinsStr = this.mixins.format();
//
//    return this.size + "oz " + this.variety
//        + (mixinsStr.equals("") ? " (without mixins)" : " (with " + mixinsStr + ")");
//  }
//}
//
//// Represents any coffee-based drink
//class Coffee implements IBeverage {
//
//  String variety; // Arabica, Robusta, Excelsa or Liberica
//
//  String style; // americano, demitasse, espresso, etc.
//
//  boolean isIced; // whether it's cold or hot
//
//  ILoString mixins; // cream, sugar, flavored syrup, etc.
//
//  Coffee(String variety, String style, boolean isIced, ILoString mixins) {
//    this.variety = variety;
//    this.style = style;
//    this.isIced = isIced;
//    this.mixins = mixins;
//  }
//
//  // Determines if this coffee beverage is de-caffeinated
//  public boolean isDecaf() {
//    return false;
//  }
//
//  // Determines if a given ingredient has been mixed into this coffee beverage
//  public boolean containsIngredient(String ingredient) {
//    return this.mixins.contains(ingredient);
//  }
//
//  // Produces a String showing how this coffee beverage would appear on a menu
//  public String format() {
//    String mixinsStr = this.mixins.format();
//
//    return (this.isIced ? "Iced " : "Hot ") + this.variety + " " + this.style + " "
//        + ((mixinsStr.equals("")) ? " (without mixins)" : " (with " + mixinsStr + ")");
//  }
//}
//
//// Represents an ice-cream-based blended drink
//class Milkshake implements IBeverage {
//
//  String flavor; // vanilla, mint-chip, strawberry, etc.
//
//  String brandName; // Ben&Jerrys, JPLicks, etc.
//
//  int size; // in ounces
//
//  ILoString toppings; // whipped cream, sprinkles, cookie crumbles, etc.
//
//  Milkshake(String flavor, String brandName, int size, ILoString toppings) {
//    this.flavor = flavor;
//    this.brandName = brandName;
//    this.size = size;
//    this.toppings = toppings;
//  }
//
//  // Determines if this milk shake beverage is de-caffeinated
//  public boolean isDecaf() {
//    return true;
//  }
//
//  // Determines if a given ingredient has been mixed into this milk shake beverage
//  public boolean containsIngredient(String ingredient) {
//    return this.toppings.contains(ingredient);
//  }
//
//  // Produces a String showing how this milk shake beverage would appear on a menu
//  public String format() {
//    String mixinsStr = this.toppings.format();
//
//    return this.size + "oz " + this.brandName + " " + this.flavor + " "
//        + ((mixinsStr.equals("")) ? " (without mixins)" : " (with " + mixinsStr + ")");
//  }
//}
//
//// lists of strings
//interface ILoString {
//
//  // Determines if this list of strings contains a given string
//  boolean contains(String s);
//
//  // Produces a string showing this list of strings separated by commas
//  String format();
//}
//
//// an empty list of strings
//class MtLoString implements ILoString {
//
//  // Determines if this empty list contains a given string
//  public boolean contains(String s) {
//    return false;
//  }
//
//  // Produces a string showing this empty list separated by commas
//  public String format() {
//    return "";
//  }
//}
//
//// a non-empty list of strings
//class ConsLoString implements ILoString {
//  String first;
//  ILoString rest;
//
//  ConsLoString(String first, ILoString rest) {
//    this.first = first;
//    this.rest = rest;
//  }
//
//  // Determines if this non empty list of strings contains a given string
//  public boolean contains(String s) {
//    return this.first.equals(s) || this.rest.contains(s);
//  }
//
//  // Produces a string showing this non empty list of strings separated by commas
//  public String format() {
//    return this.first + ", " + this.rest.format();
//  }
//}
//
//class ExamplesBeverages {
//  ILoString empty = new MtLoString();
//
//  IBeverage bt0 = new BubbleTea("Black tea", 16, new ConsLoString("boba", empty));
//  IBeverage bt1 = new BubbleTea("Oolong", 16,
//      new ConsLoString("boba", new ConsLoString("milk", empty)));
//
//  IBeverage c0 = new Coffee("Arabica", "americano", true, new ConsLoString("cream", empty));
//  IBeverage c1 = new Coffee("Robusta", "espresso", false,
//      new ConsLoString("cream", new ConsLoString("sugar", empty)));
//
//  IBeverage m0 = new Milkshake("vanilla", "Ben&Jerrys", 16,
//      new ConsLoString("whipped cream", empty));
//  IBeverage m1 = new Milkshake("mint-chip", "JPLicks", 16,
//      new ConsLoString("whipped cream", new ConsLoString("sprinkles", empty)));
//
//}
