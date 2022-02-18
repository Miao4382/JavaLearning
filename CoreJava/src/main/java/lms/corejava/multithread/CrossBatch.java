package lms.corejava.multithread;

import java.util.concurrent.ThreadLocalRandom;

/**
 * The class CrossBatch implements the runnable, it uses a bridge to "cross" all the car stored in the batch.
 */
public class CrossBatch implements Runnable{

    String direction;
    Car[] cars;
    Bridge bridge;
    int carNum;

    public CrossBatch(String direction, int carNum, Bridge bridge) {
        this.direction = direction;
        this.carNum = carNum;
        this.bridge = bridge;

        cars = new Car[carNum];
        for (int i = 0; i < carNum; i++) {
            cars[i] = new Car(direction);
        }
    }

    @Override
    public void run() {
        for (Car car : cars) {
            try {
                Thread.sleep(ThreadLocalRandom.current().nextLong(1, 200));    // wait time
                bridge.cross(car);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
