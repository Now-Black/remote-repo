package test;




public class ceshi {
    int val;
    ceshi next;
    ceshi() {}
    ceshi(int val) { this.val = val; }
    ceshi(int val, ceshi next) { this.val = val; this.next = next; }

    public void test1(){

        Main.LRUCache now = new Main.LRUCache(1);
        now.capacity = 4;
    }

}
