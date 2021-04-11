package com.lll.concurent.lock;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Version 1.0
 * Created by lll on 17/10/14.
 * Description  文件锁测试
 * copyright generalray4239@gmail.com
 */
public class FileLockLearn {


  public void randomFileChannel() throws IOException {
    File lockFile = new File("aaa", "MultiDex.lock");
    RandomAccessFile lockRaf = new RandomAccessFile(lockFile, "rw");
    FileChannel lockChannel = null;
    FileLock cacheLock = null;

    try {
      lockChannel = lockRaf.getChannel();
      System.out.println("Blocking on lock " + lockFile.getPath());
      cacheLock = lockChannel.lock();
    } finally {
      if (cacheLock != null) {
        try {
          cacheLock.release();
        } catch (IOException e) {
          System.out.println("Failed to release lock on " + lockFile.getPath());
          // Exception while releasing the lock is bad, we want to report it, but not at
          // the price of overriding any already pending exception.
        }
      }
      if (lockChannel != null) {
        closeQuietly(lockChannel);
      }
      closeQuietly(lockRaf);
    }
  }

  /**
   * Closes the given {@code Closeable}. Suppresses any IO exceptions.
   */
  private static void closeQuietly(Closeable closeable) {
    try {
      closeable.close();
    } catch (IOException e) {
      System.out.println("Failed to close resource");
    }
  }

}
