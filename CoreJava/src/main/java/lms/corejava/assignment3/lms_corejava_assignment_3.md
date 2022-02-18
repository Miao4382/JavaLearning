# Assignment 3
1. Explain polymorphism.
    - Polymorphism means an object can take many forms. It occurs when we have many classes that are related to each other by inheritance.
2. What is overloading?
    - Overloading a method means creating a method with same method name but different number or type of parameters (so their signatures are different)
3. What is overriding?
    - Two methods having the same method signature in two different classes in which an inheritance relationship is present.
4. What does the `final` mean in this method:  `public void doSomething(final Car aCar){}`
    - It means the reference passed in can not be changed, i.e. we can not assign another object to this reference.
5. Suppose in question 4, the Car class has a method `setColor(Color color){…}`, inside doSomething method, Can we call `aCar.setColor(red);`?
    - Yes, we can call it. Because the `final` only guarantees the reference variable be immutable. The object being referenced can change.
6. Can we declare a static variable inside a method?
    - No. `static` variable is related to a type. It can not be a local variable.
7. What is the difference between interface and abstract class?
    - Availability of methods 
      - Interface can only have abstract methods, abstract class can have both abstract and non-abstract methods.
    - Variable types
      - Interface can only have static and final variable, the abstract class can have non-static and non-final variables.
    - Inheritance
      - You can inherit from multiple interfaces (using `implements` keyword), but you can only extend from one abstract class (using `extend` keyword).
    - Data member accessbility
      - By default, the class data members of interfaces are public, while the abstract class can also be protected or private.
8. Can an abstract class be defined without any abstract methods?
    - Yes.
9.  Since there is no way to create an object of abstract class, what’s the point of constructors of abstract class?
    - Although the abstract class itself can not be instantiated, the derived class of the abstract class can implement the abstract method and then become a non-abstract class, which can be instantiated. Constructors of abstract class can then be used to initialize the data fields of the abstract class.
10. What is a native method?
    - A native method is a method marked by `native` keyword. It suggests that this method is implemented in native code using JNI (Java Native Interface).
11. What is marker interface?
    - A marker interface is an interface that has no methods or constants inside it. It provides run-time type information about objects, so the compiler and JVM have additional information about the object. For example, `Serializable` and `Clonable` are marker interface.
12. Why to override `equals` and `hashCode` methods?
    - Override `equals()` method can define the condition of two objects being equal in value.
    - Override `hashCode()` method can define the logic of computing the hash code of an object.
    - When we add an object to a hash table, we first call `hashCode()` to get the hash code of the object. This will determine where do we put the object in the hash table (the index). If there is a hash collision, `equals()` will be called to compare if two objects are the same. If they are not the same, both objects will be added to the hash table.
    - Two different objects can have the same hash code. Same object should have the same hash code (that's why sometimes we need to override both `equals()` and `hashCode()`).
13. What’s the difference beween int and Integer?
    - `int` is a primitive type.
    - `Integer` is the wrapper class for `int`. It allows we use an integer as an object.
14. What is serialization?
    - Converting an object into a byte stream is known as Serialization. The byte stream can be used to persist the object after the program exits (e.g. save it to a file or send it over a network).
15. Create List and Map. List A contains 1,2,3,4,10 (integer) . Map B contains ("a","1") ("b","2") ("c","10")   (key = string, value = string). Question: get a list which contains all the elements in list A, but not in map B.
    - Please check `Q15.java` in my [repo](https://github.com/Miao4382/JavaLearning/tree/master/src/main/java/lms/corejava/assignment3)
16. Implement a group of classes that have common behavior/state as Shape. Create Circle, Rectangle and Square for now as later on we may need more shapes. They should have the ability to calculate the area. They should be able to compare using area. Please write a program to demonstrate the classes and comparison.  You can use either abstract or interface. Comparator or Comparable interface.
    - Please check `Q16.java` in my [repo](https://github.com/Miao4382/JavaLearning/tree/master/src/main/java/lms/corejava/assignment3)
