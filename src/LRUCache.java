import java.util.HashMap;
import java.util.Map;

class LRUCache {
    static class Node{
        private Node pre;
        private Node next;
        private int val;
        private int key;
        public Node(int val,int key){
            this.val = val;
            this.key = key;
        }
    }
    private final int capacity;
    private final Node dummy;
    private final Map<Integer,Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        dummy = new Node(0, 0);
        dummy.next = dummy;
        dummy.pre = dummy;
        map = new HashMap<>();
    }

    public int get(int key) {
        if(map.containsKey(key)){
            delete(map.get(key));
            recent_node(map.get(key));
            return map.get(key).val;
        }else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            delete(map.get(key));
            recent_node(map.get(key));
            map.get(key).val = value;
        }else {
            Node cur = new Node(value, key);
            map.put(key,cur);
            recent_node(cur);
            if(map.size() > capacity){
                map.remove(dummy.pre.key);
                delete(dummy.pre);
            }
        }


    }
    private void recent_node(Node node){
        node.next = dummy.next;
        node.pre = dummy;
        dummy.next.pre = node;
        dummy.next = node;
    }
    private void delete(Node node){
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }
}