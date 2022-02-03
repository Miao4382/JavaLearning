package lms.corejava.assignment1;

import java.util.Arrays;

/**
 * Q15: Write a program to find the second largest number inside an array of int
 */
public class Q15 {

    public static void main(String[] args) {
        int[] arr = {5, 4, 2, 1, 9, 7, 6, 8, 3};

        int largest = arr[0], secondLargest = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > largest) {
                secondLargest = largest;
                largest = arr[i];
            } else {
                secondLargest = Math.max(secondLargest, arr[i]);
            }
        }
        System.out.println("The second largest number in " + Arrays.toString(arr) + " is " + secondLargest);

    }
}
