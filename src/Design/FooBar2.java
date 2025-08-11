package Design;

import java.util.concurrent.Semaphore;

/**
 * sempore test
 */
class FooBar2 {
    private int n;
    Semaphore semaphore_foo;
    Semaphore semaphore_bar;
    public FooBar2(int n) {
        this.n = n;
        this.semaphore_foo = new Semaphore(1);
        this.semaphore_bar = new Semaphore(0);
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            semaphore_foo.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            semaphore_bar.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            semaphore_bar.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            semaphore_foo.release();
        }
    }
}
