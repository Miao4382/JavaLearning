package jdbcdemo;

import jdbcdemo.dbconfig.JdbcConfig;

import java.sql.*;
import java.util.Arrays;

public class BatchDemo {


    public static void main(String[] args) {
        batchPreparedStatementDemo();
    }

    public static void batchPreparedStatementDemo() {
        String[] titles = {"Batch_Java", "Batch_C++", "Batch_Python"};
        int[] prices = {200, 230, 100};
        int[] qtys = {100, 120, 300};

        try (
                Connection conn = DriverManager.getConnection(
                        JdbcConfig.getUrl(),
                        JdbcConfig.getUser(),
                        JdbcConfig.getPassword()
                );
                PreparedStatement insertBook = conn.prepareStatement(
                        "insert into books (title, price, qty) values (?, ?, ?)",
                        PreparedStatement.RETURN_GENERATED_KEYS
                );
                ) {
            // loop through arrays and set up the batch statement
            for (int i = 0; i < 3; i++) {
                insertBook.setString(1, titles[i]);
                insertBook.setInt(2, prices[i]);
                insertBook.setInt(3, qtys[i]);
                insertBook.addBatch();
            }
            insertBook.executeBatch();

            Statement statement = conn.createStatement();
            String selectQuery = "select title, qty from books where title like 'Batch%'";
            ResultSet resultSet = statement.executeQuery(selectQuery);
            System.out.println("Inserted books and quantity:");
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                int qty = resultSet.getInt("qty");
                System.out.printf("\tTitle: %s, Quantity: %d\n", title, qty);
            }

            // remove added books
            statement.executeUpdate("delete from books where title like 'Batch%'");

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Demos how the batch processing works using Statement
     */
    public static void batchStatementDemo() {
        try (
                Connection conn = DriverManager.getConnection(
                        JdbcConfig.getUrl(),
                        JdbcConfig.getUser(),
                        JdbcConfig.getPassword()
                );
                Statement statement = conn.createStatement();
                ) {

            // before batch inserting, check the total number of books
            String countQuery = "select count(*) as total_book from books";
            ResultSet resultSet = statement.executeQuery(countQuery);
            if (resultSet.next())
                System.out.println("Before batch inserting test, total number of books: " +
                        resultSet.getInt("total_book"));

            // add batch query and execute it
            statement.addBatch("insert into books (title, price, qty) values ('Batch_Java', 200, 100)");
            statement.addBatch("insert into books (title, price, qty) values ('Batch_C++', 230, 120)");
            statement.addBatch("insert into books (title, price, qty) values ('Batch_Python', 100, 300)");
            statement.addBatch("update books set qty = 2 * qty where title like 'Batch%'");
            // res[i] is the count of affected rows for i-th query in the batch
            int[] res = statement.executeBatch();

            // print out the res array
            System.out.printf("res = %s\n", Arrays.toString(res));

            // Print out the result of batch processing
            resultSet = statement.executeQuery(countQuery);
            if (resultSet.next())
                System.out.println("After batch inserting test, total number of books: " +
                        resultSet.getInt("total_book"));

            String selectQuery = "select title, qty from books where title like 'Batch%'";
            resultSet = statement.executeQuery(selectQuery);
            System.out.println("Inserted books and quantity:");
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                int qty = resultSet.getInt("qty");
                System.out.printf("\tTitle: %s, Quantity: %d\n", title, qty);
            }

            // remove added books
            statement.executeUpdate("delete from books where title like 'Batch%'");

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
