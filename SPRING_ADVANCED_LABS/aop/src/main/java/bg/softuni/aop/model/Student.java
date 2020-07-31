package bg.softuni.aop.model;

import org.springframework.stereotype.Component;

@Component
public class Student {

  public void sayHello() {
    System.out.println("Hello, I'm a student!");
  }

  public void echo(String echo) {
    System.out.println("I have to echo: " + echo);
  }

}
