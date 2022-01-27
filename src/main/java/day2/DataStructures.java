package day2;

import java.util.*;

public class DataStructures {

    public static void main(String[] args) {
        Set<Double> set = new TreeSet<>((o1, o2) -> (int) (o2 - o1));
    }
}

class DSNode implements Comparable<DSNode> {
    int val;
    public DSNode(int val) {
        this.val = val;
    }

    @Override
    public int compareTo(DSNode node) {
        if (this.val == node.val) {
            return 0;
        }
        return this.val < node.val ? -1 : 1;
    }
}
