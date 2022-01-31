package day4assignment.functionalinterface;

import java.util.function.BiFunction;

public class BiFunctionDemo {
    public static void main(String[] args) {
        int num1 = 1, num2 = 100;
        System.out.println(addOneAndMultiply.apply(num1, num2));
        System.out.println("\nInsert to middle example: " + insertCharToMiddle.apply("abcd", 'O'));
        System.out.println("\nInsert to front example: " + insertCharToFront.apply("abcd", 'O'));
    }

    static BiFunction<Integer, Integer, Integer> addOneAndMultiply = (num1, num2) -> (num1 + 1) * num2;

    // another example of inserting a character into the middle of a string
    static BiFunction<String, Character, String> insertCharToMiddle =
            (s, c) -> new StringBuilder(s).insert(s.length() / 2, c).toString();

    static BiFunction<String, Character, String> insertCharToFront =
            (s, c) -> {
                StringBuilder sb = new StringBuilder(s);
                sb.insert(0, c);
                return sb.toString();
            };
}
