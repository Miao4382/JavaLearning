package zoomclass.day3;

import java.util.ArrayList;
import java.util.List;

public class KeywordDemo {

    public static void main(String[] args) {
        Student s1 = new Student("Jack", 5);
        s1.printDetail();
    }

    public static void finalVariableDemo() {
        final List<Integer> nums = new ArrayList<>();
        nums.add(5);
        // the final nums means the reference can't be changed, but the list object being referenced can be changed
        System.out.println(nums.get(0));
    }

}

class People {
    String name;
    int age;

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void printDetail() {
        System.out.println("Name: " + name + ", age: " + age);
    }

}

class Student extends People{

    public Student(String name, int age) {
        super(name, age);
    }

    @Override
    public void printDetail() {
        super.printDetail();
        System.out.println("This is a student");
    }
}