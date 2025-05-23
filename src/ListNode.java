  public class ListNode extends Main{
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        public void test(){
              LRUCache now = new LRUCache(1);
              now.capacity = 4;
        }
  }
