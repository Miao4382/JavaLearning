package com.example.app;

import java.util.HashMap;
import java.util.Map;

public class MainTest {

    public static void main(String[] args) {
        Map<int[], Integer> test = new HashMap<>();
        int[] key = new int[] {2, 1};
        int[] key2 = new int[] {key[0], key[1]};
        test.put(key, 5);

        System.out.println(test.get(key2));
    }
}
