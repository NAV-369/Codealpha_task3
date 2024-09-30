package com.example.task3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TravelPlanner extends Application {

    // UI Components
    private TextField destinationField;
    private DatePicker startDatePicker;
    private DatePicker endDatePicker;
    private ChoiceBox<String> accommodationDropdown;
    private TextField accommodationDetailsField;
    private TextField activitiesField;
    private TextField accommodationBudgetField;
    private TextField foodBudgetField;
    private TextField travelBudgetField;
    private TextField miscBudgetField;

    @Override
    public void start(Stage stage) {
        // Create UI components
        Label titleLabel = new Label("Travel Planner");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        destinationField = new TextField();
        destinationField.setPromptText("Enter Destination");

        startDatePicker = new DatePicker();
        endDatePicker = new DatePicker();

        accommodationDropdown = new ChoiceBox<>();
        accommodationDropdown.getItems().addAll("Hotel", "Hostel", "Airbnb", "Guest House");
        accommodationDetailsField = new TextField();
        accommodationDetailsField.setPromptText("Enter Accommodation Details");

        activitiesField = new TextField();
        activitiesField.setPromptText("Enter Activities");

        accommodationBudgetField = new TextField();
        accommodationBudgetField.setPromptText("Enter Accommodation Budget");

        foodBudgetField = new TextField();
        foodBudgetField.setPromptText("Enter Food Budget");

        travelBudgetField = new TextField();
        travelBudgetField.setPromptText("Enter Travel Budget");

        miscBudgetField = new TextField();
        miscBudgetField.setPromptText("Enter Miscellaneous Budget");

        Button calculateButton = new Button("Calculate Total Budget");
        calculateButton.setOnAction(e -> showSummaryWindow());

        // Layout setup for the main UI
        VBox mainContent = new VBox(10, titleLabel,
                new Label("Destination:"), destinationField,
                new Label("Start Date:"), startDatePicker,
                new Label("End Date:"), endDatePicker,
                new Label("Accommodation:"), accommodationDropdown,
                new Label("Accommodation Details:"), accommodationDetailsField,
                new Label("Activities:"), activitiesField,
                new Label("Accommodation Budget:"), accommodationBudgetField,
                new Label("Food Budget:"), foodBudgetField,
                new Label("Travel Budget:"), travelBudgetField,
                new Label("Miscellaneous Budget:"), miscBudgetField,
                calculateButton);

        mainContent.setPadding(new Insets(20));
        mainContent.setSpacing(10);

        // Set up the scene and stage
        Scene scene = new Scene(mainContent, 600, 750);
        stage.setTitle("Travel Planner");
        stage.setScene(scene);
        stage.show();
    }

    private void showSummaryWindow() {
        try {
            // Get budget values
            double accommodation = Double.parseDouble(accommodationBudgetField.getText());
            double food = Double.parseDouble(foodBudgetField.getText());
            double travel = Double.parseDouble(travelBudgetField.getText());
            double misc = Double.parseDouble(miscBudgetField.getText());

            // Calculate total budget
            double total = accommodation + food + travel + misc;

            // Generate a summary of the travel plan
            String destination = destinationField.getText();
            String startDate = startDatePicker.getValue() != null ? startDatePicker.getValue().toString() : "N/A";
            String endDate = endDatePicker.getValue() != null ? endDatePicker.getValue().toString() : "N/A";
            String accommodationType = accommodationDropdown.getValue() != null ? accommodationDropdown.getValue() : "N/A";
            String accommodationDetails = accommodationDetailsField.getText().isEmpty() ? "N/A" : accommodationDetailsField.getText();
            String activities = activitiesField.getText().isEmpty() ? "N/A" : activitiesField.getText();

            // Create styled summary using Text and TextFlow
            Text summaryTitle = new Text("Travel Plan Summary:\n\n");
            summaryTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));
            summaryTitle.setFill(Color.DARKBLUE);

            Text destinationText = new Text("Destination: ");
            destinationText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            Text destinationValue = new Text(destination + "\n\n");

            Text travelDatesText = new Text("Travel Dates: ");
            travelDatesText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            Text travelDatesValue = new Text(startDate + " - " + endDate + "\n\n");

            Text accommodationText = new Text("Accommodation: ");
            accommodationText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            Text accommodationValue = new Text(accommodationType + " - " + accommodationDetails + "\n\n");

            Text activitiesText = new Text("Activities: ");
            activitiesText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            Text activitiesValue = new Text(activities + "\n\n");

            Text budgetTitle = new Text("Budget:\n");
            budgetTitle.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            Text accommodationBudgetText = new Text("  - Accommodation: ");
            accommodationBudgetText.setFont(Font.font("Arial", FontWeight.BOLD, 12));
            Text accommodationBudgetValue = new Text(String.format("$%.2f\n", accommodation));

            Text foodBudgetText = new Text("  - Food: ");
            foodBudgetText.setFont(Font.font("Arial", FontWeight.BOLD, 12));
            Text foodBudgetValue = new Text(String.format("$%.2f\n", food));

            Text travelBudgetText = new Text("  - Travel: ");
            travelBudgetText.setFont(Font.font("Arial", FontWeight.BOLD, 12));
            Text travelBudgetValue = new Text(String.format("$%.2f\n", travel));

            Text miscBudgetText = new Text("  - Miscellaneous: ");
            miscBudgetText.setFont(Font.font("Arial", FontWeight.BOLD, 12));
            Text miscBudgetValue = new Text(String.format("$%.2f\n\n", misc));

            Text totalBudgetText = new Text("Total Budget: ");
            totalBudgetText.setFont(Font.font("Arial", FontWeight.BOLD, 16));
            totalBudgetText.setFill(Color.DARKRED);
            Text totalBudgetValue = new Text(String.format("$%.2f", total));
            totalBudgetValue.setFont(Font.font("Arial", FontWeight.BOLD, 16));
            totalBudgetValue.setFill(Color.DARKRED);

            // Combine all Text elements in a TextFlow
            TextFlow summaryFlow = new TextFlow(
                    summaryTitle,
                    destinationText, destinationValue,
                    travelDatesText, travelDatesValue,
                    accommodationText, accommodationValue,
                    activitiesText, activitiesValue,
                    budgetTitle,
                    accommodationBudgetText, accommodationBudgetValue,
                    foodBudgetText, foodBudgetValue,
                    travelBudgetText, travelBudgetValue,
                    miscBudgetText, miscBudgetValue,
                    totalBudgetText, totalBudgetValue
            );

            summaryFlow.setPadding(new Insets(10));
            summaryFlow.setLineSpacing(5);

            // Create a new window (stage) to display the summary
            Stage summaryStage = new Stage();
            summaryStage.initModality(Modality.APPLICATION_MODAL);
            summaryStage.setTitle("Travel Summary");

            ScrollPane scrollPane = new ScrollPane(summaryFlow);
            scrollPane.setFitToWidth(true);

            VBox summaryLayout = new VBox(10, new Label("Travel Summary:"), scrollPane);
            summaryLayout.setPadding(new Insets(20));

            Scene summaryScene = new Scene(summaryLayout, 500, 400);
            summaryStage.setScene(summaryScene);
            summaryStage.show();

        } catch (NumberFormatException ex) {
            // Show an error alert if the user enters invalid budget values
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter valid numeric values for all budget fields.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}
