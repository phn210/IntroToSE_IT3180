package services;

import java.sql.*;

public class DBConnection {
    private static Connection connection;
    private static String connectionURL = "jdbc:sqlserver://localhost;" +
            "databaseName=QuanLyCapPhanThuong;";
    private static String user = "sa";
    private static String password = "";

    public static Connection getConnection() throws SQLException{
        try {
            connection = DriverManager.getConnection(connectionURL, user, password);
            System.out.println("Database successfully connected!");
        }
        catch(SQLException e){
            System.out.println("Some errors occurred!");
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
