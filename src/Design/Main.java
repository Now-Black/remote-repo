package Design;
import java.util.*;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();


        System.out.print("请输入缓存容量: ");
        int capacity = scanner.nextInt();

        LRUCache lruCache = new LRUCache(capacity);

        System.out.println("支持的操作：");
        System.out.println("1. get <key> - 获取key的值");
        System.out.println("2. put <key> <value> - 插入/更新key-value对");
        System.out.println("3. exit - 退出程序");

        while (true) {
            System.out.print("请输入操作: ");
            String operation = scanner.next();

            if ("exit".equals(operation)) {
                break;
            } else if ("get".equals(operation)) {
                int key = scanner.nextInt();
                int result = lruCache.get(key);
                System.out.println("结果: " + result);
            } else if ("put".equals(operation)) {
                int key = scanner.nextInt();
                int value = scanner.nextInt();
                lruCache.put(key, value);
                System.out.println("插入成功");
            } else {
                System.out.println("无效操作，请重新输入");
            }
        }

        scanner.close();
    }

}
class LRUCache {

    class Node {
        int key;      // 键
        int value;    // 值
        Node prev;    // 前驱节点
        Node next;    // 后继节点

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;                    // 缓存容量
    private Map<Integer, Node> cache;        // HashMap存储key到节点的映射
    private Node head;                       // 虚拟头节点（最近使用）
    private Node tail;                       // 虚拟尾节点（最久未使用）


    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();

        // 创建虚拟头尾节点，简化边界处理
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }


    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;  // key不存在
        }

        // key存在，将节点移动到头部（标记为最近使用）
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);

        if (node != null) {
            // key已存在，更新值并移动到头部
            node.value = value;
            moveToHead(node);
        } else {
            // key不存在，需要插入新节点
            Node newNode = new Node(key, value);

            if (cache.size() >= capacity) {
                // 容量已满，删除尾部节点（LRU）
                Node lastNode = removeTail();
                cache.remove(lastNode.key);
            }

            // 插入新节点到头部
            cache.put(key, newNode);
            addToHead(newNode);
        }
    }

    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private Node removeTail() {
        Node lastNode = tail.prev;
        removeNode(lastNode);
        return lastNode;
    }


}




