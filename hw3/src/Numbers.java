import tester.*;

// represent a list of int
interface ILoInt {

  // a helper that carry the current number and operation to operate with rest of numbers
  boolean isSequenceLike1(int f1, String type);

  // a helper that carry the last andcurrent number and operation to operate with rest of numbers
  boolean isSequenceLike2(int f1, int f2, String type);

  // determine whether the list of number follow the Fibonacci numbers rule
  boolean isFibLike();
  
  // determine whether the list of number follow the Pell numbers rule
  boolean isPellLike();

  // determine whether the list of number follow the Nega-Fibonacci numbers rule
  boolean isNegaFibLike();

  // determine whether the list of number follow the Jacobsthal numbers rule
  boolean isJacobsthalLike();

  // determine whether the list contain minimal legal amount of numbers
  boolean legalLength(int n);

  // find the largest number in the list
  int largestNum(int largest);

  // generate the same list with the given index number removed
  ILoInt removeAtIndex(int index);

  // locate the index of the largest number
  int locateLargetsNumIndex(int largest, int index, int largestNumIndex);

  // find the nth largest number
  int nthLargestNumHelp(int n);

  // find the second largest number
  int secondLargestNum();

  // find the fifth largest number
  int fifthLargestNum();

  // count how many target are in the list
  int countFrequency(int target);

  // generate a bijection list represent the frequency of each element in the list
  ILoInt frequencyList();

  // find the number in the given index
  int numAtIndex(int index);

  // determine whether the list contain the target
  boolean contains(int target);

  // generate the same list with all target elements removed
  ILoInt removeAll(int target);

  // find the n the most common number
  int nthmostCommonNumHelper(int n);

  // find the most common number
  int mostCommonNum();

  // find the third most common number
  int thirdMostCommonNum();

}

// represent an empty list of int
class MtLoInt implements ILoInt {

  /* TEMPLATE:
   Fields:
   -----------------
   Methods:
   ... this.isSequenceLike1(int, String) ...         --boolean
   ... this.isSequenceLike2(int, int, String) ...    --boolean
   ... this.isFibLike() ...                          --boolean
   ... this.isPellLike() ...                         --boolean
   ... this.isNegaFibLike() ...                      --boolean
   ... this.isJacobsthalLike() ...                   --boolean
   ... this.legalLength(int) ...                     --boolean
   ... this.locateLargetsNumIndex(int, int, int) ... --int
   ... this.removeAtIndex(int) ...                   --ILoInt
   ... this.largestNum(int) ...                      --int
   ... this.nthLargestNumHelp(int) ...               --int
   ... this.secondLargestNum() ...                   --int
   ... this.fifthLargestNum() ...                    --int
   ... this.countFrequency(int) ...                  --int
   ... this.frequencyList() ...                      --ILoInt
   ... this.numAtIndex(int) ...                      --int
   ... this.contains(int) ...                        --boolean
   ... this.removeAll(int) ...                       --ILoInt
   ... this.nthmostCommonNumHelper(int) ...          --int
   ... this.mostCommonNum() ...                      --boolean
   ... this.thirdMostCommonNum() ...                 --ILoInt
   -----------------
   Methods for Fields:
   */

  // a helper that carry the current number and operation to operate with rest of numbers
  public boolean isSequenceLike1(int f1, String type) {
    return true;
  }

  // determine whether the list of number follow the given type of number rule
  public boolean isSequenceLike2(int f1, int f2, String type) {
    return true;
  }

  // determine whether the list of number follow the Fibonacci numbers rule
  public boolean isFibLike() {
    return true;
  }

  // determine whether the list of number follow the Pell numbers rule
  public boolean isPellLike() {
    return true;
  }

  // determine whether the list of number follow the Nega-Fibonacci numbers rule
  public boolean isNegaFibLike() {
    return true;
  }

  // determine whether the list of number follow the Jacobsthal numbers rule
  public boolean isJacobsthalLike() {
    return true;
  }

  // determine whether the list contain minimal legal amount of numbers
  public boolean legalLength(int n) {
    return false;
  }

  // find the largest number in the list
  public int largestNum(int largest) {
    return largest;
  }

