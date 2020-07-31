package bg.softuni.proxies.cache;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Cacheable {
  String value();
}
