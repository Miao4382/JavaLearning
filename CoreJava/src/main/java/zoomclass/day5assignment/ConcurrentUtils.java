package zoomclass.day5assignment;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class ConcurrentUtils {

    public static void sleep(long amt, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(amt);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void stop(ExecutorService executor) {
        try {
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        } finally {
            if (!executor.isTerminated()) {
                System.err.println("Shutting down all unfinished tasks");
            }
            executor.shutdownNow();
        }
    }
}
