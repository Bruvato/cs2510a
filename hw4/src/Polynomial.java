import tester.Tester;

class Polynomial {
  ILoMonomial monomials;

  public Polynomial(ILoMonomial monomials) {
    if (monomials.containsDuplicates()) {
      throw new IllegalArgumentException("There are monomials with same degree");
    }
    this.monomials = monomials.normalize();
  }

  public Polynomial() {
    this.monomials = new MtLoMonomial();
  }

  int evaluate(int value) {
    return this.monomials.evaluate(value);
  }

  Polynomial add(Polynomial polynomial) {
    return new Polynomial(this.monomials.add(polynomial.monomials));
  }

  boolean samePolynomial(Polynomial polynomial) {
    return this.monomials.sameLoMonomial(polynomial.monomials);
  }

  Polynomial multiply(Polynomial polynomial) {
    return new Polynomial(this.monomials.multiply(polynomial.monomials));
  }

}

class Monomial {
  int coefficient;
  int degree;

  public Monomial(int coefficient, int degree) {
    this.coefficient = coefficient;
    this.degree = degree;
  }

  int evaluate(int value) {
    return this.coefficient * (int) Math.pow(value, this.degree);
  }

  boolean sameDegree(Monomial monomial) {
    return this.degree == monomial.degree;
  }

  int compareTo(Monomial monomial) {
    int diff = this.degree - monomial.degree;

    if (diff == 0) {
      return this.coefficient - monomial.coefficient;
    }

    return diff;
  }

  boolean hasZeroCoefficient() {
    return coefficient == 0;
  }

  Monomial add(Monomial monomial) {
    return new Monomial(this.coefficient + monomial.coefficient, this.degree);
  }

  boolean sameMonomial(Monomial monomial) {
    return this.degree == monomial.degree && this.coefficient == monomial.coefficient;
  }

  Monomial multiply(Monomial monomial) {
    return new Monomial(this.coefficient * monomial.coefficient, this.degree + monomial.degree);
  }

}

interface ILoMonomial {

  int evaluate(int value);

  ILoMonomial normalize();

  ILoMonomial sortByDegree();

  ILoMonomial insertByDegree(Monomial monomial);

  ILoMonomial removeZeroCoefficients();

  // ----------------

  boolean containsSameDegree(Monomial monomial);

  boolean containsDuplicates();

  // -------------------------------

  ILoMonomial addMonomial(Monomial monomial);

  ILoMonomial add(ILoMonomial monomials);

  // --------------------

  boolean sameLoMonomial(ILoMonomial loMonomial);

  boolean sameMtLoMonomial(MtLoMonomial mtLoMonomial);

  boolean sameConsLoMonomial(ConsLoMonomial consLoMonomial);

  // --------------------

  ILoMonomial multiplyMonomial(Monomial monomial);

  ILoMonomial multiply(ILoMonomial loMonomial);

}

class MtLoMonomial implements ILoMonomial {

  public int evaluate(int value) {
    return 0;
  }

  public ILoMonomial normalize() {
    return this;
  }

  public ILoMonomial sortByDegree() {
    return this;
  }

  public ILoMonomial insertByDegree(Monomial monomial) {
    return new ConsLoMonomial(monomial, this);
  }

  public ILoMonomial removeZeroCoefficients() {
    return this;
  }

  // ----------------------

  public boolean containsSameDegree(Monomial monomial) {
    return false;
  }

  public boolean containsDuplicates() {
    return false;
  }

  // -------------------

  public ILoMonomial addMonomial(Monomial monomial) {
    return new ConsLoMonomial(monomial, this);
  }

  public ILoMonomial add(ILoMonomial monomials) {
    return monomials;
  }

  // ------------------------------

  public boolean sameLoMonomial(ILoMonomial loMonomial) {
    return true;
  }

  public boolean sameMtLoMonomial(MtLoMonomial mtLoMonomial) {
    return true;
  }

  public boolean sameConsLoMonomial(ConsLoMonomial consLoMonomial) {
    return false;
  }

  // ------

  public ILoMonomial multiplyMonomial(Monomial monomial) {
    return this;
  }

  public ILoMonomial multiply(ILoMonomial loMonomial) {
    return this;
  }

}

class ConsLoMonomial implements ILoMonomial {
  Monomial first;
  ILoMonomial rest;

