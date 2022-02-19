package jdbcdemo;

import jdbcdemo.dbconfig.JdbcConfig;

import java.sql.*;

public class CallableStatementDemo {

    public static void main(String[] args) {
        callableStatementDemo();
    }

    private static void callableStatementDemo() {
        try (
                Connection conn = DriverManager.getConnection(
                        JdbcConfig.getUrl(),
                        JdbcConfig.getUser(),
                        JdbcConfig.getPassword()
                );
                Statement statement = conn.createStatement();
                CallableStatement callableStatement = conn.prepareCall("call increase_stock(?, ?)");
                ) {
            // print the books before calling the procedure
            String selectQuery = "select title, qty from books order by qty";
            ResultSet resultSet = statement.executeQuery(selectQuery);
            System.out.println("Before calling procedure:");
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                int qty = resultSet.getInt("qty");
                System.out.printf("\tTitle: %-60s, Quantity: %d\n", title, qty);
            }

            // now set the parameters and execute the callable statement: add 100 to qty of books with less than 500 qty
            callableStatement.setInt(1, 500);
            callableStatement.setInt(2, 100);
            callableStatement.execute();

            // print the books after the execution, see if quantities changed
            selectQuery = "select title, qty from books order by qty";
            resultSet = statement.executeQuery(selectQuery);
            System.out.println("\n\nAfter calling procedure:");
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                int qty = resultSet.getInt("qty");
                System.out.printf("\tTitle: %-60s, Quantity: %d\n", title, qty);
            }

            // restore book quantity
            callableStatement.setInt(1, 600);
            callableStatement.setInt(2, -100);
            callableStatement.execute();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
