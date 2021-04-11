package com.lll.basic;

import java.util.Scanner;

/**
 * Version 1.0
 * Created by lll on 2020-05-13.
 * Description
 * <pre>
 *
 *     键盘输入测试
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class ScannerTest {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextInt()) {
            scanner.nextInt();//读取输入
        }
    }
}
