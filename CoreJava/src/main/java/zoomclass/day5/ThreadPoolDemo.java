package zoomclass.day5;

import java.util.concurrent.*;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        int corePoolSize = 2, maxPoolSize = 5;
        long keepAliveTime = 2L;
        TimeUnit timeUnit = TimeUnit.SECONDS;
        ArrayBlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(4);
        ThreadPoolExecutor.AbortPolicy abortPolicy = new ThreadPoolExecutor.AbortPolicy();

        // create customized thread pool
        ExecutorService threadPool = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                timeUnit,
                blockingQueue,
                Executors.defaultThreadFactory(),
                abortPolicy
        );

        // we are pushing ten tasks to the thread pool to execute. After the maxPoolSize has been reached, additional
        // tasks will be put to the blockingQueue. The size of the blockingQueue is set to 4, so when the last task come
        // in, the queue is full. A RejectedExecutionException will be thrown
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            threadPool.execute(() -> System.out.println(Thread.currentThread().getName() + " is running for i = " + finalI));
        }

        threadPool.shutdown();
    }

    static void predefinedThreadPoolDemo() {
        ExecutorService pool1 = Executors.newFixedThreadPool(5);    // core = 5, max = 5
        ExecutorService pool2 = Executors.newSingleThreadExecutor();        // core 1, max 1
        ExecutorService pool3 = Executors.newCachedThreadPool();            // core 0, max = Integer.MAX_VALUE
        ExecutorService pool4 = Executors.newScheduledThreadPool(3);    // core = 3
    }
}
