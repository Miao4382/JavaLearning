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
Demo code: `CommitRollback`.

By default, after we execute a query, JDBC will automatically commit it. We can call `connection.setAutoCommit(false)` to disable the automatic commit. After we execute the query, we can call `connection.commit()` to commit the change. In case anything bad happens before commit, we can call `connection.rollback()`.

## Prepared statement
Demo code: `PreparedStatementDemo.java`.

Documentation page for [prepared statement](https://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html).

> The main feature of a `PreparedStatement` object is that, unlike a `Statement` object, it is given a SQL statement when it is created. The advantage to this is that in most cases, this SQL statement is sent to the DBMS right away, where it is compiled. As a result, the `PreparedStatement` object contains not just a SQL statement, but a SQL statement that has been precompiled. This means that when the `PreparedStatement` is executed, the DBMS can just run the `PreparedStatement` SQL statement without having to compile it first.

`PreparedStatement` can be used for SQL statements that take parameters. You can use the same statement and supply it with different values each time you execute it.

The most important advantage of prepared statements is that they help prevent SQL injection attacks.
