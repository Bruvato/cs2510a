import tester.Tester;

interface IShape {
  double accept(IShapeVisitor<Double> visitor);
}

class Circle implements IShape {
  int x, y;
  int radius;
  String color;

  Circle(int x, int y, int r, String color) {
    this.x = x;
    this.y = y;
    this.radius = r;
    this.color = color;
  }

  public double accept(IShapeVisitor<Double> visitor) {
    return visitor.visitCircle(this);
  }
}

class Rect implements IShape {
  int x, y, w, h;
  String color;

  Rect(int x, int y, int w, int h, String color) {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.color = color;
  }

  public double accept(IShapeVisitor<Double> visitor) {
    return visitor.visitRect(this);
  }
}

class Square implements IShape {
  int x, y, size;
  String color;

  Square(int x, int y, int size, String color) {
    this.x = x;
    this.y = y;
    this.size = size;
    this.color = color;
  }

  public double accept(IShapeVisitor<Double> visitor) {
    return visitor.visitSquare(this);
  }

}
// -----------------------------

interface IList<T> {

  <U> IList<U> map(IFunc<T, U> func);
}

interface IFunc<T, U> {
  U apply(T arg);
}

interface IShapeVisitor<R> extends IFunc<IShape, R> {
  R visitCircle(Circle circle);

  R visitRect(Rect rect);

  R visitSquare(Square square);
}

class ShapeArea implements IShapeVisitor<Double> {

  public Double visitCircle(Circle circle) {
    return Math.PI * circle.radius * circle.radius;
  }

  public Double visitRect(Rect rect) {
    return (double) (rect.w * rect.h);
  }

  public Double visitSquare(Square square) {
    return (double) (square.size * square.size);
  }

  public Double apply(IShape arg) {
    return arg.accept(this);
  }

}