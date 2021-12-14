package com.example.demo;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ScratchSpring2Application {

  public static void main(String[] args) {
    SpringApplication.run(ScratchSpring2Application.class, args);
  }

  @Bean
  public KeyGenerator keyGenerator(){
    return new KeyGenerator() {
      @Override
      public Object generate(Object target, Method method, Object... params) {
        return params[0];
      }
    };
  }

  @RestController
  public static class DefaultController {
    @Autowired
    CacheManager cacheManager;

    @Cacheable(value = "cacheStore", keyGenerator = "keyGenerator")
    @GetMapping
    public String test(String id) {
      return LocalDateTime.now().toString();
    }

    @GetMapping("test2")
    public String test2(){ //break point 를 걸어 cachemanager의 상태를 확인할 메소드
      return LocalDateTime.now().toString();
    }
  }

}
