package com.example.demo;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheLogger implements CacheEventListener<Object, Object> {
  private final Logger logger = LoggerFactory.getLogger(CacheLogger.class);

  @Override
  public void onEvent(CacheEvent<?, ?> event) {
    logger.info("{} / {} / {} / {}", event.getType(), event.getKey(), event.getOldValue(), event.getNewValue());
  }
}
