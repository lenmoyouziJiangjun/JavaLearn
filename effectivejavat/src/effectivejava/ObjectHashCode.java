package effectivejava;

import java.util.HashMap;
import java.util.Map;

/**
 * Version 1.0
 * Created by lll on 2019/2/25.
 * Description
 * <pre>
 * 一、重写HashCode的原则
 *
 * 二、hashCode：
 * 重写了equals方法，不重写hashCode会有什么问题？导致该类无法结合所有基于散列的集合(hashMap,HashSet,HashTable)一起正常运作。
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class ObjectHashCode {


    /**
     *
     */
    public static class PhoneNumber {

        private final short areaCode;
        private final short prefix;
        private final short lineNumber;

        public PhoneNumber(short areaCode, short prefix, short lineNumber) {
            this.areaCode = areaCode;
            this.prefix = prefix;
            this.lineNumber = lineNumber;
        }

        /**
         * @param obj
         * @return
         */
        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof PhoneNumber)) {
                return false;
            }
            PhoneNumber number = (PhoneNumber) obj;
            return number.prefix == this.prefix && number.areaCode == number.areaCode
                    && number.lineNumber == this.lineNumber;
        }
    }


    public static void main(String[] args) {
        Map<PhoneNumber, String> map = new HashMap<>();
        //我们把一个对象作为key存储在数据库，必须重写对象的hashCode方法。
        map.put(new PhoneNumber((short) 909, (short) 22, (short) 12121), "Jenny");



    }


}
