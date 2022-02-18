package lms.corejava.multithread;

import java.util.concurrent.ThreadLocalRandom;

/**
 * The Bridge class provides a synchronized method cross() to cross a car. Using the synchronized keyword, this method
 * is thread-safe, only one thread can access it at the same time.
 */
public class Bridge {

    public synchronized void cross(Car car) throws InterruptedException {
        System.out.println(car + " has started crossing");
        long crossTime = ThreadLocalRandom.current().nextLong(1, 20);
        Thread.sleep(crossTime);
        System.out.println(car + " has crossed the bridge in " + crossTime + " milliseconds");
    }
}
