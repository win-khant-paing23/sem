package com.napier.sem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {

    private Connection con = null;

    /* Connect to the MySQL database with retry mechanism.
            */
    public void connect() {
        try {
            // Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        // Get database location from environment variable or fallback to localhost
        String dbLocation = System.getenv("DB_LOCATION");
        if (dbLocation == null || dbLocation.isEmpty()) {
            dbLocation = "localhost:3306";
        }

        // Database connection URL parameters
        String url = "jdbc:mysql://" + dbLocation + "/world?useSSL=false&allowPublicKeyRetrieval=true";
        String user = "root";
        String password = "example_password"; // Replace with your MySQL root password

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait for DB to start up
                Thread.sleep(5000);

                // Connect to database
                con = DriverManager.getConnection(url, user, password);
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /* Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Disconnected from database");
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    public static void main(String[] args) {
        App app = new App();

        // Connect to database
        app.connect();

        // Disconnect from database
        app.disconnect();
    }
}