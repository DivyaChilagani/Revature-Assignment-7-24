package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String db_url = "jdbc:mysql://localhost:3306/rev_practice";
    private static final String user = "root";
    private static final String password = "Divya#123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(db_url, user, password);
    }
}
