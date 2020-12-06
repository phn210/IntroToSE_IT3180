package services;

import java.sql.*;

public class DBConnection {
    private static Connection connection;
    private static String connectionURL = "jdbc:sqlserver://localhost:1433;" +
            "databaseName=QuanLyCapPhanThuong;";
    private static String user = "tan";
    private static String password = "14235678";

    public static Connection getConnection() throws SQLException{
        try {
            connection = DriverManager.getConnection(connectionURL, user, password);
            System.out.println("Database successfully connected!");
        }
        catch(SQLException e){
            System.out.println("Connect to database failed, some errors occurred!");
            e.printStackTrace();
        }
        return connection;
    }

    public static Connection getConnection(String hostName, String userName, String password, String dbName) throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlserver://" + hostName + ";databaseName=" + dbName + ";");
        System.out.println("Database successfully connected");

        return connection;
    }
    public static ResultSet getData(String stringSQL, Connection conn) throws SQLException {
        ResultSet rs = null;
        Statement st = conn.createStatement();
        rs = st.executeQuery(stringSQL);
        return rs;
    }
}
