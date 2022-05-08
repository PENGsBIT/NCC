package P1;/**
 * @program: NCC
 * @author: zpc
 * @create: 2022-05-08 19:47
 **/

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: zpc
 * @Description:
 * @Create: 2022-05-08 19:47
 **/


public class CacheDemo {
    public static void main(String[] args) {
        CacheData myCache = new CacheData();
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.getData(temp + "");
            }, String.valueOf(i)).start();
        }
    }
}
class CacheData {
    private final Map<String, Object> cache = new HashMap<String, Object>();
    private final ReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();
    private final Random random = new Random();

    public Object getData(String key) {
        r.lock();
        Object value;
        try {
            value = cache.get(key);
            if (value == null) {                // Must release read lock before acquiring write lock
                r.unlock();
                w.lock();
                r.lock(); // Downgrade by acquiring read lock before releasing write lock
                try {
                    value = cache.get(key);
                    if (value == null) {
                        value = random.nextInt(1000);// 实际是去query DB 或者从其他地方获取
                        System.out.println(Thread.currentThread().getName() + " produces value "
                                + value + ", for key " + key);
                        cache.put(key, value);
                    }
                } finally {
                    w.unlock();// Unlock write, still hold read
                }
            }
        } finally {
            r.unlock();
        }
        System.out.println(Thread.currentThread().getName() + " gets value " + value);
        return value;
    }
}
