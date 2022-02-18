package zoomclass.day5assignment;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Concurrency API also supports counting semaphores. Whereas locks usually grant exclusive access to variables or
 * resources, a semaphore is capable of maintaining whole sets of permits (limited number of access is granted to a
 * certain resources).
 *
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        demo();
    }

    public static void demo() {
        // create a new semaphore with 3 available permits
        Semaphore semaphore = new Semaphore(3);

        // define the task
        Runnable task = () -> {
            boolean hasAccess = false;
            try {
                hasAccess = semaphore.tryAcquire();
                if (hasAccess) {
                    System.out.println("Semaphore permits acquired, starting task");
                    ConcurrentUtils.sleep(1, TimeUnit.SECONDS);
                } else {
                    System.out.println("No permit, abort");
                    ConcurrentUtils.sleep(100, TimeUnit.MILLISECONDS);
                }
            } finally {
                if (hasAccess) {
                    semaphore.release();
                }
            }
        };


        ExecutorService executor = Executors.newFixedThreadPool(5);
        // simulate a stream of 1000 tasks, and see how many tasks are executed
        IntStream.range(0, 1000).forEach(i -> executor.submit(task));

        // this will wait for termination for 5 sec, see the implementation of stop() method
        ConcurrentUtils.stop(executor);
    }
}
