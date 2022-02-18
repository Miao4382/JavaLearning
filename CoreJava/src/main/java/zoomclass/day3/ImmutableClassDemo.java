package zoomclass.day3;

import java.util.HashMap;
import java.util.Map;

public class ImmutableClassDemo {

    public static void main(String[] args) {

    }
}


/**
 * This is an immutable class. We achieve immutability by:
 *      - defining the class as final
 *      - all the fields are private final
 *      - no setter method
 *      - return deep copy of referenced object in the fields
 */
final class Employee {
    private final String name;
    private final int id;
    private final Map<Integer, Integer> record;

    // use constructor to initialize the private final fields
    // deep copy any collections
    public Employee(String name, int id, Map<Integer, Integer> record) {
        this.name = name;
        this.id = id;
        this.record = new HashMap<>(record);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    /**
     * To make sure the final field record can't be modified, we need to return a deep copy of the record.
     * @return a deep copy of record
     */
    public Map<Integer, Integer> getRecord() {
        return new HashMap<>(record);
    }

}
