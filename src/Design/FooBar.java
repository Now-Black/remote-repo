package Design;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class FooBar {
    private final int n;
    private final ExecutorService executor;

    public FooBar(int n) {
        this.n = n;
        this.executor = Executors.newFixedThreadPool(2);
    }

    public CompletableFuture<Void> start(Runnable printFoo, Runnable printBar) {

        CompletableFuture<Void> chain = CompletableFuture.completedFuture(null);
        for (int i = 0; i < n; i++) {
            final int iteration = i;

            // 先执行foo
            chain = chain.thenRunAsync(() -> {
                System.out.println("Iteration " + iteration + " - foo");
                printFoo.run();
            }, executor);

            // 再执行bar
            chain = chain.thenRunAsync(() -> {
                System.out.println("Iteration " + iteration + " - bar");
                printBar.run();
            }, executor);
        }

        return chain;
    }


}
