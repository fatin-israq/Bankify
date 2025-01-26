//For connecting to the database
//It will define the functions that we want to use in a model

package com.example.bankify.Models;

import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseDriver {
    private static final Logger LOGGER = Logger.getLogger(DatabaseDriver.class.getName());
    private Connection conn;
    private static final String DB_URL = "jdbc:sqlite:bankify.db";

    public DatabaseDriver() {
        try {
            this.conn = DriverManager.getConnection(DB_URL);
            conn.createStatement().execute("PRAGMA busy_timeout = 5000");
            createMessagesTable();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error connecting to SQLite database!", e);
        }
    }

    public void createMessagesTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Messages (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                     "sender TEXT NOT NULL, " +
                     "receiver TEXT NOT NULL, " +
                     "message TEXT NOT NULL, " +
                     "date TEXT NOT NULL)";
        try (Statement statement = this.conn.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

    public ResultSet getTransactions(String pAddress, int limit){
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Transactions WHERE Sender = '" + pAddress + "' OR Receiver='" + pAddress + "' LIMIT " + limit + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    //Method returns savings ammount balance
    public double getSavingsAccountBalance(String pAddress){
        Statement statement;
        ResultSet resultSet;
        double balance = 0;
        try{
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM SavingsAccounts WHERE Owner = '" + pAddress + "';");
            balance = resultSet.getDouble("Balance");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return balance;
    }

    //Method to either add or subtract from balance given operation
    public void updateBalance(String pAddress, double amount, String operation){
        Statement statement;
        ResultSet resultSet;
        try{
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM SavingsAccounts WHERE Owner='"+pAddress+"';");
            double newBalance;
            if(operation.equals("ADD")){
                newBalance = resultSet.getDouble("Balance") + amount;
                statement.executeUpdate("UPDATE SavingsAccounts SET Balance = "+newBalance+" WHERE Owner = '"+pAddress+"';");
            } else if (operation.equals("CHECKING")) {
                statement.executeUpdate("UPDATE CheckingAccounts SET Balance = " + amount + " WHERE Owner = '" + pAddress + "';");
            } else if (operation.equals("SAVINGS")) {
                statement.executeUpdate("UPDATE SavingsAccounts SET Balance = " + amount + " WHERE Owner = '" + pAddress + "';");
            } else{
                if(resultSet.getDouble("Balance") >= amount){
                    newBalance = resultSet.getDouble("Balance") - amount;
                    statement.executeUpdate("UPDATE SavingsAccounts SET Balance = "+newBalance+" WHERE Owner = '"+pAddress+"';");
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    //Create and records new transactions
    public void newTransaction(String sender, String receiver, double amount, String message){
        Statement statement;
        try{
            statement = this.conn.createStatement();
            LocalDate date = LocalDate.now();
            statement.executeUpdate("INSERT INTO Transactions(Sender, Receiver, Amount, Date, Message) VALUES ('"+sender+"', '"+receiver+"', "+amount+", '"+date+"', '"+message+"');");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    /*
    Admin Section
     */

    public ResultSet getAdminData(String username, String password){
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Admins WHERE Username = '" + username + "' AND Password = '" + password + "';");
        } catch (Exception e) {
           e.printStackTrace();
        }
        return resultSet;
    }

    public void createClient(String fName, String lName, String pAddress, String password, LocalDate date){
        Statement statement;
        try{
            statement = this.conn.createStatement();
            statement.executeUpdate("INSERT INTO " +
                    "Clients (FirstName, LastName, PayeeAddress, Password, Date)" +
                    "VALUES ('"+fName+"', '"+lName+"', '"+pAddress+"', '"+password+"', '"+date.toString()+"');");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void createCheckingAccount(String owner, String number, double tLimit, double balance ){
        Statement statement;
        try{
            statement = this.conn.createStatement();
            statement.executeUpdate("INSERT INTO " +
                    "CheckingAccounts(Owner, AccountNumber, TransactionLimit, Balance)" +
                    " VALUES ('"+owner+"', '"+number+"', "+tLimit+", "+balance+")");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void createSavingsAccount(String owner, String number, double wLimit, double balance ){
        Statement statement;
        try{
            statement = this.conn.createStatement();
            statement.executeUpdate("INSERT INTO " +
                    "SavingsAccounts(Owner, AccountNumber, WithdrawalLimit, Balance)" +
                    " VALUES ('"+owner+"', '"+number+"', "+wLimit+", "+balance+")");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public ResultSet getAllClientsData() {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Clients");
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void depositSavings(String pAddress, double amount) {
        Statement statement;
        try {
            statement = this.conn.createStatement();
            statement.executeUpdate("UPDATE SavingsAccounts SET Balance = " + amount + " WHERE Owner = '" + pAddress + "';");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /*
    Utility Methods
     */

    // For searching a client
    public ResultSet searchClient(String pAddress) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Clients WHERE PayeeAddress='" + pAddress + "';");
        } catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public int getLastClientsID(){
        Statement statement;
        ResultSet resultSet;
        int id = 0;
        try{
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT  * FROM sqlite_sequence WHERE name = 'Clients';");
            id = resultSet.getInt("seq");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public ResultSet getCheckingAccountData(String pAddress){
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM CheckingAccounts WHERE Owner = '"+pAddress+"';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getSavingsAccountData(String pAddress){
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM SavingsAccounts WHERE Owner = '"+pAddress+"';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    // Chat section

    public void insertMessage(String sender, String receiver, String message) {
        synchronized (conn) {
            String sql = "INSERT INTO Messages (sender, receiver, message, date) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, sender);
                pstmt.setString(2, receiver);
                pstmt.setString(3, message);
                pstmt.setString(4, LocalDate.now().toString());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ResultSet getMessages(String sender, String receiver) {
        ResultSet resultSet = null;
        synchronized (conn) {
            try (Statement statement = conn.createStatement()) {
                String sql = "SELECT * FROM Messages WHERE (sender = '" + sender + "' AND receiver = '" + receiver + "') OR (sender = '" + receiver + "' AND receiver = '" + sender + "') ORDER BY date";
                resultSet = statement.executeQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultSet;
    }


    public void deleteClient(String pAddress) {
        try (Statement statement = this.conn.createStatement()) {
            statement.executeUpdate("DELETE FROM Clients WHERE PayeeAddress = '" + pAddress + "';");
            statement.executeUpdate("DELETE FROM CheckingAccounts WHERE Owner = '" + pAddress + "';");
            statement.executeUpdate("DELETE FROM SavingsAccounts WHERE Owner = '" + pAddress + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
