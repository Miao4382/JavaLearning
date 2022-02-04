# Assignment 4

1. What’s the difference between `final`, `finally`? What is `finalize()`?
    - `final`: can be used on variables, methods or classes to impose restriction
      - final variables: constant value, can't be changed
      - final method: can't be overriden in derived class
      - final class: can't be inherited from by another class
    - `finally`: used on try/catch block, it defines a block that will always be executed no matter of the result of the try/catch block
    - `finalize()`: prior to the garbage collection of an object, the `finalize()` method will be called. Clean-up activity can be implemented in this method. `finalize()` is deprecated since Java 9.
2. What’s the difference between throw and throws?
    - `throw` is a keyword that is used to explicitly throw an exception from a method or a block of code.
    - `throws` is a keyword that is used to in the signature of the method to indicate that this method might throw one of the listed checked exceptions.
3. What are the two types of exceptions?
    - Checked exception
      - Exceptions that are checked at compile time. Methods that could throw a checked exception must handle the exception using try/catch block, or it must specify the exception in the method signature using `throws` keyword.
    - Unchecked exception
      - Exceptions that are not checked at compile time. Those exceptions may throw at runtime. In Java, errors and RuntimeException are unchecked.
4. What is error in java?
    - `Error` is subclass of `java.lang.Throwable` class. It indicates some serious problems happened during runtime. A reasonable application should not try to catch and resolve an error, since most such errors are abnormal conditions. `Error` and its subclasses are regarded as unchecked exceptions for the purpose of compile-time checking of exceptions.
5. Exception is object, true or false?
    - True. Exceptions thrown are instantiated exception objects.
6. Can a finally block exist with a try block but without a catch?
    - Yes. However, if an exception is thrown in the try block, program will terminate there since there is no catch block to catch the exception. The `finally` block will be executed and the program terminates.
7. From Java 1.7, give an example of the try-resource feature
    - Prior to Java 1.7, we can use `finally` block to close the resources in case there is any exception thrown in the try block.
    - In Java 1.7, we can use try-resource block. We declare the resources in the parenthese following the `try` keyword.
    - Please check `Q7.java` my [repo](https://github.com/Miao4382/JavaLearning/tree/master/src/main/java/lms/corejava/assignment4) for the code example.
8. What will happen to the Exception object after exception handling?
    - After the exception handling, exception object will be out of scope, there will be no reference to the exception object.
    - Unreachable objects (without references) will be recycled by garbage collector.
9.  Can we use String as a condition in `switch(str){}` clause?
    - Starting from Java 1.7, `String` can be used in the expression of a switch statement.
10. What’s the difference between ArrayList, LinkedList and vector?

    ||`ArrayList`|`LinkedList`|`Vector`|
    |---|:---:|:---:|:---:|
    |Internal data structure|Array|Doubly linked list|Array|
    |Random access|Yes|No|Yes|
    |Insert/Delete time complexity|$O(n)$|$O(1)$|$O(n)$|
    |Interface implemented|`List`|`List`, `Deque`|`List`|
    |Thread safe|No|Yes|No|
    |Is legacy class|No|No|Yes|
    
11. What’s the difference between `HashTable` and `HashMap`?
    - `HashTable` is synchronzied, thread safe. It does not allow `null` key or value. It is a legacy class.
    - `HashMap` is not synchronized. It allows one `null` key and multiple `null` value. It is not a legacy class.
12. What is static import?
    - Using static import, we can access the static members of a class directly without class name or any object. For example, we can first do `import static java.lang.Math.*;`, then we can use the static method in `Math` class directly in the code: `int maxNum = max(num1, nums2)`.
13. What is static block?
    - A static block is a set of instructions that is run only once when a class is loaded into memory. A static block is also called a static initialization block. This is because it is an option for initializing or setting up the class at run-time.
14. Explain the keywords: default(java 1.8), break, continue, synchronized, strictfp, transient, volatile, instanceOf
    - `default`: in Java 1.8, using `default` allows an interface to have method with default implementation.
    - `break`: often used inside loops and switch statements. When we reach `break` in a loop, the cloest loop is immediately terminated and the program control goes to the next statement following the loop.
    - `continue`: often used inside loop, it will terminate the current iteration of the loop and continue to the next iteration.
    - `synchronized`: this keyword is used on instance methods, static methods and code blocks. The marked method/block can only be accessed by one thread at the same time, which achieves synchronization.
    - `strictfp`: it represents strict floating point. This keyword can be used with class, interface and method. It is used to restrict the floating point calculations and ensuring the same result on every platform regarding operations involving floating point calculations.
    - `transient`: it can be used with variables. Variables marked as `transient` will not be serialized during serialization process.
    - `volatile`: it can be used with variables. Volatile variable values are always read from the main memory, instead of the thread's cache memory. This is used mainly during synchronization.
    - `instanceOf`: to check if an object is a target type.
15. Create a program including two threads – thread read and thread write.
    - Please check `Q15.java` in my [repo](https://github.com/Miao4382/JavaLearning/tree/master/src/main/java/lms/corejava/assignment4).