  public ConsLoMonomial(Monomial first, ILoMonomial rest) {
    this.first = first;
    this.rest = rest;
  }

  public int evaluate(int value) {
    return this.first.evaluate(value) + this.rest.evaluate(value);
  }

  public ILoMonomial normalize() {
    return this.sortByDegree().removeZeroCoefficients();
  }

  public ILoMonomial sortByDegree() {
    return this.rest.sortByDegree().insertByDegree(this.first);
  }

  public ILoMonomial insertByDegree(Monomial monomial) {
    if (this.first.compareTo(monomial) > 0) {
      return new ConsLoMonomial(monomial, this);
    }

    return new ConsLoMonomial(this.first, this.rest.insertByDegree(monomial));
  }

  public ILoMonomial removeZeroCoefficients() {
    if (this.first.hasZeroCoefficient()) {
      return this.rest;
    }
    return new ConsLoMonomial(this.first, this.rest.removeZeroCoefficients());
  }

  // -------------------------------

  public boolean containsSameDegree(Monomial monomial) {
    if (this.first.sameDegree(monomial)) {
      return true;
    }
    return this.rest.containsSameDegree(monomial);
  }

  public boolean containsDuplicates() {
    return this.rest.containsSameDegree(this.first) || this.rest.containsDuplicates();
  }

  // -----------------------------

  public ILoMonomial addMonomial(Monomial monomial) {
    if (this.first.sameDegree(monomial)) {
      return new ConsLoMonomial(this.first.add(monomial), this.rest);
    }

    return new ConsLoMonomial(this.first, this.rest.addMonomial(monomial)).normalize();
  }

  public ILoMonomial add(ILoMonomial monomials) {
    return this.rest.add(monomials.addMonomial(this.first));
  }

  // ----------------------------------------------

  public boolean sameLoMonomial(ILoMonomial loMonomial) {
    return this.evaluate(this.evaluate(1) + 1) == loMonomial.evaluate(loMonomial.evaluate(1) + 1);
  }

  public boolean sameMtLoMonomial(MtLoMonomial mtLoMonomial) {
    return false;
  }

  public boolean sameConsLoMonomial(ConsLoMonomial consLoMonomial) {
    return this.first.sameMonomial(consLoMonomial.first)
        && this.rest.sameConsLoMonomial(consLoMonomial);
  }

  // ----------------------------

  public ILoMonomial multiplyMonomial(Monomial monomial) {
    return new ConsLoMonomial(this.first.multiply(monomial), this.rest.multiplyMonomial(monomial));
  }

  public ILoMonomial multiply(ILoMonomial loMonomial) {
    return loMonomial.multiplyMonomial(this.first).add(this.rest.multiply(loMonomial));
  }

}

class PolynomialExamples {

  ILoMonomial mt = new MtLoMonomial();
  Monomial x0 = new Monomial(0, 100);
  Monomial x12 = new Monomial(1, 2);
  Monomial x23 = new Monomial(2, 3);
  Monomial x56 = new Monomial(5, 6);
  Monomial x78 = new Monomial(7, 8);
//  ILoMonomial illegal = new ConsLoMonomial(x12, new ConsLoMonomial(x12, mt));
  Polynomial PolyMt = new Polynomial();
  Polynomial Poly1 = new Polynomial(new ConsLoMonomial(x12, mt));
  Polynomial Poly2 = new Polynomial(
      new ConsLoMonomial(x12, new ConsLoMonomial(x23, new ConsLoMonomial(x56, mt))));
  Polynomial Poly3 = new Polynomial(new ConsLoMonomial(x56,
      new ConsLoMonomial(x0, new ConsLoMonomial(x12, new ConsLoMonomial(x23, mt)))));
  Polynomial Poly5 = new Polynomial(new ConsLoMonomial(x56, new ConsLoMonomial(x0,
      new ConsLoMonomial(x12, new ConsLoMonomial(x23, new ConsLoMonomial(x78, mt))))));
  Polynomial Poly4 = new Polynomial(new ConsLoMonomial(new Monomial(2, 2), new ConsLoMonomial(
      new Monomial(4, 3), new ConsLoMonomial(new Monomial(10, 6), new ConsLoMonomial(x78, mt)))));

//  boolean testNormalize(Tester t) {
//    return t.checkExpect(Poly1.normalize(), Poly1) 
//        && t.checkExpect(PolyMt.normalize(), PolyMt)
//        && t.checkExpect(Poly3.normalize(), Poly2);
//  }

