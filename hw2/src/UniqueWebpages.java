//Represents a web page

import tester.Tester;

//Represents a web page
class Webpage {
  String name;
  ILoContent content;

  public Webpage(String name, ILoContent content) {
    this.name = name;
    this.content = content;
  }

  // Template

  // Fields:
  // this.name - String
  // this.content - ILoContent

  // Methods:
  // this.totalCredits() - int
  // this.totalSize - double
  // this.pictureInfo() - String
  // this.visitedContent(ILoContent acc) - ILoContent

  // Methods for Fields:
  // this.content.size() - double
  // this.content.pictureInfo() - String
  // this.content.visitedContent(ILoContent acc) - ILoContent

  // Computes the total number of credits it costs to build this website
  int totalCredits() {
    return ((int) Math.ceil(this.totalSize())) * 50;
  }

  // Computes the total number of megabytes for this Webpage
  double totalSize() {
    return this.visitedContent(new MtLoContent()).size();
  }

  // Produces one String that has the title of all pictures reachable from this
  // webpage, with their description in parentheses, and each separated by comma
  // and space
  String pictureInfo() {
    return this.visitedContent(new MtLoContent()).pictureInfo();
  }

  // Produces a list of all the visited content with no duplicates
  ILoContent visitedContent(ILoContent acc) {
    return content.visitedContent(acc);
  }
  // TODO
  // 1. represents the accumulated list of unique contents visited
  // 2. starts with the empty list
  // 3. we cons the first content of every content in a list of content onto the
  // acc if the acc doesn't already contain such content (we have not visited such
  // content)
  // 4. when we reach the empty list (base case), we return the list of contents
  // visited with no duplicates which we can use for total credits and picture
  // info
}

// Represents content
interface IContent {

  // Template
  // Methods
  // this.sameName(IContent content) - boolean
  // this.hasName(String name) - boolean
  // this.size() - double
  // this.pictureInfo - String
  // this.visitedContent(ILoContent acc) - ILoContent

  // Determines if this content has the same name as another content
  boolean sameName(IContent content);

  // Determines if this content has a given name
  boolean hasName(String name);

  // Computes the total number of megabytes for this content
  double size();

  // Produces the picture info of this content
  String pictureInfo();

  // Produces a list of all the visited content in this content with no duplicates
  ILoContent visitedContent(ILoContent acc);
}

// Represents a list of Content
interface ILoContent {

  // Determines if this list of contents contains a content
  boolean contains(IContent content);

  // Computes the total number of megabytes for this list of contents
  double size();

  // Produces the picture info of this list of contents
  String pictureInfo();

  // Produces a list of all the visited content in this list of contents with no
  // duplicates
  ILoContent visitedContent(ILoContent acc);

}

// Represents a empty list of Content
class MtLoContent implements ILoContent {

  // Determines if this empty list of contents contains a content
  public boolean contains(IContent content) {
    return false;
  }

  // Computes the total number of megabytes for this empty list of contents
  public double size() {
    return 0;
  }

  // Produces the picture info of this empty list of contents
  public String pictureInfo() {
    return "";
  }

  // Produces a list of all the visited content in this empty list of content with
  // no duplicates
  public ILoContent visitedContent(ILoContent acc) {
    return acc;
  }

}

// Represents a cons with a first Content and the rest of the list of contents
class ConsLoContent implements ILoContent {
  IContent first;
  ILoContent rest;

  public ConsLoContent(IContent first, ILoContent rest) {
    this.first = first;
    this.rest = rest;
  }

  // Determines if this non-empty list of contents contains a content
  public boolean contains(IContent content) {
    return this.first.sameName(content) || this.rest.contains(content);
  }

  // Computes the total number of megabytes for this first content and this rest
  // of the list of contents
  public double size() {
    return this.first.size() + this.rest.size();
  }

