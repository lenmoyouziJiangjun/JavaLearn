package com.lll.nio;/**
 * Created by liaoxueyan on 17/6/1.
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Version 1.0
 * Created by lll on 17/6/1.
 * Description 文件管道测试
 * copyright generalray4239@gmail.com
 */
public class FileChannelTest {


    public void testFileChannel(String filePath, String fileOutPath) throws IOException {
        RandomAccessFile readAndWriteFile = new RandomAccessFile(filePath, "rw");
        FileChannel readAndWriteChannel = readAndWriteFile.getChannel();

        FileInputStream fileInputStream = new FileInputStream(filePath);
        FileChannel readChannel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream(fileOutPath);
        FileChannel writeChannel = fileOutputStream.getChannel();


        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (readChannel.read(buffer) != -1) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                writeChannel.write(buffer);
            }
            buffer.clear();
        }

        readChannel.close();
        writeChannel.close();
    }

    /**
     * 文件锁机制，用来在进程之间进行文件的共享与独占锁定。注意两点，文件锁定是在进程之间进行的，一个进程的多个线程之间，文件锁定无效；
     * 第二，锁定分为共享锁与独占锁，但是若操作系统或文件系统不支持，则锁的种类会自动升级。
     * 例如若某个操作系统没有共享锁，则Java的共享锁会被自动升级为独占锁，以保证语法的正确性。
     * 但这样会带来极大的开销，因此在使用文件锁之前，请仔细研究程序的运行环境，确保不会因为文件锁而带来难以忍受的性能开销。
     * 下面的代码演示了文件锁的使用方法，代码需执行两次，每次使用不同的参数运行，FileLockExample –w（请先运行这个）和FileLockExample –r，其中一个进程获得文件锁以后，写入一个递增的数字至文件中的指定位置；而另一个进程获得文件锁以后从文件中读取那个数字：
     */
    private static void testFileLock() {

    }

    private static void lockAndWrite(){

    }
}
