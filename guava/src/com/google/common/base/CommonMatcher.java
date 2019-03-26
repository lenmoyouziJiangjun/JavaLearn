package com.google.common.base;

/**
 * Version 1.0
 * Created by lll on 2019/2/26.
 * Description
 * copyright generalray4239@gmail.com
 */
public abstract class CommonMatcher {
    abstract boolean matches();

    abstract boolean find();

    abstract boolean find(int index);

    abstract String replaceAll(String replacement);

    abstract int end();

    abstract int start();
}
