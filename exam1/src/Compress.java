import tester.Tester;

// a list of type T
interface IList<T> {

  // compresses this list using run length encoding
  IList<Counted<T>> compress();

  IList<Counted<T>> compressHelp(T elem, int count);

}

// a empty list of type T
class Empty<T> implements IList<T> {

  public IList<Counted<T>> compress() {
    return new Empty<Counted<T>>();
  }

  public IList<Counted<T>> compressHelp(T elem, int count) {
    return new Cons<Counted<T>>(new Counted<T>(count, elem), new Empty<Counted<T>>());
  }

}

// a non empty list of type T
class Cons<T> implements IList<T> {
  T first;
  IList<T> rest;

  public Cons(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }

  public IList<Counted<T>> compress() {
    return this.compressHelp(this.first, 0);
  }

  public IList<Counted<T>> compressHelp(T elem, int count) {
    if (this.first.equals(elem)) {
      return this.rest.compressHelp(elem, count + 1);
    }
    return new Cons<Counted<T>>(new Counted<T>(count, elem), this.compress());
  }// bbbcd

}

// a counted value
class Counted<T> {
  int count;
  T value;

  public Counted(int count, T value) {
    this.count = count;
    this.value = value;
  }

}

// compress
// makeList() -> makeList()
// makeList("a") -> makeList(new Counted<String>(1, "a"))
// makeList("a", "a") -> makeList(New Counted<String>(2, "a"))
// makeList("a", "b") -> makeList(New Counted<String>(1, "a"), new Counted<String>(1, "b"))

// countElem("a")
// makeList() -> 0
// makeList("a") -> 1
// makeList("a", "a") -> 2
// makeList("b") -> 0
// makeList("a", "b") -> 1

// removeAll("a")
// makeList() -> makeList()
// makeList("a") -> makeList()
// makeList("a", "a") -> makeList()
// makeList("b") -> makeList("b")
// makeList("a", "b") -> makeList("b")
// aaabaaa

class CompressExamples {

  Empty<String> empty = new Empty<>();
  Cons<String> letters = new Cons<String>("a",
      new Cons<String>("a", new Cons<String>("b", new Cons<String>("b",
          new Cons<String>("b", new Cons<String>("c", new Cons<String>("d", empty)))))));

  void test(Tester t) {
    t.checkExpect(new Empty<String>().compress(), new Empty<>());
    t.checkExpect(letters.compress(), null);
  }
}
