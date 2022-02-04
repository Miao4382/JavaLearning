package lms.corejava.java8streamapi;

import java.util.List;

/**
 * Write a method that returns a comma separated string based on a given list of integers. Each element should be
 * preceded by the letter 'e' if the number is even, preceded by the letter 'o' if the number is odd. For example, if
 * the input list is (3,44), the output should be 'o3,e44'.
 */
public class Q2 {

    public static void main(String[] args) {
        Q2 q2 = new Q2();
        List<Integer> list = List.of(3, 44, 17, 16, 4, 3, 1, 9, 0);
        System.out.println(q2.getString(list));
    }

    public String getString(List<Integer> list) {
        return list.stream()
                .map(num -> num % 2 == 0 ? "e" + num : "o" + num)
                .reduce("", (res, s) -> (res + "," + s)).substring(1);
    }
}
