package bg.softuni.proxies.cache;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class StudentServiceIfcInvocationHandler implements InvocationHandler {

  private Object realObj;
  private Map<String, Object> cache = new HashMap<>();

  public StudentServiceIfcInvocationHandler(Object realObj) {
    this.realObj = realObj;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    Cacheable cacheableAnnotation = realObj.
        getClass().
        getMethod(method.getName(), method.getParameterTypes()).
        getAnnotation(Cacheable.class);

    if (cacheableAnnotation != null) {
      if (cache.containsKey(cacheableAnnotation.value())) {
        return cache.get(cacheableAnnotation.value());
      } else {
        Object toCache = method.invoke(realObj, args);
        cache.put(cacheableAnnotation.value(), toCache);
        return toCache;
      }
    } else {
      return method.invoke(realObj, args);
    }
  }
}
