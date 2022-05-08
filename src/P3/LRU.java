package P3;/**
 * @program: NCC
 * @author: zpc
 * @create: 2022-05-08 19:57
 **/

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: zpc
 * @Description:
 * @Create: 2022-05-08 19:57
 **/


public class LRU<K,V> extends LinkedHashMap<K, V> {
    private int capacity;

    public LRU(int initialCapacity, float loadFactor, boolean accessOrder) {
        super(initialCapacity, loadFactor, accessOrder);
        this.capacity = initialCapacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        if(size() > capacity){
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        LRU<String, Integer> lru = new LRU<>(8, 0.75f, true);
        String s = "my,test,tt,t1,t2,t3,my,tt,my,t1";
        String[] sp = s.split(",");
        for (int i = 0; i < sp.length; i++) {
            lru.put(sp[i], i);
        }
        System.out.println(lru.get("t1"));
        Iterator iter = lru.entrySet().iterator();
        while (iter.hasNext()){
            System.out.println(iter.next());
        }
    }
}

