package org.company.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.company.App;
import org.company.model.Car;
import java.time.temporal.ChronoUnit;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class ReservationController {

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TextField totalCostTextField;

    @FXML
    private Button btnConfirmReservation;

    @FXML
    private Button btnCalculateCost;

    long daysOfLoan;

    @FXML
    void btnCalculateCostHandler(ActionEvent event) {
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        if (LocalDate.now().compareTo(startDate)>0 ){
            displayAllert("Incorrect dates!");
        }
        else if(startDate.compareTo(endDate) >= 0){
            displayAllert("Start date cannot be after end Date!");
        }
        else {
            daysOfLoan = ChronoUnit.DAYS.between(startDate, endDate);
            float totalCost = calculateReservationCost(ShoppingCartController.carsList);
            totalCostTextField.setText(Float.toString(totalCost));
        }
    }


    @FXML
    void btnConfirmReservationHandler(ActionEvent event) throws IOException {
        SceneController.switchScenes(event,"client_panel_shopping_cart");
    }
    private void  displayAllert(String allertText){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText(allertText);
        errorAlert.showAndWait();
    }

    private float calculateReservationCost(ArrayList<Car> carsList){
        float reservationCost = 0;
        for (Car car:carsList
             ) {
            reservationCost += car.getPrice() * daysOfLoan;
        }
        return reservationCost;
    }
}
