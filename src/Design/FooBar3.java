package Design;

import java.util.concurrent.*;

/**
 * sempore test
 */
class FooBar3 {
    private final int n;
    private final ExecutorService executor;
    public FooBar3(int n) {
        this.n = n;
        this.executor = Executors.newFixedThreadPool(2);
    }
    public CompletableFuture<Void> start(Runnable foo , Runnable bar){

        CompletableFuture<Void> chain = CompletableFuture.completedFuture(null);
        /*创建一个已完成的起始点*/
        for(int i = 0 ; i < n ; i++){
            chain = chain.thenRunAsync(()->{foo.run();},executor);
            /*↑ 将 foo 任务追加到链的末尾*/
            chain = chain.thenRunAsync(()->{bar.run();},executor);
        }
        return chain;
    }

}
