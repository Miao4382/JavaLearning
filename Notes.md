# 2022-01-25
## Maven
The Maven repository check order:
    - local 
    - central
    - remote (private remote repositories for companies)

## Integer constant pool in Java

The `Integer` class also uses constant pool, in the range of `[-128, 127]`, or $[-2^7, 2^7 - 1]$.


## Git Repo

https://github.com/Arthur-Shuahua-Zhang/JavaBatch23

## Google Doc

https://docs.google.com/document/d/1FFk9Uxcoc603C1zHSPdGM8M5uBepfD5VQyx7GmEsKNc/edit

# 2022-01-26
## HashMap

The bucket list becomes red-black tree when the size of the list in that bucket grows more than 8.

Internally, HashSet is implemented using HashMap. HashTable is also implemented using HashMap.

The index of the bucket of `null` key is 0.

- HashTable vs. ConcurrentHashMap
  - https://stackoverflow.com/questions/12646404/concurrenthashmap-and-hashtable-in-java

Interface oriented coding (type declared as interface).

## Stack
Stack is implemented using circular array internally?

ArrayDeque (implemented using circular array)

You can also use LinkedList to implement the Deque interface:  `Deque<Integer> stack = new LinkedList<>()`



## Coding Example

### Object in sorted collection

Your custom object should implement the Comparable interface to define the way to compare the custom object (override the Comparable interface).

Or you need to provide the Comparator when you define the sorted collection.

Comparator overflow issue (better not to use `o1.val - o2.val` directly)

You can try to define the comparator for a TreeMap using sorted Value.

## Homework

- What is JVM
- Play around the comparator, comparable, data structures, and upload to github account.

# 2022-01-27
## Class Loader
- Application class loader
- Extension class loader
- Bootstrap class loader
  - What classes does those loader load?

## Garbage Collector
- Serial GC
  - It has a single thread in the environment. When this thread runs, other threads will be paused and let the serial GC do the work.
  - -XX:UseSerialGC
- Parallel GC
  - It is the default GC
  - It works in parallel.
  - -XX:UseParallelGC
- G1 GC
  - Divide the whole heap into equal sized chunks, it will prioritize the different chunks by the number of garbages each chunk has.

### GC Process
The heap has three generations:
- Young generation
  - Eden
    - Newly allocated object will be put into Eden. When the Eden region is full, minor GC will be triggered, unreferenced object will be garbage collected, referenced object will be put into S0.
  - S0
    - If S0 is full, GC will be triggered, unreferenced object will be removed from S0 and Eden. Referenced object will be moved to S1.
  - S1
    - If S1 is full, referenced object will be moved to S0, while unreferenced object in Eden, S0, S1 will be removed.
- Old generation
  - If object in young generation ages to a certain threshold (aging), it will be moved to old generation.
  - Tenured
- Permanent generation
  - Permanent

## Keywords in Java
- Final
- Immutable class: a class that is not mutable
  - Make the class as final
  - All the fields are private final
  - No setter method
  - If collections present in private field, returns a deep copy of the field
- static
  - blocks
    - A static block is a set of instructions that is run only once when a class is loaded into memory. A static block is also called a static initialization block. This is because it is an option for initializing or setting up the class at run-time.
  - variable
    - The variable is shared with all instances of the class
  - methods
    - The method belongs to a type, instead of an instance
  - class
    - A static class is really a class within a class (static nested class), one example is `Map.Entry<>`, which is a static class. `static` modifier can only be used on a nested class (i.e. no top-level static class).
    - A static class is simialr to a class that is both abstract and sealed. A static class cannot be instantiated (no instance constructor) or inherited and all the members of the static class are static in nature.
    - 
- native
  -  It is applied to a method to indicate that the method is implemented in native code using JNI (Java Native Interface). native keyword is only applicable for methods. A native method indicates that it is implemented in platform-dependent code (e.g. C/C++)
  - Main purpose of using native keywo    
    - To improve the performance of the system
    - to achieve machine level/memory level communication
    - To use already existing legacy non-java code


**Assignment**: Do some research of Java's keyword.

## OOP Concepts
- Abstraction
  - Use abstract class or interface
- Encapsulation
- Inheritance
- Polymorphism

Access modifier
- public
- protected
- private
- default (leave the modifier empty)

## Exception
- Checked exception
- Unchecked exception
- Exception pipe (handle multiple exceptions)
- Try with resources (implement AutoCloseable interface (override the close9) method), so resources inside the try block will automatically close themselves if anything happens)

# 2022-01-28
## Generics
- Advantages
  - Code is more concise, easier and less error-prone
  - Enforce type correctness at compile time
  - Without causing any extra overhead to the application

## I/O Stream
- Byte stream
- Character stream

## Serialization/Deserialization
- Serialization ID is used to uniquely identify the class.
- `static` field of an object will not be serialized too

