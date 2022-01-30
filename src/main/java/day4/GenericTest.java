package day4;

public class GenericTest {
    public static void main(String[] args) {
        Node<String, Integer> node1 = new Node<>("one", 1);
        System.out.println(getFirstElement(new String[] {"A", "B", "C"}));
    }

    /**
     * An example of generic method. The generic types are declared in the bracket (any letter is fine, you can also
     * declare multiple types if you need more than one). The 'E' is the return type of the method.
     * @param array a generic array
     * @param <E> The type of the array entry
     * @return the first object in the array if it is not empty. Otherwise it returns null
     */
    public static <E> E getFirstElement(E[] array) {
        if (array.length > 0)
            return array[0];
        else
            return null;
    }
}

/**
 * Generic class for (key, value) pair
 * @param <K> Type for key
 * @param <V> Type for value
 */
class Node<K, V> {
    K key;
    V value;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
