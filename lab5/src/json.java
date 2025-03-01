//// a json value
//interface JSON {
//  int accept(JSONVisitor<Integer> vis);
//}
//
//// no value
//class JSONBlank implements JSON {
//
//  public int accept(JSONVisitor<Integer> vis) {
//    return vis.visitBlank(this);
//  }
//}
//
//// a number
//class JSONNumber implements JSON {
//  int number;
//
//  JSONNumber(int number) {
//    this.number = number;
//  }
//
//  public int accept(JSONVisitor<Integer> vis) {
//    return vis.visitNumber(this);
//  }
//}
//
//// a boolean
//class JSONBool implements JSON {
//  boolean bool;
//
//  JSONBool(boolean bool) {
//    this.bool = bool;
//  }
//
//  public int accept(JSONVisitor<Integer> vis) {
//    return vis.visitBool(this);
//  }
//}
//
//// a string
//class JSONString implements JSON {
//  String str;
//
//  JSONString(String str) {
//    this.str = str;
//  }
//
//  public int accept(JSONVisitor<Integer> vis) {
//    return vis.visitString(this);
//  }
//}
//
////a list of JSON values
//class JSONList implements JSON {
//  IList<JSON> values;
//
//  JSONList(IList<JSON> values) {
//    this.values = values;
//  }
//
//  public int accept(JSONVisitor<Integer> vis) {
//    return vis.visitList(this);
//  }
//}
//
//class MtLoJSON implements JSON {
//
//  public int accept(JSONVisitor<Integer> vis) {
//    return vis.visitMt(this);
//  }
//
//}
//
//class ConsLoJSON implements JSON {
//  JSON first;
//  JSONList rest;
//
//  public int accept(JSONVisitor<Integer> vis) {
//    return vis.visitCons(this);
//  }
//
//}
//
//interface IFunc<T, U> {
//  U apply(T arg);
//}
//
//interface JSONVisitor<T> extends IFunc<JSON, T> {
//  T visitBlank(JSONBlank b);
//
//  T visitNumber(JSONNumber n);
//
//  T visitBool(JSONBool b);
//
//  T visitString(JSONString s);
//
//  T visitList(JSONList l);
//
//  T visitMt(MtLoJSON mt);
//
//  T visitCons(ConsLoJSON cons);
//
//}
//
//class JSONToNumber implements JSONVisitor<Integer> {
//
//  public Integer apply(JSON arg) {
//    return arg.accept(this);
//  }
//
//  public Integer visitBlank(JSONBlank b) {
//    return 0;
//  }
//
//  public Integer visitNumber(JSONNumber n) {
//    return n.number;
//  }
//
//  public Integer visitBool(JSONBool b) {
//    if (b.bool) {
//      return 1;
//    }
//    return 0;
//  }
//
//  public Integer visitString(JSONString s) {
//    return s.str.length();
//  }
//
//  public Integer visitList(JSONList l) {
//    return this.vis
//  }
//
//  public Integer visitMt(MtLoJSON mt) {
//    return 0;
//  }
//
//  public Integer visitCons(ConsLoJSON cons) {
//    return this.apply(cons.first) + this.apply(cons.rest);
//  }
//}
//
//interface IList<T> {
//
//}
//
//class Empty<T> implements IList<T> {
//
//}
//
//class Cons<T> implements IList<T> {
//  T first;
//  IList<T> rest;
//
//}
