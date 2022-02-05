package zoomclass.day5assignment;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class LockDemo {

    public static int count = 0;
    public static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        testLockSync();
    }

    public static void testLockSync() throws InterruptedException {
        count = 0;
        ExecutorService executor = Executors.newFixedThreadPool(2);
        IntStream.range(0, 10000).forEach(i -> executor.submit(LockDemo::incrementLockSync));
        executor.awaitTermination(100, TimeUnit.MILLISECONDS);
        System.out.println("Sync increment result: " + count);
        executor.shutdown();
    }

    public static void incrementLockSync() {
        lock.lock();
        // we need to do the critical section in try/finally block, to make sure the lock can be unlocked. This is to
        // avoid the case when exception happens during the execution, and the lock can not be unlocked.
        try {
            count = count + 1;
        } finally {
            lock.unlock();
        }
    }

    public static void increment() {
        count = count + 1;
    }
}
