import java.awt.Color;
import javalib.funworld.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;
import tester.Tester;

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
}

interface ICellObject {

  // stacks this cell object with another given
  ICellObject stackWith(ICellObject other);

  // stacks this cell object with another given ground object
  ICellObject stackWithGround(AGround other);

  // stacks this cell object with another given content object
  ICellObject stackWithContent(AContent other);
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
}

// a cell that contains a player
class Player extends AContent {
  String facingDirection;

  public Player(String facingDirection) {
    super();
    this.facingDirection = facingDirection;
  }

}

// a cell that contains a trophy
class Trophy extends AContent {
  String color;

  public Trophy(String color) {
    super();
    this.color = color;
  }
}

// a cell that contains a wall
class Wall extends AContent {

  public Wall() {
    super();
  }

}

// a cell that contains a box
class Box extends AContent {

  public Box() {
    super();
  }

}

//a cell that contains a target on the ground
class Target extends AGround {
  String color;

  public Target(String color) {
    super();
    this.color = color;
  }

}

// -----------------------------------

// the state of a sokoban board
class BoardState {
  // TODO
}

// a sokoban level
class Level {
  IList<IList<Cell>> grid;

  public Level(String groundDescription, String contentsDescription) {

  }

}

// a level description
interface ILevelDescription {

  // converts a given cell description to a cell
  Cell cellDescriptionToCell(String s);

  // converts a row description to a row of cells
  IList<Cell> rowDescriptionToRow(String row);

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

// ------------------------
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

}

// an empty list of type T
class MtList<T> implements IList<T> {

}

// a non empty list of T
class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;

  public ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }

}

// ------------------------------------------

class SokobanExamples {
  WorldImage circle = new CircleImage(100, OutlineMode.SOLID, Color.red);
  WorldImage box = new FromFileImage("./src/assets/box.png");

  String exampleLevelGround = "________\n" + "___R____\n" + "________\n" + "_B____Y_\n"
      + "________\n" + "___G____\n" + "________";
  String exampleLevelContents = "__WWW___\n" + "__W_WW__\n" + "WWWr_WWW\n" + "W_b>yB_W\n"
      + "WW_gWWWW\n" + "_WW_W___\n" + "__WWW___";

  void testDraw(Tester t) {
    WorldCanvas c = new WorldCanvas(500, 500);
    WorldScene s = new WorldScene(500, 500);
    c.drawScene(s.placeImageXY(box, 0, 0));
    c.show();
  }

  // ----------------------------------

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
    t.checkException(IllegalArgumentException, null, exampleLevelContents, null);
    
    t.checkExpect(new Cell(target, blank).stackCell(new Cell(target, trophy)),
        new Cell(target, trophy));

    


  }

  // test level descriptions -------------------------------------------------
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

}