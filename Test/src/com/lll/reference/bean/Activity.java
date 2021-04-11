package com.lll.reference.bean;

/**
 * Version 1.0
 * Created by lll on 2020-08-06.
 * Description
 * 模拟android activity
 * copyright generalray4239@gmail.com
 */
public class Activity {

    public String name;

    public Activity(String name) {
        this.name = name;
    }

    class InnerClass extends Thread {
        @Override
        public void run() {
            try {

                System.out.println(name + "------------innerClass before sleep");
                String tag = new String(name);
                sleep(10000);
                if (name == null) {
                    System.out.println(tag + "do after onDestroy");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void startInnerClass() {
        new InnerClass().start();
    }

    public void ondestroy() {
        this.name = null;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println(name + "--------do finalize");
    }
}
