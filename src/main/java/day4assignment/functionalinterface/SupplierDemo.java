package day4assignment.functionalinterface;

import java.util.function.Supplier;

public class SupplierDemo {

    public static void main(String[] args) {
        System.out.println(getName.get());
    }

    // this supplier supplies a string
    static Supplier<String> getName = () -> "Name";

}
