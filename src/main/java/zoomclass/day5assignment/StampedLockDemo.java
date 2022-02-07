package zoomclass.day5assignment;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * When acquiring lock, the stamped lock returns a stamp which represents the state of the lock.
 * The lock can only be released if the given stamp matches the state of the lock
 */
public class StampedLockDemo {

    public static void main(String[] args) {
        convertLockDemo();
    }

    // shows how the optimistic lock works. When writeLock() is acquired, it can make previously acquired readLock()
    // invalid. So when working with optimistic locks you have to validate the lock every time after accessing any
    // shared mutable variable to make sure the read was still valid.
    public static void optimisticLock() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        StampedLock lock = new StampedLock();

        Runnable optimisticReader = () -> {
            long stamp = lock.tryOptimisticRead();
            try {
                System.out.println(lock.validate(stamp) ? "Read has lock" : "Reader lost lock");
                ConcurrentUtils.sleep(1, TimeUnit.SECONDS);

                System.out.println(lock.validate(stamp) ? "Read has lock" : "Reader lost lock");
                ConcurrentUtils.sleep(1, TimeUnit.SECONDS);

                System.out.println(lock.validate(stamp) ? "Read has lock" : "Reader lost lock");
                ConcurrentUtils.sleep(1, TimeUnit.SECONDS);

                System.out.println(lock.validate(stamp) ? "Read has lock" : "Reader lost lock");
                ConcurrentUtils.sleep(1, TimeUnit.SECONDS);
            } finally {
                lock.unlock(stamp);
            }
        };

        executor.submit(optimisticReader);

        executor.submit(() -> {
            long stamp = lock.writeLock();
            try {
                System.out.println("Writer acquired lock");
                ConcurrentUtils.sleep(1, TimeUnit.SECONDS);
            } finally {
                lock.unlock(stamp);
                System.out.println("Writer release lock");
            }
        });

        ConcurrentUtils.stop(executor);
    }

    // shows how to use the stamp lock as a ReadWriteLock
    public static void stampDemo() {
        Map<String, String> map = new HashMap<>();

        StampedLock lock = new StampedLock();

        Runnable readTask = () -> {
            long stamp = lock.readLock();
            try {
                System.out.println(map.getOrDefault("key", "Key not found!"));
                ConcurrentUtils.sleep(500, TimeUnit.MILLISECONDS);
            } finally {
                System.out.println("Unlocking read lock with stamp " + stamp);
                lock.unlockRead(stamp);
            }
        };

        Runnable writeTask = () -> {
            long stamp = lock.writeLock();
            try {
                ConcurrentUtils.sleep(1, TimeUnit.SECONDS);
                map.put("key", "Courage and patience");
            } finally {
                System.out.println("Unlocking write lock with stamp " + stamp);
                lock.unlockWrite(stamp);
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(readTask);
        executor.submit(writeTask);
        executor.submit(readTask);
        ConcurrentUtils.stop(executor);
    }

    // shows how to convert a read lock to a write lock using stamped lock
    public static void convertLockDemo() {
        List<Integer> nums = new ArrayList<>();
        StampedLock lock = new StampedLock();
        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(() -> {
            long stamp = lock.readLock();
            try {
                if (nums.isEmpty()) {
                    ConcurrentUtils.sleep(1, TimeUnit.SECONDS);
                    // here we try to convert the read lock to write lock
                    stamp = lock.tryConvertToWriteLock(stamp);
                    if (stamp == 0L) {
                        System.out.println("Could not convert to write lock, try lock.writeLock() instead");
                        // will be blocked until we acquire lock
                        stamp = lock.writeLock();
                    } else {
                        System.out.println("Conversion to write lock succeeded.");
                    }
                    // if we reach here, it means we have acquired the lock
                    nums.add(1);
                    System.out.println("After adding, read nums[n - 1] = " + nums.get(nums.size() - 1));
                } else {
                    System.out.println("Reading nums[n - 1] = " + nums.get(nums.size() - 1));
                }
            } finally {
                lock.unlock(stamp);
            }
        });

        // in fact, the writer thread will be blocked at lock.writeLock(), since the reader thread has already acquired
        // the read lock. Since it is not optimistic lock, it will prevent this thread acquiring write lock
        executor.submit(() -> {
            long stamp = lock.writeLock();
            try {
                nums.add(2);
                ConcurrentUtils.sleep(1, TimeUnit.SECONDS);
            } finally {
                lock.unlock(stamp);
            }
        });

        ConcurrentUtils.stop(executor);

        System.out.println("Final nums = " + nums);
    }
}
