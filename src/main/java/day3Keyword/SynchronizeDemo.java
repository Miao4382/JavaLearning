package day3Keyword;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SynchronizeDemo {

    public static void main(String[] args) throws InterruptedException {
        // service uses 5 threads to do non-synchronized increment()
        ExecutorService service = Executors.newFixedThreadPool(5);
        // service2 uses 5 threads to do synchronized increment()
        ExecutorService service2 = Executors.newFixedThreadPool(5);

        Summation summation = new Summation();
        Summation summation2 = new Summation();

        for (int i = 0; i < 1000; i++) {
            service.submit(summation::increament);
        }

        for (int i = 0; i < 1000; i++) {
            service2.submit(summation2::synchronizedIncrement);
        }

        // wait for the termination
        service.awaitTermination(100, TimeUnit.MILLISECONDS);
        service2.awaitTermination(100, TimeUnit.MILLISECONDS);

        // print out the result. Non-synchronized is not 1000, synchronized is 1000
        System.out.println("Non-synchronized result = " + summation.getNum());
        System.out.println("Synchronized result = " + summation2.getNum());
        service.shutdown();
        service2.shutdown();
    }
}

class Summation {
    private int num = 0;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    // this method is not synchronized
    public void increament() {
        setNum(getNum() + 1);
    }

    // this method is synchronized (if multiple threads are accessing this concurrently, they will be arranged by the
    // locking mechanism to access this method one by one
    public synchronized void synchronizedIncrement() {
        setNum(getNum() + 1);
    }
}