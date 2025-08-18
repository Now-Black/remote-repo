package Design;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

class FooBar_que {
    private int n;
    private BlockingQueue<Integer> foo = new ArrayBlockingQueue<>(1);
    private BlockingQueue<Integer> bar = new ArrayBlockingQueue<>(1);
    public FooBar_que(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            foo.put(i);
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            bar.put(i);
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            foo.take();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            bar.take();
        }
    }
}
