//import tester.Tester;
//
//interface ILoInt {
//  ILoInt sort();
//
//  ILoInt insert(int x);
//
//  boolean isSorted();
//
//  boolean isSortedHelp(int first);
//}
//
//class MtLoInt implements ILoInt {
//  public ILoInt sort() {
//    return this;
//  }
//
//  public ILoInt insert(int x) {
//    return new ConsLoInt(x, this);
//  }
//
//  public boolean isSorted() {
//    return true;
//  }
//
//  public boolean isSortedHelp(int first) {
//    return true;
//  }
//}
//
//class ConsLoInt implements ILoInt {
//  int first;
//  ILoInt rest;
//
//  public ConsLoInt(int first, ILoInt rest) {
//    this.first = first;
//    this.rest = rest;
//  }
//
//  public ILoInt sort() {
//    return this.rest.sort().insert(this.first);
//  }
//
//  public ILoInt insert(int x) {
//    if (x <= this.first) {
//      return new ConsLoInt(x, this);
//    }
//    else {
//      return new ConsLoInt(this.first, this.rest.insert(x));
//    }
//  }
//
//  public boolean isSorted() {
//    return this.rest.isSortedHelp(this.first) && this.rest.isSorted();
//  }
//
//  public boolean isSortedHelp(int first) {
//    return first <= this.first;
//  }
//
//}
//
//class Examples {
//  ILoInt empty = new MtLoInt();
//
//  ILoInt test1 = new ConsLoInt(4,
//      new ConsLoInt(1, new ConsLoInt(3, new ConsLoInt(2, new ConsLoInt(5, empty)))));
//  ILoInt test2 = new ConsLoInt(1,
//      new ConsLoInt(2, new ConsLoInt(3, new ConsLoInt(4, new ConsLoInt(5, empty)))));
//
//  boolean test(Tester t) {
//    return
////        t.checkExpect(test1.sort(), test1) 
//    t.checkExpect(test2.isSorted(), null);
//
//  }
//
//}