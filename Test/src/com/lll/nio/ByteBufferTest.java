package com.lll.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Version 1.0
 * Created by lll on 17/6/1.
 * Description byteBuffer 测试
 * copyright generalray4239@gmail.com
 */
public class ByteBufferTest {

    public static void main(String[] args) throws Exception {
//        testByteBuffer();
        testFloatBuffer();
    }

    public static void testByteBuffer() throws IOException {
        //键盘输入
        ReadableByteChannel readableByteChannel = Channels.newChannel(System.in);
        //输出到屏幕
        WritableByteChannel writableByteChannel = Channels.newChannel(System.out);
        ByteBuffer buffer = ByteBuffer.allocate(1024);//定义一个1024字节的缓冲区
        try {
            while (readableByteChannel.read(buffer) != -1) {
                buffer.flip();//刷新缓冲区
                while (buffer.hasRemaining()) {//buffer里面是否还有数据
                    writableByteChannel.write(buffer);
                }
                buffer.clear();//清空缓冲区
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testFloatBuffer() {
        //定义一个浮点数组
        float[] t = new float[]{1.f, 3.5f, 2.4f, 3.1f, 1.2f};
        int size = t.length * Float.BYTES;
        FloatBuffer buffer = ByteBuffer.allocateDirect(size).order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        buffer.put(t, 0, t.length).position(0);

        System.out.println("Float.BYTES==="+Float.BYTES+"====toString=="+buffer.toString());

    }


}
