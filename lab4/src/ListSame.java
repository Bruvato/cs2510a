
interface ILoInt {

  boolean sameList(ILoInt other);

  boolean sameListMt(MtLoInt other);

  boolean sameListCons(ConsLoInt other);

}

class MtLoInt implements ILoInt {

  public boolean sameList(ILoInt other) {
    return other.sameListMt(this);
  }

  public boolean sameListMt(MtLoInt other) {
    return true;
  }

  public boolean sameListCons(ConsLoInt other) {
    return false;
  }

}

class ConsLoInt implements ILoInt {
  int first;
  ILoInt rest;

  public boolean sameList(ILoInt other) {
    return other.sameListCons(this);
  }

  public boolean sameListMt(MtLoInt other) {
    return false;
  }

  public boolean sameListCons(ConsLoInt other) {
    return this.first == other.first && this.rest.sameList(other.rest);
  }
}