## Java 8 Features
- Lambda expression
- Functional interface
  - Four important functional interface
    - Predicate: `public Boolean test(T t)`
      - It represents a boolean-valued function of one argument
      - `test(T t)` is used to pass in the object `t` being tested
      - Often used in `filter()` to filter out certain objects.
      - Predicates can be combined using `and()`/`or()` to form a complex predicate.
      - The `BiPredicate<T, U>` is similar with `Predicate<T>`, except BiPredicate accepts two objects as argument.
    - Function: `public R apply(T t)`
      - It represents a function that accepts one input and produces a result. `T` is the data type of the input, and `R` is the data type of the output.
      - `apply(T t)` is used to "call" the instance of the function, and pass in the argument `t`.
      - You can create a `Function` instance by using lambda expression, here is an example:
      ```java
      Function<Integer, Integer> addOne = num -> num + 1;
      ```
      - `andThen(Function<T, R>)` can be used to chain two functions together. For example:
      ```java
      Function<Integer, Integer> addOneMultiplyTen = addOne.andThen(num -> num * 10)
      ```
      The above statement creates a function entity that add one to the argument, then the result is multiplied by 10.
      - The `BiFunction<T, U, R>` is similar with `Function<T, R>`, except it takes two argument instead of one.
    - Consumer: `public void accept(T t)`
      - It represents an operation that accepts a single input argument and returns no result. The type of the argument is `T`.
      - `accept(T t)` is used to pass the object `t` being consumed to consumer.
    - Supplier: `public R get()`
      - It represents a supplier of results `R`.
- Optional
- Stream API
  - Intermediate operation (returns a stream as result)
    - `filter(Predicate<T>)`: Returns a stream consisting of the elements of this stream that match the given predicate.
    - `map(Function<T, R>)`: Returns a stream consisting of the results of applying the given function to the elements of this stream.
    - `flatmap(Function<T, ? extends Stream<? extends R>>)`: Returns a stream consisting of the results of replacing each element of this stream with the contents of a mapped stream produced by applying the provided mapping function to each element (results in the mapped stream `Stream<R>` will be flattened to a single stream)
    - `distinct()`: Returns a stream consisting of the distinct elements (according to `Object.equals(Object)`) of this stream.
    - `sorted(Comparator<T>)`: Returns a stream consisting of the elements of this stream, sorted according to the provided Comparator. If no comparator is provided, will use the natural order of the object to sort (must override `compareTo()`)
    - `peek(Consumer<? super T> action)`: Returns a stream consisting of the elements of this stream, additionally performing the provided action on each element as elements are consumed from the resulting stream.
  - Terminal operation (returns non stream)
    - `boolean anyMatch(Predicate<? super T>) predicate`
    - `boolean allMatch(Predicate<? super T> predicate)`: Returns whether all elements of this stream match the provided predicate. May not evaluate the predicate on all elements if not necessary for determining the result. If the stream is empty then true is returned and the predicate is not evaluated.
    - `void forEach(Consumer<? super T> action)`
    - `collect(Collector<? super T, A, R>)`: collect elements of this stream using a `Collector`
    - `long count()`: Returns the count of elements in this stream
    - `Optional<T> findFirst()`: Returns an Optional describing the first element of this stream, or an empty Optional if the stream is empty. If the stream has no encounter order, then any element may be returned.
    - `Optional<T> min(Comparator<? super T>)`: Returns the minimum element of this stream according to the provided Comparator. This is a special case of a reduction.
    - `max()`
    - `sum()`
    - `average()`
## Assignment
- Generics
  - why `List<int> arr = new ArrayList<>();` caused error? (because type erasure)
  - Type erasure
  - `<T extends E>`
  - `<? extends E>`
  - `<? super T>`
- I/O Stream
  - Character stream demo
- Functional interface demo
  - Predicate
  - Function
  - Consumer
  - Supplier
- Java8
  - Stream API Demo (10)
    - `map`
    - `flatmap` difference between `map`
    - `filter`
    - `distinct`
    - `limit`
    - `forEach`
    - `collect` etc
  - Method reference demo (class::method)
  - Additional features

# 2022-01-31
## Multithread
**Process vs. Thread**
- Process
  - Independent memory space, stack, heap and OS resources
- Thread
  - Shared memory space
  - private stack, program counter, register

**Thread state**
- new
  - A thread is just created but now started yet
- runnable
- blocked
  - Blocked at a lock (trying to access a critical point)
- waiting
  - `Object.wait` with no timeout
  - `Thread.join` with no timeout
  - `park()`
- timed_waiting
  - `Thread.sleep()`
  - `Object.wait` with timeout
  - `Thread.join` with timeout
  - `park()` with timeout
- terminated

**Thread creation**
- Extends `Thread`
- Implements `runnable`
- Implements `callable`
- Thread pool

**`runnable` vs `callable`**
- no return / has return
- no exception / has exception
- override `run()` / override `call()`

**Thread pool**
- Customized thread pool
- `ThreadPoolExecutor`
  - `corePoolSize`
  - `maximumPoolSize`
  - `keepAliveTime`
  - `unit`
  - `workQueue`
  - `threadFactory`
  - `handler`: different rejection policies (invoked when there is not enough thread to execute new task (queue is full and max pool size is reached))
    - AbortPolicy (the default handler policy)
    - CallerRunPolicy
    - DiscardPolicy
    - DiscardOldestPolicy

