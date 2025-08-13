package Design;

class FooBar_yield {
    private int n;
    private boolean foo_permit = true;
    public FooBar_yield(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; ) {
            while (foo_permit) {
                i++;
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                foo_permit = false;
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; ) {
            while (!foo_permit) {
                i++;
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                foo_permit = true;
            }
        }
    }
}
