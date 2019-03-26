package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.sun.istack.internal.Nullable;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static com.google.common.base.Preconditions.checkState;

/**
 * Version 1.0
 * Created by lll on 2019/2/26.
 * Description
 * Note this class is a copy of  (for dependency
 * reasons).
 * copyright generalray4239@gmail.com
 */
@GwtCompatible
abstract class AbstractIterator<T> implements Iterator<T> {
    private State state = State.NOT_READY;

    protected AbstractIterator() {
    }

    private enum State {
        READY,
        NOT_READY,
        DONE,
        FAILED,
    }

    private T next;

    protected abstract T computeNext();

    @Nullable
    protected final T endOfData() {
        state = State.DONE;
        return null;
    }

    @Override
    public final boolean hasNext() {
        checkState(state != State.FAILED);
        switch (state) {
            case READY:
                return true;
            case DONE:
                return false;
            default:
        }
        return tryToComputeNext();
    }

    private boolean tryToComputeNext() {
        state = State.FAILED; // temporary pessimism
        next = computeNext();
        if (state != State.DONE) {
            state = State.READY;
            return true;
        }
        return false;
    }

    @Override
    public final T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        state = State.NOT_READY;
        T result = next;
        next = null;
        return result;
    }

    @Override
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
