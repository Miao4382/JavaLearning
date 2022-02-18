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

## AWS ElastiCache
Amazon ElastiCache is a fully managed in-memory data store and cache service by Amazon Web Services. The service improves the performance of web applications by retrieving information from managed in-memory caches, instead of relying entirely on slower disk-based databases.

AWS ElastiCache supports the Redis and Memcached as the cache engines.

## View vs. Stored Procedure
**View**
- A view is a virtual table created based on `SELECT` query. A view references one or more existing database tables or other views. 
- If you always use query to generate some joins of tables repeatedly, you can create a view based on this repeated query.
- A view does not require any storage in a database because it does not exist physically
**Stored Procedure**
- A stored procedure is like a function, you can define the parameters and the function logic to perform a certain task in the stored procedure, whether it is updating and inserting data, or returning single values or data sets.

**Comparison of View and Stored Procedure**
||Stored Procedure|View|
|---|:---:|:---:|
|Accepts parameters|Yes|No|
|Can modify table|Yes|No|
|Can be used as building block in a larger query|No|Yes|

## View vs. Materialized View
|View|Materialized View|
|---|---|
|Virtual table, not stored on disk|Physically stored on disk|
|Data in view are always updated|Data is updated manually or by applying triggers|
|Responds slower that materialized view|Faster than view|
