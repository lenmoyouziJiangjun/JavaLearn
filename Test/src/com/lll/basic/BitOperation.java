package com.lll.basic;

/**
 * Version 1.0
 * Created by lll on 2020-04-07.
 * Description
 * <pre>
 *     位运算
 *
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class BitOperation {

    protected int mGroupFlags;

    static final int FLAG_CLIP_CHILDREN = 0x1;

    // When set, ViewGroup excludes the padding area from the invalidate rectangle
    // Set by default
    private static final int FLAG_CLIP_TO_PADDING = 0x2;

    // When set, there is either no layout animation on the ViewGroup or the layout
    // animation is over
    // Set by default
    static final int FLAG_ANIMATION_DONE = 0x10;
    private static final int FLAG_ANIMATION_CACHE = 0x40;
    /**
     * @deprecated functionality removed
     */
    private static final int FLAG_ALWAYS_DRAWN_WITH_CACHE = 0x4000;


    /**
     * When set, this ViewGroup should not intercept touch events.
     * {@hide}
     */
    protected static final int FLAG_DISALLOW_INTERCEPT = 0x80000;

    /**
     * When set, this ViewGroup will split MotionEvents to multiple child Views when appropriate.
     */
    private static final int FLAG_SPLIT_MOTION_EVENTS = 0x200000;

    public BitOperation() {
        mGroupFlags |= FLAG_CLIP_CHILDREN;
        mGroupFlags |= FLAG_CLIP_TO_PADDING;
        mGroupFlags |= FLAG_ANIMATION_DONE;
        mGroupFlags |= FLAG_ANIMATION_CACHE;
        mGroupFlags |= FLAG_ALWAYS_DRAWN_WITH_CACHE;
        mGroupFlags |= FLAG_SPLIT_MOTION_EVENTS;
        printGroupFlags("init");
    }

    private void printGroupFlags(String content) {
        System.out.println("------------" + content + "---------------");
        printNumHex(mGroupFlags);
    }

    private void printNumHex(int num) {
        String flag = Integer.toHexString(num);
        System.out.println("num====" + num + "    the flag ==" + flag);
    }

    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        if (disallowIntercept == ((mGroupFlags & FLAG_DISALLOW_INTERCEPT) != 0)) {
            // We're already in this state, assume our ancestors are too
            return;
        }

        if (disallowIntercept) {
            printGroupFlags("before disallowIntercept true");
            mGroupFlags |= FLAG_DISALLOW_INTERCEPT;

            printGroupFlags("after disallowIntercept true");
        } else {
            printGroupFlags("before disallowIntercept false");
            mGroupFlags &= ~FLAG_DISALLOW_INTERCEPT;
            printGroupFlags("before disallowIntercept false");
        }
    }

    public void getDisallowFlagState() {
        printNumHex((mGroupFlags & FLAG_DISALLOW_INTERCEPT));
        final boolean disallowIntercept = (mGroupFlags & FLAG_DISALLOW_INTERCEPT) != 0;
        if (!disallowIntercept) {
            System.out.println("the result == false");
        } else {
            System.out.println("the result == true");
        }
    }


    public static void main(String[] args) {
        BitOperation bitOperation = new BitOperation();
//        bitOperation.requestDisallowInterceptTouchEvent(true);
//        bitOperation.getDisallowFlagState();

        bitOperation.printNumHex(1);

    }
}
