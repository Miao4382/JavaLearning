package zoomclass.day3Keyword;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Showcase try, catch, throw, throws, finally keywords. They are used in exception handling.
 */
public class ExceptionDemo {

    // we can wrap statements that may cause checked exception into a try/catch block
    public static void main(String[] args) {
        String filename = "test.txt";
        List<String> lines = null;
        try {
            lines = readFileByLine(filename);
        } catch (FileNotFoundException exception) {
            System.out.println("FileNotFound exception caught: " + exception);
        } catch (Exception exception) {
            System.out.println("General exception caught: " + exception);
        } finally {
            System.out.println("This finally block will execute no matter what result is in the try block\n");
        }

        for (String line : lines) {
            System.out.println(line);
        }
    }


    // throws keyword is used to declare checked exceptions (telling compiler that programmer is aware of the possible
    // exceptions. Here, we are trying to read a file, and FileNoteFoundException is a checked exception
    public static List<String> readFileByLine(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        return lines;
    }

}
