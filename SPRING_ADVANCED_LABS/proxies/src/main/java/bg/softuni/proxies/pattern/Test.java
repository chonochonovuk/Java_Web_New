package bg.softuni.proxies.pattern;

public class Test {

  public static void main(String[] args) {
    GreeterIfc myGreeter = new ProxyGreeter(new RealGreeter());
    myGreeter.greet();
  }

}
