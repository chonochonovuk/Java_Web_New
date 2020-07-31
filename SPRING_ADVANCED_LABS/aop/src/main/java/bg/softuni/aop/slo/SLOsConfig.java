package bg.softuni.aop.slo;

import java.util.LinkedList;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="slos-config")
public class SLOsConfig {

  private List<SLOConfig> slos = new LinkedList<>();

  public List<SLOConfig> getSlos() {
    return slos;
  }

  public SLOsConfig setSlos(List<SLOConfig> slos) {
    this.slos = slos;
    return this;
  }

  public SLOConfig getSLOById(String latencyId) {
    return slos.
        stream().
        filter(s -> s.id.equals(latencyId)).
        findAny().orElseThrow(() -> new IllegalArgumentException("Latency with id " + latencyId +
        " not found!"));
  }

  public static class SLOConfig {
    private String id;
    private int threshold;

    public String getId() {
      return id;
    }

    public int getThreshold() {
      return threshold;
    }

    public SLOConfig setThreshold(int threshold) {
      this.threshold = threshold;
      return this;
    }

    public SLOConfig setId(String id) {
      this.id = id;
      return this;
    }

    @Override
    public String toString() {
      return "SLOConfig{" +
          "id='" + id + '\'' +
          ", threshold=" + threshold +
          '}';
    }
  }

  @Override
  public String toString() {
    return "SLOsConfig{" +
        "slos=" + slos +
        '}';
  }
}
