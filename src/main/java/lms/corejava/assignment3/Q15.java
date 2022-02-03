package lms.corejava.assignment3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Q15 {

    public static void main(String[] args) {

        List<Integer> nums = List.of(1, 2, 3, 4, 10);

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("a", "1");
        hashMap.put("b", "2");
        hashMap.put("c", "10");

        Set<Integer> mapElement = hashMap.values().stream().map(Integer::parseInt).collect(Collectors.toSet());
        List<Integer> res = nums.stream().filter(num -> !mapElement.contains(num)).collect(Collectors.toList());

        System.out.println("res = " + res);

    }
}
