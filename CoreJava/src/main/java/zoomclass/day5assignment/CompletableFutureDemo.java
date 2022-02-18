package zoomclass.day5assignment;

import java.util.concurrent.*;

public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        completeFutureDemo();
    }

    /**
     * This method demos the block of future.get(). The thread calling this method will be blocked. Since we created the
     * future directly, there is no one completes it. So the program will be blocked there indefinitely.
     */
    private static void futureBlockDemo() throws InterruptedException, ExecutionException{
        Future<String> future = new CompletableFuture<>();
        System.out.println("Try accessing the result from an empty future instance, will block indefinitely:");
        System.out.println(future.get());
    }

    /**
     * This method demos we can use the complete() method in {@code CompletableFuture} to provide the "result" for the
     * future instance, and make it completable. So calling {@code future.get()} will not be blocked.
     */
    private static void completeFutureDemo() throws InterruptedException, ExecutionException{
        CompletableFuture<String> future = new CompletableFuture<>();
        future.complete("Completed");
        System.out.println(future.get());

        // or we can call the method calculateAsync() to give us a delayed completable future
        Future<String> future2 = calculateAsync();
        System.out.println("Second future result: ");
        System.out.println(future2.get());

    }

    /**
     * This method will create a {@code CompletableFuture<String>} instance.
     * A thread pool is created and the task running in this thread pool is to wait 500 ms, then call the {@code
     * complete()} method of the {@code CompletableFuture<String>}. We provide the value "Hello" when calling the
     * complete method.
     * @return an instance of a {@code CompletableFuture} class
     */
    public static Future<String> calculateAsync() throws InterruptedException{
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        // call the complete() method to set the value returned by future.get() to be the value provided here ("Hello")
        // so the completable future is completed. The effect is after 500 ms, the completableFuture instance can be
        // completed (can call get() method and get the result, not blocked from it)
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(() -> {
            Thread.sleep(500);
            completableFuture.complete("Hello");
            return null;
        });

        executor.shutdown();

        return completableFuture;
    }


}
