package effectivejava;

import java.awt.*;

/**
 * Version 1.0
 * Created by lll on 2019/2/25.
 * Description
 * object 类方法复写原则
 * 一、equals
 *
 *
 *
 * copyright generalray4239@gmail.com
 */
public class ObjectEqualsLearn {

    /**
     * 一、Equals
     * 1、每个类的实例本质上是唯一的
     * 2、类是私有的或者包级私有的，可以确定它的equals方法永远不会被调用
     */
    public final class CaseInsensitiveString {
        private final String s;

        public CaseInsensitiveString(String s) {
            if (s == null) {
                throw new NullPointerException();
            }
            this.s = s;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof CaseInsensitiveString) {
                return s.equalsIgnoreCase(((CaseInsensitiveString) obj).s);
            }
//            if (obj instanceof String) {  //这段代码违背了自反性，只能调用CaseInsensitiveString.equals(String) 而不能调用String.equals(CaseInsensitiveString)
//                ((String) obj).equalsIgnoreCase(s);
//            }
            return false;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }

    /**
     *
     */
    public class Point {

        public final int x;
        public final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Point)) {
                return false;
            }
            Point p = (Point) obj;
            return p.x == this.x && p.y == y;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }

    public class ColorPoint extends Point {
        private final Color color;

        public ColorPoint(int x, int y, Color color) {
            super(x, y);
            this.color = color;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof ColorPoint)) {
                return false;
            }
            ColorPoint point = (ColorPoint) obj;
            return point.x == this.x && point.y == this.y && point.color.equals(this.color);
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }

}
