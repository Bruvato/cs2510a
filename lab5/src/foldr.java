//import tester.Tester;
//
//// Represents functions of signature A -> R, for some argument type A and
//// result type R
//interface IFunc<A, R> {
//  R apply(A input);
//}
//
//interface IBiFunc<A, B, R> {
//  R apply(A input1, B input2);
//}
//
//// generic list
//interface IList<T> {
//  // map over a list, and produce a new list with a (possibly different)
//  // element type
//  <U> IList<U> map(IFunc<T, U> f);
//
//  <U> U foldr(IFunc<T, U> f, U base);
//}
//
//// empty generic list
//class MtList<T> implements IList<T> {
//  public <U> IList<U> map(IFunc<T, U> f) {
//    return new MtList<U>();
//  }
//
//  public <U> U foldr(IFunc<T, U> f, U base) {
//    // TODO Auto-generated method stub
//    return null;
//  }
//
//}
//
//// non-empty generic list
//class ConsList<T> implements IList<T> {
//  T first;
//  IList<T> rest;
//
//  ConsList(T first, IList<T> rest) {
//    this.first = first;
//    this.rest = rest;
//  }
//
//  public <U> IList<U> map(IFunc<T, U> f) {
//    return new ConsList<U>(f.apply(this.first), this.rest.map(f));
//  }
//
//  public <U> U foldr(IFunc<T, U> f, U base) {
////    return f.apply(this.first, this.rest.foldr(f, base));
//    return null;
//  }
//}
//
//class ConsFunc implements IFunc<Integer, IList<Integer>> {
//
//  public IList<Integer> apply(Integer input) {
//    // TODO Auto-generated method stub
//    return null;
//  }
//
//  public IList<Integer> apply(Integer input1, IList<Integer> input2) {
//    return new ConsList<Integer>(input1, input2);
//  }
//}
//
//class Examples {
//  IList<Integer> empty = new MtList<Integer>();
//
//  IList<Integer> list = new ConsList<Integer>(1,
//      new ConsList<Integer>(2, new ConsList<Integer>(3, new ConsList<Integer>(4, empty))));
//
//  boolean test(Tester t) {
//    return t.checkExpect(list.foldr(new ConsFunc(), empty), null);
//  }
//
//}