package zoomclass.day4assignment.functionalinterface;

import java.util.function.Consumer;

public class ConsumerDemo {
    public static void main(String[] args) {
        Employee employee = new Employee("Jack", 12345);
        printName.accept(employee);
    }

    static Consumer<Employee> printName = employee -> System.out.println(employee.name);

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
