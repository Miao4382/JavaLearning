JDBC Demo
=========

# Preparation
## Set up postgres database
To test JDBC functionality, we need to set up a database. This note uses postgresql docker container. The steps are as follows:
- Download and install docker
- Pull postgresql image by using  `docker pull postgres`
- Create the container using the image:
> `docker run --name test_db -e POSTGRES_PASSWORD=123123 -dp 5430:5432 postgres`
- Now the container is running, we can enter postgres database by using:
> `docker exec -it test_db psql -U postgres`

## Create tables used in the demo
Add following tables to the database.
```sql
create table books (
    id      BIGSERIAL NOT NULL PRIMARY KEY,
    title   TEXT,
    price   TEXT,
    qty     INTEGER
);
```
We can use [mockaroo](https://www.mockaroo.com/) to generate fake data and populate our table.

# JDBC Functionality Demos

## Select Demo
The code can be found in [`jdbcdemo.SelectDemo.java`]().

This demo shows the basic steps to use JDBC functionality to query table in the database.
- Establish a database connection
- Create a statement
- Write a query as a string and execute the query using the statement
- Get the query result

## Update Demo
The code can be found in [`jdbcdemo.UpdateDemo.java`]().

This demo shows the basic steps to run an update query to the database. We call `statement.executeUpdate(updateQueryStr)` to execute the query.

## Insert Delete Demo
The code can be found in [`jdbcdemo.InsertDeleteDemo.java`]().

To execute an insert query, we call `statement.executeUpdate(insertQueryStr)` to execute the query. 

## Commit and rollback
Demo code: `CommitRollbackDemo.java`.

By default, after we execute a query, JDBC will automatically commit it. We can call `connection.setAutoCommit(false)` to disable the automatic commit. After we execute the query, we can call `connection.commit()` to commit the change. In case anything bad happens before commit, we can call `connection.rollback()`.

## `PreparedStatement`
Demo code: `PreparedStatementDemo.java`.

Documentation page for [prepared statement](https://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html).

> The main feature of a `PreparedStatement` object is that, unlike a `Statement` object, it is given a SQL statement when it is created. The advantage to this is that in most cases, this SQL statement is sent to the DBMS right away, where it is compiled. As a result, the `PreparedStatement` object contains not just a SQL statement, but a SQL statement that has been precompiled. This means that when the `PreparedStatement` is executed, the DBMS can just run the `PreparedStatement` SQL statement without having to compile it first.

`PreparedStatement` can be used for SQL statements that take parameters. You can use the same statement and supply it with different values each time you execute it.

The most important advantage of prepared statements is that they help prevent SQL injection attacks.

## Batch processing
Demo code: `BatchDemo.java`.

Batch processing groups multiple queries into one unit and passes it in a single network trip to a database.

JDBC provides two classes, `Statement` and `PreparedStatement` to execute queries on the database. Both classes have their own implementation of the `addBatch()` and `executeBatch()` methods which provide us with the batch processing functionality.

### Using `Statement`
We can call `statement.addBatch()` and provide the query. Then call `statement.executeBatch()` to execute the batch statements. Example:
```java
statement.addBatch("insert into books (title, price, qty) values ('Batch_Java', 200, 100)");
statement.addBatch("insert into books (title, price, qty) values ('Batch_C++', 230, 120)");
statement.addBatch("insert into books (title, price, qty) values ('Batch_Python', 100, 300)");
statement.addBatch("update books set qty = 2 * qty where title like 'Batch%'");
// res[i] is the count of affected rows for i-th query in the batch
int[] res = statement.executeBatch();
```

### Using `PreparedStatement`
`PreparedStatement` also have `addBatch()` method to add multiple statements. We need to set the new parameters for each query.

## CallableStatement
Demo code: `CallableStatementDemo.java`.

The `CallableStatement` interface provides methods to execute the stored procedures. Since the JDBC API provides a stored procedure SQL escape syntax, you can call stored procedures of all RDBMS in single standard way.

- Creating a `CallableStatement` 
  - To create an object of `CallableStatement`, use `prepareCall()` method of `Connection` interface and provide a string representing a query to call the stored procedure. Similar to `PreparedStatement`, we use question mark as placeholder for parameters.
    ```java
    CallableStatement callableStatement = conn.prepareCall("call increase_stock(?, ?)");
    ```
- Setting values to the input parameters
  - Similar to how you set values in `PreparedStatement`, by calling `set()` method for each parameters at different position in the query. The position index starts at 1.
  ```java
  callableStatement.setInt(1, 500);
  callableStatement.setInt(2, 100);
  ```
- Executing the `CallableStatement`
  - Call `statement.execute()` to execute.
  ```java
  callableStatement.execute();
  ```

