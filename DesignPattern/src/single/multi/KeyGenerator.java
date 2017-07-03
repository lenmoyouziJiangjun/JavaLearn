package single.multi;

import java.util.HashMap;

/**
 * Version 1.0
 * Created by lll on 17/6/29.
 * Description
 * copyright generalray4239@gmail.com
 */
public class KeyGenerator {

    private static final HashMap<String, KeyGenerator> keyCache = new HashMap(10);
    private static final int POOL_SIZE = 20;
    private KeyInfo mKeyInfo;


    private KeyGenerator() {

    }

    private KeyGenerator(String keyName) {
        mKeyInfo = new KeyInfo(POOL_SIZE, keyName);
    }

    public static synchronized KeyGenerator getInstance(String keyName) {
        KeyGenerator keyGenerator;
        if (!keyCache.containsKey(keyName)) {
            keyGenerator = new KeyGenerator(keyName);
            keyCache.put(keyName, keyGenerator);
        } else {
            keyGenerator = keyCache.get(keyName);
        }
        return keyGenerator;
    }
}
