import java.awt.Color;
import javalib.funworld.*;
import javalib.worldcanvas.WorldCanvas;
import javalib.worldimages.*;
import tester.Tester;

// represent a bolt
interface ILightningBolt {

  // draw the bolt
  WorldImage draw();

  // calculate the total current in the bolt
  int totalCurrent();

  // check whether the bolt is physically possible, meaning the bottom bolt always
  // have
  // larger current than the top
  boolean isPhysicallyPossible();

  // rotate this bolt counter-clockwise at given degree (counter-clockwise base on
  // positive x axis)
  ILightningBolt rotate(double degree);

  // combine two bolt into one. Link them with the given length, capacity, and
  // angel between
  // the bolt and positive x axis
  ILightningBolt combine(int leftLength, int rightLength, int leftCapacity, int rightCapacity,
      double leftTheta, double rightTheta, ILightningBolt otherBolt);

  // get the x value at the left-most point in the bolt
  double getLeft();

  // get the x value at the right-most point in the bolt
  double getRight();

  // calculate the total width in the bolt by use the x value of right-most point
  // to minus the x value of the left-most point
  double getWidth();
}

// represents one final end point of a lightning bolt
class Tip implements ILightningBolt {

  /*
   * Template Fields:
   * 
   * Methods: this.draw() -- WorldImage this.totalCurrent() -- int
   * this.isPhysicallyPossiable() -- boolean this.rotate(double degree) --
   * ILightningBolt this.combine(int leftLength, int rightLength, int
   * leftCapacity, int rightCapacity, double leftTheta, double rightTheta,
   * ILightningBolt otherBolt) -- ILightningBolt this.getLeft() -- double
   * this.getRight() -- double this.getWidth() -- double
   */

  // draw the bolt
  public WorldImage draw() {
    return new CircleImage(5, OutlineMode.SOLID, Color.YELLOW);
  }

  // calculate the total current in the bolt
  public int totalCurrent() {
    return 0;
  }

  // check whether the bolt is physically possible, meaning the bottom bolt always
  // have
  // larger current than the top
  public boolean isPhysicallyPossible() {
    return true;
  }

  // rotate this bolt counter-clockwise at given degree (counter-clockwise base on
  // positive x axis)
  public ILightningBolt rotate(double degree) {
    return this;
  }

  // combine two bolt into one. Link them with the given length, capacity, and
  // angel between
  // the bolt and positive x axis
  public ILightningBolt combine(int leftLength, int rightLength, int leftCapacity,
      int rightCapacity, double leftTheta, double rightTheta, ILightningBolt otherBolt) {
    return new Fork(leftLength, rightLength, leftCapacity, rightCapacity, leftTheta % 360,
        rightTheta % 360, this, otherBolt.rotate(rightTheta));
  }

  // get the x value at the left-most point in the bolt
  public double getLeft() {
    return 0;
  }

  // get the x value at the right-most point in the bolt
  public double getRight() {
    return 0;
  }

  // calculate the total width in the bolt by use the x value of right-most point
  // to minus the x value of the left-most point
  public double getWidth() {
    return 0;
  }
}

// represents a straight section of a lightning bolt
class Segment implements ILightningBolt {

  /*
   * Template Fields: this.length -- int this.current -- int this.theta -- double
   * this.bolt -- ILightningBolt
   * 
   * Methods: this.draw() -- WorldImage this.totalCurrent() -- int
   * this.isPhysicallyPossiable() -- boolean this.rotate(double degree) --
   * ILightningBolt this.combine(int leftLength, int rightLength, int
   * leftCapacity, int rightCapacity, double leftTheta, double rightTheta,
   * ILightningBolt otherBolt) -- ILightningBolt this.getLeft() -- double
   * this.getRight() -- double this.getWidth() -- double
   */

  // How long this piece of the bolt is is
  int length;
  // The electric current carried in this part of the bolt, measured in
  // kilo-Amperes
  int current;
  // The angle (in degrees) of this flow, relative to the +x axis
  double theta;
  // The rest of the lightning bolt
  ILightningBolt bolt;

  Segment(int length, int current, double theta, ILightningBolt bolt) {
    this.length = length;
    this.current = current;
    this.theta = theta;
    this.bolt = bolt;
  }

  // draw the bolt
  public WorldImage draw() {
    WorldImage segmentImage = new RectangleImage(current / 2, length, OutlineMode.SOLID,
        Color.BLUE);

    segmentImage = new RotateImage(segmentImage, -theta - 90);

    segmentImage = segmentImage.movePinhole(length * Math.cos(Math.toRadians(theta)) / 2,
        -length * Math.sin(Math.toRadians(theta)) / 2);

    segmentImage = new OverlayOffsetAlign(AlignModeX.PINHOLE, AlignModeY.PINHOLE, bolt.draw(), 0, 0,
        segmentImage);

    segmentImage = segmentImage.movePinhole(-length * Math.cos(Math.toRadians(theta)),
        length * Math.sin(Math.toRadians(theta)));

    return segmentImage;
  }

