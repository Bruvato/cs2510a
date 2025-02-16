import java.awt.Color;
import javalib.funworld.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;
import tester.Tester;
import java.util.function.*;

// a cell 
class Cell {
  ICellObject ground;
  ICellObject content;

  // initializes a cell with a given ground object and content object
  public Cell(ICellObject ground, ICellObject content) {
    this.ground = ground;
    this.content = content;
  }

  // initializes a cell with a given ground object
  public Cell(AGround ground) {
    this.ground = ground;
    this.content = new Blank();
  }

  // initializes a cell with a given content object
  public Cell(AContent content) {
    this.ground = new Blank();
    this.content = content;
  }

  // initializes a empty cell
  public Cell() {
    this.ground = new Blank();
    this.content = new Blank();
  }

  Cell stackCell(Cell cell) {
    return new Cell(this.ground.stackWith(cell.ground), this.content.stackWith(cell.content));
  }

  // converts this cell to an image
  WorldImage cellToImage() {
    return new OverlayImage(this.content.cellObjToImage(), this.ground.cellObjToImage());
  }
}

interface ICellObject {

  // stacks this cell object with another given
  ICellObject stackWith(ICellObject other);

  // stacks this cell object with another given ground object
  ICellObject stackWithGround(AGround other);

  // stacks this cell object with another given content object
  ICellObject stackWithContent(AContent other);

  // converts this cell object to an image
  WorldImage cellObjToImage();
}

// a ground object in a cell
abstract class AGround implements ICellObject {

  // stacks this ground object with a given cell object
  public ICellObject stackWith(ICellObject other) {
    return other.stackWithGround(this);
  }

  // stacks this ground object with a given ground object
  public ICellObject stackWithGround(AGround other) {
    throw new IllegalArgumentException(
        "invalid stack: cannot stack this gound object with an existing ground object");
  }

  // stacks this ground object with a given content object
  public ICellObject stackWithContent(AContent other) {
    throw new IllegalArgumentException(
        "invalid stack: cannot stack this gound object with an existing content object");
  }

  // converts this ground cell object to an image
  public abstract WorldImage cellObjToImage();

}

// a content object in a cell
abstract class AContent implements ICellObject {

  // stacks this content object with a given cell object
  public ICellObject stackWith(ICellObject other) {
    return other.stackWithContent(this);
  }

  // stacks this content object with a given ground object
  public ICellObject stackWithGround(AGround other) {
    throw new IllegalArgumentException(
        "invalid stack: cannot stack this content object with an existing ground object");
  }

  // stacks this content object with a given content object
  public ICellObject stackWithContent(AContent other) {
    throw new IllegalArgumentException(
        "invalid stack: cannot stack this content object with an existing content object");
  }

  // converts this content cell object to an image
  public abstract WorldImage cellObjToImage();

}

// a blank cell object
class Blank implements ICellObject {

  // stacks this blank object with a given cell object
  public ICellObject stackWith(ICellObject other) {
    return other;
  }

  // stacks this blank object with a given ground object
  public ICellObject stackWithGround(AGround other) {
    return other;
  }

  // stacks this blank object with a given content object
  public ICellObject stackWithContent(AContent other) {
    return other;
  }

  // converts this blank cell object to an image
  public WorldImage cellObjToImage() {
    return new RectangleImage(120, 120, OutlineMode.OUTLINE, Color.black).movePinhole(-60, -60);
  }
}

// a cell that contains a player
class Player extends AContent {
  String facingDirection;

  public Player(String facingDirection) {
    super();
    this.facingDirection = facingDirection;
  }

  // converts this player cell object to an image
  public WorldImage cellObjToImage() {
    return new FromFileImage("./src/assets/player.png").movePinhole(-60, -60);
  }

}

// a cell that contains a trophy
class Trophy extends AContent {
  String color;

  public Trophy(String color) {
    super();
    this.color = color;
  }

  // converts this trophy cell object to an image
  public WorldImage cellObjToImage() {
    return new FromFileImage("./src/assets/trophy_" + this.color + ".png").movePinhole(-60, -60);
  }
}

// a cell that contains a wall
class Wall extends AContent {

  public Wall() {
    super();
  }

  // converts this wall cell object to an image
  public WorldImage cellObjToImage() {
    return new FromFileImage("./src/assets/wall.png").movePinhole(-60, -60);
  }

}

// a cell that contains a box
class Box extends AContent {

  public Box() {
    super();
  }

