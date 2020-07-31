package bg.softuni.aop;

import bg.softuni.aop.model.Student;
import bg.softuni.aop.slo.SLOsConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AopApplicationInit implements CommandLineRunner {

  private final SLOsConfig config;

  public AopApplicationInit(SLOsConfig config) {

    this.config = config;
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println(config);
  }
}
