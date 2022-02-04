package lms.corejava.assignment4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Read implements Runnable{

    String filePath;
    LinkedList<String> buffer;

    @Override
    public void run() {
        // read file and do the calculation?
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() > 0)
                    buffer.add(calculate(line));
            }
            // mark the end of the operation
            buffer.add("eof");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Read(String filePath, LinkedList<String> buffer) {
        this.filePath = filePath;
        this.buffer = buffer;
    }

    private String calculate(String inputLine) {
        String[] tokens = inputLine.split(" ");
        int sum = 0;
        int sign = 1;
        for (String token : tokens) {
            if (token.equals("+")) {
                sign = 1;
            } else if (token.equals("-")) {
                sign = -1;
            } else {
                // assume the parsing will always work (input is always valid)
                sum += sign * Integer.parseInt(token);
            }
        }
        return inputLine + " = " + sum;
    }
}
