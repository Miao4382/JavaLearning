package zoomclass.day5assignment;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * To demonstrate the racing condition using a summation example.
 * The thread.sleep(100); at line 24 and line 33 are important. Otherwise, when we print the result, the thread may not
 * finish the looping yet, so the final result may not be 10000 even for synchronized increment() method.
 */
public class RacingConditionDemo {

    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        testSyncIncrement();
        testNonSyncIncrement();
        testSyncBlockIncrement();
    }

    public static void testNonSyncIncrement() throws InterruptedException {
        count = 0;
        ExecutorService executor = Executors.newFixedThreadPool(2);
        IntStream.range(0, 10000).forEach(i -> executor.submit(RacingConditionDemo::increment));
        Thread.sleep(100);
        System.out.println("No-Sync: " + count);
        executor.shutdown();
    }

    public static void testSyncIncrement() throws InterruptedException {
        count = 0;
        ExecutorService executor = Executors.newFixedThreadPool(2);
        IntStream.range(0, 10000).forEach(i -> executor.submit(RacingConditionDemo::incrementSync));
        Thread.sleep(100);
        System.out.println("Sync: " + count);
        executor.shutdown();
    }

    public static void testSyncBlockIncrement() throws InterruptedException {
        count = 0;
        ExecutorService executor = Executors.newFixedThreadPool(2);
        IntStream.range(0, 10000).forEach(i -> executor.submit(RacingConditionDemo::incrementSyncBlock));
        Thread.sleep(100);
        System.out.println("Block sync: " + count);
        executor.shutdown();
    }

    public static void increment() {
        count = count + 1;
    }

    public static synchronized void incrementSync() {
        count = count + 1;
    }

    public static void incrementSyncBlock() {
        synchronized (RacingConditionDemo.class) {
            count = count + 1;
        }
    }
}
