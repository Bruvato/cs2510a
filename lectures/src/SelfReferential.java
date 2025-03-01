
class Book {
  String title;
  String author;
  int year;
}

interface ILoBook {

  int count();

}

class MtLoBook implements ILoBook {

  public int count() {
    return 0;
  }

}

class ConsLoBook implements ILoBook {
  Book first;
  ILoBook rest;

  public ConsLoBook(Book first, ILoBook rest) {
    this.first = first;
    this.rest = rest;
  }

  public int count() {
    return 1 + this.rest.count();
  }

}
