//import tester.*;
//
////Represents a web page
//class Webpage {
//  String name;
//  ILoContent content;
//
//  public Webpage(String name, ILoContent content) {
//    this.name = name;
//    this.content = content;
//  }
//
//  // Template
//
//  // Fields:
//  // this.name - String
//  // this.content - ILoContent
//
//  // Methods:
//  // this.totalCredits() - int
//  // this.totalSize - double
//  // this.pictureInfo() - String
//
//  // Methods for Fields:
//  // this.content.size() - double
//  // this.content.pictureInfo() - String
//
//  // Computes the total number of credits it costs to build this website
//  int totalCredits() {
//    return ((int) Math.ceil(this.totalSize())) * 50;
//  }
//
//  // Computes the total number of megabytes for this Webpage
//  double totalSize() {
//    return this.content.size();
//  }
//
//  // Produces one String that has the title of all pictures reachable from this
//  // webpage, with their description in parentheses, and each separated by comma
//  // and space
//  String pictureInfo() {
//    return content.pictureInfo();
//  }
//
//}
//
//// Represents content
//interface IContent {
//
//  // Template
//  // Methods
//  // this.size() - double
//  // this.pictureInfo - String
//  //
//
//  // Computes the total number of megabytes for this content
//  public double size();
//
//  // Produces the picture info of this content
//  public String pictureInfo();
//}
//
//// Represents a list of Content
//interface ILoContent {
//
//  // Template
//  // Methods:
//  // size() - double
//  // pictureInfo() - String
//
//  // Computes the total number of megabytes for this list of contents
//  public double size();
//
//  // Produces the picture info of this list of contents
//  public String pictureInfo();
//
//}
//
//// Represents a empty list of Content
//class MtLoContent implements ILoContent {
//
//  // Template
//  // Methods:
//  // size() - double
//  // pictureInfo() - String
//
//  // Computes the total number of megabytes for this empty list of contents
//  public double size() {
//    return 0;
//  }
//
//  // Produces the picture info of this empty list of contents
//  public String pictureInfo() {
//    return "";
//  }
//
//}
//
//// Represents a cons with a first Content and the rest of the list of contents
//class ConsLoContent implements ILoContent {
//  IContent first;
//  ILoContent rest;
//
//  public ConsLoContent(IContent first, ILoContent rest) {
//    this.first = first;
//    this.rest = rest;
//  }
//
//  // Template
//  // Fields:
//  // this.first IContent
//  // this.rest - ILoContent
//
//  // Methods:
//  // size() - double
//  // pictureInfo() - String
//
//  // Methods on fields:
//  // this.first.size() - double
//  // this.first.pictureInfo() - string
//  // this.rest.size() - double
//  // this.rest.pictureInfo() - String
//
//  // Computes the total number of megabytes for this first content and this rest
//  // of the list of contents
//  public double size() {
//    return this.first.size() + this.rest.size();
//  }
//
//  // Produces the picture info of this first content and this rest of the list of
//  // contents
//  public String pictureInfo() {
//    if (this.first.pictureInfo().equals("")) {
//      return this.rest.pictureInfo();
//    }
//    else if (this.rest.pictureInfo().equals("")) {
//      return this.first.pictureInfo();
//    }
//    else {
//      return this.first.pictureInfo() + ", " + this.rest.pictureInfo();
//    }
//
//  }
//
//}
//
//// Represents text
//class Text implements IContent {
//  String name;
//  int numLines;
//  boolean inMarkdown;
//
//  public Text(String name, int numLines, boolean inMarkdown) {
//    this.name = name;
//    this.numLines = numLines;
//    this.inMarkdown = inMarkdown;
//  }
//
//  // Template
//  // Fields:
//  // this.name - String
//  // this.numLines - int
//  // this.inMarkdown - boolean
//
//  // Methods:
//  // size() - double
//  // pictureInfo() - String
//
//  // Computes the total number of megabytes for this text
//  public double size() {
//    return 0;
//  }
//
//  // Produces the picture info of this text
//  public String pictureInfo() {
//    return "";
//  }
//}
//
//// Represents a picture
//class Picture implements IContent {
//  String name;
//  String description;
//  double megabytes;
//
//  public Picture(String name, String description, double megabytes) {
//    this.name = name;
//    this.description = description;
//    this.megabytes = megabytes;
//  }
//
//  // Template
//  // Fields:
//  // this.name - String
//  // this.description - String
//  // this.megabytes - double
//
//  // Methods:
//  // size() - double
//  // pictureInfo() - String
//
//  // Computes the total number of megabytes for this picture
//  public double size() {
//    return this.megabytes;
//  }
//
//  // Produces the picture info of this picture
//  public String pictureInfo() {
//    return this.name + " (" + this.description + ")";
//  }
//}
//
//// Represents a hyperlink
//class Hyperlink implements IContent {
//  String text;
//  Webpage destination;
//
//  public Hyperlink(String text, Webpage destination) {
//    this.text = text;
//    this.destination = destination;
//  }
//
//  // Template
//  // Fields:
//  // this.text - String
//  // this.destination - Webpage
//
//  // Methods:
//  // size() - double
//  // pictureInfo() - String
//
//  // Methods for fields:
//  // this.destination.totalSize() - double
//  // this.destination.pictureInfo() - String
//
//  // Computes the total number of megabytes for this hyperlink
//  public double size() {
//    return this.destination.totalSize();
//  }
//
//  // Produces the picture info of this hyperlink
//  public String pictureInfo() {
//    return this.destination.pictureInfo();
//  }
//
//}
//
//class ExamplesWebpages {
//
//  // TODO
//  // Describe (in English, or in a diagram, or in code...)
//  // a website that has at least one text section, two pictures,
//  // three webpages, and four hyperlinks. It should be different from the one
//  // below.
//
//  // A personal website with four webpages: Home, About, Blog, Contact.
//  // Each webpage consists of the following content:
//  // a body of text with 10 lines in markdown,
//  // two 1.8MB pictures of you,
//  // and four hyperlinks which redirects to the other web pages
//
//  // Assignment 1
//  ILoContent assignment1Content = new ConsLoContent(
//      new Picture("Submission", "submission screenshot", 13.7), new MtLoContent());
//
//  Webpage assignment1WP = new Webpage("Assignment 1", assignment1Content);
//
//  // Syllabus
//  ILoContent syllabusContent = new ConsLoContent(new Picture("Java", "HD Java logo", 4),
//      new ConsLoContent(new Text("Week 1", 10, true),
//          new ConsLoContent(new Hyperlink("First Assignment", assignment1WP), new MtLoContent())));
//
//  Webpage syllabusWP = new Webpage("Syllabus", syllabusContent);
//
//  // Assignments
//  ILoContent assignmentsContent = new ConsLoContent(new Text("Pair Programming", 10, false),
//      new ConsLoContent(new Text("Expectations", 15, false),
//          new ConsLoContent(new Hyperlink("First Assignment", assignment1WP), new MtLoContent())));
//
//  Webpage assignmentsWP = new Webpage("Assignments", assignmentsContent);
//
//  // Fundies 2 Homepage
//  ILoContent fundies2HomepageContent = new ConsLoContent(new Text("Course Goals", 5, true),
//      new ConsLoContent(new Text("Instructor Contact", 1, false), new ConsLoContent(
//          new Picture("Eclipse", "Eclipse logo", 0.13),
//          new ConsLoContent(new Picture("Coding Background", "digital rain from the Matrix", 30.2),
//              new ConsLoContent(new Hyperlink("Course Syllabus", syllabusWP), new ConsLoContent(
//                  new Hyperlink("Course Assignments", assignmentsWP), new MtLoContent()))))));
//
//  Webpage homepage = new Webpage("Fundies 2 Homepage", fundies2HomepageContent);
//
//  boolean testTotalCredits(Tester t) {
//    return t.checkExpect(homepage.totalCredits(), 3100)
//        && t.checkExpect(syllabusWP.totalCredits(), 900)
//        && t.checkExpect(assignmentsWP.totalCredits(), 700)
//        && t.checkExpect(assignment1WP.totalCredits(), 700);
//  }
//
//  boolean testPictureInfo(Tester t) {
//    return t.checkExpect(homepage.pictureInfo(),
//        "Eclipse (Eclipse logo), " + "Coding Background (digital rain from the Matrix), "
//            + "Java (HD Java logo), " + "Submission (submission screenshot), "
//            + "Submission (submission screenshot)");
//
//  }
//
//  // TODO
//  // Make sure you have at least one example whose that demonstrates the expected
//  // behavior, and leave a comment explaining why the answer is the way it should
//  // be.)
//
//  // for homepage:
//  // 13.7 + 13.7 + 4 + 0.13 + 30.2 => 62 MB => 2450 credits
//  // we first total the number of megabytes for all the pictures
//  // then round up to the nearest whole number
//  // and then multiply by 50 to get the credits
//
//  // TODO
//  // In a comment in your file, explain why some methods double-count some
//  // information. Also tell us if there are any other methods in your code where
//  // this duplication occurs.
//
//  // some methods double count because we are visiting already-visited webpages
//  // via hyper links. for example, total credits and picture info both double
//  // count because both the Syllabus and Assignments webpages have hyper links
//  // that both point to the assignment 1 webpage, thus leading us to visit
//  // assignment 1 twice and double count assignment 1's contents
//}
