//For connecting to the database
//It will define the functions that we want to use in a model

package com.example.bankify.Models;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseDriver {
    private static final Logger LOGGER = Logger.getLogger(DatabaseDriver.class.getName());
    private Connection conn;

    public DatabaseDriver() {
        try {
            this.conn = DriverManager.getConnection("jdbc:sqlite:bankify.db");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error connecting to SQLite database!", e);
        }
    }

    /*
    Client Section
     */
    public ResultSet getClientData(String pAddress, String password){
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Clients WHERE PayeeAddress = '" + pAddress + "' AND Password = '" + password + "';");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting client data!", e);
        }
        return resultSet;
    }


    /*
    Admin Section
     */


    /*
    Utility Methods
     */

}
