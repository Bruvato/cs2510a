
class Runner {
  String name;
  int age;
  int bib;
  boolean isMale;
  int pos;
  int time;

  boolean isMale() {
    return isMale;
  }

}

interface ILoRunner {
  ILoRunner findAllMale();
}

class MtLoRunner implements ILoRunner {

  public ILoRunner findAllMale() {
    return this;
  }

}

class ConsLoRunner implements ILoRunner {
  Runner first;
  ILoRunner rest;

  public ConsLoRunner(Runner first, ILoRunner rest) {
    this.first = first;
    this.rest = rest;
  }

  public ILoRunner findAllMale() {
    if (this.first.isMale()) {
      return new ConsLoRunner(this.first, this.rest.findAllMale());
    }

    return this.rest.findAllMale();
  }

}

interface IRunnerPred {
  boolean apply(Runner r);
}

class isMaleRunner implements IRunnerPred {
  public boolean apply(Runner r) {
    return r.isMale();
  }
}