  boolean testNormalizeLoMonomials(Tester t) {
    return t.checkExpect(new ConsLoMonomial(x12, mt).normalize(), new ConsLoMonomial(x12, mt))
        && t.checkExpect(mt
            .normalize(), mt)
        && t.checkExpect(
            new ConsLoMonomial(x56,
                new ConsLoMonomial(x0, new ConsLoMonomial(x12, new ConsLoMonomial(x23, mt))))
                .normalize(),
            new ConsLoMonomial(x12, new ConsLoMonomial(x23, new ConsLoMonomial(x56, mt))));
  }

  boolean testEvaluate(Tester t) {
    return t.checkExpect(Poly1.evaluate(3), 9) && t.checkExpect(PolyMt.evaluate(3), 0)
        && t.checkExpect(Poly3.evaluate(5), 78400);
  }

  boolean testAdd(Tester t) {
    return t.checkExpect(Poly1.add(PolyMt), Poly1) && t.checkExpect(PolyMt.add(Poly3), Poly3)
        && t.checkExpect(Poly3.add(Poly5), Poly4);
  }

  boolean testAddLoMonomial(Tester t) {
    return t.checkExpect(new ConsLoMonomial(x12,
        mt).add(
            mt),
        new ConsLoMonomial(x12, mt))
        && t.checkExpect(
            mt.add(new ConsLoMonomial(x56, new ConsLoMonomial(x0,
                new ConsLoMonomial(x12, new ConsLoMonomial(x23, mt))))),
            new ConsLoMonomial(x56,
                new ConsLoMonomial(x0, new ConsLoMonomial(x12, new ConsLoMonomial(x23, mt)))))
        && t.checkExpect(
            new ConsLoMonomial(x56,
                new ConsLoMonomial(x0, new ConsLoMonomial(x12, new ConsLoMonomial(x23, mt))))
                .add(new ConsLoMonomial(x56,
                    new ConsLoMonomial(x0,
                        new ConsLoMonomial(x12,
                            new ConsLoMonomial(x23, new ConsLoMonomial(x78, mt)))))),
            new ConsLoMonomial(new Monomial(2, 2), new ConsLoMonomial(new Monomial(4, 3),
                new ConsLoMonomial(new Monomial(10, 6), new ConsLoMonomial(x78, mt)))));
  }

  boolean testSamePolynomial(Tester t) {
    return t.checkExpect(Poly1.samePolynomial(PolyMt), false)
        && t.checkExpect(PolyMt.samePolynomial(PolyMt), true)
        && t.checkExpect(Poly3.samePolynomial(Poly2), true)
        && t.checkExpect(Poly3.samePolynomial(Poly5), false);
  }

  boolean testMultiplyMonoMonomial(Tester t) {
    return t.checkExpect(x12.multiply(x12), new Monomial(1, 4))
        && t.checkExpect(x23.multiply(x0), new Monomial(0, 103))
        && t.checkExpect(x23.multiply(x78), new Monomial(14, 11));
  }

  boolean testMultiplyMonomial(Tester t) {
    return t.checkExpect(
        (new ConsLoMonomial(x12, new ConsLoMonomial(x23, new ConsLoMonomial(x56, mt))))
            .multiplyMonomial(x12),
        new ConsLoMonomial(new Monomial(1, 4),
            new ConsLoMonomial(new Monomial(2, 5), new ConsLoMonomial(new Monomial(5, 8), mt))));
  }

  boolean testMultiply(Tester t) {
    return t
        .checkExpect(Poly2.multiply(Poly5),
            new Polynomial(
                new ConsLoMonomial(new Monomial(1, 4),
                    new ConsLoMonomial(new Monomial(4, 5),
                        new ConsLoMonomial(new Monomial(4, 6),
                            new ConsLoMonomial(new Monomial(10, 8),
                                new ConsLoMonomial(new Monomial(20, 9),
                                    new ConsLoMonomial(new Monomial(7, 10),
                                        new ConsLoMonomial(new Monomial(14, 11), new ConsLoMonomial(
                                            new Monomial(25, 12),
                                            new ConsLoMonomial(new Monomial(35, 14), mt)))))))))));
  }
}