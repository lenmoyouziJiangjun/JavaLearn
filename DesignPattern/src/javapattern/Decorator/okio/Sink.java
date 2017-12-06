package javapattern.Decorator.okio;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

/**
 * Version 1.0
 * Created by lll on 17/10/9.
 * Description 类似OutputStream
 * copyright generalray4239@gmail.com
 */
public interface Sink extends Closeable, Flushable{
    /** Removes {@code byteCount} bytes from {@code source} and appends them to this. */
    void write(Buffer source, long byteCount) throws IOException;

    /** Pushes all buffered bytes to their final destination. */
    @Override void flush() throws IOException;

    /** Returns the timeout for this sink. */
    Timeout timeout();

    /**
     * Pushes all buffered bytes to their final destination and releases the
     * resources held by this sink. It is an error to write a closed sink. It is
     * safe to close a sink more than once.
     */
    @Override void close() throws IOException;
}
