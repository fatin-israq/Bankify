package com.example.bankify.Controllers.Client;

import com.example.bankify.Models.Client;
import com.example.bankify.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    public Label profile_username;
    public Label profile_name;
    public TextField search_user;
    public TextField chat_message;
    public Label first_name;
    public Label last_name;
    public Label email;
    public Label phone;
    public Label nid_number;
    public Label address;
    public Button search_button;
    public Button send_button;
    public ListView<String> inbox_list;
    public Label search_result;

    private Client searchedClient;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadClientData();
        addListeners();
    }

    private void loadClientData() {
        Model model = Model.getInstance();
        Client client = model.getClient();

        profile_username.setText(client.pAddressProperty().get());
        profile_name.setText(client.firstNameProperty().get() + " " + client.lastNameProperty().get());
        first_name.setText("First Name: " + client.firstNameProperty().get());
        last_name.setText("Last Name: " + client.lastNameProperty().get());
        email.setText("Email: " + client.pAddressProperty().get() + "@gmail.com");
        phone.setText("Phone: +123456789"); // Placeholder, replace with actual phone number if available
        nid_number.setText("NID Number: 56745523457"); // Placeholder, replace with actual NID number if available
        address.setText("Address: Bangla Street, London, England"); // Placeholder, replace with actual address if available
    }

    private void addListeners() {
        search_button.setOnAction(event -> searchUser());
        send_button.setOnAction(event -> sendMessage());
    }

    private void searchUser() {
        String username = search_user.getText();
        ResultSet resultSet = Model.getInstance().getDatabaseDriver().searchClient(username);
        try {
            if (resultSet.next()) {
                String fName = resultSet.getString("FirstName");
                String lName = resultSet.getString("LastName");
                String pAddress = resultSet.getString("PayeeAddress");
                searchedClient = new Client(fName, lName, pAddress, null, null, null);
                search_result.setText("Found: " + fName + " " + lName + " (" + pAddress + ")");
            } else {
                System.out.println("User not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage() {
        if (searchedClient != null) {
            String message = chat_message.getText();
            String sender = Model.getInstance().getClient().pAddressProperty().get();
            String receiver = searchedClient.pAddressProperty().get();
            Model.getInstance().getDatabaseDriver().insertMessage(sender, receiver, message);
            chat_message.setText("");
            System.out.println("Message sent to " + receiver);
            // Optionally, update the inbox list with the new message
            inbox_list.getItems().add("To " + receiver + ": " + message);
        } else {
            System.out.println("No user selected to send message");
        }
    }
}