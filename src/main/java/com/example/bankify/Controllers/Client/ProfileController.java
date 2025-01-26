package com.example.bankify.Controllers.Client;

import com.example.bankify.Models.Client;
import com.example.bankify.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadClientData();
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
}