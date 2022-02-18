package jdbcdemo;

import jdbcdemo.dbconfig.JdbcConfig;

import java.sql.*;

public class PreparedStatementDemo {

    public static void main(String[] args) throws SQLException {
        try (
                Connection conn = DriverManager.getConnection(
                        JdbcConfig.getUrl(),
                        JdbcConfig.getUser(),
                        JdbcConfig.getPassword()
                );

                // create a prepared statement object. The question mark is used as placeholder, we need to provide
                // values for the placeholder (at least before the initial call).
                PreparedStatement insertBook = conn.prepareStatement(
                        "insert into books (title, price, qty) values (?, ?, ?)",
                        PreparedStatement.RETURN_GENERATED_KEYS
                );

                PreparedStatement pstmtSelect = conn.prepareStatement("select * from books");

                PreparedStatement deleteTempBook = conn.prepareStatement("delete from books where title like 'prepareStatement_%'");
        ) {
            // set all the parameters. We set up the PreparedStatement.RETURN_GENERATED_KEYS, as the id column is set as
            // auto increment.
            insertBook.setString(1, "prepareStatement_Python");
            insertBook.setInt(2, 100);
            insertBook.setInt(3, 200);
            int rowsInserted = insertBook.executeUpdate();
            System.out.println(rowsInserted + " records are inserted");

            // partial changes (unchanged placeholder value will stay the same)
            insertBook.setString(1, "prepareStatement_Partial book");
            rowsInserted = insertBook.executeUpdate();
            System.out.println(rowsInserted + " records are inserted");

            ResultSet rset = pstmtSelect.executeQuery();
            while (rset.next()) {
                String title = rset.getString("title");
                String price = rset.getString("price");
                int qty = rset.getInt("qty");
                System.out.println(title + ", " + price + ", " + qty);
            }

            // delete the inserted books
            deleteTempBook.executeUpdate();
        }
    }
}
