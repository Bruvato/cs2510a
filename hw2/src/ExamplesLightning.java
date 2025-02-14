//import lightning.*;
import tester.Tester;
import javalib.funworld.WorldScene;
import javalib.worldcanvas.WorldCanvas;
import javalib.worldimages.*;

class ExamplesLightning {
  ILightningBolt TIP = new Tip();
  ILightningBolt BOLT1 = new Fork(30, 20, 10, 10, 135, 40, TIP, TIP);
  ILightningBolt SEGMENT1 = new Segment(40, 10, 90, BOLT1);
  ILightningBolt BOLT2 = new Fork(30, 30, 10, 10, 115, 65, TIP, TIP);
  ILightningBolt BOLT3 = new Fork(30, 9000, 10, 10, 115, 65, SEGMENT1, TIP);
  ILightningBolt SEGMENT2 = new Segment(50, 80, 10, BOLT1);
  ILightningBolt LEFTBOLT = new Fork(30, 30, 10, 10, 135, 100, TIP, TIP);
  ILightningBolt RIGHTBOLT = new Fork(30, 30, 10, 10, 70, 40, TIP, TIP);

  boolean testIsPhysicallyPossible(Tester t) {
    return t.checkExpect(TIP.isPhysicallyPossible(), true)
        && t.checkExpect(BOLT1.isPhysicallyPossible(), true)
        && t.checkExpect(BOLT3.isPhysicallyPossible(), false)
        && t.checkExpect(SEGMENT1.isPhysicallyPossible(), false)
        && t.checkExpect(SEGMENT2.isPhysicallyPossible(), true);
  }

//  boolean testIsPhysicallyPossibleNegative(Tester t) {
//    return t.checkExpect(
//        new Segment(40, -10, 90, new Segment(40, -20, 90, TIP)).isPhysicallyPossible(), true)
//        && t.checkExpect(
//            new Segment(40, -10, 90, new Segment(40, 0, 90, TIP)).isPhysicallyPossible(), false);
//  }

//  boolean testIsPhysicallyPossibleZero(Tester t) {
//    return t.checkExpect(
//        new Segment(40, 0, 90, new Segment(40, 0, 90, TIP)).isPhysicallyPossible(), true);
//  }

  boolean testIsPhysicallyPossibleTip(Tester t) {
    return t.checkExpect(TIP.isPhysicallyPossible(), true);
  }

//  boolean testIsPhysicallyPossibleBolt(Tester t) {
//    return t.checkExpect(BOLT1.isPhysicallyPossible(), true)
//    && t.checkExpect(BOLT1.isPhysicallyPossible(), true);
//  }
  boolean testIsPhysicallyPossibleSegmentTrue(Tester t) {
    return t.checkExpect(SEGMENT2.isPhysicallyPossible(), true);
  }

  boolean testIsPhysicallyPossibleSegmentFalse(Tester t) {
    return t.checkExpect(SEGMENT1.isPhysicallyPossible(), false);
  }
//  boolean testIsPhysicallyPossibleCombine(Tester t) {
//    return t.checkExpect(SEGMENT1.combine(30, 20, 10, 10, 135, 40, SEGMENT2).isPhysicallyPossible()
//        , false);
//  }

  boolean testGetWidth(Tester t) {
    return t.checkInexact(TIP.getWidth(), 0.0, 0.001)
        && t.checkInexact(new Segment(40, 10, 90, TIP).getWidth(), 0.0, 0.001)
        && t.checkInexact(BOLT1.getWidth(),
            -Math.cos(Math.toRadians(135)) * 30 + Math.cos(Math.toRadians(40)) * 20, 0.001)
        && t.checkInexact(LEFTBOLT.getWidth(), -Math.cos(Math.toRadians(135)) * 30, 0.001)
        && t.checkInexact(RIGHTBOLT.getWidth(), Math.cos(Math.toRadians(40)) * 30, 0.001)
        && t.checkInexact(SEGMENT2.getWidth(),
            Math.cos(Math.toRadians(10)) * 50 + Math.cos(Math.toRadians(40)) * 20, 0.001);
  }

  boolean testGetWidthTip(Tester t) {
    return t.checkInexact(TIP.getWidth(), 0.0, 0.001);
  }

  boolean testGetWidthSegment(Tester t) {
    return t.checkInexact(SEGMENT2.getWidth(),
        Math.cos(Math.toRadians(10)) * 50 + Math.cos(Math.toRadians(40)) * 20, 0.001);
  }

  boolean testGetWidthLeftAndRight(Tester t) {
    return t.checkInexact(LEFTBOLT.getWidth(), -Math.cos(Math.toRadians(135)) * 30, 0.001)
        && t.checkInexact(RIGHTBOLT.getWidth(), Math.cos(Math.toRadians(40)) * 30, 0.001);
  }

