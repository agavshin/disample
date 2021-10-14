package ru.vsu.db.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private final static String DB_URL = "jdbc:h2:../catalog";
    private final static String DB_USER = "sa";
    private final static String DB_PASS = "";

    private static ConnectionManager instance;

    private ConnectionManager() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to find db driver: " + e.getMessage());
        }
    }

    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException  {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
}
