package bg.softuni.aop.basic;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class BasicPointcutSample {

  @Pointcut("execution(* bg.softuni.aop.model.Student.sayHello())")
  public void trackSayHello() { }

  //@Before("trackSayHello()")
  public void logBeforeTheMethod() {
    System.out.println("Before executing of say hello!");
  }

  //@After("trackSayHello()")
  public void logAfterTheMethod() {
    System.out.println("After executing of say hello!");
  }

  @Around("trackSayHello()")
  public Object logAroundMethodExecution(ProceedingJoinPoint pjp) throws Throwable {
    System.out.println("Before execution");
    Object ret = pjp.proceed();
    System.out.println("After execution");
    return ret;
  }
}
