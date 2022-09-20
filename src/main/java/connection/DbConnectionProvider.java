package connection;

import java.sql.*;

public class DbConnectionProvider {

    private static final String BM_URL = "jdbc:mysql://localhost:3306/book_managment?useUnicode = true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

    private static final DbConnectionProvider INSTANCE = new DbConnectionProvider();
    private Connection connection;
    private DbConnectionProvider() {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DbConnectionProvider getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        try {
            if(connection == null || connection.isClosed()){
                connection = DriverManager.getConnection(BM_URL,USERNAME,PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
