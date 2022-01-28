package day3;

public class StaticDemo {

    public static void main(String[] args) {
        // we can invoke the static method directly without creating an instance
        printHi();

        // we need to create an object to use non-static method
        StaticDemo demo = new StaticDemo();
        demo.printGreeting();

        // access the static member of the Dog class
        System.out.println(Dog.bark);
        Dog dog = new Dog();

        // try to create an instance of static nested class in OuterClass
        OuterClass.StaticClass staticClass = new OuterClass.StaticClass();
        // try to call a static method in the static class without an instance of OuterClass
        OuterClass.StaticClass.printDetail();

        // try to call method in inner class does not work
//        OuterClass.InnerClass.printDetail();
        // we must create an instance of OuterClass to use OuterClass.InnerClass
        OuterClass outerClass = new OuterClass();
        outerClass.setId(100);
        OuterClass.InnerClass innerClass = outerClass.new InnerClass();
        innerClass.printDetail();
    }

    public void printGreeting() {
        System.out.println("Greetings!");
    }

    public static void printHi() {
        System.out.println("Hi!");
    }

}

class Dog {
    static String bark;

    // A static block is a set of instructions that is run only once when a class is loaded into memory. A static block
    // is also called a static initialization block. This is because it is an option for initializing or setting up the
    // class at run-time.
    static {
        System.out.println("Static block executing...");
        bark = "WongWong";
    }

    public void shout() {
        System.out.println(bark);
    }
}

/**
 * In this class, we demo a static class and a non-static inner class
 */
class OuterClass {
    private static String info;
    private int id;

    // use a static block to initialize the static member of ParentClass
    static {
        System.out.println("Initializing the static field of ParentClass");
        OuterClass.info = "Parent info";
    }

    // here we define a static inner class
    public static class StaticClass {
        public static void printDetail() {
            // in static nested class, we can only access info (the static member), we can't access id (non-static)
            // this is because the static nested class can be instanced without an instance of OuterClass
            System.out.println("In static class, info = " + info);
//            System.out.println("id = " + id);
        }
    }

    // here we define a non-static inner class
    public class InnerClass {
        public void printDetail() {
            // in non-static nested class, we can access info and id (static and non-static member of ParentClass)
            // this is because InnerClass can not be instantiated without an instance of OuterClass
            System.out.println("In inner class, info = " + info);
            System.out.println("id = " + id);
        }
    }

    public void setId(int id) {
        this.id = id;
    }
}
