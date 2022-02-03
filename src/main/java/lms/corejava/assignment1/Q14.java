package lms.corejava.assignment1;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Q14: Write a program to merge two array of int.
 */
public class Q14 {

    public static void main(String[] args) {
        int[] arr1 = {4, 2, 1, 3, 5, 9, 8};
        int[] arr2 = {40, 20, 10, 30, 50, 90, 80};

        System.out.println("arr1 = " + Arrays.toString(arr1));
        System.out.println("arr2 = " + Arrays.toString(arr2));
        System.out.println("Merged array: " + Arrays.toString(mergeArray(arr1, arr2)));
    }

    private static int[] mergeArray(int[] arr1, int[] arr2) {
        return IntStream.concat(Arrays.stream(arr1), Arrays.stream(arr2)).toArray();
    }
}
