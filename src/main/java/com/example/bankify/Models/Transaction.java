package com.example.bankify.Models;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Transaction {
    private final StringProperty sender;
    private final StringProperty reciever;
    private final DoubleProperty amount;
    private final ObjectProperty<LocalDate> date;
    private final StringProperty message;

    public Transaction(String sender, String reciever, Double amount, LocalDate date, String message) {
        this.sender = new SimpleStringProperty(this, "sender", sender);
        this.reciever = new SimpleStringProperty(this, "Reciever", reciever);
        this.amount = new SimpleDoubleProperty(this, "Amount", amount);
        this.date = new SimpleObjectProperty(this, "Date", date);
        this.message = new SimpleStringProperty(this, "Message", message);
    }

    public StringProperty senderProperty() {return this.sender;}

    public StringProperty recieverProperty() {return this.reciever;}

    public DoubleProperty amountProperty() {return this.amount;}

    public ObjectProperty<LocalDate> dateProperty() {return this.date;}

    public StringProperty messageProperty() {return this.message;}
}
