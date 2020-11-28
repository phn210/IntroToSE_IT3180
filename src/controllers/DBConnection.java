package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;
    private static String connectionURL = "jdbc:sqlserver://localhost:59628;" +
            "databaseName=QLBongDa;";
    private static String user = "huy";
    private static String password = "123456";

    public static void main(String[] args){
        try {
            connection = DriverManager.getConnection(connectionURL, user, password);
            System.out.println("Database successfully connected!");
        }
        catch(SQLException e){
            System.out.println("Some errors occurred!");
            e.printStackTrace();
        }
    }
}
