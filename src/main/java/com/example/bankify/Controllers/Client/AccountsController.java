package com.example.bankify.Controllers.Client;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountsController implements Initializable {
    public Label ch_acc_num;
    public Label transaction_limit;
    public Label ch_acc_date;
    public Label ch_acc_bal;
    public Label sv_acc_num;
    public Label withdrawal_limit;
    public Label sv_acc_date;
    public Label sv_acc_balance;
    public FontAwesomeIconView trans_to_sv_btn;
    public TextField amount_to_sv;
    public Button trans_to_cv_btn;
    public TextField amount_to_ch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