  // Produces the picture info of this first content and this rest of the list of
  // contents
  public String pictureInfo() {
    if (this.first.pictureInfo().equals("")) {
      return this.rest.pictureInfo();
    }
    else if (this.rest.pictureInfo().equals("")) {
      return this.first.pictureInfo();
    }
    else {
      return this.first.pictureInfo() + ", " + this.rest.pictureInfo();
    }
  }

  // Produces a list of all the visited content in this non-empty list of contents
  // with no duplicates
  public ILoContent visitedContent(ILoContent acc) {

    if (acc.contains(this.first)) {
      // skip this item
      return this.rest.visitedContent(acc);
    }

    // add this item to the acc list
    return this.first.visitedContent(this.rest.visitedContent(acc));
  }
}

// Represents text
class Text implements IContent {
  String name;
  int numLines;
  boolean inMarkdown;

  public Text(String name, int numLines, boolean inMarkdown) {
    this.name = name;
    this.numLines = numLines;
    this.inMarkdown = inMarkdown;
  }

  // Template
  // Fields
  // this.name - String
  // this.numLines - int
  // this.inMarkdown - boolean

  // Methods
  // this.sameName(IContent content) - boolean
  // this.hasName(String name) - boolean
  // this.size() - double
  // this.pictureInfo - String
  // this.visitedContent(ILoContent acc) - ILoContent

  // Methods for fields
  // this.name.equals(String name)
  //

  // Determines if this text content has the same name as another content
  public boolean sameName(IContent content) {
    return content.hasName(this.name);
  }

  // Determines if this text content has a given name
  public boolean hasName(String name) {
    return this.name.equals(name);
  }

  // Computes the total number of megabytes for this text
  public double size() {
    return 0;
  }

  // Produces the picture info of this text
  public String pictureInfo() {
    return "";
  }

  // Produces a list of all the visited content in this text content with no
  // duplicates
  public ILoContent visitedContent(ILoContent acc) {
    return new ConsLoContent(this, acc);
  }

}

// Represents a picture
class Picture implements IContent {
  String name;
  String description;
  double megabytes;

  public Picture(String name, String description, double megabytes) {
    this.name = name;
    this.description = description;
    this.megabytes = megabytes;
  }

  // Template
  // Fields
  // this.name - String
  // this.description - String
  // this.megabytes - double

  // Methods
  // this.sameName(IContent content) - boolean
  // this.hasName(String name) - boolean
  // this.size() - double
  // this.pictureInfo - String
  // this.visitedContent(ILoContent acc) - ILoContent

  // Methods for fields
  // this.name.equals(String name)
  //

  // Determines if this picture content has the same name as another content
  public boolean sameName(IContent content) {
    return content.hasName(this.name);
  }

  // Determines if this picture content has a given name
  public boolean hasName(String name) {
    return this.name.equals(name);
  }

  // Computes the total number of megabytes for this picture
  public double size() {
    return this.megabytes;
  }

  // Produces the picture info of this picture
  public String pictureInfo() {
    return this.name + " (" + this.description + ")";
  }

  // Produces a list of all the visited content in this picture content with no
  // duplicates
  public ILoContent visitedContent(ILoContent acc) {
    return new ConsLoContent(this, acc);
  }

}

// Represents a hyperlink
class Hyperlink implements IContent {
  String text;
  Webpage destination;

  public Hyperlink(String text, Webpage destination) {
    this.text = text;
    this.destination = destination;
  }

  // Template
  // Fields
  // this.text - String
  // this.destination - Webpage

  // Methods
  // this.sameName(IContent content) - boolean
  // this.hasName(String name) - boolean
  // this.size() - double
  // this.pictureInfo - String
  // this.visitedContent(ILoContent acc) - ILoContent

  // Methods for fields
  // this.destination.totalSize() - double
  // this.destination.pictureInfo() - String
  // this.text.equals(String name)
  //

  // Determines if this hyperlink content has the same name as another content
  public boolean sameName(IContent content) {
    return content.hasName(this.text);
  }

