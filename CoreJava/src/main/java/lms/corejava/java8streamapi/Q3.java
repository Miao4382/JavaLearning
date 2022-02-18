package lms.corejava.java8streamapi;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Write a method that returns the average of a list of integers
 */
public class Q3 {

    public static void main(String[] args) {
        List<Integer> list = Stream.of(1, 2, 3, 4, 5, 6).collect(Collectors.toList());
        System.out.println(new Q3().average(list));
    }

    public Double average(List<Integer> list) {
        return list.stream().mapToInt(num -> num).average().orElse(0);
    }
}
