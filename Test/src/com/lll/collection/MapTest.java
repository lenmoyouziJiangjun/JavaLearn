package com.lll.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * Version 1.0
 * Created by lll on 17/8/29.
 * Description
 * copyright generalray4239@gmail.com
 */
public class MapTest {


    public static void main(String[] args) {
        Map<String, String> params = new HashMap<>();
        params.put("key1", "value1");
        params.put("key2", "value2");
        params.put("key3", "value3");
        params.put("key4", "value4");
        System.out.print(params.toString());
    }
}
