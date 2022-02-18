package jdbcdemo;

import jdbcdemo.dbconfig.JdbcConfig;

import java.sql.*;

public class InsertDeleteDemo {

    public static void main(String[] args) {
        try(
                Connection conn  = DriverManager.getConnection(
                        JdbcConfig.getUrl(),
                        JdbcConfig.getUser(),
                        JdbcConfig.getPassword()
                );
                Statement statement = conn.createStatement();
        ) {
            // clear the database
            String deleteQuery = "delete from books where title = 'C++ Programming Language'";
            int count = statement.executeUpdate(deleteQuery);
            System.out.printf("The delete query is \"%s\"\nThere are %d rows deleted\n", deleteQuery, count);

            // print the total number of books before inserting. We can get the result named "total_book" from resultSet
            String countQuery = "select count(*) as total_book from books";
            ResultSet resultSet = statement.executeQuery(countQuery);
            if (resultSet.next())
                System.out.println("Before inserting, total number of books: " + resultSet.getInt("total_book"));

            // create an insert query and insert a book
            String insertQuery = "insert into books (title, price, qty) values ('C++ Programming Language', 300, 200)";
            count = statement.executeUpdate(insertQuery);
            System.out.printf("%d records are inserted\n", count);

            // print the total number of books after inserting
            resultSet = statement.executeQuery(countQuery);
            if (resultSet.next())
                System.out.printf("After inserting, total number of books: %s\n", resultSet.getInt("total_book"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
