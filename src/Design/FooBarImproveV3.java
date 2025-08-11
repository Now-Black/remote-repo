package Design;

import java.util.concurrent.*;

public class FooBarImproveV3 {
    private final int n;
    private final ExecutorService executor;

    public FooBarImproveV3(int n) {
        this.n = n;
        this.executor = Executors.newFixedThreadPool(2);
    }

    public CompletableFuture<Void> start(Runnable foo, Runnable bar) {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        CompletableFuture<Void> fooFuture = CompletableFuture.runAsync(() -> {
            try {
                for (int i = 0; i < n; i++) {
                    queue.take(); // 等待信号
                    foo.run();
                    queue.put("bar"); // 发送信号给bar
                }
            } catch (InterruptedException e) {}
        }, executor);

        CompletableFuture<Void> barFuture = CompletableFuture.runAsync(() -> {
            try {
                for (int i = 0; i < n; i++) {
                    queue.take(); // 等待信号
                    bar.run();
                    if (i < n - 1) queue.put("foo"); // 发送信号给foo
                }
            } catch (InterruptedException e) {}
        }, executor);

        // 启动第一个信号
        queue.offer("foo");

        return CompletableFuture.allOf(fooFuture, barFuture);
    }

    public void shutdown() {
        executor.shutdown();
    }
}
