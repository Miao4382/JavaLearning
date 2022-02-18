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

