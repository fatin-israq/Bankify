package com.example.bankify.Controllers.Admin;

import com.example.bankify.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    public Button create_client_btn;
    public Button clients_btn;
    public Button deposit_btn;
    public Button logout_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        addListeners();
    }

    private void addListeners(){}

    private void onCreateClient(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set("CreateClient");
    }
}
