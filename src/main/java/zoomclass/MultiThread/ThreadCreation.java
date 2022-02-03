package zoomclass.MultiThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadCreation {

    // multi-thread by
    static class FromThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello world from Thread thread " + Thread.currentThread().getName());
        }
    }

    static class FromRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello world from Runnable thread " + Thread.currentThread().getName());
        }
    }

    static class FromCallable implements Callable {
        @Override
        public Integer call() {
            System.out.println("Hello world from Callable thread " + Thread.currentThread().getName());
            return 300;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread t1 = new FromThread();
        t1.start();

        Thread t2 = new Thread(new FromRunnable());
        t2.start();

        // use future task to run the Callable
        FutureTask task = new FutureTask(new FromCallable());
        Thread t3 = new Thread(task);
        t3.start();
        // we can get the return value from the task.get() method
        System.out.println(task.get());
    }
}
