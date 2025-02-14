import tester.*;

import java.awt.Color;

class Ball {
  int x;
  int y;
  int radius;
  Color color;

  Ball(int x, int y, int radius, Color color) {
    this.x = x;
    this.y = y;
    this.radius = radius;
    this.color = color;
  }

  // Returns the area of this ball
  double area() {
    return Math.PI * Math.pow(this.radius, 2);
  }

  // Returns the circumference of this ball
  double circumference() {
    return 2 * Math.PI * this.radius;
  }
  
  // Returns the distance from this ball's center to another ball's center
  double distanceTo(Ball ball) {
    return Math.sqrt((this.x - ball.x) * (this.x - ball.x) + (this.y - ball.y) * (this.y - ball.y));
  }
  
  // Returns whether this ball overlaps with another given ball
  boolean overlaps(Ball ball) {
    return this.distanceTo(ball) < (this.radius + ball.radius);
  }

}

class ExamplesBall {
  Ball b = new Ball(0, 0, 5, Color.BLUE);

  boolean testBalls(Tester t) {
    return t.checkInexact(b.area(), 78.5, 0.001);
  }
  
  boolean testCircumference(Tester t) {
    return t.checkInexact(b.circumference(), 31.415, 0.001);
  }
  
}