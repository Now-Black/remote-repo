import java.util.HashMap;
import java.util.Map;

class LRUCache {
    class Node{
        int val;
        Node next;
        Node pre;
        public Node(int val,Node node,Node pre){
            this.val = val;
            this.next =node;
            this.pre = pre;
        }
        public Node(int val){
            this.val = val;
        }
    }
    private Map<Integer,Node> map;
    private int capatity;
    private Node dummy;
    public LRUCache(int capacity) {
        this.capatity = capacity;
        this.map = new HashMap<>();
        this.dummy = new Node(0);
    }

    public int get(int key) {
        if(!map.containsKey(key)){return -1;}
        else {
            fre_use(key);
            return map.get(key).val;
        }
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            map.get(key).val = value;
            fre_use(key);
        }else {
            Node cur = new Node(value);

            Node temp = dummy.next;
            dummy.next = cur;
            cur.next = temp;



            map.put(key,cur);
            if(map.size()>capatity){

                delete_fre();
            }
        }
    }

    private void fre_use(int key){
        Node cur = map.get(key);


    }
    private void delete_fre(){


    }
}