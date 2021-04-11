package com.lll.concurent.exector;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Version 1.0
 * Created by lll on 2019-12-18.
 * Description
 * copyright generalray4239@gmail.com
 */
public class ExectorTest {

    ExecutorService service = Executors.newCachedThreadPool();


    class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            return null;
        }
    }

}
