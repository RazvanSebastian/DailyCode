package leastRecentlyUsedCache;

public interface LRUCacheActions<K, V> {

    void put(K key, V value);

    V get(K key);

    /**
     *
     * @param key - Primitive wrapper or a custom object which must override hash method
     * @return The value stored into the cache if exists; otherwise return null
     */
    V evict(K key);

    void show();
}
