package day3Keyword;

import java.util.ArrayList;
import java.util.List;

public class VolatileDemo {

    private volatile static int num;
    private static boolean ready;

    static class Reader implements Runnable {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            // when this read happens, num may not reach 9999 yet (jump from main thread to this thread in middle of the
            // for loop).
            System.out.println("Read num = " + num);
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new Reader());
        thread.start();
        ready = true;
        for (int i = 0; i < 10000; i++) {
            num = i;
        }
        System.out.println("Final num = " + num);
    }
}
