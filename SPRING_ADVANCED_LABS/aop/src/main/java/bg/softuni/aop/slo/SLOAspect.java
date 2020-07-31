package bg.softuni.aop.slo;

import bg.softuni.aop.slo.SLOsConfig.SLOConfig;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class SLOAspect {

  private static final Logger LOGGER = LoggerFactory.getLogger(SLOAspect.class);

  private final SLOsConfig config;

  SLOAspect(SLOsConfig config) {

    this.config = config;
  }

  @Around(value = "@annotation(TrackLatency)")
  public Object tackLatency(
      ProceedingJoinPoint pjp,
      TrackLatency TrackLatency
  ) throws Throwable {
    SLOConfig sloConfig = config.getSLOById(TrackLatency.latency());
    int maxLatency = sloConfig.getThreshold();

    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    Object ret = pjp.proceed();
    stopWatch.stop();

    long executionTime = stopWatch.getLastTaskTimeMillis();
    if (executionTime > maxLatency) {
      LOGGER.error("Method {} executed for {}ms which is more than the limit of {}ms.",
          pjp.getSignature(), executionTime, maxLatency);
    }

    return ret;
  }

}