  // calculate the total current in the bolt
  public int totalCurrent() {
    return current + bolt.totalCurrent();
  }

  // check whether the bolt is physically possible, meaning the bottom bolt always
  // have
  // larger current than the top
  public boolean isPhysicallyPossible() {
    return current >= bolt.totalCurrent();
  }

  // rotate this bolt counter-clockwise at given degree (counter-clockwise base on
  // positive x axis)
  public ILightningBolt rotate(double degree) {
    return new Segment(length, current, theta + degree - 90, bolt.rotate(degree));
  }

  // combine two bolt into one. Link them with the given length, capacity, and
  // angel between
  // the bolt and positive x axis
  public ILightningBolt combine(int leftLength, int rightLength, int leftCapacity,
      int rightCapacity, double leftTheta, double rightTheta, ILightningBolt otherBolt) {
    return new Fork(leftLength, rightLength, leftCapacity, rightCapacity, leftTheta % 360,
        rightTheta % 360, this.rotate(leftTheta), otherBolt.rotate(rightTheta));
  }

  // get the x value at the left-most point in the bolt
  public double getLeft() {
    return Math.min(bolt.getLeft() + Math.cos(Math.toRadians(theta)) * length, 0.0);
  }

  // get the x value at the right-most point in the bolt
  public double getRight() {
    return Math.max(bolt.getRight() + Math.cos(Math.toRadians(theta)) * length, 0.0);
  }

  // calculate the total width in the bolt by use the x value of right-most point
  // to minus the x value of the left-most point
  public double getWidth() {
    return this.getRight() - this.getLeft();
  }
}

// represents the lightning bolt splitting in two
class Fork implements ILightningBolt {

  /*
   * Template Fields: this.leftLength -- int this.rightLength -- int
   * this.leftCurrent -- int this.rightCurrent -- int this.leftTheta -- double
   * this.rightTheta -- double this.left -- ILightningBolt this.right --
   * ILightningBolt
   * 
   * Methods: this.draw() -- WorldImage this.totalCurrent() -- int
   * this.isPhysicallyPossiable() -- boolean this.rotate(double degree) --
   * ILightningBolt this.combine(int leftLength, int rightLength, int
   * leftCapacity, int rightCapacity, double leftTheta, double rightTheta,
   * ILightningBolt otherBolt) -- ILightningBolt this.getLeft() -- double
   * this.getRight() -- double this.getWidth() -- double
   * 
   */

  // How long the left and right branches are
  int leftLength;
  int rightLength;
  // The electric current in each part of the bolt, measured in kilo-Amperes
  int leftCurrent;
  int rightCurrent;
  // The angle (in degrees) of the two branches, relative to the +x axis,
  double leftTheta;
  double rightTheta;
  // The remaining parts of the lightning bolt
  ILightningBolt left;
  ILightningBolt right;

  Fork(int leftLength, int rightLength, int leftCurrent, int rightCurrent, double leftTheta,
      double rightTheta, ILightningBolt left, ILightningBolt right) {
    this.leftLength = leftLength;
    this.rightLength = rightLength;
    this.leftCurrent = leftCurrent;
    this.rightCurrent = rightCurrent;
    this.leftTheta = leftTheta;
    this.rightTheta = rightTheta;
    this.left = left;
    this.right = right;
  }

  // draw the bolt
  public WorldImage draw() {
    WorldImage leftImage = new RectangleImage(leftCurrent / 2, leftLength, OutlineMode.SOLID,
        Color.BLUE);
    leftImage = new RotateImage(leftImage, -leftTheta + 90);

    leftImage = leftImage.movePinhole(leftLength * Math.cos(Math.toRadians((-leftTheta))) / 2,
        leftLength * Math.sin(Math.toRadians((-leftTheta))) / 2);

    leftImage = new OverlayOffsetAlign(AlignModeX.PINHOLE, AlignModeY.PINHOLE, left.draw(), 0, 0,
        leftImage);

    leftImage = leftImage.movePinhole(-leftLength * Math.cos(Math.toRadians((-leftTheta))),
        -leftLength * Math.sin(Math.toRadians((-leftTheta))));

    WorldImage rightImage = new RectangleImage(rightCurrent / 2, rightLength, OutlineMode.SOLID,
        Color.BLUE);

    rightImage = new RotateImage(rightImage, -rightTheta + 90);

    rightImage = rightImage.movePinhole(rightLength * Math.cos(Math.toRadians((-rightTheta))) / 2,
        rightLength * Math.sin(Math.toRadians((-rightTheta))) / 2);

    rightImage = new OverlayOffsetAlign(AlignModeX.PINHOLE, AlignModeY.PINHOLE, right.draw(), 0, 0,
        rightImage);

    rightImage = rightImage.movePinhole(-rightLength * Math.cos(Math.toRadians((-rightTheta))),
        -rightLength * Math.sin(Math.toRadians((-rightTheta))));

    return new OverlayOffsetAlign(AlignModeX.PINHOLE, AlignModeY.PINHOLE, leftImage, 0, 0,
        rightImage);
  }

