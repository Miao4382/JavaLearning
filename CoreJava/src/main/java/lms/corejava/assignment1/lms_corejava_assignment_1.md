# Assignment 1

1. What is JDK? JRE? JVM?
   - JVM
     - JVM stands for Java Virtual Machine. It is a virtual machine that enables a computer to run Java programs that are compiled to Java bytecode.
   - JRE
     - JRE stands for Java Runtime Environment. It is a set of components to run a Java application. JRE is made up of a JVM, Java class libraries and the Java class loader.
   - JDK
     - JDK is abbreviation for Java Development Kit. It provides the environment to develop and execute the Java program. JDK includes:
       - JRE (to execute the Java program)
       - Development tools (to develop Java program)

1. What is java compiler?
   - Java compiler is a program that compiles the Java code into platform-independent bytecode.

2. Why is java platform independent?
   - Because JVM acts as the bridge between a Java progarm and the platform. A Java program is first compiled to bytecode, which is platform-independent and only executable by the JVM. Then, a platform-dependent JVM is used to execute the bytecode on different platforms. The JVM specification guarantees that the execution result of the bytecode is the same across different platforms. 

3. What is IDE? Why is it important for developers?
   - IDE stands for Integrated Development Environment. It is a collection of important development tools (e.g. compiler, debugger etc.). It is important for developers since it integrates essential components during software development, so programmer can focus on coding.

4. Is java case sensitive?
   - Yes.
6. What do the following key words do?
static, final, public, private, void, null, package, class, new
   - `static`
     - Static member: when we define a member as static, it means this member belongs to a type itself, not to an instance of that type. The static member is shared across all instances of the type.
       - Static variable: the variable belongs to a type. e.g. `Integer.MIN_VALUE`
       - Static method: the method is not related to an object. It can be invoked directly from the class. e.g. `Math.min()`.
       - Static class: a nested class can be declared as static. A static nested class can only use static member of the outter class. It can be instantiated without an object of the outter class.
     - Static block: we can label a block of code as `static`. This block of code is executed when the class is loaded (also called static initializer). We can initialize static variables in the static block.
   - `final`
     - final variable
       - Like a constant variable. Once it is assigned, it's value can not be changed (error checked at compile time)
       - The syntax is to put `final` before the data type: `final int NUM`
       - If an instance final variable has not been assigned, it can only be assigned by the constructor of the class
     - final method
       - A final method of a class can not be overriden by it's derived class
       - Constructor can not be marked as final, because the constructors are not inherited in class inheritance. It does not make sense to mark a constructor as final because no class can override what is not inherited
     - final class
       - No classes can be inherited from the class declared as final
   - `public`
     - It is an access modifier. It can be used on class, method, constructor, interface etc. An entity being declared as public means it can be accessed by any other class.
   - `private`
     - It is an access modifier. It can be used on methods, variables and constructors. An entity being declared as private can only be accessed within the declared class itself.
   - `void`
     - It is used on method, which indicates the method does not return a value.
   - `null`
     - It is a literal value that represents a null reference.
   - `package`
     - The `package` keyword can create a package. A Java package organizes Java classes into namespaces, providing a unique namespace for each type it contains. Classes in the same package can access each other's package-private and protected members. 
   - `class`
     - The `class` keyword is used to declare a new Java class.
   - `new`
     - The `new` keyword is used to create an instance of a class. It instantiates a class by allocating memory for a new object and returning a reference to that object in heap memory.
7. What is primitive type and reference type?
   - Primitive type: Primitive types are not considered as objects, they are raw values. Java has eight primitive types (byte, short, int, long, float, double, char, boolean). 
   - Reference type: In Java, all the non-primitive types are reference type. We need to use a reference to access reference type variables. The reference type variable is the instance of a class.

8. Is parameter passed by value or reference?
   - Passed by value. Java does not support passing a parameter by reference. The parameter we pass in to a method is actually a copied reference value (for reference types) or a copied raw value (for primitive types).
9. What is the output: `System.out.println(1 > 0 : "A":"B");`
    - This line of code does not compile. The result of `System.out.println(1 > 0 ? "A" : "B")` is `A`.
10. How to define constants in java?
    - We can use `final` keyword. For example, declaring a constant `int`: `final int num = 5;`
    - If the final variable is not initialized when defining, we need to initialize it in the constructor (or in static block if the constant is static)
11. What is String? Is it primitive type?
    - String is a sequence of characters. In Java, all strings are object of String class, so it is not a primitive type. All strings are immutable in Java.
12. How to check if a String is representing a number?
    - We could use `Integer.parseInt()` or `Double.parseDouble()` to parse the string. If no exception is thrown, it means the parsing is successful, which means the string is representing an integer or decimal number.
13. Write a program to implement the following activity diagram.
    - Please check `Q13.java` in my [repo](https://github.com/Miao4382/JavaLearning/tree/master/src/main/java/lms/corejava/assignment1).
14. Write a program to merge two array of int.
    - Please check `Q14.java` in my [repo](https://github.com/Miao4382/JavaLearning/tree/master/src/main/java/lms/corejava/assignment1).
15. Write a program to find the second largest number inside an array of int.
    - Please check `Q15.java` in my [repo](https://github.com/Miao4382/JavaLearning/tree/master/src/main/java/lms/corejava/assignment1).
