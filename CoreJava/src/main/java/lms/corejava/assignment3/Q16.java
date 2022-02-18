package lms.corejava.assignment3;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Demonstration of Shape, Circle, Rectangle, Square and the ability to compare them
 */
public class Q16 {

    public static void main(String[] args) {

        List<Shape> shapes =  Arrays.asList(
                new Circle(6), new Rectangle(2, 4), new Square(2)
        );

        // sort the list in ascending order
        Collections.sort(shapes);
        System.out.println(shapes);

    }
}
