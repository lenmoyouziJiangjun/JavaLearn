package com.lll.aop.demo.account;/**
 * Created by liaoxueyan on 17/6/27.
 */

/**
 * Version 1.0
 * Created by lll on 17/6/27.
 * Description 方式一：装饰模式，添加检查权限
 * copyright generalray4239@gmail.com
 */
public class AccountOpeDecorator implements Account {
    Account mAccount;

    public AccountOpeDecorator(Account account){
        this.mAccount = account;
    }

    @Override
    public void operation() {
       if(SecurityChecker.checkSecurity()){
           mAccount.operation();
       }
    }

}