  // Determines if this text content has a given name
  public boolean hasName(String name) {
    return this.text.equals(name);
  }

  // Computes the total number of megabytes for this hyperlink
  public double size() {
    return this.destination.totalSize();
  }

  // Produces the picture info of this hyperlink
  public String pictureInfo() {
    return this.destination.pictureInfo();
  }

  // Produces a list of all the visited content in this hyperlink content with no
  // duplicates
  public ILoContent visitedContent(ILoContent acc) {
    return this.destination.visitedContent(acc);
  }

}

class ExamplesWebpages {

  // Assignment 1
  ILoContent assignment1Content = new ConsLoContent(
      new Picture("Submission", "submission screenshot", 13.7), new MtLoContent());

  Webpage assignment1WP = new Webpage("Assignment 1", assignment1Content);

  // Syllabus
  ILoContent syllabusContent = new ConsLoContent(new Picture("Java", "HD Java logo", 4),
      new ConsLoContent(new Text("Week 1", 10, true),
          new ConsLoContent(new Hyperlink("First Assignment", assignment1WP), new MtLoContent())));

  Webpage syllabusWP = new Webpage("Syllabus", syllabusContent);

  // Assignments
  ILoContent assignmentsContent = new ConsLoContent(new Text("Pair Programming", 10, false),
      new ConsLoContent(new Text("Expectations", 15, false),
          new ConsLoContent(new Hyperlink("First Assignment", assignment1WP), new MtLoContent())));

  Webpage assignmentsWP = new Webpage("Assignments", assignmentsContent);

  // Fundies 2 Homepage
  ILoContent fundies2HomepageContent = new ConsLoContent(new Text("Course Goals", 5, true),
      new ConsLoContent(new Text("Instructor Contact", 1, false), new ConsLoContent(
          new Picture("Eclipse", "Eclipse logo", 0.13),
          new ConsLoContent(new Picture("Coding Background", "digital rain from the Matrix", 30.2),
              new ConsLoContent(new Hyperlink("Course Syllabus", syllabusWP), new ConsLoContent(
                  new Hyperlink("Course Assignments", assignmentsWP), new MtLoContent()))))));

  Webpage homepage = new Webpage("Fundies 2 Homepage", fundies2HomepageContent);

  boolean testGetName(Tester t) {
    IContent txt = new Text("txt", 10, true);
    IContent pic = new Picture("pic", "o", 10);
    IContent hl = new Hyperlink("hl", homepage);

    return t.checkExpect(txt.hasName("txt"), true) && t.checkExpect(pic.hasName("pic"), true)
        && t.checkExpect(hl.hasName("hl"), true);
  }

  boolean testEquals(Tester t) {
    IContent c = new Text("aaa", 10, true);
    return t.checkExpect(c.sameName(new Text("aaa", 20, true)), true);
  }

  boolean testContains(Tester t) {
    return t.checkExpect(assignment1Content.contains(new Picture("aaa", "aaaa", 12)), false)
        && t.checkExpect(
            assignment1Content.contains(new Picture("Submission", "submission screenshot", 13.7)),
            true);
  }

  boolean testTotalCredits(Tester t) {
    // 13.7 + 4 + (0.13 + 30.2) => 49 MB => 2450 credits
    return t.checkExpect(homepage.totalCredits(), 2450)
        // 13.7 + 4 = 18 => 900 credits
        && t.checkExpect(syllabusWP.totalCredits(), 900)
        // 13.7 = 14 => 700 credits
        && t.checkExpect(assignmentsWP.totalCredits(), 700)
        && t.checkExpect(assignment1WP.totalCredits(), 700);
  }

  boolean testPictureInfo(Tester t) {
    return t.checkExpect(homepage.pictureInfo(),
        "Eclipse (Eclipse logo), " + "Coding Background (digital rain from the Matrix), "
            + "Java (HD Java logo), " + "Submission (submission screenshot)");
  }
}