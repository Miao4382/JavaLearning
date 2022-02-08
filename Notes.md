- [2022-01-25](#2022-01-25)
  - [Maven](#maven)
  - [Integer constant pool in Java](#integer-constant-pool-in-java)
  - [Git Repo](#git-repo)
  - [Google Doc](#google-doc)
- [2022-01-26](#2022-01-26)
  - [HashMap](#hashmap)
  - [Stack](#stack)
  - [Comparisons](#comparisons)
  - [Coding Example](#coding-example)
    - [Object in sorted collection](#object-in-sorted-collection)
  - [Homework](#homework)
- [2022-01-27](#2022-01-27)
  - [Class Loader](#class-loader)
  - [Garbage Collector](#garbage-collector)
    - [GC Process](#gc-process)
  - [Keywords in Java](#keywords-in-java)
  - [OOP Concepts](#oop-concepts)
  - [Exception](#exception)
- [2022-01-28](#2022-01-28)
  - [Generics](#generics)
  - [I/O Stream](#io-stream)
  - [Serialization/Deserialization](#serializationdeserialization)
  - [Java 8 Features](#java-8-features)
  - [Assignment](#assignment)
- [2022-01-31](#2022-01-31)
  - [Multithread](#multithread)
  - [Assignment](#assignment-1)
- [2022-02-01](#2022-02-01)
  - [What is database, database management system (DBMS) and SQL?](#what-is-database-database-management-system-dbms-and-sql)
  - [File system vs. DBMS](#file-system-vs-dbms)
  - [Normalization](#normalization)
  - [NoSQL](#nosql)
  - [CAP Theorem](#cap-theorem)
  - [Sharding and Replica](#sharding-and-replica)
  - [Assignment](#assignment-2)
- [2022-02-02](#2022-02-02)
  - [MongoDB](#mongodb)
  - [Redis](#redis)
  - [Memchached vs. Redis](#memchached-vs-redis)
  - [SQL vs. No-SQL](#sql-vs-no-sql)
  - [Index](#index)
  - [SQL tuning](#sql-tuning)
  - [Application tuning](#application-tuning)
  - [Assignment](#assignment-3)
- [2022-02-03](#2022-02-03)
  - [Transaction](#transaction)
  - [Concurrency](#concurrency)
  - [Isolation Level](#isolation-level)
  - [Lock](#lock)
  - [Distributed Transaction design pattern: 2PC](#distributed-transaction-design-pattern-2pc)
  - [Distributed transaction design pattern: Saga](#distributed-transaction-design-pattern-saga)
  - [Assignment](#assignment-4)
- [2022-02-04](#2022-02-04)
  - [SQL](#sql)
- [Summary](#summary)
  - [Maven](#maven-1)
  - [Git](#git)
  - [Java](#java)
  - [Database](#database)


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



## Comparisons
**List vs. Set**
|List|Set|
|---|---|
|Ordered sequence|Unordered sequence|
|Allows duplicate|Doesn't allow duplicate|
|Provides random access|No random access allowed|

**HashTable vs. ConcurrentHashMap**
- HashTable uses single lock for the whole map, while the latter uses multiple locks on segment level (16 by default) instead of object level.
- In ConcurrentHashMap, locking is applied only for updates. In case of retrievals, it allows full concurrency, retrievals reflect the results of the most recently completed update operations. So reads can happen very fast while writes are done with a lock.

## Coding Example

### Object in sorted collection

Your custom object should implement the Comparable interface to define the way to compare the custom object (implements the Comparable interface).

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
    - `T reduce(T identity, BinaryOperator<T> accumulator)`: the `reduce()` operation allow us to product one single result from a sequence of elements, by repeatedly applying a combining operation to the elements in the sequence.
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
- Implements `Runnable`
  - No return value
  - A `Runnable` task can be executed by Thread or ExecutorService
- Implements `Callable<T>`
  - Has one return value of type `T`.
  - A `Callable<T>` task can only be executed by ExecutorService, by using `executorService.submit(Callable<T> task)`, `invokeAll()`, `invokeAny()`
  - The `submit()` method will return a special result of type `Future`, which can be used to retrieve the return result of the callable task.
    - We can call `future.isDone()` to check if the method has finished the execution and returned the result 
    - We can call `future.get()` to get the result of the executed task.
      - Calling the method `get()` blocks the current thread and waits until the callable completes.
      - We can pass in a timeout amount, so the current thread will only be blocked for that amount of time. For example: `future.get(1, TimeUnit.SECONDS)`.
  - The `invokeAll()` method accepts a collection of `Callable` and returns a list of `Future`.
  - The `invokeAny()` method accepts a collection of `Callable`, it will block the current thread, until the first callable terminates and returns the result of that callable (notice it is not returning a `Future`, but the actual result of the `Callable` task).
- Thread pool

**`Runnable` vs `Callable`**
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

- Builtin thread pool
  - Fixed thread pool
  - Single thread pool
  - Cached thread pool
  - Scheduled thread pool

**Lock**
- Two ways to implement a lock
  - Use `synchronized` keyword
    - It is used with:
      - block of code (object level lock?)
      - method (object level lock)
      - static method (class level lock)
      - class
    - Object level lock, class level lock
  - Implements the `Lock` interface:
    - `ReentrantLock`

Three classes implements `Lock` interface:
- `ReentrantLock`
  - `lock()`: call this method to try to acquire the lock. If the lock is not available, thread will be dormant until the lock is available.
  - `isLocked()`: 
  - `unlock()`: call this method to unlock the obtained lock
  - `tryLock()`: try to acquire the lock without pausing the current thread. If the lock is held, then the method will return `false` immediately (no block).
  - `newCondition()`
  - `lockInterruptibly()`: acquire the lock unless the current thread is interrupted.
- `ReentrantReadWriteLock.ReadLock`
- `ReentrantReadWriteLock.WriteLock`


## Assignment
- Write study notes for multithreading and lock, including high level explaination
- Lock
  - Understand the lock concept, what does each keyword mean
  - Future / completeable future
  - Write demo code

- `Future` vs. `CompletableFuture`
  - `Future` is used as a reference to the result of an asynchronous computation. We can use `isDone()` to check if the computation is done or not. To get the result of the computation, we can use `get()`.
  - `CompletableFuture` is an extension to `Future`. It is a class that implements `Future` interface and `CompletionStage` interface, providing additional APIs for managing asynchronous computation, e.g. manually completion, creating, chaining and combining multiple `Futures`.

# 2022-02-01

## What is database, database management system (DBMS) and SQL?
- Database is an organized collection of structured information, or data, typically store electronically in a computer system.
- DBMS is a software designed to store, retrieve and manage data in the database
- SQL is short for Structured Query Language. It is a language for managing data held in a relational DBMS.

## File system vs. DBMS
||File System|DBMS|
|---|---|---|
|Usage|Manages files in a storage medium within a computer|Managing the database|
|Data redundancy|Has redundant data|No redundant data|
|Backup and recovery|No backup and recovery mechanism|Has backup and recovery mechanism|
|Query processing|Inefficient|Efficient|
|Consistency|Lower data consistency|Higher data consistency|
|Complexity|Less complex|More complex|
|Security|Less security|More security|
|Cost|Less expensive|More expensive|

## Normalization
**What is normalization?**
- Normalization is a technique of organizing the data in the database. Normalization is a systematic approach of decomposing tables to eliminate data redundancy and undesirable characteristics like insertion, update and deletion anomalies.

**The first three normalization form**
- 1st normal form
  - Each column should have atomic values
  - Each column should have unique name
  - Each column should have value of the same type
  - The order in which data is saved doesn't matter
- 2nd normal form
  - The table should be in 1st normal form
  - Table should not have partial dependency (each attribute describes primary key)
    - For example, if the table's primary key is `student_id` and `subject_id` (the table stores student's score), then other columns in this table should depend on both of the two primary keys, not one of them. So if a column is `teacher_name`, it is not in 2nd normal form, because it depends on `subject_id` only. This is called partial dependency.
- 3rd normal form
  - The table should be in 2nd normal form
  - Table should not have transitive dependency (each attribute should not describe non-primary key)
    - Transitive dependency means, a non-prim attribute depends on other non-prime attributes, rather than depending upon the primary attributes or primary key. In another word, a column can be uniquely ideitified using another non-primary key column, instead of using primary key alone.
    - For example, a table `student` uses `id` as the primary key. It has a column `age` and a column `birth date`. This table has transitive dependancy since the `age` column can be uniquely identified using a non-primary key `birth date`.

## NoSQL
**Advantages of NoSQL**
- Handles big data efficiently
- Data models: no predefined schema
- Data structure: NoSQL handles unstructured data (data stored can have different data structure)
- Cheaper to manage
- Scaling: scale out / horizontal scaling

**Advantages of RDBMS**
- Better for relational data
- Normalization: organizes the data in a way that limits the redundancy, results in better performance
- Well known language (SQL)
- Allows data integrity rules (using constraints)
- ACID compliance (atomicity, consistency, isolation, durability)

**Types of NoSQL**
- Document databases
  - Stores data in format like JSON objects
  - MongoDB, CouchDB
- Column databases
  - Optimized for reading data in columns, instead of rows
  - Apache Cassandra, Hbase
- Key-Value stores
  - Data maintained as key-value pair
  - Very fast, for huge dataset
  - Redis, Couchbase server, Riak
- Cache systems
  - Redis, Memcache
- Graph databases
  - Data in a graph database is seen as a node
  - A node can have relationship with other nodes (edge)
  - Huge data set with relations (social networks)
  - Neo4J, GraphDB

## CAP Theorem
It formalizes some useful limits on reliability. CAP stands for Consistency, Availability, Partition Tolerance. It formalizes the trade-off between consistency and availability in the presence of partitions.
- Consistency
  - All clients always have the same view of the data
- Availability
  - Each client can always read and write
- Partition Tolerance
  - The system can work well in the presence of physical network partitions
  - The system continues to operate despite an arbitrary number of messages being dropped (or delayed) by the network between nodes.
  - When a network partition failure happens, it must be decided whether to
    - Cancel the operation and thus decrease availability but ensure consistency
    - Proceed with the operation and thus provide availability but risk inconsistency.

We can only provide two of the three guarantees mentioned above.
- Consistency + Partition tolerance
  - Prioritizes partition and consistency of data in the sarcrifice of availability
  - MongoDB, Hbase, BigTable, Redis
- Availability + Partition tolerance
  - Prioritizes availability and partition in the sarcrifice of consistency
  - Dynamo, Cassandra, SimpleDB, CouchDB

## Sharding and Replica
**Sharding**
- Sharding is the practice that distributes a single logical database across a cluster of machine.
- Problems in Sharding
  - Joins across shardings
  - Fixed number of shards (can be solved by splitting shards into smaller shards)
- Master-Slave architecture

**Replica**
- Use redundancy to provide failover

## Assignment
- Finish all the assignments about Java on LMS (2022/02/03)

# 2022-02-02
## MongoDB
**Architecture**
- Mongod: database instance
- Mongos: sharding process
  - Analogous to a database router
  - Process all the request based on information in the config server
  - decide how many/which mongods should receive the query
- Mongo: interactive shell

**Functionality**
- Document based database
- Dynamic schema
- Secondary indexes
  - A secondary index, put simply, is a way to efficiently access records in a database (the primary) by means of some piece of information other than the usual (primary) key.
- Primary-secondary node with automated failover
- Built-in horizontal scaling via automated range based partitioning of data (sharding)
- Follows CP (Favor Consistency-Partition Tolerance over Availability)

**MongoDB Atlas**
- MongoDB Atlas is a multi-cloud database service.

## Redis

Redis stands for Remote Directory Server. It is Key-Value store NoSQL database

**Redis feature**
- In memory database
- Key-Value data store
- Supports storing value in different kinds of data structure
  - String
  - List
  - Sets
  - Sorted sets
  - Hashes

**Usage**
- Cache
- Distributed lock
- Message queue (not recommended)
- Store configuration information

Why Redis is powerful? It supports two kinds of persistence mechanism:
- RDB (Redis Database File): the RDB persistence performs point-in-time snapshots of database at specific intervals.
- Journaling via AOF (append only file): the AOF persistence logs every write operation received by the server, what will be played again at server startup, reconstructing original dataset

## Memchached vs. Redis
Memchached and Redis are both in memory Key-Value store NoSQL database. **Similarities**
- Sub-millisecond latency: they keep data in memory, so they are very fast
- Data partitioning: both allow distributing data across multiple nodes
- Support for a broad set of programming languages (Java, Python, JavaScript, C, Ruby)
- High scalability: both of them offer high scalability to handle large data

**Differences**
- Advanced data structures
  - Memcached stores key-value pairs as a string and has a 1MB size limit per value.
  - Redis supports other data structure such as list, set, sorted set, hash. It can store values of up to 512MB in size.
- Multithreaded architecture
  - Memcached has a multi-threaded architecture. It performs better than Redis when storing larger datasets.
  - Redis is single-threaded (except for asynchronous data persistence task)
- Disk I/O dumping
  - Memcached does not support disk dumping. Third-party tools are required to perfom such task.
  - Redis provides highly configurable mechanisms like RDB (snapshot and save Redis database file) or AOF (journaling via append-only files).
- Replication
  - Memcached does not support replication. Third-party forks (like repcached) have implemented this feature.
  - Redis supports replication out-of-the-box.
- Transaction
  - Memcached does not support transactions, although its operations are atomic.
  - Redis supports for transactions to execute commands.
- Publication/Subscription messaging
  - Memcached does not support pub/sub messaging.
  - Redis provides functionality to publish and subscribe to messages using pub/sub message queues.
- LUA scripting
  - Memcached does not support LUA scripting
  - Redis provides commands like `EVAL` and `SCRIPT LOAD` which are useful for the execution of the LUA scripts.
- Geospatial support
  - Memcached does not support geospatial feature.
  - Redis provides commands to manage real-time geospatial data.

Generally speaking, Redis outperforms memcached by offering richer functionality and various features that are promising for complex use-cases.

## SQL vs. No-SQL
|SQL|NoSQL|
|---|---|
|For relational database|For non-relational database|
|Pre-defined schema|Dynamic schema|
|Can scale vertically|Can scale horizontally|
|ACID principle|CAP theorem|
|Not suited for hierarchical data store|Suited for hierarchical store|


## Index
Indexing is a way to optimize the performace of database by minimizing the number of the disk access required when a query is processed.
- Clustered index (primary index)
  - It defines the order in which data is physically stored
  - Only one clustered index per table
  - When you apply primary key to a column, that column (primary key) will automatically become clustered index
- Non-clustered index (secondary index)
  - Can have multiple non-clustered index
  - Doesn't sort the physical data in table
  - Non-clustered index node stores the reference to the physical record

**Data structures**
- B+ tree
- Bitmap
- Hashtable
- R tree


## SQL tuning
SQL tuning: database tuning describes a group of activities used to optimize and homogenize the performance of a database. It usually overlaps with query tuning, but refers to design of the database files, selection of the database management system application, and configuration of the database's environment.

Some tips:
- Using execution plan to identify the cause of slowness
  - Execution plan is a set of instructions that describes which process steps are performed while a query is executed by the database engine.
  - You can use tools supported by the DBMS to generate the execution plans. For example, in PostgreSQL, you can use command `EXPLAIN` to generate the execution plan for specified statement.
- Try to reduce joins, remove unused join and join conditions
- Use the index to improve the performace
- Union all instead of union
- Use `LIMIT`
- Use View or stored procedure

## Application tuning
- Check the DB query
  - do the SQL tuning
- DB connection usage
  - check the connection pool
- Do JVM tuning
  - Jstack, JMap, JConsole
- Server side
  - CPU, memory usage by using commands like `top`, `ps`
- Code review
- Check networking, firewall, load balancer

## Assignment
- memcache vs. redis (when should we use which)
- AWS: Elastic Cache
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

## Isolation Level
Isolation level means the how two concurrent transactions are isolated with each other.

|Isolation Level|Dirty Reads|Unrepeatable Reads|Phamtom Reads|
|---|---|---|---|
|Read uncommited|Y|Y|Y|
|Read commited|N|Y|Y|
|Repeatable Read|N|N|Y|
|Serializable|N|N|N|

Different isolation levels can avoid the above mentioned problems at different level.

## Lock
**Binary lock**
- The lock has two values, 1 and 0, represents locked or not locked
- Use an additional column `locked` to store the lock the each row. If the value in this column is 1, it means that row is locked. Otherwise, it is not locked.

**Shared and exclusive lock**
- Share lock: also known as read lock (multiple transactions can have the read lock, because the record can be read concurrently)
- Exclusive lock: write lock (it is exclusive, only one transaction can obtain the lock)

**Optimistic lock**
- The optimistic lock strategy is when you read a record, take note of a stamp/version number. When you want to read/write to that record, check if the stamp has changed. If it has changed (it means the record is dirty), the transaction should be aborted
- This strategy is most applicable to high-volume systems and three-tier architectures where you do not necessarily maintain a connection to the database for your session. In this situation the client cannot actually maintain database locks as the connections are taken from a pool and you may not be using the same connection from one access to the next.
**Pessimistic lock**
- Pessimistic lock will lock the record for you exclusively until you have finished all the transaction operation. It has better integrity than optimistic lock at the expense of more overhead.

**Deadlock**
- A deadlock is a state in which each member of a group waits for another member, including itself, to take action
- Can be detected by checking if there is any cycle in the wait-for-graph 

## Distributed Transaction design pattern: 2PC
Two phase commit is a design pattern for distributed transaction.
- Phase 1: prepare phase
  - The coordinator will send prepare message to the servers involved in the distributed transaction. After each server is prepared, a prepared signal will be sent back to the coordinator. So coordinator will know all the server will be ready to do the transaction.
- Phase 2: commit phase
  - The coordinator will send commit signal to all the servers to actually do the transaction. After the commit is done on one server, the signal will be sent back to coordinator. When the transaction is done (coordinator receives all the done message), coordinator will end the transaction.

One problem for 2PC is that the coordinator will wait for some servers to respond. It may wait indefinitely. We can use other design pattern to solve this problem (e.g. Saga design pattern).

## Distributed transaction design pattern: Saga
The Saga design pattern is a way to manage data consistency across microservices in distributed transaction scenarios. A saga is a sequence of transactions that updates each service and publishes a message or event to trigger the next transaction step. If a step in the sequence fails, the saga executes compensating transactions that counteract the preceding transactions.

**Transaction types in Saga**
- Compensable transaction: transactions that can potentially be reversed
- Pivot transaction: if the pivot transaction commits, the saga runs until completion.
- Retryable transactions: they are transactions follow the pivot transaction and are guaranteed to succeed.

**Two common implementation approaches**
- Choreography
  - Event based
  - Saga participants exchange events without a centralized control.
  - Each local transaction publishes domain events that trigger local transactions in other services.
- Orchestration
  - Command based
  - Use a centralized controller (saga orchestrator) tells the saga participants what local transactions to execute.
  - The saga orchestrator handles all the transactions and tells the participants which operations to perform based on the events. It also handles failure recovery with compensating transactions.

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

# 2022-02-04
## SQL
- DDL (Data Definition Language)
  - Create, drop, alter, truncate
- DQL (Data Query Language)
  - select
- DML (Data Manipulation Language)
  - insert, update, delete
- DCL (Data Control Language)
  - grant, revoke (manage the priviledge of database users)
- DTL (Data Transaction Language)
  - commit, rollback

SQL
- Get familiar with the basic SQL clause
- Group by
  - Use `Having` to define the condition of grouping
- Aggregation function (`group by`)
  - max, count, min, avg, sum
- Subquery
  - Group the subquery into parenthese
- `rank()` vs. `dense_rank()`
  - `dense_rank()` only ranks non-equal entity
- union, union all, intersect, minus
- join, left join, right join, inner join, outter join
- Difference between union and join
  - Union: row-wise combination
  - Join: column-wise combination


# Summary
## Maven
- Local, central, remote repository
- The life cycle of Maven
- Command line for Maven

## Git
- How to use git in intellij
- How to use git in terminal
- Git workflow

## Java
- Primitive type
  - `byte`, `short`, `int`, `long`, `float`, `double`, `boolean`, `char`
- Wrapper class
- Autoboxing and unboxing
- String / StringBuilder / StringBuffer
- String, Integer constant pool
- `equals()` / `hashCode()`
  - We should always override both of them
- Collection
  - List
  - Set
  - Queue
  - Map
  - Comparing between collections
    - List vs. Set
    - ArrayList vs LinkedList
    - heap: PriorityQueue
    - deque: ArrayDeque
    - HashMap vs. HashTable vs. ConcurrentHashMap
    - HashSet, TreeSet, LinkedHashSet
    - TreeMap, LinkedHashMap
    - Stack, Queue
    - Binary tree, balanced binary tree
- Comparator vs. Comparable
  - Which method to override
  - In which case you need what?
    - `Comparable` interface enables the internal natural order of the class
- JVM
  - Classloader
    - How does class loader load classes
    - Bootstrap, Extension, Application
    - Three phases
      - Loading
      - Linking
      - Initialization
  - Runtime data area
    - Method areas
    - Heap
    - Stack
    - PC register
    - Native method stack
  - Execution engine
    - Interpreter
    - JIT compiler
    - Garbage Collector
  - Native
    - Native method interface
    - Native method library
- Garbage Collector
  - Types of GC
    - Serial GC
    - Parallel GC
    - G1 GC
    - CMS GC (deprecated since Java 9, removed in Java 14)
  - GC Process
    - What object stored in each of the following generations? What is the process goes like?
    - Young Generation
      - Eden
      - S0
      - S1
    - Old Generation
    - Permanent Generation
  - Minor GC, Major GC
- Keywords (know all the keywords)
  - 53 keywords
  - Reserved literals: `true`, `false`, `null`
  - Unused keywords: `goto`, `const`
  - 48 used keywords
    - Primitive type (8)
    - Control flow
  - final, finally, finalized
  - volatile
  - static
  - implements vs extends
  - immutable class
  - throw vs. throws
- OOP
  - Abstraction
    - Abstract class
    - Interface
  - Encapsulation
    - Private
    - Setter, getter
  - Inheritance
    - Extends
    - Implements
  - Polymorphism
    - Override
    - Overload
  - Access modifier
    - Public
    - Protected
    - Default
    - Private
- Exception
  - Checked exception vs. Unchecked exception
    - Examples of checked exception
      - IOException
      - ClassNotFoundException
    - Examples of unchecked exception
      - NullPointerException
  - Exception vs. Error
  - How to handle exception
  - How to customize exception
  - How to define the order of handling multiple exceptions
  - Try-with resources
    - All the resources in the try-with block should implement the autoclosable interfac and override the `close()` method
- Generics basics
  - What is generics
  - How do we use generics 
  - Advantages
  - Disadvantages
  - E vs. ?
    - Difference? When should we use what
    - `? extends E` meaning
  - Type erasure
- IO stream
  - Byte stream
  - Character stream
  - InputStream
  - OutputStream
  - Reader
  - Writer
  - File
- Serialization and Deserialization
  - To make a class serializable, we must implements the mark interface `Serializable`
  - Use `ObjectInputStream` to serialize 
  - Use `ObjectOutputStream` to deserialize
  - Define the serialization ID
  - Keyword `transient` meaning
- Java 8 features
  - Lambda (What? Why? How?)
    - Objects created by lambda is immutable, and it is thread safe
  - Functional interface
    - `@FunctionalInterface`
    - Predefined functional interface
      - Predicate -> `test`
      - Function -> `apply`
      - Consumer -> `accept`
      - Supplier -> `get`
  - Optional
    - What? Why? How?
    - `of`, `ofNullable`, `orElse`, `orElseThrow`
  - Stream
    - What? Why? How?
    - Intermediate operation for elements in the collection
    - Terminal operation
    - API
      - `map` vs. `flatmap`
      - Convert list to map
  - Method reference
- Multi thread
  - Process vs. Thread
  - Thread state
    - New
    - Runnable
    - Wait
    - Timed_wait
    - Block
    - Terminated
  - How to create thread
    - Extends `Thread`
    - Implement `Runnable`
    - Implement `Callable`
    - Thread pool
  - Thread pool
    - What is thread pool
    - Customized thread pool
      - ThreadPoolExecutor
        - `corePoolSize`
        - `maximumPoolSize`
        - `keepAliveTime`
        - `unit`
        - `workQueue`
        - `threadFactory`
        - 
        - Details for ea ch parameter
    - Built-in thread pool (what is the difference between them, use case for each one)
      - `newFixedSizedThreadPool`
      - `newSingleThreadPool`
      - `newCachedThreadPool`
      - `newScheduledThreadPool`
  - Lock
    - `synchronized` keyword
      - Difference of using `synchronized` on static method and non-static method
    - Lock interface
      - Reentrant lock
    - `ReadWriteLock` interface
      - ReentrantReadWriteLock
- Enum

## Database

- Database vs. DBMS vs. SQL
- File system vs. database
- Database normalization
- Major categories of No-SQL
  - Document data store
    - e.g. MongoDB
  - Key-value data store
    - e.g. Redis
  - Graph
  - Columnar
- CAP principle
  - C:
  - A:
  - P:
- Sharding vs. Replica
  - Sharding: improve performance
  - Replica: avoid failure
- MongoDB
  - Architect
  - Functionality
  - Usage
  - Sharding and replica
- Redis
  - How does it is used as a cache
  - Other functionalities Redis provide
  - Data structures Redis supports
  - Persistence mechanism
  - Usage
    - As message queue (not recommended)
    - Configuration server (?)
- SQL vs. No-SQL
- Index
  - Clustered index
  - Non-clustered index
- Data structures 
  - B tree
  - B+ tree
- SQL / Application tuning
  - view vs. stored procedure
  - view vs. materialized view
- Transaction
  - ACID principle
    - A:
    - C:
    - I:
    - D: 
- Concurrency
  - Problems
    - Dirty read
    - Non-repeatable read
    - Phantom read
  - Isolation level
    - Levels
    - Tradeoffs 
- Lock
  - Binary lock
  - Shared lock
  - Exclusive lock
  - Optimistic lock
  - Pessimistic lock
  - Dead lock
    - How to detect dead lock
    - How to prevent dead lock
- Distributed transaction
  - Design patterns, when should we choose which one
    - 2PC
    - Saga
- SQL
