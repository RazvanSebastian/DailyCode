package leastRecentlyUsedCache;

public class Test {

    public static void main(String... args) {
        LRUCache<Integer,String> cache = new LRUCache(3);
        cache.put(2, "String2");
        cache.put(1, "String1");
        cache.put(3, "String3");
        cache.show();
        System.out.println("\n Add new on cache.size = max_capacity");
        cache.put(4, "String4");
        cache.show();
        System.out.println("\n Get from cache by key");
        System.out.print("Key = 4 => "+cache.get(4)+'\n');
        cache.show();
        System.out.println("\n Get from cache by key");
        System.out.print("Key = 1 => "+cache.get(1)+'\n');
        cache.show();
        System.out.println("\n Evict from cache by key");
        cache.evict(4);
        cache.show();
        cache.evict(3);
        cache.show();
        cache.evict(1);
        cache.show();
        cache.evict(2);
        cache.show();
    }
}
