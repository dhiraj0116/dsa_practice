package random;

import java.util.HashMap;
import java.util.Map;

public final class LRUCache {

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));  // returns 1

        cache.put(3, 3);                   // evicts key 2 (least recently used)
        System.out.println(cache.get(2));  // returns -1 (not found)

        cache.put(4, 4);                   // evicts key 1
        System.out.println(cache.get(1));  // returns -1 (not found)
        System.out.println(cache.get(3));  // returns 3
        System.out.println(cache.get(4));  // returns 4
    }

    private static final class Node {
        final int key;
        int value;
        Node previous;
        Node next;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> nodeByKey = new HashMap<>();
    private final Node mostRecent = new Node(0, 0);
    private final Node leastRecent = new Node(0, 0);

    public LRUCache(int capacity) {
        this.capacity = capacity;
        mostRecent.next = leastRecent;
        leastRecent.previous = mostRecent;
    }

    public int get(int key) {
        Node node = nodeByKey.get(key);
        if (node == null) {
            return -1;
        }
        remove(node);
        addAfterMostRecent(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node existing = nodeByKey.get(key);
        if (existing != null) {
            existing.value = value;
            remove(existing);
            addAfterMostRecent(existing);
            return;
        }
        if (nodeByKey.size() == capacity) {
            Node nodeToEvict = leastRecent.previous;
            remove(nodeToEvict);
            nodeByKey.remove(nodeToEvict.key);
        }
        Node added = new Node(key, value);
        addAfterMostRecent(added);
        nodeByKey.put(key, added);
    }

    private void remove(Node node) {
        node.previous.next = node.next;
        node.next.previous = node.previous;
    }

    private void addAfterMostRecent(Node node) {
        node.previous = mostRecent;
        node.next = mostRecent.next;
        mostRecent.next.previous = node;
        mostRecent.next = node;
    }
}