  // locate the index of the largest number
  public int locateLargetsNumIndex(int largest, int index, int largestNumIndex) {
    return largestNumIndex;
  }

  // generate the same list with the given index number removed
  public ILoInt removeAtIndex(int index) {
    return this;
  }

  // find the nth largest number
  public int nthLargestNumHelp(int n) {
    throw new IllegalArgumentException("The length of the list is shorter than required.");
  }

  // find the second largest number
  public int secondLargestNum() {
    throw new IllegalArgumentException("The list is empty, so there is no second largest number");
  }

  // find the fifth largest number
  public int fifthLargestNum() {
    throw new IllegalArgumentException("The list is empty, so there is no fifth largest number");
  }

  // count how many target are in the list
  public int countFrequency(int target) {
    return 0;
  }

  // generate a bijection list represent the frequency of each element in the list
  public ILoInt frequencyList() {
    return this;
  }

  // find the number in the given index
  public int numAtIndex(int index) {
    throw new IllegalArgumentException("The length of the list is shorter than required.");
  }

  // determine whether the list contain the target
  public boolean contains(int target) {
    return false;
  }

  // generate the same list with all target elements removed
  public ILoInt removeAll(int target) {
    return this;
  }

  // find the n the most common number
  public int nthmostCommonNumHelper(int n) {
    throw new IllegalArgumentException("The length of the list is shorter than required.");
  }
  
  // find the most common number
  public int mostCommonNum() {
    throw new IllegalArgumentException("The list is empty, so there is no most common number.");
  }

  // find the third most common number
  public int thirdMostCommonNum() {
    throw new IllegalArgumentException(
        "The list is empty, so there is no third most common number");
  }

}

// represent a list of int
class ConsLoInt implements ILoInt {
  
  // represent the first int in the list
  int first;
  
  // represent the rest ints in the list
  ILoInt rest;

  public ConsLoInt(int first, ILoInt rest) {
    this.first = first;
    this.rest = rest;
  }

  /* TEMPLATE:
   Fields:
  
   ... this.first ...                                -- int
   ... this.rest ...                                 -- ILoString
  
   -----------------
   Methods:
   ... this.isSequenceLike1(int, String) ...         --boolean
   ... this.isSequenceLike2(int, int, String) ...    --boolean
   ... this.isFibLike() ...                          --boolean
   ... this.isPellLike() ...                         --boolean
   ... this.isNegaFibLike() ...                      --boolean
   ... this.isJacobsthalLike() ...                   --boolean
   ... this.legalLength(int) ...                     --boolean
   ... this.locateLargetsNumIndex(int, int, int) ... --int
   ... this.removeAtIndex(int) ...                   --ILoInt
   ... this.largestNum(int) ...                      --int
   ... this.nthLargestNumHelp(int) ...               --int
   ... this.secondLargestNum() ...                   --int
   ... this.fifthLargestNum() ...                    --int
   ... this.countFrequency(int) ...                  --int
   ... this.frequencyList() ...                      --ILoInt
   ... this.numAtIndex(int) ...                      --int
   ... this.contains(int) ...                        --boolean
   ... this.removeAll(int) ...                       --ILoInt
   ... this.nthmostCommonNumHelper(int) ...          --int
   ... this.mostCommonNum() ...                      --boolean
   ... this.thirdMostCommonNum() ...                 --ILoInt
   -----------------
   Methods for Fields:
   ... this.rest.isSequenceLike1(int, String) ...         --boolean
   ... this.rest.isSequenceLike2(int, int, String) ...    --boolean
   ... this.rest.isFibLike() ...                          --boolean
   ... this.rest.isPellLike() ...                         --boolean
   ... this.rest.isNegaFibLike() ...                      --boolean
   ... this.rest.isJacobsthalLike() ...                   --boolean
   ... this.rest.legalLength(int) ...                     --boolean
   ... this.rest.locateLargetsNumIndex(int, int, int) ... --int
   ... this.rest.removeAtIndex(int) ...                   --ILoInt
   ... this.rest.largestNum(int) ...                      --int
   ... this.rest.nthLargestNumHelp(int) ...               --int
   ... this.rest.secondLargestNum() ...                   --int
   ... this.rest.fifthLargestNum() ...                    --int
   ... this.rest.countFrequency(int) ...                  --int
   ... this.rest.frequencyList() ...                      --ILoInt
   ... this.rest.numAtIndex(int) ...                      --int
   ... this.rest.contains(int) ...                        --boolean
   ... this.rest.removeAll(int) ...                       --ILoInt
   ... this.rest.nthmostCommonNumHelper(int) ...          --int
   ... this.rest.mostCommonNum() ...                      --boolean
   ... this.rest.thirdMostCommonNum() ...                 --ILoInt
   */

