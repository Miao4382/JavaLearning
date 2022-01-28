package day3Keyword;

/**
 * Abstract is a non-access modifier used on class or method. It is used to achieve abstraction.
 *
 */
public class AbstractDemo {
    public static void main(String[] args) {
        Student s = new Student("Jack", 15);
        s.printDetail();
    }
}


/**
 * People class is defined as an abstract class. It can have non-abstract methods as well as abstract methods.
 * Abstract methods only specifies the method signature (method name and parameter type/number). Any class extends this
 * abstract class should provide implementation to the abstract methods.
 */
abstract class People {
    public String name;
    public int age;

    public People() {
        this.name = "N/A";
        this.age = 20;
    }

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // this is an abstract method
    public abstract void printDetail();
}

/**
 * Student class is sub-class of People. It should provide the implementation of the abstract class printDetail().
 */
class Student extends People {

    @Override
    public void printDetail() {
        System.out.println(super.name + " is a student.");
    }

    public Student(String name, int age) {
        super(name, age);
    }
}
