package bg.softuni.aop.joinpoint;

import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class JoinPointSample {

  @Before("execution(* bg.softuni.aop.model.Student.echo(..))")
  public void beforeEcho(JoinPoint jp) {

    System.out.println(Arrays.toString(jp.getArgs()));
    System.out.println(jp.getSignature());
    System.out.println(jp.getKind());
    System.out.println(jp.getStaticPart());
    System.out.println(jp.toShortString());
  }

}