  // a helper that carry the current number and operation to operate with rest of numbers
  public boolean isSequenceLike1(int f1, String type) {
    return this.rest.isSequenceLike2(f1, this.first, type);
  }

  // determine whether the list of number follow the given type of number rule
  public boolean isSequenceLike2(int f1, int f2, String type) {
    if (type.equals("Fib")) {
      return f1 + f2 == this.first;
    }
    else if (type.equals("Pell")) {
      return f1 + f2 * 2 == this.first;
    }
    else if (type.equals("NegaFib")) {
      return f1 - f2 == this.first;
    }
    else if (type.equals("Jacobsthal")) {
      return 2 * f1 + f2 == this.first;
    }
    else {
      return false;
    }
  }

  // determine whether the list of number follow the Fibonacci numbers rule
  public boolean isFibLike() {
    return this.rest.isSequenceLike1(this.first, "Fib") && this.rest.isFibLike();
  }

  // determine whether the list of number follow the Pell numbers rule
  public boolean isPellLike() {
    return this.rest.isSequenceLike1(this.first, "Pell") && this.rest.isPellLike();
  }

  // determine whether the list of number follow the Nega-Fibonacci numbers rule
  public boolean isNegaFibLike() {
    return this.rest.isSequenceLike1(this.first, "NegaFib") && this.rest.isNegaFibLike();
  }

  // determine whether the list of number follow the Jacobsthal numbers rule
  public boolean isJacobsthalLike() {
    return this.rest.isSequenceLike1(this.first, "Jacobsthal") && this.rest.isJacobsthalLike();
  }

  // determine whether the list contain minimal legal amount of numbers
  public boolean legalLength(int n) {
    if (n == 1) {
      return true;
    }
    else {
      return this.rest.legalLength(n - 1);
    }
  }

  // find the largest number in the list
  public int largestNum(int largest) {
    if (this.first > largest) {
      return largestNum(this.first);
    }
    else {
      return this.rest.largestNum(largest);
    }
  }

  // locate the index of the largest number
  public int locateLargetsNumIndex(int largest, int index, int largestNumIndex) {
    if (this.first > largest) {
      return this.rest.locateLargetsNumIndex(this.first, index + 1, index);
    }
    else {
      return this.rest.locateLargetsNumIndex(largest, index + 1, largestNumIndex);
    }
  }

  // generate the same list with the given index number removed
  public ILoInt removeAtIndex(int index) {
    if (index == 0) {
      return this.rest;
    }
    else {
      return new ConsLoInt(this.first, this.rest.removeAtIndex(index - 1));
    }
  }

  // find the nth largest number
  public int nthLargestNumHelp(int n) {
    if (n == 1) {
      return this.largestNum(this.first);
    }
    else {
      return this.removeAtIndex(this.locateLargetsNumIndex(this.first, 0, 0))
          .nthLargestNumHelp(n - 1);
    }
  }

  // find the second largest number
  public int secondLargestNum() {
    if (this.legalLength(2)) {
      return nthLargestNumHelp(2);
    }
    else {
      throw new IllegalArgumentException(
          "The list is shorter than 2, so there is no second largest number");
    }
  }

  // find the fifth largest number
  public int fifthLargestNum() {
    if (this.legalLength(5)) {
      return nthLargestNumHelp(5);
    }
    else {
      throw new IllegalArgumentException(
          "The list is shorter than 5, so there is no fifth largest number");
    }
  }

  // count how many target are in the list
  public int countFrequency(int target) {
    if (this.first == target) {
      return 1 + this.rest.countFrequency(target);
    }
    else {
      return this.rest.countFrequency(target);
    }
  }

  // generate a bijection list represent the frequency of each element in the list
  public ILoInt frequencyList() {
    return new ConsLoInt(this.countFrequency(this.first), this.rest.frequencyList());
  }

