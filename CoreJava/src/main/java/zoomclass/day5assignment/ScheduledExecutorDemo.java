package zoomclass.day5assignment;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * A scheduled executor service can schedule tasks to run either periodically or once after a certain amount of time has
 * elapse.
 */
public class ScheduledExecutorDemo {

    public static void main(String[] args) {
        scheduleWithFixedDelay();
    }

    /**
     * Only schedule the task once in a future timestamp
     */
    private static void scheduleOnce() {
        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> System.out.println("Scheduling..." + System.nanoTime());

        // we call schedule to schedule the task in future
        // the return type is a special ScheduledFuture<?>
        ScheduledFuture<?> future = scheduledExecutor.schedule(task, 3, TimeUnit.SECONDS);

        System.out.println("Remaining delay: " + future.getDelay(TimeUnit.MILLISECONDS) + " ms");

        scheduledExecutor.shutdown();
    }

    /**
     * Tasks will be scheduled no matter how long they run. For example, if the period is 1 sec, and the task uses 2 sec
     * to run, it means the task will be scheduled again before it finishes. the thread pool will work to capacity
     * very soon.
     */
    private static void scheduleAtFixedRate() {
        Runnable task = () -> System.out.println("This is a scheduled task from " + Thread.currentThread().getName());
        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(1);
        long initialDelay = 2;
        long period = 1;

        scheduledExecutor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);

        scheduledExecutor.shutdown();
    }

    /**
     * This method ensures that the interval between two schedules of the same task is fixed (i.e. it will count down
     * the interval only after the task ended. Unlike scheduleWithFixedRate.
     */
    private static void scheduleWithFixedDelay() {
        Runnable task = () -> System.out.println("This is a scheduled task from " + Thread.currentThread().getName());
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        long initialDelay = 0;
        long delay = 1;
        executor.scheduleWithFixedDelay(task, initialDelay, delay, TimeUnit.SECONDS);
    }
}
