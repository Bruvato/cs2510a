//import tester.Tester;
//
////Represents a drink you might get at a coffee shop
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
//// Represents a beverage
//abstract class ABeverage implements IBeverage {
//  
//  // represent the flavor or variety of the beverage
//  // may including detailed information like brands or style
//  String taste;
//  
//  // represent the size in ounce of the beverage if available
//  // will be 0 if no size is indicated
//  int size;
//  
//  // the list of ingredient added in the beverage
//  ILoString ingredient;
//
//  ABeverage(String taste, int size, ILoString ingredient) {
//    this.taste = taste;
//    this.ingredient = ingredient;
//    this.size = size;
//  }
//
//  //Determines if this beverage is de-caffeinated
//  public abstract boolean isDecaf();
//
//  //Determines if a given ingredient has been mixed into this beverage
//  public boolean containsIngredient(String ingredient) {
//    return this.ingredient.contains(ingredient);
//  }
//
//  //Produces a String showing how this beverage would appear on a menu
//  public String format() {
//    if (this.ingredient.isEmpty()) {
//      return size + "oz " + this.taste + " (without mixins)";
//    }
//    else {
//      return size + "oz " + this.taste + " (with " + this.ingredient.toString() + ")";
//    }
//  }
//}
//
//// Represents a bubble-tea drink, with various mixins
//class BubbleTea extends ABeverage {
//
//  BubbleTea(String variety, ILoString mixins, int size) {
//    super(variety, size, mixins);
//  }
//  
//
//  /*  TEMPLATE 
//   Fields:
//   ... this.taste ...                -- String
//   ... this.size ...                 -- int
//   ... this.ingredient ...           -- ILoString
//   Methods:
//   ... this.isDecaf() ...                  -- boolean 
//   ... this.containsIngredient(String) ... -- boolean
//   ... this.format() ...                   -- String
//   Methods for fields:
//   ... this.ingredient.isEmpty() ...       -- boolean 
//   ... this.ingredient.contains(String) ...-- boolean 
//   ... this.ingredient.toString() ...      -- String 
//   */
//
//  //Determines if this beverage is de-caffeinated
//  public boolean isDecaf() {
//    return this.taste.equals("Rooibos");
//  }
//
//}
//
//// Represents any coffee-based drink
//class Coffee extends ABeverage {
//
//  String variety; // Arabica, Robusta, Excelsa or Liberica
//
//  String style; // americano, demitasse, espresso, etc.
//
//  boolean isIced; // whether it's cold or hot
//
//  Coffee(String variety, ILoString mixins, String style, boolean isIced) {
//    super(variety + " " + style, 0, mixins);
//    this.isIced = isIced;
//    this.variety = variety;
//    this.style = style;
//  }
//
//  /*  TEMPLATE 
//   Fields:
//   ... this.variety ...              -- String
//   ... this.style ...                -- String
//   ... this.taste ...                -- String
//   ... this.isIced ...               -- boolean
//   ... this.ingredient ...           -- ILoString
//   Methods:
//   ... this.isDecaf() ...                  -- boolean 
//   ... this.containsIngredient(String) ... -- boolean
//   ... this.format() ...                   -- String
//   Methods for fields:
//   ... this.ingredient.isEmpty() ...       -- boolean 
//   ... this.ingredient.contains(String) ...-- boolean 
//   ... this.ingredient.toString() ...      -- String 
//   */
//
//  //Determines if this beverage is de-caffeinated
//  public boolean isDecaf() {
//    return false;
//  }
//
//  //Produces a String showing how this beverage would appear on a menu
//  public String format() {
//    String format;
//    if (this.isIced) {
//      format = "Iced " + this.taste;
//    }
//    else {
//      format = "Hot " + this.taste;
//    }
//    if (this.ingredient.isEmpty()) {
//      return format + " (without mixins)";
//    }
//    else {
//      return format + " (with " + this.ingredient.toString() + ")";
//    }
//  }
//}
//
//// Represents an ice-cream-based blended drink
//class Milkshake extends ABeverage {
//  
//  String flavor;  // vanilla, mint-chip, strawberry, etc.
//
//  String brandName; // Ben&Jerrys, JPLicks, etc.
//
//  Milkshake(String flavor, ILoString toppings, String brandName, int size) {
//    super(brandName + " " + flavor, size, toppings);
//    this.flavor = flavor;
//    this.brandName = brandName;
//  }
//
//  /*  TEMPLATE 
//   Fields:
//   ... this.flavor ...               -- String
//   ... this.brandName ...            -- String
//   ... this.taste ...                -- String
//   ... this.size ...                 -- int
//   ... this.ingredient ...           -- ILoString
//   Methods:
//   ... this.isDecaf() ...                  -- boolean 
//   ... this.containsIngredient(String) ... -- boolean
//   ... this.format() ...                   -- String
//   Methods for fields:
//   ... this.ingredient.isEmpty() ...       -- boolean 
//   ... this.ingredient.contains(String) ...-- boolean 
//   ... this.ingredient.toString() ...      -- String 
//   */
//
//  //Determines if this beverage is de-caffeinated
//  public boolean isDecaf() {
//    return true;
//  }
//  
//  
//}
//
//// lists of strings
//interface ILoString {
//
//  /*  TEMPLATE 
//  Fields:
//  
//  Methods:
//  ... this.isEmpty() ...       -- boolean 
//  ... this.contains(String) ...-- boolean 
//  ... this.toString() ...      -- String 
//  Methods for fields:
//  
//  */
//  
//  // check whether the list contains specific string
//  boolean contains(String target);
//
//  // check whether the string is empty or not
//  boolean isEmpty();
//
//  // turn the list of string to one string and use comma to separate 
//  // each string
//  String toString();
//}
//
//// an empty list of strings
//class MtLoString implements ILoString {
//
//  /*  TEMPLATE 
//   Fields:
//   
//   Methods:
//   ... this.isEmpty() ...       -- boolean 
//   ... this.contains(String) ...-- boolean 
//   ... this.toString() ...      -- String 
//   Methods for fields:
//   
//   */
//
//  // check whether the list contains specific string
//  public boolean contains(String target) {
//    return false;
//  }
//
//  // check whether the string is empty or not
//  public boolean isEmpty() {
//    return true;
//  }
//
//  // turn the list of string to one string and use comma to separate 
//  // each string
//  public String toString() {
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
//  /*  TEMPLATE 
//   Fields:
//   ... this.first ...                -- String
//   ... this.rest ...                 -- ILoString
//   Methods:
//   ... this.isEmpty() ...            -- boolean 
//   ... this.contains(String) ...     -- boolean 
//   ... this.toString() ...           -- String 
//   Methods for fields:
//   ... this.rest.isEmpty() ...       -- boolean 
//   ... this.rest.contains(String) ...-- boolean 
//   ... this.rest.toString() ...      -- String 
//   
//   */
//
//  // check whether the list contains specific string
//  public boolean contains(String target) {
//    return this.first == target || this.rest.contains(target);
//  }
//
//  // check whether the string is empty or not
//  public boolean isEmpty() {
//    return false;
//  }
//
//  // turn the list of string to one string and use comma to separate 
//  // each string
//  public String toString() {
//    if (this.rest.isEmpty()) {
//      return this.first;
//    }
//    else {
//      return this.first + ", " + this.rest.toString();
//    }
//  }
//}
//
//// represent some example beverages
//class ExamplesBeverages {
//  ILoString empty = new MtLoString();
//
//  IBeverage bt0 = new BubbleTea("Black tea", new ConsLoString("boba", empty), 16);
//  IBeverage bt1 = new BubbleTea("Rooibos",
//      new ConsLoString("boba", new ConsLoString("milk", empty)), 16);
//
//  IBeverage c0 = new Coffee("Arabica", new ConsLoString("cream", empty), "americano", true);
//  IBeverage c1 = new Coffee("Robusta", new ConsLoString("cream", new ConsLoString("sugar", empty)),
//      "espresso", false);
//
//  IBeverage m0 = new Milkshake("vanilla", new ConsLoString("whipped cream", empty), "Ben&Jerrys",
//      16);
//  IBeverage m1 = new Milkshake("mint-chip",
//      new ConsLoString("whipped cream", new ConsLoString("sprinkles", empty)), "JPLicks", 16);
//
//  boolean testIsDecaf(Tester t) {
//    return t.checkExpect(bt0.isDecaf(), false) && t.checkExpect(bt1.isDecaf(), true)
//        && t.checkExpect(c0.isDecaf(), false) && t.checkExpect(m0.isDecaf(), true);
//  }
//
//  boolean testContainsIngredient(Tester t) {
//    return t.checkExpect(bt0.containsIngredient("boba"), true)
//        && t.checkExpect(c0.containsIngredient("cream"), true)
//        && t.checkExpect(m0.containsIngredient("whipped cream"), true)
//        && t.checkExpect(bt0.containsIngredient("milk"), false)
//        && t.checkExpect(m0.containsIngredient("sugar"), false)
//        && t.checkExpect(c0.containsIngredient("sprinkles"), false);
//  }
//
//  boolean testFormat(Tester t) {
//    return t.checkExpect(bt0.format(), "16oz Black tea (with boba)")
//        && t.checkExpect(c0.format(), "Iced Arabica americano (with cream)")
//        && t.checkExpect(m0.format(), "16oz Ben&Jerrys vanilla (with whipped cream)")
//        && t.checkExpect(bt1.format(), "16oz Rooibos (with boba, milk)")
//        && t.checkExpect(m1.format(), "16oz JPLicks mint-chip (with whipped cream, sprinkles)")
//        && t.checkExpect(c1.format(), "Hot Robusta espresso (with cream, sugar)");
//  }
//}