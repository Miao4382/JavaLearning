package day2;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class EqualsHashCode {

    public static void main(String[] args) {
        equalsHashCodeDemo();
    }

    /**
     * HashSet uses HashMap under the hood to implement the set. It first uses hashCode() to hash the object. If two
     * object has same hash code, it uses equals() to determine if two objects are the same.
     *
     * If equals() is not implemented, the default Object.equals() will be used to compare two objects
     * (which uses == operator)
     */
    public static void equalsHashCodeDemo() {

        // test on Node class
        Node n1 = new Node(1);
        Node n2 = new Node(1);

        Set<Node> set = new HashSet<>();
        set.add(n1);
        set.add(n2);
        System.out.println("Size of set is: " + set.size());

        // test on Node2 class (override equals())
        Node2 n3 = new Node2(1);
        Node2 n4 = new Node2(1);

        Set<Node2> set2 = new HashSet<>();
        set2.add(n3);
        set2.add(n4);

        System.out.println("Size of set2 is: " + set2.size());

        // test on Node3 class (override hashCode()), although n5 and n6 has same hash code, set3.size() is still 2
        Node3 n5 = new Node3(1);
        Node3 n6 = new Node3(1);

        Set<Node3> set3 = new HashSet<>();
        set3.add(n5);
        set3.add(n6);

        System.out.println("n5.hashCode() == " + n5.hashCode());
        System.out.println("n6.hashCode() == " + n6.hashCode());
        System.out.println("Size of set3 is: " + set3.size());

        // test on Node4 class (override hashCode() and equals())
        Node4 n7 = new Node4(1);
        Node4 n8 = new Node4(1);

        Set<Node4> set4 = new HashSet<>();
        set4.add(n7);
        set4.add(n8);
        System.out.println("Size of set4 is: " + set4.size());
    }
}

/**
 * Base class without overriding equals() and hashCode()
 */
class Node {
    public int val;

    public Node(int val) {
        this.val = val;
    }
}

/**
 * Only equals() override
 */
class Node2 {
    public int val;

    public Node2(int val) {
        this.val = val;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Node2)) return false;
        return this.val == ((Node2) o).val;
    }
}

/**
 * Only hashCode() override
 */
class Node3 {
    public int val;

    public Node3(int val) {
        this.val = val;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(val);
    }
}

/**
 * Both equals() and hashCode() was override
 */
class Node4 {
    public int val;

    public Node4(int val) {
        this.val = val;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Node4)) return false;
        return this.val == ((Node4) o).val;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(val);
    }
}