  // find the number in the given index
  public int numAtIndex(int index) {
    if (index == 0) {
      return this.first;
    }
    else {
      return this.rest.numAtIndex(index - 1);
    }
  }

  // determine whether the list contain the target
  public boolean contains(int target) {
    return this.first == target || this.rest.contains(target);
  }

  // generate the same list with all target elements removed
  public ILoInt removeAll(int target) {
    if (this.first == target) {
      return this.rest.removeAll(target);
    }
    else {
      return new ConsLoInt(this.first, this.rest.removeAll(target));
    }
  }

  // find the n the most common number
  public int nthmostCommonNumHelper(int n) {
    if (n == 1) {
      return this.numAtIndex(this.frequencyList().locateLargetsNumIndex(0, 0, 0));
    }
    else {
      return this.removeAll(this.mostCommonNum()).nthmostCommonNumHelper(n - 1);
    }
  }

  // find the most common number
  public int mostCommonNum() {
    return nthmostCommonNumHelper(1);
  }

  // find the third most common number
  public int thirdMostCommonNum() {
    if (this.legalLength(3)) {
      return nthmostCommonNumHelper(3);
    }
    else {
      throw new IllegalArgumentException(
          "The list is shorter than 3, so there is no third most common number");
    }
  }
}

class ExamplesLoInt {
  ILoInt empty = new MtLoInt();

  ILoInt list0 = new ConsLoInt(1, new ConsLoInt(1,
      new ConsLoInt(1, new ConsLoInt(2, new ConsLoInt(2, new ConsLoInt(3, empty))))));

  ILoInt nat0 = new ConsLoInt(1, empty);
  ILoInt nat1 = new ConsLoInt(1, new ConsLoInt(2, empty));
  ILoInt nat2 = new ConsLoInt(1, new ConsLoInt(2, new ConsLoInt(3, empty)));
  ILoInt nat3 = new ConsLoInt(1, new ConsLoInt(2, new ConsLoInt(3, new ConsLoInt(4, empty))));
  ILoInt nat4 = new ConsLoInt(1,
      new ConsLoInt(2, new ConsLoInt(3, new ConsLoInt(4, new ConsLoInt(5, empty)))));

  ILoInt fib2 = new ConsLoInt(0, new ConsLoInt(1, new ConsLoInt(1, empty)));
  ILoInt fib5 = new ConsLoInt(0, new ConsLoInt(1,
      new ConsLoInt(1, new ConsLoInt(2, new ConsLoInt(3, new ConsLoInt(5, empty))))));
  ILoInt lucas5 = new ConsLoInt(2, new ConsLoInt(1,
      new ConsLoInt(3, new ConsLoInt(4, new ConsLoInt(7, new ConsLoInt(11, empty))))));

  ILoInt pell2 = new ConsLoInt(1, new ConsLoInt(2, new ConsLoInt(5, empty)));
  ILoInt pell4 = new ConsLoInt(1,
      new ConsLoInt(2, new ConsLoInt(5, new ConsLoInt(12, new ConsLoInt(29, empty)))));
  ILoInt pellLucas4 = new ConsLoInt(2,
      new ConsLoInt(6, new ConsLoInt(14, new ConsLoInt(34, new ConsLoInt(82, empty)))));

  ILoInt negaFib8 = new ConsLoInt(1,
      new ConsLoInt(1, new ConsLoInt(0, new ConsLoInt(1, new ConsLoInt(-1,
          new ConsLoInt(2, new ConsLoInt(-3, new ConsLoInt(5, new ConsLoInt(-8, empty)))))))));

  ILoInt jocabsthal8 = new ConsLoInt(0,
      new ConsLoInt(1, new ConsLoInt(1, new ConsLoInt(3, new ConsLoInt(5,
          new ConsLoInt(11, new ConsLoInt(21, new ConsLoInt(43, new ConsLoInt(85, empty)))))))));

