package com.lll.net;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Version 1.0
 * Created by lll on 2018/7/9.
 * Description
 * copyright generalray4239@gmail.com
 */
public class HttpURLTest {

  private static final String BaseUrl = "https://api-app.glpfin.com/glp-api/v1.0/message/add";

  public static void main(String args[]) {
    HttpURLTest test = new HttpURLTest();
    try {
      test.run("apply_refuse");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  private void run(String text) throws IOException {
    // System.out.println(url2); // 反馈请带上此url，浏览器上可以测试
    HttpURLConnection conn = (HttpURLConnection) new URL(BaseUrl).openConnection();
    conn.setConnectTimeout(5000);
    conn.setRequestMethod("POST");
    conn.addRequestProperty("token", "0210143964992085");
    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    conn.setDoOutput(true);
    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
    // 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
    String content = "type=" + URLEncoder.encode(text, "UTF-8") + "&mobile=" + URLEncoder.encode("15328034239", "UTF-8");
    // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
    out.writeBytes(content);
    //流用完记得关
    out.flush();
    out.close();


    String res = ConnUtil.getResponseString(conn);
    System.err.println(res);
  }
}
