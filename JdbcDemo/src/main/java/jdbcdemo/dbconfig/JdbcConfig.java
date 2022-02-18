package jdbcdemo.dbconfig;

public class JdbcConfig {

    private static final String url = "jdbc:postgresql://localhost:5430/demo_db";
    private static final String user = "postgres";
    private static final String password = "123123";

    public JdbcConfig() {
    }

    public static String getUrl() {
        return url;
    }

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }
}