  // calculate the total current in the bolt
  public int totalCurrent() {
    return leftCurrent + rightCurrent + left.totalCurrent() + right.totalCurrent();
  }

  // check whether the bolt is physically possible, meaning the bottom bolt always
  // have
  // larger current than the top
  public boolean isPhysicallyPossible() {
    return leftCurrent >= left.totalCurrent() && rightCurrent >= right.totalCurrent();
  }

  // rotate this bolt counter-clockwise at given degree (counter-clockwise base on
  // positive x axis)
  public ILightningBolt rotate(double degree) {
    return new Fork(leftLength, rightLength, leftCurrent, rightCurrent, leftTheta + degree - 90,
        rightTheta + degree - 90, left.rotate(degree), right.rotate(degree));
  }

  // combine two bolt into one. Link them with the given length, capacity, and
  // angel between
  // the bolt and positive x axis
  public ILightningBolt combine(int leftLength, int rightLength, int leftCapacity,
      int rightCapacity, double leftTheta, double rightTheta, ILightningBolt otherBolt) {
    return new Fork(leftLength, rightLength, leftCapacity, rightCapacity, leftTheta % 360,
        rightTheta % 360, this.rotate(leftTheta), otherBolt.rotate(rightTheta));
  }

  // get the x value at the left-most point in the bolt
  public double getLeft() {
    return Math.min(
        Math.min(left.getLeft() + Math.cos(Math.toRadians(leftTheta)) * leftLength,
        right.getLeft() + Math.cos(Math.toRadians(rightTheta)) * rightLength), 0.0);
  }

  // get the x value at the right-most point in the bolt
  public double getRight() {
    return Math.max(Math.max(left.getRight() + Math.cos(Math.toRadians(leftTheta)) * leftLength,
        right.getRight() + Math.cos(Math.toRadians(rightTheta)) * rightLength), 0.0);
  }

  // calculate the total width in the bolt by use the x value of right-most point
  // to minus the x value of the left-most point
  public double getWidth() {
    return this.getRight() - this.getLeft();
  }
}

class ExamplesLightingBolt {
  ILightningBolt TIP = new Tip();
  ILightningBolt BOLT1 = new Fork(30, 30, 10, 10, 135, 40, TIP, TIP);
  ILightningBolt SEGMENT1 = new Segment(40, 10, 90, BOLT1);
  ILightningBolt BOLT2 = new Fork(30, 30, 10, 10, 115, 65, TIP, TIP);
  ILightningBolt BOLT3 = new Fork(30, 30, 10, 10, 115, 65, SEGMENT1, TIP);
  ILightningBolt SEGMENT2 = new Segment(50, 80, 10, BOLT1);

  boolean testIsPhysicallyPossible(Tester t) {
    return t.checkExpect(TIP.isPhysicallyPossible(), true)
        && t.checkExpect(BOLT1.isPhysicallyPossible(), true)
        && t.checkExpect(BOLT3.isPhysicallyPossible(), false)
        && t.checkExpect(SEGMENT1.isPhysicallyPossible(), false)
        && t.checkExpect(SEGMENT2.isPhysicallyPossible(), true);
  }

  boolean testGetWidth(Tester t) {
    return t.checkInexact(TIP.getWidth(), 0.0, 0.001)
        && t.checkInexact(BOLT1.getWidth(),
            Math.cos(Math.toRadians(45)) * 30 + Math.cos(Math.toRadians(40)) * 30, 0.001)
        && t.checkInexact(SEGMENT2.getWidth(),
            Math.cos(Math.toRadians(10)) * 50 + Math.cos(Math.toRadians(40)) * 30, 0.001);
  }

  boolean testCombine(Tester t) {
    return t.checkExpect(TIP.combine(30, 30, 10, 10, 135, 40, TIP), BOLT1)
        && t.checkExpect(SEGMENT1.combine(30, 30, 10, 10, 135, 40, SEGMENT2),
            new Fork(30, 30, 10, 10, 135, 40,
                new Segment(40, 10, 135, new Fork(30, 30, 10, 10, 180, 85, TIP, TIP)),
                new Segment(50, 80, -40, new Fork(30, 30, 10, 10, 85, -10, TIP, TIP))))
        && t.checkExpect(BOLT1.combine(40, 50, 10, 10, 150, 30, BOLT2),
            new Fork(40, 50, 10, 10, 150, 30, new Fork(30, 30, 10, 10, 195, 100, TIP, TIP),
                new Fork(30, 30, 10, 10, 55, 5, TIP, TIP)));
  }

  boolean testDrawBolt(Tester t) {
    WorldCanvas c = new WorldCanvas(500, 500);
    WorldScene s = new WorldScene(500, 500);
    return c.drawScene(
        s.placeImageXY(BOLT2.combine(30, 30, 10, 10, 135, 40, BOLT3).draw(), 250, 250)) && c.show();
  }
}