package com.example.bankify.Models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Client {
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty payeeAddress;
    private final ObjectProperty<Account> chekingAccount;
    private final ObjectProperty<Account> savingsAccount;
    private final ObjectProperty<LocalDate> dateCreated;

    public Client(String fName, String lName, String pAddress, Account cAccount, Account sAccount, LocalDate date) {
        this.firstName = new SimpleStringProperty(this, "FirstName", fName);
        this.lastName = new SimpleStringProperty(this, "LastName", lName);
        this.payeeAddress = new SimpleStringProperty(this, "Payee Address", pAddress);
        this.chekingAccount = new SimpleObjectProperty<>(this, "Checking Account", cAccount);
        this.savingsAccount = new SimpleObjectProperty<>(this, "Saving Account", sAccount);
        this.dateCreated = new SimpleObjectProperty<>(this, "Date Created", date);
    }

    public StringProperty firstNameProperty() {return firstName;}
    public StringProperty lastNameProperty() {return lastName;}
    public StringProperty pAddressProperty() {return payeeAddress;}
    public ObjectProperty<Account> chekingAccountProperty() {return chekingAccount;}
    public ObjectProperty<Account> savingsAccountProperty() {return savingsAccount;}
    public ObjectProperty<LocalDate> dateProperty() {return dateCreated;}

}