  // converts this box cell object to an image
  public WorldImage cellObjToImage() {
    return new FromFileImage("./src/assets/box.png").movePinhole(-60, -60);
  }

}

//a cell that contains a target on the ground
class Target extends AGround {
  String color;

  public Target(String color) {
    super();
    this.color = color;
  }

  // converts this target cell object to an image
  public WorldImage cellObjToImage() {
    Color color;
    switch (this.color) {
    case "blue":
      return new CircleImage(60, OutlineMode.OUTLINE, Color.BLUE).movePinhole(-60, -60);
    case "green":
      return new CircleImage(60, OutlineMode.OUTLINE, Color.GREEN).movePinhole(-60, -60);
    case "red":
      return new CircleImage(60, OutlineMode.OUTLINE, Color.RED).movePinhole(-60, -60);
    case "yellow":
      return new CircleImage(60, OutlineMode.OUTLINE, Color.YELLOW).movePinhole(-60, -60);
    }
    return new CircleImage(60, OutlineMode.OUTLINE, Color.BLACK).movePinhole(-60, -60);
  }

}

// --------------LEVEL---------------------

// a sokoban level
class Level {
  IList<IList<Cell>> grid;

  // configures this level given the ground and content level description strings
  public Level(String groundDescription, String contentsDescription) {
    IList<IList<Cell>> groundLevel = new GroundLevelDescription()
        .gridDescriptionToGrid(groundDescription);
    IList<IList<Cell>> contentsLevel = new ContentLevelDescription()
        .gridDescriptionToGrid(contentsDescription);

    this.grid = groundLevel.parallel(new StackCells(), contentsLevel);
  }

  // draws this level
  WorldImage draw() {
    return this.grid.map(new CellsToImages()).map(new ImagesToImage()).foldr(new AboveImages(),
        new EmptyImage());
  }

}

// a level description
interface ILevelDescription {

  // converts a given cell description to a cell
  Cell cellDescriptionToCell(String s);

  // converts a row description to a row of cells
  IList<Cell> rowDescriptionToRow(String row);

  // converts a given grid description to a grid of cells
  IList<IList<Cell>> gridDescriptionToGrid(String grid);

}

// a level description
abstract class ALevelDescription implements ILevelDescription {

  // converts a given row description to a row of cells
  public IList<Cell> rowDescriptionToRow(String row) {
    String firstRow = row.substring(0, 1);
    String restRow = row.substring(1);
    Cell cell = this.cellDescriptionToCell(firstRow);

    if (restRow.equals("") || restRow.equals("\n")) {
      return new ConsList<Cell>(cell, new MtList<Cell>());
    }

    return new ConsList<Cell>(cell, this.rowDescriptionToRow(restRow));
  }

  // converts a given grid description to a grid of cells
  public IList<IList<Cell>> gridDescriptionToGrid(String grid) {
    int i = grid.indexOf("\n");
    if (i < 0) {
      return new ConsList<IList<Cell>>(this.rowDescriptionToRow(grid), new MtList<IList<Cell>>());
    }

    String firstGrid = grid.substring(0, i + 1);
    String restRow = grid.substring(i + 1);

    return new ConsList<IList<Cell>>(this.rowDescriptionToRow(firstGrid),
        this.gridDescriptionToGrid(restRow));
  }
}

// a ground level description
class GroundLevelDescription extends ALevelDescription {

  // converts a given ground cell description to a cell
  public Cell cellDescriptionToCell(String s) {
    switch (s) {
    case "Y":
      return new Cell(new Target("yellow"));
    case "G":
      return new Cell(new Target("green"));
    case "B":
      return new Cell(new Target("blue"));
    case "R":
      return new Cell(new Target("red"));
    case "_":
      return new Cell();
    }
    throw new IllegalArgumentException("invalid ground cell description");
  }

}

// a content level description
class ContentLevelDescription extends ALevelDescription {

  // converts a given content cell description to a cell
  public Cell cellDescriptionToCell(String s) {
    switch (s) {
    case "y":
      return new Cell(new Trophy("yellow"));
    case "g":
      return new Cell(new Trophy("green"));
    case "b":
      return new Cell(new Trophy("blue"));
    case "r":
      return new Cell(new Trophy("red"));
    case "W":
      return new Cell(new Wall());
    case "B":
      return new Cell(new Box());
    case ">":
      return new Cell(new Player("right"));
    case "<":
      return new Cell(new Player("left"));
    case "^":
      return new Cell(new Player("up"));
    case "v":
      return new Cell(new Player("down"));
    case "_":
      return new Cell();
    }
    throw new IllegalArgumentException("invalid content cell description");
  }

}

