import tester.Tester;

// Represents a dye recipe
class DyeRecipe {

  double red; // the weight of red dye in grams
  double yellow; // the weight of yellow dye in grams
  double blue; // the weight of blue dye in grams
  double black; // the weight of black dye in grams

  static final double TOLERANCE = 0.001;

  public DyeRecipe(double red, double yellow, double blue, double black) {

    Utils.enforcePositiveWeights(red, yellow, blue, black);
    Utils.enforceConstraints(red, yellow, blue, black);

    this.normalize(red, yellow, blue, black);
  }

  public DyeRecipe(double red, double yellow, double blue) {

    Utils.enforcePositiveWeights(red, yellow, blue, 0);

    double maxBlue;
    if (yellow > 0 && blue > 0.1 * yellow) {
      maxBlue = 0.1 * yellow;
    }
    else {
      maxBlue = blue;
    }

    double nonBlack = red + yellow + maxBlue;
    double maxBlack = nonBlack * 0.05;

    normalize(red, yellow, maxBlue, maxBlack);

  }

  public DyeRecipe(DyeRecipe dr1, DyeRecipe dr2) {

    Utils.enforcePositiveWeights(dr1.red, dr1.yellow, dr1.blue, dr1.black);
    Utils.enforcePositiveWeights(dr2.red, dr2.yellow, dr2.blue, dr2.black);

    double totalRed = dr1.red + dr2.red;
    double totalYellow = dr1.yellow + dr2.yellow;
    double totalBlue = dr1.blue + dr2.blue;
    double totalBlack = dr1.black + dr2.black;

    double totalNonBlack = dr1.red + dr1.yellow + dr1.blue + dr2.red + dr2.yellow + dr2.blue;

    double maxBlack;
    if (totalBlack > 0.05 * totalNonBlack) {
      maxBlack = 0.05 * totalNonBlack;
    }
    else {
      maxBlack = totalBlack;
    }

    normalize(totalRed, totalYellow, totalBlue, maxBlack);

  }

  // TEMPLATE
  //
  // FIELDS
  // ... this.red -- double
  // ... this.yellow ... -- double
  // ... this.blue ... -- double
  // ... this.black ... -- double
  // ... this.TOLERANCE ... -- double
  //
  // METHODS
  // ... this.sameRecipe(DyeRecipe other) ... -- boolean
  // ... this.normalize(double red, double yellow,
  // double blue, double black) ... -- void
  //
  // METHODS FOR FIELDS:
  //
  //

  boolean sameRecipe(DyeRecipe other) {
    return Utils.aproxEqual(this.red, other.red, TOLERANCE)
        && Utils.aproxEqual(this.yellow, other.yellow, TOLERANCE)
        && Utils.aproxEqual(this.blue, other.blue, TOLERANCE)
        && Utils.aproxEqual(this.black, other.black, TOLERANCE);
  }

  void normalize(double red, double yellow, double blue, double black) {

    double total = red + yellow + blue + black;

    this.red = red / total;
    this.yellow = yellow / total;
    this.blue = blue / total;
    this.black = black / total;

  }

  // Represents utility/helper methods for DyeRecipe
  static class Utils {

    // TEMPLATE
    //
    // FIELDS
    //
    // METHODS
    // ... this.enforcePositiveWeights(double red, double yellow,
    // double blue, double black) ... -- void
    // ... this.enforceConstraints(double red, double yellow,
    // double blue, double black) ... -- void
    // ... this.aproxEqual(double a, double b, double tolerance) - boolean
    //
    // METHODS FOR FIELDS:
    //
    //

    // Enforces positive inputs
    static void enforcePositiveWeights(double red, double yellow, double blue, double black) {

      String dyeRecipeStr = "Red: " + red + ", Yellow: " + yellow + ", Blue: " + blue + ", Black: "
          + black;

      if (red < 0 || yellow < 0 || blue < 0 || black < 0) {
        throw new IllegalArgumentException(
            "Invalid dye recipe: " + dyeRecipeStr + " (weights cannot be negative)");
      }

    }

    // Enforces the constraints for a perfect dye recipe
    static void enforceConstraints(double red, double yellow, double blue, double black) {

      String dyeRecipeStr = "Red: " + red + ", Yellow: " + yellow + ", Blue: " + blue + ", Black: "
          + black;
      double nonBlack = red + yellow + blue;

      if (black > 0.05 * nonBlack && nonBlack > 0.1 * black) {
        throw new IllegalArgumentException(
            "Invalid dye recipe: " + dyeRecipeStr + " (black pigment must be"
                + " no more than 5% the weight of the other three colors combined OR"
                + " combined weight of the other three colors must be"
                + " no more than 10% the weight of the black pigment)");
      }

      if (yellow > 0 && blue > 0.1 * yellow) {
        throw new IllegalArgumentException("Invalid dye recipe: " + dyeRecipeStr
            + " (blue must be no more than a tenth of the yellow dye)");
      }
    }

    // Determines if two given doubles are approximately equal within a given
    // tolerance
    static boolean aproxEqual(double a, double b, double tolerance) {
      return Math.abs(a - b) <= tolerance;
    }
  }

}

class ExamplesDyes {
  DyeRecipe dr1 = new DyeRecipe(5.0, 6.5, 0.5);
  DyeRecipe red = new DyeRecipe(10.0, 0, 0, 0);
  DyeRecipe blue = new DyeRecipe(0, 0, 10.0, 0);
  DyeRecipe mix = new DyeRecipe(red, blue);
  DyeRecipe rNb = new DyeRecipe(1.0, 0, 1.0, 0);

  boolean testConstructor(Tester t) {
    return t
        .checkConstructorException(new IllegalArgumentException(
            "Invalid dye recipe: Red: -1.0, Yellow: 1.0, Blue: 1.0, Black: 1.0 "
                + "(weights cannot be negative)"),
            "DyeRecipe", -1.0, 1.0, 1.0, 1.0)
        && t.checkConstructorException(
            new IllegalArgumentException(
                "Invalid dye recipe: Red: 1.0, Yellow: 1.0, Blue: 1.0, Black: 1000.0 "
                    + "(blue must be no more than a tenth of the yellow dye)"),
            "DyeRecipe", 1.0, 1.0, 1.0, 1000.0)
        && t.checkConstructorException(new IllegalArgumentException(
            "Invalid dye recipe: Red: 1.0, Yellow: 10.0, Blue: 0.5, Black: 15.0 "
                + "(black pigment must be no more than 5% the weight of the other three colors "
                + "combined OR combined weight of the other three colors must be no more than 10% "
                + "the weight of the black pigment)"),
            "DyeRecipe", 1.0, 10.0, 0.5, 15.0);
  }

  boolean testSameRecipe(Tester t) {
    return t.checkExpect(dr1.sameRecipe(dr1), true) && t.checkExpect(dr1.sameRecipe(rNb), false)
        && t.checkExpect(mix.sameRecipe(rNb), true);
  }
}
