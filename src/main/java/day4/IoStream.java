package day4;

import java.io.*;
import java.util.Arrays;

public class IoStream {

    public static void main(String[] args) throws IOException {
        fileDemo();
    }


    public static void byteStreamDemo() throws IOException {
        InputStream in = null;
        OutputStream out = null;
        String outputFilePath = "test2.txt";

        // catch the
        try {
            in = new FileInputStream("test.txt");
            out = new FileOutputStream(outputFilePath);
            int c;

            while ((c = in.read()) != -1) {
                System.out.println("int read: " + (char) c);
                out.write(c);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            // we close the stream in finally block
            if (in != null) in.close();
            if (out != null) out.close();
        }
    }

    //TODO use reader/writer
    public static void characterStreamDemo() throws IOException {
        FileReader fileReader = null;
        FileWriter fileWriter = null;

        try {
            fileReader = new FileReader("test.txt");
            fileWriter = new FileWriter("test2.txt");
            int temp;
            while ((temp = fileReader.read()) != -1) {
                fileWriter.write(temp);
            }
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) fileReader.close();
        }
    }

    //TODO use buffer
    public static void readLine() {

    }

    // list the number of
    public static void fileDemo() {
        String dir = ".";
        File f = new File(dir);
        String[] paths = f.list();
        System.out.println(Arrays.toString(paths));
    }
}