// ---------------FUNCTION OBJECTS-------------------------

// a function object that stacks two given cells
class StackCell implements BiFunction<Cell, Cell, Cell> {
  // stacks two given cells
  public Cell apply(Cell ground, Cell content) {
    return ground.stackCell(content);
  }
}

// a function object that stacks two given list of cells
class StackCells implements BiFunction<IList<Cell>, IList<Cell>, IList<Cell>> {
  // stacks two given list of cells
  public IList<Cell> apply(IList<Cell> ground, IList<Cell> content) {
    return ground.parallel(new StackCell(), content);
  }
}

// a function object that converts a cell to a image
class CellToImage implements Function<Cell, WorldImage> {
  // converts a cell to a image
  public WorldImage apply(Cell cell) {
    return cell.cellToImage();
  }
}

//a function object that converts a list of cells to a list of images
class CellsToImages implements Function<IList<Cell>, IList<WorldImage>> {
  // converts a list of cells to a list of images
  public IList<WorldImage> apply(IList<Cell> cells) {
    return cells.map(new CellToImage());
  }
}

// a function object that converts a list of images to an image via beside images
class ImagesToImage implements Function<IList<WorldImage>, WorldImage> {
  // converts a list of images to an image via beside images
  public WorldImage apply(IList<WorldImage> images) {
    return images.foldr(new BesideImages(), new EmptyImage());
  }
}

// a function object that aligns a list of images horizontally
class BesideImages implements BiFunction<WorldImage, WorldImage, WorldImage> {
  // aligns a list of images horizontally
  public WorldImage apply(WorldImage current, WorldImage acc) {
    return new BesideImage(current, acc);
  }
}

//a function object that aligns a list of images vertically
class AboveImages implements BiFunction<WorldImage, WorldImage, WorldImage> {
  // aligns a list of images vertically
  public WorldImage apply(WorldImage current, WorldImage acc) {
    return new AboveImage(current, acc);
  }
}

// ------------- HELPERS / UTLITY--------------
class Posiion {
  int row;
  int col;

  public Posiion(int row, int col) {
    this.row = row;
    this.col = col;
  }

}

// a list of type T
interface IList<T> {

  // maps through this list of T to produce a list of U
  <U> IList<U> map(Function<T, U> f);

  // applies foldr through this list of T to produce a U
  <U> U foldr(BiFunction<T, U, U> f, U base);

  // produces a new list of T by applying a binary function to corresponding
  // elements of this list of T and another list of T in parallel
  IList<T> parallel(BiFunction<T, T, T> f, IList<T> list);

  // produces a new list of T by applying a binary function to corresponding
  // elements of this list of T and another empty list of T in parallel
  IList<T> parallel(BiFunction<T, T, T> f, MtList<T> list);

  // produces a new list of T by applying a binary function to corresponding
  // elements of this list of T and another non empty list of T in parallel
  IList<T> parallel(BiFunction<T, T, T> f, ConsList<T> list);

}

// an empty list of type T
class MtList<T> implements IList<T> {

  // maps through this empty list of T to produce a list of U
  public <U> IList<U> map(Function<T, U> f) {
    return new MtList<U>();
  }

  // applies foldr through this empty list of T to produce a U
  public <U> U foldr(BiFunction<T, U, U> f, U base) {
    return base;
  }

  // produces a new list of T by applying a binary function to corresponding
  // elements of this empty list of T and another list of T in parallel
  public IList<T> parallel(BiFunction<T, T, T> f, IList<T> list) {
    return list.parallel(f, this);
  }

  // produces a new list of T by applying a binary function to corresponding
  // elements of this empty list of T and another empty list of T in parallel
  public IList<T> parallel(BiFunction<T, T, T> f, MtList<T> list) {
    return this;
  }

  // produces a new list of T by applying a binary function to corresponding
  // elements of this empty list of T and another non empty list of T in parallel
  public IList<T> parallel(BiFunction<T, T, T> f, ConsList<T> list) {
    throw new IllegalArgumentException("invalid lists: lists cannot be of different size");
  }

}

