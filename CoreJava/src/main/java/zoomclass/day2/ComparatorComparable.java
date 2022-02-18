package zoomclass.day2;

import java.util.*;

public class ComparatorComparable {

    public static void main(String[] args) {
        Set<DSNode> set = new TreeSet<>();
        set.add(new DSNode(9));
        set.add(new DSNode(1));
        set.add(new DSNode(3));
        set.add(new DSNode(5));

        System.out.println("First node in set has value of " + set.iterator().next().val);

        // You can also provide a comparator when creating the collection, this comparator will be used when comparing
        // two objects, instead of using the natural order
        Queue<DSNode> heap = new PriorityQueue<>(new Comparator<DSNode>() {
            @Override
            public int compare(DSNode o1, DSNode o2) {
                if (o1.val == o2.val) return 0;
                return o1.val < o2.val ? 1 : -1;
            }
        });

        heap.add(new DSNode(5));
        heap.add(new DSNode(1));
        heap.add(new DSNode(6));
        heap.add(new DSNode(9));
        heap.add(new DSNode(4));

        System.out.println("Root node in heap has value of " + heap.peek().val);
    }
}

/**
 * In order to add the object of a class to collection that has order, you need to make sure the class has natural order
 * (i.e. it implements the Comparable interface), or you have to provide a comparator when creating the collection.
 *
 * This DSNode class implements Comparable so it has natural order (ascending).
 */
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
