package day4assignment.functionalinterface;

import java.util.function.BiConsumer;

public class BiConsumerDemo {

    public static void main(String[] args) {
        String msg = "Welcome";
        greeting.accept(new Employee("Maria", 123), msg);
    }

    // BiConsumer is similar with BiFunction, it accepts two arguments
    static BiConsumer<Employee, String> greeting = ((employee, s) -> System.out.println(s + ", " + employee.getName()));

    static class Employee {
        private String name;
        private int id;

        public Employee(String name, int id) {
            this.name = name;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
