package lms.corejava.assignment4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * A simple demo of try-resource block. We put the resource object instantiation in the try() parentheses.
 * The resource will be closed properly no matter what happens in the try block (exceptions).
 */
public class Q7 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("./test.txt"))) {
            System.out.println("First line: " + br.readLine());
        }
    }
}
