package zoomclass.day3Keyword;

/**
 * The assert keyword allows programmers to quickly verify certain assumptions or state of a program.
 * By default, assertion is disabled (to make sure backward compatibility for codes before Java 1.4)
 * To enable it, you need to pass -enableassertions or -ea as command line argument when running Java program.
 * In intellij, you can add "-ea" or "-enableassersions" in the VM options (Run -> Edit Configurations -> add VM options)
 */
public class AssertDemo {
    public static void main(String[] args) {
        int a = 2, b = 3;

        // if the condition being asserted is false, an AssertionError will be thrown
        assert (a == b);
    }
}
