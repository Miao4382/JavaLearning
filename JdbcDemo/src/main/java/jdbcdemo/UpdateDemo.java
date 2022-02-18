package jdbcdemo;

import jdbcdemo.dbconfig.JdbcConfig;

import java.sql.*;


public class UpdateDemo {
    public static void main(String[] args) {
        try(
                Connection conn  = DriverManager.getConnection(
                        JdbcConfig.getUrl(),
                        JdbcConfig.getUser(),
                        JdbcConfig.getPassword()
                );
                Statement stmt = conn.createStatement();
        ){
            String strUpdate = "update books set price = price * 2, qty = qty - 1 where title = 'Java'";
            String strSelect = "select * from books where title = 'Java'";

            System.out.println("Before update, query book with title Java:");
            ResultSet resultSet = stmt.executeQuery(strSelect);
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String price = resultSet.getString("price");
                int qty = resultSet.getInt("qty");
                System.out.println(title + ", " + price + ", " + qty);
            }


            System.out.println("\nThe update sql query is \"" + strUpdate + "\"");
            System.out.println();

            // call executeUpdate() method to execute the update query
            int countUpdate = stmt.executeUpdate(strUpdate);
            System.out.println(countUpdate + " records are updated");


            System.out.println("After the update, book Java becomes:");
            resultSet = stmt.executeQuery(strSelect);
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String price = resultSet.getString("price");
                int qty = resultSet.getInt("qty");
                System.out.println(title + ", " + price + ", " + qty);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
