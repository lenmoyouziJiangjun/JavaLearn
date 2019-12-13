package com.lll.reflect.proxy;/**
 * Created by liaoxueyan on 17/6/15.
 */

/**
 * Version 1.0
 * Created by lll on 17/6/15.
 * Description 动态代理测试
 * copyright generalray4239@gmail.com
 */
public interface IConfig {
  @Value("db.url")
  String dbUrl();

  @Value("db.validation")
  boolean isValidated();

  @Value("db.pool.size")
  int poolSize();
}
