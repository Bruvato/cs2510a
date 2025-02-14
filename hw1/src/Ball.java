import tester.Tester;
import java.awt.Color;
import javalib.worldimages.*;


class BouncingBall {
  Posn pos;
  Color color;
  int size;
  int dx; // how fast is the ball moving to the right?
  int dy; // how fast is the ball moving downward?

  BouncingBall(Posn pos, Color color, int size, int dx, int dy) {
    this.pos = pos;
    this.color = color;
    this.size = size;
    this.dx = dx;
    this.dy = dy;
  }
  

  // Returns the area of this ball
  double area() {
    return Math.PI * Math.pow(this.size, 2);
  }
  
  // Return the circumference of this ball
  double circumference() {
    return 2 * Math.PI * this.size;
  }
  
  // Return the distance of the two ball's centers
  double distanceTo(BouncingBall ball) {
    return Math.sqrt(Math.pow(this.pos.x - ball.pos.x, 2) + Math.pow(this.pos.y - ball.pos.y, 2));
  }
  
  // Check whether the ball is overlap with another given ball
  boolean overlaps(BouncingBall ball) {
    return this.size + ball.size > this.distanceTo(ball);
  }

  // Returns a new BouncingBall that's just like this BouncingBall, but moved
  // by this BouncingBall's dx and dy
  BouncingBall move() {
    return new BouncingBall(new Posn(this.pos.x + dx, this.pos.y + dy), 
        this.color, this.size, this.dx, this.dy);
  }

  // Returns a new BouncingBall that represents this BouncingBall just after
  // it has bounced off a side wall. Does not actually move the ball.
  // This method will be called automatically when `collidesX` returns true
  BouncingBall bounceX() {
    return new BouncingBall(this.pos, this.color, this.size, this.dx * -1, this.dy);
  }

  // Like bounceX, except for using the top or bottom walls
  BouncingBall bounceY() {
    return new BouncingBall(this.pos, this.color, this.size, this.dx, this.dy * -1);
  }

  // Detects whether the ball is colliding with a side wall.
  boolean collidesX(Posn topLeft, Posn botRight) {
    if (dx > 0) {
      return this.pos.x + this.size > botRight.x;
    } else {
      return this.pos.x - this.size < topLeft.x;
    }
  }

  // Detects whether the ball is colliding with a top or bottom wall.
  boolean collidesY(Posn topLeft, Posn botRight) {
    if (dy > 0) {
      return this.pos.y + this.size > botRight.y;
    } else {
      return this.pos.y - this.size < topLeft.y;
    }
  }
}

class ExamplesBouncingBalls {
  int WIDTH = 300;
  int HEIGHT = 300;

  // NOTE: We have provided BouncingWorld for you, in the starter code.
  // We'll see how it works in a few lectures
  boolean testBigBang(Tester t) {
    BouncingWorld w = new BouncingWorld(WIDTH, HEIGHT);
    return w.bigBang(WIDTH, HEIGHT, 0.1);
  }
  
  boolean testBall(Tester t) {
    BouncingBall a = new BouncingBall(new Posn(30,40), Color.RED, 5, -1, -1);
    BouncingBall b = new BouncingBall(new Posn(0,0), Color.RED, 10, 1, 1);
    return t.checkExpect(b.move(), new BouncingBall(new Posn(1,1), Color.RED, 10, 1, 1))
        && t.checkExpect(b.bounceX(), new BouncingBall(new Posn(0,0), Color.RED, 10, -1, 1))
        && t.checkExpect(b.bounceY(), new BouncingBall(new Posn(0,0), Color.RED, 10, 1, -1))
        && t.checkExpect(b.collidesX(new Posn(-20,-20), new Posn(0,0)), true)
        && t.checkExpect(b.collidesX(new Posn(-20,-20), new Posn(20,20)), false)
        && t.checkExpect(b.collidesY(new Posn(-3,-3), new Posn(-1,-1)), true)
        && t.checkExpect(b.collidesY(new Posn(5, 5), new Posn(100, 100)), false)
        && t.checkInexact(a.area(), 78.5, 0.001)
        && t.checkInexact(a.circumference(), 31.4, 0.001)
        && t.checkInexact(b.distanceTo(a), 50.0, 0.001)
        && t.checkExpect(b.overlaps(new BouncingBall(new Posn(1,1), Color.RED, 10, 1, 1)), true)
        && t.checkExpect(b.overlaps(a), false);
  }
}