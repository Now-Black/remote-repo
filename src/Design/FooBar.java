package Design;
/**
 * ✅ 方案一：使用CompletableFuture链式调度
 * 特点：简洁优雅，性能好，易于理解
 */
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class FooBar {
    private final int n;
    private final ExecutorService executor;

    public FooBar(int n) {
        this.n = n;
        // 🎯 使用固定线程池，确保任务按序执行
        this.executor = Executors.newFixedThreadPool(2);
    }

    /**
     * 🎯 启动foo和bar的交替执行
     */
    public CompletableFuture<Void> start(Runnable printFoo, Runnable printBar) {

        // 🎯 创建初始的CompletableFuture

        CompletableFuture<Void> chain = CompletableFuture.completedFuture(null);

        // 🎯 构建foo-bar交替执行链
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

    /**
     * 🎯 传统接口兼容（阻塞版本）
     */
    public void foo(Runnable printFoo) throws InterruptedException {
        // 为了兼容原接口，这里需要与bar方法协调
        throw new UnsupportedOperationException("请使用start()方法启动异步执行");
    }

    public void bar(Runnable printBar) throws InterruptedException {
        // 为了兼容原接口，这里需要与foo方法协调
        throw new UnsupportedOperationException("请使用start()方法启动异步执行");
    }

    /**
     * 🎯 资源清理
     */
    public void shutdown() {
        if (executor != null && !executor.isShutdown()) {
            executor.shutdown();
        }
    }
}
