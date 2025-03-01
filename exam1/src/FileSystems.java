import tester.Tester;
import tester.Tester.*;

abstract class AFileSystemItem {
  String name;

  AFileSystemItem(String name) {
    this.name = name;
  }

  abstract boolean doesntFoobar();

  // produces a list of all the paths to all the items with the given name
  abstract ILoString pathTo(String name);

  // produces a list of all the paths to all the items with the given name in the
  // given accumulated directory

  // 1. accumulates the directories you've entered
  // 2. starts with empty string
  // 3. string appends the directory name onto the accumulated path so far
  // 4. append all the accumulated paths into a list of string
  // TERMIN: eventually will have to reach a base case: a file or a empty directory
  abstract ILoString pathToHelp(String name, String acc);
}

class File extends AFileSystemItem {
  String contents;

  File(String name, String contents) {
    super(name);
    this.contents = contents;
  }

  public boolean doesntFoobar() {
    return true;
  }

  // produces a list of all the paths to all the items with the given name
  ILoString pathTo(String name) {
    return pathToHelp(name, "");

  }

  ILoString pathToHelp(String name, String acc) {
    MtLoString mt = new MtLoString();

    if (this.name.equals(name)) {
      return new ConsLoString(acc + this.name, mt);
    }
    return mt;
  }

}

class Directory extends AFileSystemItem {
  ILoFSItem contents;

  Directory(String name, ILoFSItem contents) {
    super(name);
    this.contents = contents;
  }

  public boolean doesntFoobar() {
    return this.contents.doesntFoobar();
  }

  ILoString pathTo(String name) {
    return pathToHelp(name, "");
  }

  ILoString pathToHelp(String name, String acc) {

    String pathAcc = acc + this.name + "/";

    if (this.name.equals(name)) {
      return new ConsLoString(pathAcc, new MtLoString())
          .append(this.contents.pathToHelp(name, pathAcc));
    }

    return this.contents.pathToHelp(name, pathAcc);
  }

}

interface ILoFSItem {
  boolean doesntFoobar();

  boolean doesntFoobarHelp(String foobar);

  ILoString pathToHelp(String name, String acc);
}

class MtLoFSItem implements ILoFSItem {
  public boolean doesntFoobar() {
    return true;
  }

  public boolean doesntFoobarHelp(String foobar) {
    return true;
  }

  public ILoString pathToHelp(String name, String acc) {
    return new MtLoString();
  }
}

class ConsLoFSItem implements ILoFSItem {
  AFileSystemItem first;
  ILoFSItem rest;

  ConsLoFSItem(AFileSystemItem first, ILoFSItem rest) {
    this.first = first;
    this.rest = rest;
  }

  public boolean doesntFoobar() {
    // bug
    // return this.doesntFoobarHelp(this.first.name);
    return this.first.doesntFoobar() && this.rest.doesntFoobarHelp(this.first.name);
  }

  public boolean doesntFoobarHelp(String foobar) {
    return (!this.first.name.equals(foobar) && this.rest.doesntFoobarHelp(foobar))
        && this.first.doesntFoobar() && this.rest.doesntFoobar();
  }

  public ILoString pathToHelp(String name, String acc) {
    return this.first.pathToHelp(name, acc).append(this.rest.pathToHelp(name, acc));
  }

}

interface ILoString {

  ILoString append(ILoString tail);

}

class MtLoString implements ILoString {

  public ILoString append(ILoString tail) {
    return tail;
  }

}

class ConsLoString implements ILoString {
  String first;
  ILoString rest;

  public ConsLoString(String first, ILoString rest) {
    this.first = first;
    this.rest = rest;
  }

  public ILoString append(ILoString tail) {
    return new ConsLoString(this.first, this.rest.append(tail));
  }

}

// Assumptions:
// all names are unique?
// name exists?

// pathTo("a")

// 1 file
// new File("a", "contents") -> makeList("a")

// directory
// new Directory("a", makeList()) -> makeList("a/")

// directory w file
// new Directory("dirName", makeList(new File("a", ""))) -> makeList("dirName/a")

// directory w 2 files
// new Directory("dirName", makeList(new File("a", ""), new File("b", ""))) -> makeList("dirName/a")

// 2 layer directory
// new Directory("1", makeList(new Directory("2", ""), new File("a", ""))) -> makeList("1/2/a")

// directory and file
//new Directory("a", makeList(new File("a", ""))) -> makeList("a/", "a/a")

class FileSystemExample {
  MtLoFSItem mt = new MtLoFSItem();
  ILoFSItem uniqueFiles = new ConsLoFSItem(new File("a", "contents"),
      new ConsLoFSItem(new File("b", "contents"), mt));
  ILoFSItem nonUniqueFiles = new ConsLoFSItem(new File("a", "contents"),
      new ConsLoFSItem(new File("a", "contents"), mt));
  AFileSystemItem dirWithNonUnique = new Directory("name", nonUniqueFiles);
  AFileSystemItem dirWithUnique = new Directory("name", uniqueFiles);

  ILoFSItem nested = new ConsLoFSItem(dirWithNonUnique,
      new ConsLoFSItem(new File("a", "contents"), mt));
  ILoFSItem nested2 = new ConsLoFSItem(new File("a", "contents"),
      new ConsLoFSItem(dirWithUnique, mt));

  // does not contain duplicate names within the same directory
  // uniqueNames

  void test(Tester t) {
//    t.checkExpect(nested.doesntFoobar(), null);
  }

  void testPath(Tester t) {
    t.checkExpect(new File("a", "").pathTo("a"), null);
    t.checkExpect(new File("b", "").pathTo("a"), null);
    t.checkExpect(new Directory("a", new MtLoFSItem()).pathTo("a"), null);
    t.checkExpect(
        new Directory("a", new ConsLoFSItem(new File("a", ""), new MtLoFSItem())).pathTo("a"),
        null);
    t.checkExpect(
        new Directory("a", new ConsLoFSItem(new File("b", ""), new MtLoFSItem())).pathTo("a"),
        null);
    t.checkExpect(new Directory("a",
        new ConsLoFSItem(new Directory("b", new ConsLoFSItem(new File("a", ""), new MtLoFSItem())),
            new MtLoFSItem()))
        .pathTo("a"), null);

  }

}
