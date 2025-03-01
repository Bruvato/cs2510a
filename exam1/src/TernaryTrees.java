import tester.Tester;

interface INumTree {

  // determines if this tree is the same as the given tree or is the same as the
  // tree’s mirror image
  boolean sameOrMirrored(INumTree other);

  boolean sameTree(INumTree other);

  boolean sameTreeLeaf(Leaf other);

  boolean sameTreeNode(Node other);

  //
  INumTree mirror();

}

class Leaf implements INumTree {

  public INumTree mirror() {
    return this;
  }

  public boolean sameOrMirrored(INumTree other) {
    return this.sameTree(other);
  }

  public boolean sameTree(INumTree other) {
    return other.sameTreeLeaf(this);
  }

  public boolean sameTreeLeaf(Leaf other) {
    return true;
  }

  public boolean sameTreeNode(Node other) {
    return false;
  }

}

class Node implements INumTree {
  int value;
  INumTree left, middle, right;

  public Node(int value, INumTree left, INumTree middle, INumTree right) {
    this.value = value;
    this.left = left;
    this.middle = middle;
    this.right = right;
  }

  public Node(int value) {
    this.value = value;
    this.left = new Leaf();
    this.middle = new Leaf();
    this.right = new Leaf();
  }

  public INumTree mirror() {
    return new Node(this.value, this.right.mirror(), this.middle.mirror(), this.left.mirror());
  }

  public boolean sameOrMirrored(INumTree other) {
    return other.sameTree(this) || other.mirror().sameTree(this);
  }

  public boolean sameTree(INumTree other) {
    return other.sameTreeNode(this);
  }

  public boolean sameTreeLeaf(Leaf other) {
    return false;
  }

  public boolean sameTreeNode(Node other) {
    return this.value == other.value && this.left.sameTree(other.left)
        && this.middle.sameTree(other.middle) && this.right.sameTree(other.right);
  }

}

// sameOrMirrored
// Leaf l = new Leaf
// l -> t
// new Node(1, l, l, l) -> t
// new Node(new Node(2, l, l, l), l, l) -> f
// new Node(1, l, new Node(2, l, l, l), l) -> t
// new Node(new Node(2, l, l, l), l, new Node(3, l, l, l)) -> f
//new Node(new Node(2, l, l, l), l, new Node(2, l, l, l)) -> t

class Examples {
  void test(Tester t) {
    INumTree l = new Leaf();
    INumTree t0 = new Node(0);
    INumTree t1 = new Node(0, new Node(1), l, l);
    INumTree t1M = new Node(0, l, l, new Node(1));
    INumTree t2 = new Node(1, new Node(2), l, new Node(3));
    INumTree t2M = new Node(1, new Node(3), l, new Node(2));
    INumTree t3 = new Node(0, new Node(1, new Node(2), l, l), l, l);
    INumTree t3M = new Node(0, l, l, new Node(1, l, l, new Node(2)));

    // leaf
    t.checkExpect(l.sameOrMirrored(l), true);

    // 1 node
    t.checkExpect(t0.sameOrMirrored(t0), true);
    t.checkExpect(t0.sameOrMirrored(new Node(-1, l, l, l)), false);

    // 2 nodes
    t.checkExpect(t1.sameOrMirrored(t1), true);
    t.checkExpect(t1.sameOrMirrored(t1M), true);
    t.checkExpect(t2.sameOrMirrored(t2), true);
    t.checkExpect(t2.sameOrMirrored(t2M), true);

    // 3 nodes
    t.checkExpect(t3.sameOrMirrored(t3), true);
    t.checkExpect(t3.sameOrMirrored(t3M), true);
    t.checkExpect(t3M.sameOrMirrored(t3M), true);
    t.checkExpect(t3M.sameOrMirrored(t3), true);

  }
}
