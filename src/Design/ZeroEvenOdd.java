package Design;

import sun.java2d.ReentrantContextProviderTL;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

class ZeroEvenOdd {
    private volatile int flag = 1;
    private int n;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for(int i = 0 ; i < n ; i++){
            lock.lock();
            while ((i-1)/4==0 || (i+1)/4==0){
                condition.await();
            }
            printNumber.accept(0);
            condition.signalAll();
            lock.unlock();
        }

    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for(int i = 0 ; i < n ; i++){
            lock.lock();
            while ((i-1)/4 != 0){
                condition.await();
            }
            printNumber.accept(i);
            condition.signalAll();
            lock.unlock();
        }


    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i = 0 ; i < n ; i++){
            lock.lock();
            while ((i+1)/4 != 0){
                condition.await();
            }
            printNumber.accept(i);
            condition.signalAll();
            lock.unlock();
        }
    }
}