  boolean testGetWidthTurningBack(Tester t) {
    return t.checkInexact(
        new Fork(10, 10, 10, 10, 0, 180, new Segment(10, 10, 180, TIP), new Segment(10, 10, 0, TIP))
            .getWidth(),
        20.0, 0.001);
  }

//
//  boolean testGetWidthStright(Tester t) {
//    return t.checkInexact(new Fork(100, 100, 100, 100, 90, 270, TIP, TIP).getWidth(),
//            0.0, 0.001)
//        && t.checkInexact(RIGHTBOLT.getWidth(),
//            Math.cos(Math.toRadians(40)) * 30, 0.001);
//  }

  boolean testCombine(Tester t) {
    return t.checkExpect(TIP.combine(30, 20, 10, 10, 135, 40, TIP), BOLT1)
        && t.checkExpect(SEGMENT1.combine(30, 20, 10, 10, 135, 40, SEGMENT2),
            new Fork(30, 20, 10, 10, 135, 40,
                new Segment(40, 10, 135, new Fork(30, 20, 10, 10, 180, 85, TIP, TIP)),
                new Segment(50, 80, -40, new Fork(30, 20, 10, 10, 85, -10, TIP, TIP))))
        && t.checkExpect(BOLT1.combine(40, 50, 10, 10, 150, 30, BOLT2),
            new Fork(40, 50, 10, 10, 150, 30, new Fork(30, 20, 10, 10, 195, 100, TIP, TIP),
                new Fork(30, 30, 10, 10, 55, 5, TIP, TIP)));
  }

//  boolean testCombineTipSegment(Tester t) {
//    return t.checkExpect(TIP.combine(30, 20, 10, 10, 135, 40, SEGMENT1), 
//        new Fork(30, 20, 10, 10, 135, 40, TIP,
//            new Segment(40, 10, 40, new Fork(30, 20, 10, 10, 85, -10, TIP, TIP))));
//  }

//  boolean testCombineCombine(Tester t) {
//    return t.checkExpect(
//        SEGMENT1.combine(30, 20, 10, 10, 135, 40, BOLT1.combine(40, 50, 10, 10, 150, 30, BOLT2)),
//        new Fork(30, 20, 10, 10, 135, 40,
//            new Segment(40, 10, 135, new Fork(30, 20, 10, 10, 180, 85, TIP, TIP)),
//            new Fork(40, 50, 10, 10, 100, -20, new Fork(30, 20, 10, 10, 145, 50, TIP, TIP),
//                new Fork(30, 30, 10, 10, 5, -45, TIP, TIP))));
//  }

  boolean testCombineSegment(Tester t) {
    return t.checkExpect(SEGMENT1.combine(30, 20, 10, 10, 135, 40, SEGMENT2),
        new Fork(30, 20, 10, 10, 135, 40,
            new Segment(40, 10, 135, new Fork(30, 20, 10, 10, 180, 85, TIP, TIP)),
            new Segment(50, 80, -40, new Fork(30, 20, 10, 10, 85, -10, TIP, TIP))));
  }

//    boolean testCombineSegmentZero(Tester t) {
//      return t.checkExpect(SEGMENT1.combine(0, 0, 10, 10, 135, 140, TIP),
//          new Fork(0, 0, 10, 10, 135, 140,
//              new Segment(40, 10, 135, new Fork(30, 20, 10, 10, 180, 85, TIP, TIP)),
//              TIP));
//  }

//  boolean testCombineSegmentWithFork(Tester t) {
//    return t.checkExpect(SEGMENT1.combine(30, 20, 10, 10, 135, 40, BOLT2),
//            new Fork(30, 20, 10, 10, 135, 40,
//                new Segment(40, 10, 135, new Fork(30, 20, 10, 10, 180, 85, TIP, TIP)),
//                new Fork(30, 30, 10, 10, 65, 15, TIP, TIP)));
//  }

  boolean testCombineFork(Tester t) {
    return t.checkExpect(BOLT1.combine(40, 50, 10, 10, 150, 30, SEGMENT2),
        new Fork(40, 50, 10, 10, 150, 30, new Fork(30, 20, 10, 10, 195, 100, TIP, TIP),
            new Segment(50, 80, -50, new Fork(30, 20, 10, 10, 75, -20, TIP, TIP))));
  }

//  boolean testCombineIsPhysicallyPossible(Tester t) {
//    return t.checkExpect(SEGMENT1.combine(30, 20, 10, 10, 135, 40, SEGMENT2).isPhysicallyPossible(),
//        false)
//        && t.checkExpect(TIP.combine(30, 20, 10, 10, 135, 40, TIP).isPhysicallyPossible(), true)
//        && t.checkExpect(BOLT1.combine(40, 50, 10, 10, 150, 30, BOLT2).isPhysicallyPossible(),
//            false);
//  }
//
  boolean testCombineGetWidth(Tester t) {
    return t.checkInexact(TIP.combine(30, 20, 10, 10, 135, 40, TIP).getWidth(),
        -Math.cos(Math.toRadians(135)) * 30 + Math.cos(Math.toRadians(40)) * 20, 0.001);
  }

}