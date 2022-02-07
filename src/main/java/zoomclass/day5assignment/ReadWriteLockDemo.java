package zoomclass.day5assignment;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * The idea behind read-write locks is that it's usually safe to read mutable variables concurrently as long as nobody
 * is writing to this variable. So the read-lock can be held simultaneously by multiple threads as long as no threads
 * hold the write-lock.
 */
public class ReadWriteLockDemo {

    private static List<Integer> nums = new ArrayList<>();

    public static void main(String[] args) {
        writeLockBlockedByReadDemo();
    }

    public static void readWriteLock() {

        nums = new ArrayList<>();

        ReadWriteLock lock = new ReentrantReadWriteLock();

        Runnable writeTask = () -> {
            lock.writeLock().lock();
            try {
                ConcurrentUtils.sleep(1, TimeUnit.SECONDS);
                nums.add(3);
            } finally {
                lock.writeLock().unlock();
            }
        };

        Runnable readTask = () -> {
            lock.readLock().lock();
            try {
                if (nums.size() > 0)
                    System.out.println(nums.get(0));
                else
                    System.out.println("nums is empty!");
                ConcurrentUtils.sleep(2, TimeUnit.SECONDS);
            } finally {
                lock.readLock().unlock();
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(writeTask);
        executor.submit(readTask);
        executor.submit(readTask);
        ConcurrentUtils.stop(executor);
    }

    // this method uses reentrant lock. The read operation should be slower than the ReadWriteLock, since it does not
    // allow multiple read simultaneously. The read will be one by one
    public static void reentrantLock() {
        nums = new ArrayList<>();
        Lock lock = new ReentrantLock();

        Runnable writeTask = () -> {
            lock.lock();
            try {
                ConcurrentUtils.sleep(1, TimeUnit.SECONDS);
                nums.add(3);
            } finally {
                lock.unlock();
            }
        };

        Runnable readTask = () -> {
            lock.lock();
            try {
                if (nums.size() > 0)
                    System.out.println(nums.get(0));
                else
                    System.out.println("nums is empty!");
                ConcurrentUtils.sleep(2, TimeUnit.SECONDS);
            } finally {
                lock.unlock();
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(writeTask);
        executor.submit(readTask);
        executor.submit(readTask);
        ConcurrentUtils.stop(executor);
    }

    // this demo shows the readLock() will block the writeLock()
    public static void writeLockBlockedByReadDemo() {
        ExecutorService executor = Executors.newCachedThreadPool();
        nums = new ArrayList<>(Arrays.asList(1, 2, 3));
        ReadWriteLock lock = new ReentrantReadWriteLock();

        executor.submit(() -> {
            lock.readLock().lock();
            try {
                ConcurrentUtils.sleep(2, TimeUnit.SECONDS);
                System.out.println("Read after sleep: " + nums.get(nums.size() - 1));
            } finally {
                lock.readLock().unlock();
            }
        });

        executor.submit(() -> {
            boolean lockAcquired = lock.writeLock().tryLock();
            try {
                System.out.println("Is lock acquired: " + lockAcquired);
                if (lockAcquired) {
                    nums.add(4);
                }
            } finally {
                if (lockAcquired) {
                    lock.writeLock().unlock();
                }
            }
        });
        ConcurrentUtils.stop(executor);
    }
}