  // at most 2 elements -> true
  boolean testIsFibLike(Tester t) {
    return t.checkExpect(empty.isFibLike(), true) && t.checkExpect(nat0.isFibLike(), true)
        && t.checkExpect(nat1.isFibLike(), true) && t.checkExpect(nat2.isFibLike(), true)
        && t.checkExpect(nat3.isFibLike(), false) && t.checkExpect(fib2.isFibLike(), true)
        && t.checkExpect(fib5.isFibLike(), true) && t.checkExpect(lucas5.isFibLike(), true);
  }

  boolean testIsPellLike(Tester t) {
    return t.checkExpect(empty.isPellLike(), true) && t.checkExpect(nat0.isPellLike(), true)
        && t.checkExpect(nat1.isPellLike(), true) && t.checkExpect(nat2.isPellLike(), false)
        && t.checkExpect(nat3.isPellLike(), false) && t.checkExpect(pell2.isPellLike(), true)
        && t.checkExpect(pell4.isPellLike(), true) && t.checkExpect(pellLucas4.isPellLike(), true);
  }

  boolean testIsNegaFibLike(Tester t) {
    return t.checkExpect(empty.isNegaFibLike(), true) && t.checkExpect(nat0.isNegaFibLike(), true)
        && t.checkExpect(nat1.isNegaFibLike(), true) && t.checkExpect(nat2.isNegaFibLike(), false)
        && t.checkExpect(nat3.isNegaFibLike(), false)
        && t.checkExpect(negaFib8.isNegaFibLike(), true);
  }

  boolean testIsJacobsthalLike(Tester t) {
    return t.checkExpect(empty.isJacobsthalLike(), true)
        && t.checkExpect(nat0.isJacobsthalLike(), true)
        && t.checkExpect(nat1.isJacobsthalLike(), true)
        && t.checkExpect(nat2.isJacobsthalLike(), false)
        && t.checkExpect(nat3.isJacobsthalLike(), false)
        && t.checkExpect(jocabsthal8.isJacobsthalLike(), true);
  }

  boolean testSecondLargestNum(Tester t) {
    // at least 2 elements
    return t.checkExpect(nat1.secondLargestNum(), 1) && t.checkExpect(nat2.secondLargestNum(), 2)
        && t.checkExpect(nat3.secondLargestNum(), 3) && t.checkExpect(fib2.secondLargestNum(), 1);
  }

  boolean testFifthLargestNum(Tester t) {
    // at least 5 elements
    return t.checkExpect(nat4.fifthLargestNum(), 1) && t.checkExpect(fib5.fifthLargestNum(), 1)
        && t.checkExpect(lucas5.fifthLargestNum(), 2);
  }

  boolean testMostCommonNum(Tester t) {
    // at least 1 element
    return t.checkExpect(nat0.mostCommonNum(), 1) && t.checkOneOf(nat1.mostCommonNum(), 1, 2)
        && t.checkOneOf(nat2.mostCommonNum(), 1, 2, 3) && t.checkExpect(fib2.mostCommonNum(), 1)
        && t.checkExpect(fib5.mostCommonNum(), 1);
  }

  boolean testThirdMostCommonNum(Tester t) {
    // at least 3 elements
    return t.checkOneOf(nat2.thirdMostCommonNum(), 1, 2, 3)
        && t.checkExpect(list0.thirdMostCommonNum(), 3);
  }
  
  boolean testExecption(Tester t) {
    return t.checkException( 
        new IllegalArgumentException("The list is empty, so there is no fifth largest number"),
        empty, "fifthLargestNum")
        && t.checkException( 
        new IllegalArgumentException("The list is empty, so there is no second largest number"),
        empty, "secondLargestNum")
        && t.checkException( 
        new IllegalArgumentException("The list is empty, so there is no most common number."),
        empty, "mostCommonNum")
        && t.checkException( 
        new IllegalArgumentException("The list is empty, so there is no third most common number"),
        empty, "thirdMostCommonNum")
        && t.checkException( 
        new IllegalArgumentException(
            "The list is shorter than 2, so there is no second largest number"),
        nat0, "secondLargestNum")
        && t.checkException( 
        new IllegalArgumentException(
            "The list is shorter than 3, so there is no third most common number"),
        nat0, "thirdMostCommonNum")
        && t.checkException( 
        new IllegalArgumentException(
            "The list is shorter than 5, so there is no fifth largest number"),
        nat0, "fifthLargestNum");
  }

}
