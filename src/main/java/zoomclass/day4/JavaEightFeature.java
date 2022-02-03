package zoomclass.day4;

import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;

public class JavaEightFeature {
    public static void main(String[] args) {
        optionalDemo();
    }

    public static void lambdaDemo() {
        // create an inline comparator
        Queue<int[]> heap = new PriorityQueue<>((o1, o2) -> (o2[0] - o1[0]));

        // create an instance of an interface
        Say say = () -> {
            System.out.println("Hello");
        };
        say.say();

        // create an instance of a functional interface
        Greeting greeting = () -> {
            System.out.println("Greetings");
        };
    }

    // TODO
    public static void supplierDemo() {

    }

    // TODO
    public static void consumerDemo() {

    }

    // TODO
    public static void predicateDemo() {

    }

    // TODO
    public static void functionDemo() {

    }

    // TODO
    public static void optionalDemo() {
        String str = null;

        Optional<String> opt = Optional.ofNullable(str);
        System.out.println(opt.orElse("String is null"));
    }
}

interface Say {
    void say();
}

/**
 * Functional interface annotation requires the interface has only one abstract method.
 */
@FunctionalInterface
interface Greeting {
    void say();
    default void sayHello() {
        System.out.println("Hello");
    }
}