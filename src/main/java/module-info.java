module com.example.bankify {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.example.bankify to javafx.fxml;
    exports com.example.bankify;
    exports com.example.bankify.Controllers;
    exports com.example.bankify.Controllers.Admin;
    exports com.example.bankify.Controllers.Client;
    exports com.example.bankify.Models;
    exports com.example.bankify.Views;
}