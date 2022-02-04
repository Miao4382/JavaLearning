package lms.corejava.multithread;

public class Car {
    static int idCount = 0;
    String direction;
    int id;

    public Car(String direction) {
        this.direction = direction;
        this.id = idCount++;
    }

    @Override
    public String toString() {
        return direction + " car-" + id;
    }
}
