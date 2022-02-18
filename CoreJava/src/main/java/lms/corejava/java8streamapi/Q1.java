package lms.corejava.java8streamapi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a list of Objects, write a method that returns a list of all strings that start with the letter 'a'
 * (lower case) and have exactly 3 letters
 */
public class Q1 {

    static class Person {
        String name;
        int age;
        char sex;

        public Person(String name, int age, char sex) {
            this.name = name;
            this.age = age;
            this.sex = sex;
        }
    }

    public static void main(String[] args) {
        List<Object> objects = List.of(
                2, 3, 2.4,
                "asgf", "jf", "abc", "a12", "abcd", "tgf",
                new ArrayList<Integer>(), new Person("a12", 50, 'M')
        );

        System.out.println(new Q1().search(objects));
    }


    public List<String> search(List<Object> list) {
        return list.stream()
                .filter(obj -> obj instanceof String && ((String) obj).length() == 3 && ((String) obj).charAt(0) == 'a')
                .map(String.class::cast)
                .collect(Collectors.toList());
    }

}
