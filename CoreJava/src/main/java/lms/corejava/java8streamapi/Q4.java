package lms.corejava.java8streamapi;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Write a method that converts all strings in a list to their upper case
 */
public class Q4 {

    public static void main(String[] args) {
        List<String> list = List.of("aVcde", "week", "abstRact", "mAsk", "cup");
        System.out.println(new Q4().upperCase(list));
    }

    public List<String> upperCase(List<String> list) {
        return list.stream().map(String::toUpperCase).collect(Collectors.toList());
    }
}
