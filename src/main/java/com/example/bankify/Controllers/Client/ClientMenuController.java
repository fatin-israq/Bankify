package com.example.bankify.Controllers.Client;

import com.example.bankify.Models.Model;
import com.example.bankify.Views.ClientMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientMenuController implements Initializable {
    public Button dashboard_btn;
    public Button transaction_btn;
    public Button accounts_btn;
    public Button profile_btn;
    public Button logout_btn;
    public Button report_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners() {
        dashboard_btn.setOnAction(event -> onDashboard());
        transaction_btn.setOnAction(event -> onTransactions());
        accounts_btn.setOnAction(event -> onAccounts());
        logout_btn.setOnAction(event -> onLogout());
        profile_btn.setOnAction(event -> onProfile());
        report_btn.setOnAction(event -> showReportPopup());
    }

    private void onDashboard() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.DASHBOARD);
    }

    private void onTransactions() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.TRANSACTIONS);
    }

    private void onAccounts() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.ACCOUNTS);
    }

    private void onProfile() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.PROFILE);
    }

    public void showReportPopup() {
        // Create a new Stage (popup window)
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL); // Block interaction with other windows
        popupStage.setTitle("Report an Issue");

        // Create UI elements for the popup
        Label label = new Label("Describe the issue:");
        label.setStyle("-fx-font-size: 14px; -fx-text-fill: #050000;");

        TextArea textArea = new TextArea();
        textArea.setPromptText("Enter your issue here...");
        textArea.setStyle(
                "-fx-background-color: #ffffff; " +  // White background
                        "-fx-text-fill: #000201; " +        // Dark text
                        "-fx-font-size: 13px; " +
                        "-fx-border-color: #216647; " +     // Green border
                        "-fx-border-radius: 8px; " +
                        "-fx-background-radius: 8px;"      // Rounded corners
        );

        Button reportButton = new Button("Submit Report");
        reportButton.setStyle(
                "-fx-background-color: #253f25; " + // Green background
                        "-fx-text-fill: white; " +          // White text
                        "-fx-font-size: 14px; " +
                        "-fx-padding: 8px 16px; " +         // Padding inside button
                        "-fx-background-radius: 8px;"      // Rounded corners
        );
        reportButton.setOnMouseEntered(e -> reportButton.setStyle("-fx-background-color: #216647; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 8px;")); // Darker green on hover
        reportButton.setOnMouseExited(e -> reportButton.setStyle("-fx-background-color: #253f25; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 8px;"));

        // Close the popup when the Report button is clicked
        reportButton.setOnAction(e -> {
            String reportText = textArea.getText(); // Get the text entered
            System.out.println("Report submitted: " + reportText); // Handle the report (e.g., save to a file or send to a server)
            popupStage.close(); // Close the popup
        });

        // Layout for the popup
        VBox layout = new VBox(15); // Spacing between elements
        layout.getChildren().addAll(label, textArea, reportButton);
        layout.setStyle(
                "-fx-background-color: #f2fff8; " + // Light green background
                        "-fx-padding: 20px; " +             // Padding inside the layout
                        "-fx-border-color: #253f25; " +     // Green border
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 10px; " +       // Rounded border corners
                        "-fx-background-radius: 10px;"     // Rounded background corners
        );

        layout.setAlignment(Pos.CENTER);

        // Set the Scene and show the popup
        Scene scene = new Scene(layout, 350, 250); // Adjust size as needed
        popupStage.setScene(scene);
        popupStage.showAndWait(); // Wait for the popup to close before returning
    }

    private void onLogout() {
        //Set Stage
       Stage satge = (Stage) dashboard_btn.getScene().getWindow();
       //Close the client window
        Model.getInstance().getViewFactory().closeStage(satge);
        //Show Login Window
        Model.getInstance().getViewFactory().showLoginWindow();
        //Set Client Login Success Flag
        Model.getInstance().setClientLoginSuccessFlag(false);
    }
}
