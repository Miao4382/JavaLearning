package jdbcdemo;

import jdbcdemo.dbconfig.JdbcConfig;

import java.sql.*;

public class SelectDemo {
    public static void main(String[] args) {
        // use try-resources block to handle resources auto-close
        try(
                // to connect to a database, we need to provide the url and database login credential
                Connection conn  = DriverManager.getConnection(
                        JdbcConfig.getUrl(),
                        JdbcConfig.getUser(),
                        JdbcConfig.getPassword()
                );
                Statement stmt = conn.createStatement();
        ) {
            // the query is written as a string, and it can be executed by executeQuery() method of the statement
            String strSelect = "select title, price, qty from books";

            System.out.println("The sql query is " + strSelect);
            System.out.println();

            // get the query result using the resultSet reference
            ResultSet resultSet = stmt.executeQuery(strSelect);
            System.out.println("The records selected are:");
            int rowCount = 0;
            double totalPrice = 0;

            // iterate over the result set and
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                totalPrice += resultSet.getDouble("price");
                String price = resultSet.getString("price");
                int qty = resultSet.getInt("qty");
                System.out.println(title + ", " + price + ", " + qty);
                rowCount ++;
            }
            System.out.println("Totel number of records = "+ rowCount);
            System.out.printf("Total price: %f", totalPrice);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
