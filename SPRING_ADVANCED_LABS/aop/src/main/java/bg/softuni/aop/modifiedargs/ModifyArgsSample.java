package bg.softuni.aop.modifiedargs;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ModifyArgsSample {

  @Pointcut("execution(* bg.softuni.aop.model.Student.echo(..))")
  public void trackEchoMethod(){

  }

  @Around("trackEchoMethod()")
  public Object adviceEcho(ProceedingJoinPoint pjp) throws Throwable {
    System.out.println("Starting the around advice.");
    Object arg = pjp.getArgs()[0];
    String modifiedArg = arg != null ? "Modified " + arg : "Modified null :-)";
    Object ret = pjp.proceed(new Object[]{modifiedArg});
    System.out.println("At the end of the advice...");
    return ret;
  }
}
