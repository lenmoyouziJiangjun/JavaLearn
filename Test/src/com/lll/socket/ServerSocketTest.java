package com.lll.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Version 1.0
 * Created by lll on 2020-03-02.
 * Description
 * <pre>
 *    定义一个socket 服务器
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class ServerSocketTest extends Thread {

    private ServerSocket mServerSocket;

    public int getPort() {
        return mServerSocket.getLocalPort();
    }

    @Override
    public void run() {
        try {
            mServerSocket = new ServerSocket(0);// 端口0 表示空闲端口
            while (true) {
                Socket socket = mServerSocket.accept();//阻塞接收客户端的数据

                RequestSocketHandler handler = new RequestSocketHandler(socket);
                handler.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ServerSocketTest socketTest = new ServerSocketTest();
        socketTest.start();

        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        Socket socket = new Socket(InetAddress.getLocalHost(), socketTest.getPort());
                        System.out.println("the socket connected===" + socket.isConnected());
                        socket.getInputStream().read(("我是客户端").getBytes());
                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        reader.lines().forEach(s -> System.out.println(s));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}



class RequestSocketHandler extends Thread {
    private Socket mSocket;

    public RequestSocketHandler(Socket socket) {
        mSocket = socket;
    }

    @Override
    public void run() {
        try {
            PrintWriter writer = new PrintWriter(mSocket.getOutputStream());

            writer.println("hello :" + mSocket.getInetAddress() + " keep alive==" + mSocket.getKeepAlive());
            writer.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
