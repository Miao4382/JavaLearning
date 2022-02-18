package jdbcdemo;

import jdbcdemo.dbconfig.JdbcConfig;

import java.sql.*;

public class CommitRollback {
    public static void main(String[] args) throws SQLException {
        try (
                Connection conn = DriverManager.getConnection(
                        JdbcConfig.getUrl(),
                        JdbcConfig.getUser(),
                        JdbcConfig.getPassword()
                );
                Statement statement = conn.createStatement();
        ) {
            // before inserting, check the total number of books
            String countQuery = "select count(*) as total_book from books";
            ResultSet resultSet = statement.executeQuery(countQuery);
            if (resultSet.next())
                System.out.println("Before inserting test, total number of books: " + resultSet.getInt("total_book"));

            // test rollback a commit
            try {
                conn.setAutoCommit(false);
                // insert a book
                statement.executeUpdate("insert into books (id, title, price, qty) values (20, 'Test book', 300, 200)");
                // insert another book with same primary key, which will trigger a SQLException
                statement.executeUpdate("insert into books (id, title, price, qty) values (20, 'Test book', 200, 100)");
                conn.commit();
            } catch (SQLException e) {
                System.out.println("rolling back changes");
                conn.rollback();
                e.printStackTrace();
            }

            // after the rollback test, check the total number of books
            System.out.println("Auto commit is " + conn.getAutoCommit());

            resultSet = statement.executeQuery(countQuery);
            if (resultSet.next())
                System.out.println("After inserting, total number of books: " + resultSet.getInt("total_book"));
        }
    }
}
