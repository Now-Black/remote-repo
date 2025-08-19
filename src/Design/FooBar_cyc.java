package Design;

import java.util.concurrent.CyclicBarrier;

class FooBar_cyc {
    private int n;
    CyclicBarrier cy = new CyclicBarrier(2);
    volatile boolean tar = true;
    public FooBar_cyc(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while (!tar){
                Thread.yield();
            }

            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            tar = false;
            try {
                cy.await();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            try {
                cy.await();
            }catch (Exception e){
                e.printStackTrace();
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
        }
    }
}
