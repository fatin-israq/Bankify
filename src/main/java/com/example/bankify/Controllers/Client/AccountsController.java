package com.example.bankify.Controllers.Client;

import com.example.bankify.Models.CheckingAccount;
import com.example.bankify.Models.Model;
import com.example.bankify.Models.SavingsAccount;
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
    public Button trans_to_sv_btn;
    public TextField amount_to_sv;
    public Button trans_to_cv_btn;
    public TextField amount_to_ch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindData();
        addListeners();
    }

    private void bindData() {
        Model model = Model.getInstance();

        CheckingAccount checkingAccount = (CheckingAccount) model.getClient().checkingAccountProperty().get();
        SavingsAccount savingsAccount = (SavingsAccount) model.getClient().savingsAccountProperty().get();

        ch_acc_num.textProperty().bind(model.getClient().checkingAccountProperty().get().accountNumberProperty());
        transaction_limit.textProperty().bind(checkingAccount.transactionLimitProp().asString());
        ch_acc_date.textProperty().bind(model.getClient().dateProperty().asString());
        ch_acc_bal.textProperty().bind(model.getClient().checkingAccountProperty().get().balanceProperty().asString());

        sv_acc_num.textProperty().bind(model.getClient().savingsAccountProperty().get().accountNumberProperty());
                withdrawal_limit.textProperty().bind(savingsAccount.withdrawalLimitProp().asString());
        sv_acc_date.textProperty().bind(model.getClient().dateProperty().asString());
        sv_acc_balance.textProperty().bind(model.getClient().savingsAccountProperty().get().balanceProperty().asString());
    }

    private void addListeners() {
        trans_to_sv_btn.setOnAction(event -> transferToSavings());
        trans_to_cv_btn.setOnAction(event -> transferToChecking());
    }

    private void transferToSavings() {
        try {
            double amount = Double.parseDouble(amount_to_sv.getText());
            Model model = Model.getInstance();
            CheckingAccount checkingAccount = (CheckingAccount) model.getClient().checkingAccountProperty().get();
            SavingsAccount savingsAccount = (SavingsAccount) model.getClient().savingsAccountProperty().get();

            if (checkingAccount.balanceProperty().get() >= amount) {
                checkingAccount.setBalance(checkingAccount.balanceProperty().get() - amount);
                savingsAccount.setBalance(savingsAccount.balanceProperty().get() + amount);
                model.getDatabaseDriver().updateBalance(checkingAccount.ownerProperty().get(), checkingAccount.balanceProperty().get(), "CHECKING");
                model.getDatabaseDriver().updateBalance(savingsAccount.ownerProperty().get(), savingsAccount.balanceProperty().get(), "SAVINGS");
                amount_to_sv.setText("");
            } else {
                System.out.println("Insufficient funds in Checking Account");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount entered");
        }
    }

    private void transferToChecking() {
        try {
            double amount = Double.parseDouble(amount_to_ch.getText());
            Model model = Model.getInstance();
            CheckingAccount checkingAccount = (CheckingAccount) model.getClient().checkingAccountProperty().get();
            SavingsAccount savingsAccount = (SavingsAccount) model.getClient().savingsAccountProperty().get();

            if (savingsAccount.balanceProperty().get() >= amount) {
                savingsAccount.setBalance(savingsAccount.balanceProperty().get() - amount);
                checkingAccount.setBalance(checkingAccount.balanceProperty().get() + amount);
                model.getDatabaseDriver().updateBalance(savingsAccount.ownerProperty().get(), savingsAccount.balanceProperty().get(), "SAVINGS");
                model.getDatabaseDriver().updateBalance(checkingAccount.ownerProperty().get(), checkingAccount.balanceProperty().get(), "CHECKING");
                amount_to_ch.setText("");
            } else {
                System.out.println("Insufficient funds in Savings Account");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount entered");
        }
    }
}