// a non empty list of T
class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;

  public ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }

  // maps through this non empty list of T to produce a list of U
  public <U> IList<U> map(Function<T, U> f) {
    return new ConsList<U>(f.apply(this.first), this.rest.map(f));
  }

  // applies foldr through this non empty list of T to produce a U
  public <U> U foldr(BiFunction<T, U, U> f, U base) {
    return f.apply(this.first, this.rest.foldr(f, base));
  }

  // produces a new list of T by applying a binary function to corresponding
  // elements of this non empty list of T and another list of T in parallel
  public IList<T> parallel(BiFunction<T, T, T> f, IList<T> list) {
    return list.parallel(f, this);
  }

  // produces a new list of T by applying a binary function to corresponding
  // elements of this non empty list of T and another empty list of T in parallel
  public IList<T> parallel(BiFunction<T, T, T> f, MtList<T> list) {
    throw new IllegalArgumentException("invalid lists: lists cannot be of different size");
  }

  // produces a new list of T by applying a binary function to corresponding
  // elements of this non empty list of T and another non empty list of T in
  // parallel
  public IList<T> parallel(BiFunction<T, T, T> f, ConsList<T> list) {
    return new ConsList<T>(f.apply(this.first, list.first), this.rest.parallel(f, list.rest));
  }

}

// ------------------------------------------

class SokobanExamples {
  WorldImage circle = new CircleImage(100, OutlineMode.SOLID, Color.red);
  WorldImage wall = new VisiblePinholeImage(new Wall().cellObjToImage());

  String exampleLevelGround = "________\n" + "___R____\n" + "________\n" + "_B____Y_\n"
      + "________\n" + "___G____\n" + "________";
  String exampleLevelContents = "__WWW___\n" + "__W_WW__\n" + "WWWr_WWW\n" + "W_b>yB_W\n"
      + "WW_gWWWW\n" + "_WW_W___\n" + "__WWW___";

  static final int WIDTH = 1000;
  static final int HEIGHT = 1000;

  void testDraw(Tester t) {
    WorldCanvas c = new WorldCanvas(WIDTH, HEIGHT);
    WorldScene s = new WorldScene(WIDTH, HEIGHT);

    Level level = new Level(exampleLevelGround, exampleLevelContents);
    WorldImage levelImg = level.draw();

    c.drawScene(s.placeImageXY(levelImg, WIDTH / 2, HEIGHT / 2));
    c.show();
  }

  // ---------------- test level descriptions
  // -------------------------------------------------
  GroundLevelDescription groundLevelDescription = new GroundLevelDescription();
  ContentLevelDescription contentLevelDescription = new ContentLevelDescription();

  void testCellDescriptionToCell(Tester t) {
    // ground
    t.checkExpect(groundLevelDescription.cellDescriptionToCell("Y"),
        new Cell(new Target("yellow")));
    t.checkExpect(groundLevelDescription.cellDescriptionToCell("_"), new Cell());
    // content
    t.checkExpect(contentLevelDescription.cellDescriptionToCell("g"),
        new Cell(new Trophy("green")));
    t.checkExpect(contentLevelDescription.cellDescriptionToCell("W"), new Cell(new Wall()));
    t.checkExpect(contentLevelDescription.cellDescriptionToCell("B"), new Cell(new Box()));
    t.checkExpect(contentLevelDescription.cellDescriptionToCell(">"),
        new Cell(new Player("right")));
  }

  void testRowDescriptionToRow(Tester t) {
    // ground
    t.checkExpect(groundLevelDescription.rowDescriptionToRow("_B____Y_\n"),
        new ConsList<Cell>(new Cell(),
            new ConsList<Cell>(new Cell(new Target("blue")),
                new ConsList<Cell>(new Cell(),
                    new ConsList<Cell>(new Cell(),
                        new ConsList<Cell>(new Cell(),
                            new ConsList<Cell>(new Cell(),
                                new ConsList<Cell>(new Cell(new Target("yellow")),
                                    new ConsList<Cell>(new Cell(), new MtList<Cell>())))))))));
    // content
    t.checkExpect(contentLevelDescription.rowDescriptionToRow("W_b>yB_W\n"),
        new ConsList<Cell>(new Cell(new Wall()),
            new ConsList<Cell>(new Cell(), new ConsList<Cell>(new Cell(new Trophy("blue")),
                new ConsList<Cell>(new Cell(new Player("right")),
                    new ConsList<Cell>(new Cell(new Trophy("yellow")),
                        new ConsList<Cell>(new Cell(new Box()), new ConsList<Cell>(new Cell(),
                            new ConsList<Cell>(new Cell(new Wall()), new MtList<Cell>())))))))));
  }

