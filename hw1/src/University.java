import tester.*;

// Represents a University
class University {
  String name;
  String city;
  int studentSize;
  String mostPopularMajor;
  double averageGPA;
  boolean hasCoop;

  // University constructor
  public University(String name, String city, int studentSize, String mostPopularMajor,
      double averageGPA, boolean hasCoop) {
    this.name = name;
    this.city = city;
    this.studentSize = studentSize;
    this.mostPopularMajor = mostPopularMajor;
    this.averageGPA = averageGPA;
    this.hasCoop = hasCoop;
  }
}

// Examples for the class that represents Universities
class ExamplesUniversity {
  University yale = new University("Yale", "New Haven", 14567, "economics", 4.14, false);
  University neu = new University("Northeastern", "Boston", 19940, "engineering", 4.04, true);
  University harvard = new University("Harvard", "Cambridge", 24596, "social sciences", 4.20,
      false);

  boolean testUniversities(Tester t) {
    return t.checkExpect(neu.mostPopularMajor, "engineering");
  }
}