package Design;

import java.util.concurrent.locks.LockSupport;

class FooBar_locksupport {
    private int n;
    private volatile Thread foo,bar;
    public FooBar_locksupport(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            foo = Thread.currentThread();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            LockSupport.unpark(bar);
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            bar = Thread.currentThread();
            LockSupport.park();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            LockSupport.unpark(foo);
        }
    }
}
