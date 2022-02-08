package zoomclass.day5assignment;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LockStateDemo {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);

        // run the first task that will acquire the lock and sleep
        executor.submit(() -> {
            lock.lock();
            try {
                System.out.println("Sleep while holding the lock");
                ConcurrentUtils.sleep(100, TimeUnit.MILLISECONDS);
            } finally {
                lock.unlock();
            }
        });

        // run the second task that will try to get information about the lock
        Runnable lockTask = () -> {
            System.out.println("\nIs the lock being held: " + lock.isLocked());
            System.out.println("Is the lock held by me: " + lock.isHeldByCurrentThread());
            boolean grabLock = lock.tryLock();
            System.out.println("Did I acquire the lock: " + grabLock);
        };

        // you will notice that after the acquisition of the lock by the lockTask, additional messages show the lock is
        // not held by future threads, and they could not acquire the lock. This is because in the lockTask, the lock is
        // never unlocked.
        executor.scheduleAtFixedRate(lockTask, 0, 500, TimeUnit.MILLISECONDS);
    }
}
