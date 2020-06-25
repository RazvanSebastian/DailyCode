package leastRecentlyUsedCache;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU (Least Recently Used) cache
 *
 * @param <K>
 * @param <V>
 */
public class LRUCache<K, V> implements LRUCacheActions<K, V> {

    private Map<K, Node<K, V>> cacheStore;
    private Node<K, V> dummyHead = new Node<>(null, null);
    private Node<K, V> dummyTail = new Node<>(null, null);
    private int capacity;

    public LRUCache(int capacity) {
        if (capacity < 1) {
            throw new RuntimeException("The cache capacity must greater than 0");
        }
        this.capacity = capacity;
        cacheStore = new HashMap<>();
        dummyHead.setRight(dummyTail);
        dummyTail.setLeft(dummyHead);
    }

    @Override
    public synchronized void put(K key, V value) {
        Node<K, V> newNode = new Node<>(key, value);
        // New node
        if (!cacheStore.containsKey(key)) {
            // if the cache is full remove the last one
            if (cacheStore.size() == capacity) {
                cacheStore.remove(dummyTail.getLeft().getKey());
                dummyTail = dummyTail.getLeft();
                dummyTail.setRight(null);
            }
            addNodeInQueue(newNode);
            cacheStore.put(key, newNode);
        }
        // Already in cache => update the node value and place it on the head of the queue
        else {
            Node<K, V> node = cacheStore.get(key);
            node.setValue(value);
            moveNodeInTheFrontOfQueue(node);
        }
    }

    @Override
    public V get(K key) {
        Node<K, V> node = cacheStore.get(key);
        moveNodeInTheFrontOfQueue(node);
        return node.getValue();
    }

    @Override
    public V evict(K key) {
        Node<K, V> node = cacheStore.get(key);
        if (node == null) {
            return null;
        }
        node.getLeft().setRight(node.getRight());
        node.getRight().setLeft(node.getLeft());
        cacheStore.remove(key);
        return node.getValue();
    }

    @Override
    public void show() {
        Node<K, V> node = dummyHead.getRight();
        while (node != dummyTail) {
            System.out.print(node.toString() + " ");
            node = node.getRight();
        }
        System.out.println();
    }

    private void addNodeInQueue(Node<K, V> node) {
        node.setRight(dummyHead.getRight());
        dummyHead.getRight().setLeft(node);
        node.setLeft(dummyHead);
        dummyHead.setRight(node);
    }

    /**
     * When a value from cache was retrieved, a replacement occur by putting the node which store the (key,value) pair in the front of the Queue
     *
     * @param node - node to move
     */
    private void moveNodeInTheFrontOfQueue(Node<K, V> node) {
        node.getLeft().setRight(node.getRight());
        node.getRight().setLeft(node.getLeft());
        addNodeInQueue(node);
    }
}
