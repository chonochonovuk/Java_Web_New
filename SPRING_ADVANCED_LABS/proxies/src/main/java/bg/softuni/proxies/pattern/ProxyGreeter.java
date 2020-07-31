package bg.softuni.proxies.pattern;

public class ProxyGreeter implements GreeterIfc {

  private final GreeterIfc delegate;

  public ProxyGreeter(GreeterIfc delegate) {
    this.delegate = delegate;
  }

  @Override
  public void greet() {
    System.out.println("Before execution.");
    delegate.greet();
    System.out.println("After execution.");
  }
}
