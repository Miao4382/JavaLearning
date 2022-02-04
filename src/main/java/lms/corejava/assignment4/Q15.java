package lms.corejava.assignment4;

import java.util.LinkedList;

/**
 * The input file path and output file path are passed using commandline arguments.
 */
public class Q15 {

    public static void main(String[] args) {

        // shared buffer
        LinkedList<String> buffer = new LinkedList<>();

        // create read and write thread. Assume the commandline arguments are passed in.
        // Input file: cal.txt; Output file: res.txt
        Read read = new Read(args[0], buffer);
        Write write = new Write(args[1], buffer);
        Thread readThread = new Thread(read);
        Thread writeThread = new Thread(write);

        // execute and produce the result
        writeThread.start();
        readThread.start();
    }
}