- Predefined thread pool
  - Fixed thread pool
  - Single thread pool
  - Cached thread pool
  - Scheduled thread pool

**Lock**
- Two ways to implement a lock
  - Use `synchronized` keyword
    - It is used to modify
      - block of code (object level lock?)
      - method (object level lock)
      - static method (class level lock)
      - class 
    - Object level lock, class level lock
  - Implements the `Lock` interface. `ReentrantLock` is the only class that implements the `Lock` interface
    - `lock()`
    - `unlock()`
    - `newCondition()`
    - `tryLock()`
    - `lockInterruptibly()`
  - `ReadWriteLock` interface
    - `Lock readLock()`
    - `Lock writeLock()`
    - `ReentrantReadWriteLock` class implements this interface


## Assignment
- Write study notes for multithreading and lock, including high level explaination and 
- Lock
  - Understand the lock concept, what does each keyword mean
  - Future / completeable future
  - Write demo code

# 2022-02-01
## Assignment
- Finish all the assignments about Java on LMS (2022/02/03)

# 2022-02-02
## MongoDB

## Redis
Usage
- Cache
- Distributed lock
- Message queue (not recommended)
- Store configuration information

Why Redis is powerful? It supports two kinds of persistence mechanism:
- RDB (Redis Database): the RDB persistence performs point-in-time snapshots of database at specific intervals.
- AOF (append only file): the AOF persistence logs every write operation received by the server, what will be played again at server startup, reconstructing original dataset

## SQL vs. No-SQL

## Index

## SQL Tuning and Non-SQL Tuning


## Assignment
- memcache vs. redis (when should we use which)
- AWS: Elastic Cache (how to set up elastic cache?)
- SQL/Application Tuning
  - view vs. stored procedure
  - view vs. material view


# 2022-02-03
## Transaction
A transaction is an action, or a series of actions, carried out by a single user or an application. Transaction should follow the ACID principle:
- A: Atomicity
  - All transactions are atomic. 
  - The transaction can't be executed partially.
  - Only two states possible: Commit or Rollback
- C: Consistency
  - Transactions take the database from one consistent state to another state
- I: Isolation
  - A transaction is not visible to other transactions until it completes
- D: Durability
  - Once the transaction has completed, its changes are made permanent.

A transaction example. We transfer money from account A to account B. The steps are:
- Read A
- A = A - 100
- Write A
- Read B
- B = B + 100
- Write B

The illustration of ACID principle in this example is:
- Atomicity: all these steps must be atomic. You can't do A - 100 without doing B + 100
- Consistency: A + B should be the same
- Inconsistency: other queries shouldn't see A or B's change until the transaction is completed
- Durability: after finishing the commit, money doesn't go back to A

## Concurrency
Problems in concurrency:
- Dirty read: read uncommitted data from another transaction
- Non-repeatable read: two reads are not consistent. Read committed data from an update query from anther transaction (doing update operation, two reads are before and after the update, the data read is inconsistent)
- Phantom read: two reads returns different number of results. Read commited data from insert or delete query from another transaction (doing insert or delete operation. Two reads are before and after insert/delete operation)

We use the different isolation level to avoid those problems.

## Lock
Binary lock
- The lock has two values, 1 and 0, represents locked or not locked
- Use an additional column `locked` to store the lock the each row. If the value in this column is 1, it means that row is locked. Otherwise, it is not locked.

Shared and exclusive lock
- Share lock: also known as read lock (multiple transactions can have the read lock, because the record can be read concurrently)
- Exclusive lock: write lock (it is exclusive, only one transaction can obtain the lock)

Optimistic lock and Pessimistic lock

Deadlock
- A deadlock is a state in which each member of a group waits for another member, including itself, to take action
- Can be detected by checking if there is any cycle in the wait-for-graph 

## Distributed Transaction
How do we do distributed transaction? We can use 2PC (two phase commit).
- Phase 1: prepare phase
  - The coordinator will send prepare message to the servers involved in the distributed transaction. After each server is prepared, a prepared signal will be sent back to the coordinator. So coordinator will know all the server will be ready to do the transaction
- Phase 2: commit phase
  - The coordinator will send commit signal to all the servers to actually do the transaction. After the commit is done on one server, the signal will be sent back to coordinator. When the transaction is done (coordinator receives all the done message), coordinator will end the transaction.

One problem for 2PC is that the coordinator will wait for some servers to respond. It may wait indefinitely. We can use other design pattern to solve this problem (e.g. Saga design pattern).

## Assignment
- Transaction
  - What is transaction?
  - How does transaction works on different server (distributed transaction)
- Lock
  - What is optimistic lock
  - What is pessimistic lock
  - How to solve the deadlock 
  - (?) What is live lock
  - (?) 2PL (two phase locking).
- Distributed transaction
  - Saga design pattern 