# Assignment 2

1. Why we need packages in java?
    - A Java package is a group of similar types of classes, interfaces, enumerations, annotations and sub-packages. It organizes those types into namespaces, providing a unique namespace for each type it contains. Java package provides following benefits
      - Avoid naming conflicts
      - Better organized program structure (making searching certain classes easier)
      - Provide controlled access to class members using access modifier
      - Packages can be considered as data encapsulation
2. What is the default imported package?
    - `java.lang`
3. What is Class? What is Object?
    - Class: a class is like a blueprint to construct an object. It defines the member variable and the behavior of the object.
    - Object: an object is an instantiated entity of a class. Each object has an identity, a behavior and a state. Object is created based on the "blueprint" class at runtime.
4. Why we need constructor?
    - Constructor is used to initialize the data fields of object (initializing the state of the object).
5. What is the default value of local variable? What is the default value of instance variable?
    - There are no default value of local variable. If the code tries to access the value of uninitialized local variable, there will be a compile error.
    - The default value for instance variable is as follows
      - Reference type: `null`
      - Primitive types:
        - `byte, short, int`: 0
        - `long`: 0L
        - `float`: 0.0f
        - `double`: 0.0d
        - `char`: '\u0000'
        - `boolean`: false
6. What is garbage collection?
    - Garbage collection in Java is the process of recycling resources (memory) that are used by objects that are no longer referenced / being used. This automatic process has freed programmer to take special care to manage used memory.
7. The protected data can be accessed by subclasses or same package. True or false?
    - True. Below is a table about the effect of different access modifiers.

        ||`default`|`private`|`protected`|`public`|
        |---|:---:|:---:|:---:|:---:|
        |Same Class|Yes|Yes|Yes|Yes|
        |Same package subclass|Yes|No|Yes|Yes|
        |Same package non-subclass|Yes|No|Yes|Yes|
        |Different package subclass|No|No|Yes|Yes|
        |Different package non-subclass|No|No|No|Yes|

8. What is immutable class?
    - An immutable class is a class that can not be modified. It must have following feature
      - The class is marked as final (no inheritance is allowed)
      - All the data fields are private final
      - No setter method
      - Getter method that returns a reference to a collection data field should return a reference to the deep-copied collection (avoid modifications)
9.  What’s the difference between "==" and equals method?
    - `==` operator compares reference (reference type) or raw value (primitive type)
    - `equals()` method compares if two objects are the same based on some predefined logic. It is a method in `Object` class. `Object.equals()` uses `==` to compare two objects.
10. What is wrapper class?
    - Wrapper class in Java provides a way to use primitive types as objects.
11. What is autoboxing?
    - Autoboxing is the automatic conversion that the Java compiler makes between the primitive types and their corresponding object wrapper classes.
12. StringBuilder is threadsafe but slower than StringBuffer, true or false?
    - False. StringBuffer is threadsafe but slower than StringBuilder.
13. Constructor can be inherited, true or false?
    - False. 
14. How to call a super class’s constructor?
    - We can use `super()` to call a super class's constructor
15. Which class is the super class of all classes?
    - Object
16. Create a program to count how many files/folders are there inside one folder.
    - Please check `Q16.java` in my [repo](https://github.com/Miao4382/JavaLearning/tree/master/src/main/java/lms/corejava/assignment2).
