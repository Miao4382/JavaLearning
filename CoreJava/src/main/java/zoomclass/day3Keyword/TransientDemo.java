package zoomclass.day3Keyword;

import java.io.*;

/**
 * Keyword transient is used to mark variables that are not included in the serialization process
 */
public class TransientDemo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // first, try to serialize an Employee object and save to a file
        Employee employee = new Employee("Abby", "IT", 1000, 1);
        System.out.println("Before serialization, detail:");
        employee.printDetail();

        FileOutputStream file = new FileOutputStream("obj");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(file);
        objectOutputStream.writeObject(employee);
        objectOutputStream.close();
        file.close();

        // now read the obj file and deserialize
        FileInputStream fileIn = new FileInputStream("obj");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileIn);
        Employee employee1 = (Employee) objectInputStream.readObject();
        System.out.println("\n\nAfter deserialization, detail:");
        employee1.printDetail();
    }
}

/**
 * To make a class serializable, we must implement the Serializable interface
 */
class Employee implements Serializable {
    static final long serialVersionUID = 1L;
    private String name;
    private String department;
    // this field is marked as transient, so it will not be serialized
    private transient int salary;
    private int id;

    public Employee() {
    }

    public Employee(String name, String department, int salary, int id) {
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.id = id;
    }

    public void printDetail() {
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("Salary: " + salary);
        System.out.println("ID: " + id);
    }
}
