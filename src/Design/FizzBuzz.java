package Design;

import java.math.BigDecimal;
import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class FizzBuzz {
    private int n;

    private volatile int idx = 0;
    Semaphore fizz = new Semaphore(0);
    Semaphore buzz = new Semaphore(0);
    Semaphore fizzbuzz = new Semaphore(0);
    Semaphore number = new Semaphore(1);
    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for(int i = 1 ; i < n ; i++){
            fizz.acquire();
            printFizz.run();
            number.release();
        }

    }


    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for(int i = 1 ; i <=n ; i++){
            buzz.acquire();
            printBuzz.run();
            number.release();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for(int i = 1 ; i <=n ; i++){
            fizzbuzz.acquire();
            printFizzBuzz.run();
            number.release();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1 ; i <= n ; i++){
        number.acquire();
        if (i % 3 != 0 && i % 5 != 0) {//开始打印
            printNumber.accept(i);
            number.release();
        } else if (i % 3 == 0 && i % 5 != 0) {//fizz开始打印
            fizz.release();
        } else if (i % 3 != 0 && i % 5 == 0) {//buzz开始打印
            buzz.release();
        } else {
            fizzbuzz.release();//fizzbuzz开始打印
        }

        }

    }
}