  void testGridDescriptionToGrid(Tester t) {
    // ground
    t.checkExpect(groundLevelDescription.gridDescriptionToGrid("_R\n" + "G_"),
        new ConsList<>(
            new ConsList<>(new Cell(), new ConsList<>(new Cell(new Target("red")), new MtList<>())),
            new ConsList<>(new ConsList<>(new Cell(new Target("green")),
                new ConsList<>(new Cell(), new MtList<>())), new MtList<>())));

    // content
    t.checkExpect(
        contentLevelDescription.gridDescriptionToGrid("Wr_\n"
            + ">yB"),
        new ConsList<>(new ConsList<>(new Cell(new Wall()),
            new ConsList<>(new Cell(new Trophy("red")),
                new ConsList<>(new Cell(), new MtList<>()))),
            new ConsList<>(new ConsList<>(new Cell(new Player("right")),
                new ConsList<>(new Cell(new Trophy("yellow")),
                    new ConsList<>(new Cell(new Box()), new MtList<>()))),
                new MtList<>())));
  }

  // --------------- test stack cell ----------------------------------
  void testStackCell(Tester t) {
    ICellObject blank = new Blank();
    ICellObject target = new Target("blue");
    ICellObject trophy = new Trophy("blue");
    // blank + blank
    t.checkExpect(new Cell(blank, blank).stackCell(new Cell(blank, blank)), new Cell(blank, blank));
    // ground + blank
    t.checkExpect(new Cell(target, blank).stackCell(new Cell(blank, blank)),
        new Cell(target, blank));
    // content + blank
    t.checkExpect(new Cell(blank, trophy).stackCell(new Cell(blank, blank)),
        new Cell(blank, trophy));
    // content + ground
    t.checkExpect(new Cell(target, trophy).stackCell(new Cell(blank, blank)),
        new Cell(target, trophy));
    t.checkExpect(new Cell(target, blank).stackCell(new Cell(blank, trophy)),
        new Cell(target, trophy));
    // invalid
    t.checkException(
        new IllegalArgumentException(
            "invalid stack: cannot stack this gound object with an existing ground object"),
        new Cell(target, blank), "stackCell", new Cell(target, trophy));

    // stack rows
    t.checkExpect(
        groundLevelDescription.rowDescriptionToRow("B_").parallel(new StackCell(),
            contentLevelDescription.rowDescriptionToRow("bW")),
        new ConsList<Cell>(new Cell(new Target("blue"), new Trophy("blue")),
            new ConsList<Cell>(new Cell(new Wall()), new MtList<Cell>())));
    // stack grid
    t.checkExpect(
        groundLevelDescription.gridDescriptionToGrid("B_\n" + "_R").parallel(new StackCells(),
            contentLevelDescription.gridDescriptionToGrid("bW\n" + ">B")),
        new ConsList<>(
            new ConsList<>(new Cell(new Target("blue"), new Trophy("blue")),
                new ConsList<>(new Cell(new Blank(), new Wall()), new MtList<>())),
            new ConsList<>(
                new ConsList<>(new Cell(new Blank(), new Player("right")),
                    new ConsList<>(new Cell(new Target("red"), new Box()), new MtList<>())),
                new MtList<>())));
  }

  // --------------- test parallel

  // a function object that computes the sum of two integers
  class Addition implements BiFunction<Integer, Integer, Integer> {
    // computes the sum of two given integers
    public Integer apply(Integer t, Integer u) {
      return t + u;
    }
  }

  void testParallel(Tester t) {
    IList<Integer> mt = new MtList<Integer>();
    IList<Integer> list0 = new ConsList<Integer>(1,
        new ConsList<Integer>(2, new ConsList<Integer>(3, mt)));
    IList<Integer> list1 = new ConsList<Integer>(4,
        new ConsList<Integer>(5, new ConsList<Integer>(6, mt)));
    IList<Integer> list2 = new ConsList<Integer>(4, new ConsList<Integer>(5, mt));
    Addition add = new Addition();

    // empty + empty
    t.checkExpect(mt.parallel(add, mt), mt);
    // empty + non empty
    t.checkException(
        new IllegalArgumentException("invalid lists: lists cannot be of different size"), mt,
        "parallel", add, list0);
    // same size
    t.checkExpect(list0.parallel(add, list1),
        new ConsList<Integer>(5, new ConsList<Integer>(7, new ConsList<Integer>(9, mt))));
    // different size
    t.checkException(
        new IllegalArgumentException("invalid lists: lists cannot be of different size"), list1,
        "parallel", add, list2);

  }
}