package Design;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Semaphore;

public class BoundedBlockingQueue {
    Semaphore s1;
    Semaphore s2;
    Deque<Integer> deque;
    public BoundedBlockingQueue(int capacity){
        deque = new ArrayDeque<>();
        s1 = new Semaphore(capacity);
        s2 = new Semaphore(0);
    }
    public void enqueue(int element) throws InterruptedException {
        s1.acquire();
        deque.add(element);
        s2.release();
    }
    public int dequeue() throws Exception{
        s2.acquire();
        int a =  deque.remove();
        s1.release();
        return a;
    }
    public int size(){
        return deque.size();
    }
}
