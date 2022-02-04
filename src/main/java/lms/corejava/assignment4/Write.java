package lms.corejava.assignment4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Write implements Runnable{

    private LinkedList<String> buffer;
    private String outPath;

    @Override
    public void run() {
        String line = "";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outPath))) {
            while (!line.equals("eof")) {
                // write thread should wait for reader to add result to buffer
                while (buffer.isEmpty()) {
                    Thread.sleep(1);
                }
                line = buffer.pollFirst();
                if (!line.equals("eof")) {
                    writer.write(line + "\n");
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Write(String outPath, LinkedList<String> buffer) {
        this.buffer = buffer;
        this.outPath = outPath;
    }
}
