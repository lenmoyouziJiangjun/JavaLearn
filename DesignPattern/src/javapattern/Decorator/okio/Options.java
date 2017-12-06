package javapattern.Decorator.okio;

import java.util.AbstractList;
import java.util.RandomAccess;

/**
 * Version 1.0
 * Created by lll on 17/10/9.
 * Description
 * copyright generalray4239@gmail.com
 */
public class Options extends AbstractList<ByteString> implements RandomAccess {
    final ByteString[] byteStrings;

    private Options(ByteString[] byteStrings) {
        this.byteStrings = byteStrings;
    }

    public static Options of(ByteString... byteStrings) {
        return new Options(byteStrings.clone()); // Defensive copy.
    }

    @Override public ByteString get(int i) {
        return byteStrings[i];
    }

    @Override public int size() {
        return byteStrings.length;
    }
}