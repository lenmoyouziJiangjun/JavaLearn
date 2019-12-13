package com.lll.aop.demo.account;/**
 * Created by liaoxueyan on 17/6/27.
 */

/**
 * Version 1.0
 * Created by lll on 17/6/27.
 * Description
 * copyright generalray4239@gmail.com
 */
public class AccountImpl implements Account {
  @Override
  public void operation() {
    System.out.println("挣了300块钱");
  }
}
