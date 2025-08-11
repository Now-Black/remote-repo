package Design;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args){
        FooBar3 fooBar3 = new FooBar3(15);
        CompletableFuture<Void> completableFuture = fooBar3.start(new Runnable() {
            @Override
            public void run() {
                System.out.print("foo");
            }
        }, new Runnable() {
            @Override
            public void run() {
                System.out.println("bar");
            }
        });
        CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {

            }
        },executorService);
        System.out.println("hello");

    }
}
